package com.hnevkop.office.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.hnevkop.office.model.Supplier;
import com.hnevkop.office.service.OfficeService;

@Controller
public class SupplierController {
	
	
	@Autowired
	private OfficeService officeService;

	@RequestMapping(value ="/main")
	public String redirectToMain () {
		return "main";
	}
	
	@RequestMapping(value ="/register", method = RequestMethod.GET)
	public String getSupplier(Model model) {
		Supplier supplier = new Supplier();
		model.addAttribute("supplier", supplier);
		model.addAttribute("groups", officeService.getAllGroups());
		return "supplier";
	}
	
	@RequestMapping(value ="/register", method = RequestMethod.POST)
	public String getSupplier(@ModelAttribute("supplier") Supplier supplier) {
		officeService.save(supplier);
		return "redirect:suppliers";
	}
	
	@RequestMapping(value ="/suppliers")
	public String showSuppliers(Model model) {
		model.addAttribute("suppliers", officeService.findAllSuppliers());
		return "suppliers";
	}
	
	@RequestMapping(value ="/suppliers/{id}")
	public String getSupplier(@PathVariable Long id, Model model ) {
		Supplier supplier = officeService.findById(id);
		model.addAttribute("supplier", supplier);
		return "supplier";
	}
	
	
	@RequestMapping(value ="/deleteSupplier", method = RequestMethod.POST)
	public String deleteSupplier(@RequestParam int id) {
		officeService.delete(id);
		return "redirect:suppliers";
	}
	
	

}
