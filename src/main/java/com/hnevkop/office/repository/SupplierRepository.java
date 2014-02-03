package com.hnevkop.office.repository;

import java.util.List;

import com.hnevkop.office.model.Supplier;

public interface SupplierRepository {
	

	Supplier save(Supplier supplier);
	
	List<Supplier> findAllAuppliers();
	
	Supplier findSupplierById(long id);
	
	/**
	 * Deletes all records from tables related to a supplier
	 */
	void deleteAll();

}
