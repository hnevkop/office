package com.hnevkop.office.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.hnevkop.office.model.Supplier;


@Repository("supplierRepository")
public class SupplierRepositoryImpl implements SupplierRepository {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public Supplier save(Supplier supplier) {
		entityManager.persist(supplier);
		entityManager.flush();
		return supplier;
	}

	@Override
	public List<Supplier> findAllAuppliers() {
		return entityManager.createNamedQuery(Supplier.FIND_ALL, Supplier.class)
				.getResultList();	
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
