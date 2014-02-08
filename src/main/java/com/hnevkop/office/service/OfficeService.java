package com.hnevkop.office.service;

import java.util.List;
import java.util.Set;

import com.hnevkop.office.model.Group;
import com.hnevkop.office.model.Supplier;

public interface OfficeService {
	
	Supplier saveSupplier(Supplier supplier);
	void deleteSupplier(long supplierId);
	
	List<Supplier> getAllSuppliers();
	Supplier getSupplierById(long id);
	Group getGroupById(long id);
	Set<Group> getAllGroups();
	List<Supplier> getAllSuppliersForGroup(Group group);
}
