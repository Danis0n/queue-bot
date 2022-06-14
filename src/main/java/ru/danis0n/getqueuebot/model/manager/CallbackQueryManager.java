package ru.danis0n.getqueuebot.model.manager;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import ru.danis0n.getqueuebot.dao.LessonDAO;
import ru.danis0n.getqueuebot.dao.UserLLogDAO;
import ru.danis0n.getqueuebot.model.BotState;
import ru.danis0n.getqueuebot.model.entites.Lesson;
import ru.danis0n.getqueuebot.model.entites.UserLLog;
import ru.danis0n.getqueuebot.service.MessageConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

class CurrentLesson implements CallBackHandler{

    @Override
    public BotApiMethod<?> execute(Lesson lesson,MessageConstructor messageConstructor,CallbackQuery callbackQuery, long userId) {
        return messageConstructor.printCurrentLesson(lesson,userId);
    }
}

class Queue implements CallBackHandler{

    @Override
    public BotApiMethod<?> execute(Lesson lesson,MessageConstructor messageConstructor,CallbackQuery callbackQuery, long userId) {
        return messageConstructor.getActualQueue(lesson,userId);
    }
}

class ToBook implements CallBackHandler{

    @Override
    public BotApiMethod<?> execute(Lesson lesson,MessageConstructor messageConstructor,CallbackQuery callbackQuery, long userId) {
        return messageConstructor.bookToQueue(lesson,userId);
    }
}

class Back implements CallBackHandler{

    @Override
    public BotApiMethod<?> execute(Lesson lesson, MessageConstructor messageConstructor,CallbackQuery callbackQuery, long userId) {
        return messageConstructor.backToLessons(callbackQuery,userId);
    }
}

@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CallbackQueryManager {

    final LessonDAO lessonDAO;
    final UserLLogDAO userLLogDAO;
    final MessageConstructor messageConstructor;
    final Map<BotState,CallBackHandler> callbackStrategy = new HashMap<>();

    @Autowired
    public CallbackQueryManager(LessonDAO lessonDAO, UserLLogDAO userLLogDAO, MessageConstructor messageConstructor){
        this.lessonDAO = lessonDAO;
        this.userLLogDAO = userLLogDAO;
        this.messageConstructor = messageConstructor;
        callbackStrategy.put(BotState.LESSON,new CurrentLesson());
        callbackStrategy.put(BotState.QUEUE,new Queue());
        callbackStrategy.put(BotState.TOBOOK,new ToBook());
        callbackStrategy.put(BotState.BACK,new Back());
    }

    public Lesson getLessonFromCallBackData(CallbackQuery callbackQuery){
        Lesson lesson = null;

        List<Lesson> lessons = lessonDAO.findAll();

        for(Lesson l : lessons) {
            if (Objects.equals(l.getStdId(), callbackQuery.getData())) {
                try {
                    lesson = l;
                } catch (NullPointerException e) {
                    System.out.println("NullPointer error: getLessonFromCallBackData()");
                }
            }
        }
        return lesson;
    }

    public Lesson getUpdateLesson(BotState botState, CallbackQuery callbackQuery, long userId){

        Lesson lesson = null;

        UserLLog userLLog = new UserLLog();
        userLLog.setId(userId);
        userLLog.setData(callbackQuery.getData());

        if(botState.equals(BotState.LESSON)){
            lesson = getLessonFromCallBackData(callbackQuery);
        }
        else{
            lesson = userLLogDAO.findById(userId).getLesson();
        }
        userLLog.setLesson(lesson);
        userLLogDAO.save(userLLog);

        return lesson;
    }

    public BotApiMethod<?> executeContext(BotState botState, CallbackQuery callbackQuery, long userId) {
        Lesson lesson = getUpdateLesson(botState,callbackQuery,userId);
        return callbackStrategy.get(botState).execute(lesson,messageConstructor, callbackQuery,userId);
    }
}
