package com.hnevkop.office.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.hnevkop.office.model.Group;

@Repository("groupRepository")
public class GroupRepositoryImpl implements GroupRepository {

	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<Group> findAllGroups() {
		return entityManager.createNamedQuery(Group.FIND_ALL, Group.class)
				.getResultList();	

	}

}
