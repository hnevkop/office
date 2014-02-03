package com.hnevkop.office.service;

import java.util.List;

import com.hnevkop.office.model.Supplier;

public interface OfficeService {
	Supplier save(Supplier supplier);
	void delete(long supplierId);
	List<Supplier> findAllSuppliers();
	Supplier findById(long id);
}
