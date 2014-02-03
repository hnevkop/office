package com.hnevkop.office.service;

import java.util.List;

import com.hnevkop.office.model.Supplier;

public interface OfficeService {
	Supplier save(Supplier supplier);
	void delete(Supplier supplier);
	List<Supplier> findAllSuppliers();
}
