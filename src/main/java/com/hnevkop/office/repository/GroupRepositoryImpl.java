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
	public Group save(Group group) {
		if (group.getId() == 0) {
			entityManager.persist(group);
			return entityManager.find(Group.class,
					group.getId());
		} else {
			return entityManager.merge(group);
		}
	}

	@Override
	public List<Group> findAllGroups() {
		return entityManager.createNamedQuery(Group.FIND_ALL, Group.class)
				.getResultList();	

	}

	@Override
	public Group findGroupById(long id) {
		return entityManager.find(Group.class, id);
	}

}
