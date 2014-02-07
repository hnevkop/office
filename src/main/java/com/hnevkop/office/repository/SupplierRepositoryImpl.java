package com.hnevkop.office.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.hnevkop.office.model.Supplier;


@Repository("supplierRepository")
public class SupplierRepositoryImpl implements SupplierRepository {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public Supplier save(Supplier supplier) {

// NOTE!: Implement bi-directional mapping in case you want to use this code		
//		if (supplier.getId() == 0 ) {
//			entityManager.persist(supplier);
//			entityManager.flush();
//			return entityManager.find(Supplier.class,
//					supplier.getId());
//			}

		return entityManager.merge(supplier);
	}
	
	@Override
	public Supplier updateSupplier(Supplier supplier) {
		Supplier supplierToUpdate = this.findSupplierById(supplier.getId());
		supplierToUpdate.setName(supplier.getName());
		supplierToUpdate.setAddress(supplier.getAddress());
		supplierToUpdate.setEmail(supplier.getEmail());
		supplierToUpdate.setPhone(supplier.getPhone());
		supplierToUpdate.setGroups(supplier.getGroups());
		return entityManager.merge(supplierToUpdate);
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

}
