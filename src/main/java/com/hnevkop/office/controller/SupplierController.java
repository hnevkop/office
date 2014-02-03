package com.hnevkop.office.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hnevkop.office.model.Supplier;

@Controller
public class SupplierController {

	@RequestMapping(value ="/main")
	public String redirectToMain () {
		return "main";
	}
	
	@RequestMapping(value ="/register", method = RequestMethod.GET)
	public String getSupplier(Model model) {
		Supplier supplier = new Supplier();
		model.addAttribute("supplier", supplier);
		return "supplier";
	}
	
}
