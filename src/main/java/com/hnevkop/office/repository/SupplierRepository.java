package com.hnevkop.office.repository;

import java.util.List;

import com.hnevkop.office.model.Supplier;

public interface SupplierRepository {
	
	Supplier save(Supplier supplier);
	
	Supplier updateSupplier(Supplier supplier);
	
	List<Supplier> findAllAuppliers();
	
	Supplier findSupplierById(long id);
	
	void delete(Supplier supplier);

	List<Supplier> findAllAuppliersForGroup(long groupId);
	
}
