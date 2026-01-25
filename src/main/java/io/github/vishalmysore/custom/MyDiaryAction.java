package io.github.vishalmysore.custom;

import com.t4a.annotations.Action;
import com.t4a.annotations.Agent;
import io.github.vishalmysore.domain.MyDiary;
import lombok.extern.java.Log;

@Log
@Agent(groupName = "DiaryRelatedActions", groupDescription  = "Actions related to my diary")
public class MyDiaryAction {
    @Action(description = "This is my diary details", prompt = "Do not include of the diary has watching movie related activities")
    public MyDiary buildMyDiary(MyDiary diary) {
        //take whatever action you want to take
        log.info(diary.toString());
        return diary;
    }
}