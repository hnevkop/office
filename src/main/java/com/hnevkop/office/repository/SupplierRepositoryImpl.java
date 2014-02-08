package com.hnevkop.office.repository;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.hnevkop.office.model.Group;
import com.hnevkop.office.model.Supplier;


@Repository("supplierRepository")
public class SupplierRepositoryImpl implements SupplierRepository {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public Supplier save(Supplier supplier) {
	
		if (supplier.getId() == 0 ) {
			entityManager.persist(supplier);
			entityManager.flush();
			return entityManager.find(Supplier.class,
					supplier.getId());
			} else {
				return entityManager.merge(supplier);
			}
	}
	
	@Override
	public List<Supplier> findAllAuppliers() {
		return entityManager.createNamedQuery(Supplier.FIND_ALL, Supplier.class)
				.getResultList();	
		}
	
	
	@Override
	public List<Supplier> findAllAuppliersForGroup(long groupId) {
		
		Query query= entityManager.
					createNamedQuery(Supplier.FIND_BY_GROUP, Supplier.class);
		query.setParameter("groupId", groupId);
		return query.getResultList();	
		}

	@Override
	public Supplier findSupplierById(long id) {
		return entityManager.find(Supplier.class, id);
	}

	@Override
	public void delete(Supplier supplier) {
		entityManager.remove(supplier);		
	}

	/**
	 * Delete all records from supplier and supplier_groups. 
	 */
	@Override
	public void deleteAll() {
		entityManager.createNativeQuery("delete from supplier_groups").executeUpdate();
		entityManager.createNativeQuery("delete from supplier").executeUpdate();		
	}	
	

}
