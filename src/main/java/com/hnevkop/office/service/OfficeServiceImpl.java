package com.hnevkop.office.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hnevkop.office.model.Supplier;
import com.hnevkop.office.repository.SupplierRepository;

@Service("officeService")
@Transactional
public class OfficeServiceImpl implements OfficeService {

	
	@Autowired
	private SupplierRepository supplierRepository;
		
	@Override
	public Supplier save(Supplier supplier) {
		return supplierRepository.save(supplier);
	}

	@Override
	public void delete(Supplier supplier) {

	}

	@Override
	public List<Supplier> findAllSuppliers() {
		return supplierRepository.findAllAuppliers();
	}
}
