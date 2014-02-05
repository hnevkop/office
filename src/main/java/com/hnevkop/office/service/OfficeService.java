package com.hnevkop.office.service;

import java.util.List;
import java.util.Set;

import com.hnevkop.office.model.Group;
import com.hnevkop.office.model.Supplier;

public interface OfficeService {
	
	Supplier save(Supplier supplier);
	void delete(long supplierId);
	List<Supplier> findAllSuppliers();
	Supplier findById(long id);
	Set<Group> getAllGroups();
}
