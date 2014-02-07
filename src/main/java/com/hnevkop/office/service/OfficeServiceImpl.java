package com.hnevkop.office.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	
	static final Logger LOG = LoggerFactory.getLogger(OfficeServiceImpl.class);

	@Autowired
	private SupplierRepository supplierRepository;
	
	@Autowired
	private GroupRepository groupRepository;

		
	@Override
	public Supplier save(Supplier supplier) {
		
		LOG.debug("Saving Suppiler:"+supplier.toString());
		//Hibernate.initialize(supplier.getGroups());  
		return supplierRepository.save(supplier);
	}
	
	@Override
	public Supplier update(Supplier supplier) {
		return supplierRepository.updateSupplier(supplier);
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
	public List<Supplier> findAllSuppliersForGroup(Group group) {
		return supplierRepository.findAllAuppliersForGroup(group.getId());
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
