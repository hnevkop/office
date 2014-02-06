package com.hnevkop.office.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hnevkop.office.model.Group;

@Repository("groupRepository")
public interface GroupRepository {

	List<Group> findAllGroups();

	Group findGroupById(long id);
	
	Group save(Group group);
	
	
}
