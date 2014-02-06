package com.hnevkop.office.service;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hnevkop.office.model.Group;
import com.hnevkop.office.model.Supplier;
import com.hnevkop.office.repository.GroupRepository;
import com.hnevkop.office.repository.SupplierRepository;

@Service("officeService")
@Transactional
public class OfficeServiceImpl implements OfficeService {

	
	@Autowired
	private SupplierRepository supplierRepository;
	
	@Autowired
	private GroupRepository groupRepository;

		
	@Override
	public Supplier save(Supplier supplier) {
		// Save Group
		Set<Group> suppliers = supplier.getGroups();
		Iterator<Group> it = suppliers.iterator();
		while (it.hasNext()) {
			Group group = (Group) it.next();
			groupRepository.save(group);
		}
		return supplierRepository.save(supplier);
	}

	@Override
	public void delete(long supplierId) {
		Supplier supplier = supplierRepository.findSupplierById(supplierId);
		supplierRepository.delete(supplier);
	}

	@Override
	public List<Supplier> findAllSuppliers() {
		return supplierRepository.findAllAuppliers();
	}

	@Override
	public Supplier findSupplierById(long id) {
		return supplierRepository.findSupplierById(id);
	}
	
	@Override
	public Group findGroupById(long id) {
		return groupRepository.findGroupById(id);
	}


	@Override
	public Set<Group> getAllGroups() {
		List<Group> allGroups = groupRepository.findAllGroups();
		Set<Group> setList = new HashSet<>(allGroups);
		return setList;

	}
	
}
