package io.github.vishalmysore.custom;

import com.t4a.annotations.Action;
import com.t4a.annotations.Agent;
import io.github.vishalmysore.domain.Organization;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.java.Log;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@Agent(groupName = "Organization", groupDescription = "Organization actions")
@Log
public class ListOfOrganization {
    List<Organization> organizations = new ArrayList<>();
    @Action(description = "add new orgranization")
    public Organization addOrganization(Organization org) {
        log.info(org.toString());
        organizations.add(org);
        return org;
    }
}
