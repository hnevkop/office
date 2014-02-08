package com.hnevkop.office.service;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

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
	public Supplier saveSupplier(Supplier supplier) {
		return supplierRepository.save(supplier);
	}

	@Override
	public void deleteSupplier(long supplierId) {
		Supplier supplier = supplierRepository.findSupplierById(supplierId);
		supplierRepository.delete(supplier);
	}

	/**
	 * Get all suppliers in the system
	 * @return list of suppliers
	 */
	@Override
	public List<Supplier> getAllSuppliers() {
		return supplierRepository.findAllAuppliers();
	}
	
	/**
	 * Get supplier for given group
	 * @param group
	 * @return list of suppliers 
	 */
	@Override
	public List<Supplier> getAllSuppliersForGroup(Group group) {
		return supplierRepository.findSuppliersForGroup(group.getId());
	}

	@Override
	public Supplier getSupplierById(long id) {
		return supplierRepository.findSupplierById(id);
	}
	
	@Override
	public Group getGroupById(long id) {
		return groupRepository.findGroupById(id);
	}

	@Override
	public Set<Group> getAllGroups() {
		List<Group> allGroups = groupRepository.findAllGroups();
		Set<Group> setList = new HashSet<>(allGroups);
		return setList;

	}
	
}
