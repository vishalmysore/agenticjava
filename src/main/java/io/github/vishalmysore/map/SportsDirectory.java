package io.github.vishalmysore.map;

import com.t4a.annotations.Action;
import com.t4a.annotations.Agent;
import com.t4a.annotations.MapKeyType;
import com.t4a.annotations.MapValueType;
import lombok.extern.java.Log;

import java.util.Map;
@Log
@Agent(groupName = "CustomSportsAgent", groupDescription = "Agent to manage sports directory")
public class SportsDirectory {
    @Action(description = "add new Sports into the map")
    public Map<Integer,String> addSports(@MapKeyType(Integer.class)  @MapValueType(String.class) Map<Integer,String> mapOfSportsName) {
       log.info("Adding new sports to the directory: " + mapOfSportsName);
        return mapOfSportsName;
    }
}
