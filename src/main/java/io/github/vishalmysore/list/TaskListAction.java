package io.github.vishalmysore.list;

import com.t4a.annotations.Action;
import com.t4a.annotations.Agent;
import com.t4a.annotations.ListType;
import lombok.extern.java.Log;

import java.util.List;

@Agent
@Log
public class TaskListAction {
    @Action
    public String[] myTaskList(String[] myTaskList) {
        log.info(" my task List"+myTaskList);
        return myTaskList;
    }
}
