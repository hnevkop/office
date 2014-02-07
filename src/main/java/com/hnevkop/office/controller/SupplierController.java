package com.hnevkop.office.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hnevkop.office.model.Group;
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
	
	@RequestMapping(value ="/supplier", method = RequestMethod.GET)
	public String getSupplier(ModelMap map) {
		Supplier supplier = new Supplier();
		map.addAttribute("supplier", supplier);
		map.addAttribute("allGroups", officeService.getAllGroups());
		map.addAttribute("message", "New Supplier");
		return "supplier";
	}
	
	@RequestMapping(value ="/supplier", method = RequestMethod.POST)
	public String saveNewSupplier(@ModelAttribute("supplier") Supplier supplier, BindingResult result) {		
		if (result.hasErrors()) {
	         return "supplier";
	      } else {
	    	  officeService.save(supplier);
	         return "redirect:/suppliers";
	      }
	}
	
	@RequestMapping(value ="/suppliers", method = RequestMethod.GET)
	public String getSuppliers(ModelMap map) {
		map.addAttribute("suppliers", officeService.findAllSuppliers());
		map.addAttribute("allGroups", officeService.getAllGroups());
		map.addAttribute("group", "1");
		return "suppliers";
	}
	
	@RequestMapping(value ="/ajaxsuppliers", method = RequestMethod.GET)
	public @ResponseBody List<Supplier> loadSuppliers() {
		return officeService.findAllSuppliers();
	}
	
	@RequestMapping(value ="/suppliers/{id}", method = RequestMethod.POST)
	public String updateSupplier(@ModelAttribute("supplier") Supplier supplier, BindingResult result) {
		
		if (result.hasErrors()) {
	         return "supplier";
	      } else {
	    	  officeService.update(supplier);
	    	  return "redirect:/suppliers";
	      }
	}
	
	
	@RequestMapping(value ="/suppliers/{id}", method = RequestMethod.GET)
	public String editSupplier(@PathVariable Long id, ModelMap map ) {
		Supplier supplier = officeService.findSupplierById(id);
		map.addAttribute("supplier", supplier);
		map.addAttribute("groups", supplier.getGroups());
		map.addAttribute("allGroups", officeService.getAllGroups());
		map.addAttribute("message", "Edit Supplier");
		return "supplier";
	}
	
	
	@RequestMapping(value ="/deleteSupplier", method = RequestMethod.POST)
	public String deleteSupplier(@RequestParam int id) {
		officeService.delete(id);
		return "redirect:/suppliers";
	}
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) throws Exception {
		binder.registerCustomEditor(Set.class, "groups", new CustomCollectionEditor(Set.class) {
			protected Object convertElement(Object element) {
				if (element instanceof Group) {
					System.out.println("Converting from Group to Group: " + element);
					return element;
				}
				if (element instanceof String) {
					long id = Long.parseLong((String)element);
					Group group = officeService.findGroupById(id);
					System.out.println("Looking up group for String id " + element + ": " + group);
					return group;
				}
				
				if (element instanceof Long) {
					Group group = officeService.findGroupById((Long)element);
					System.out.println("Looking up group for Long id " + element + ": " + group);
					return group;
				}
				
				if (element instanceof Integer) {
					Group group = officeService.findGroupById((Integer)element);
					System.out.println("Looking up group for Integer id " + element + ": " + group);
					return group;
				}
				
				System.out.println("Don't know what to do with: " + element.toString());
				return null;
			}
		});
	}
	
	

}
