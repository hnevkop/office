package com.hnevkop.office.controller;

import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

/**
 * Main application controller.
 * @author Premysl Hnevkovsky
 *
 */
@Controller
public class SupplierController {
	
	private static final String EDIT_SUPPLIER = "Edit Supplier";

	private static final String NEW_SUPPLIER = "New Supplier";

	static final Logger LOG = LoggerFactory.getLogger(SupplierController.class);
	
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
		map.addAttribute("message", NEW_SUPPLIER);
		return "supplier";
	}
	
	@RequestMapping(value ="/supplier", method = RequestMethod.POST)
	public String saveSupplier(@ModelAttribute("supplier") Supplier supplier, BindingResult result) {		
		if (result.hasErrors()) {
	         return "supplier";
	      } else {
	    	  officeService.saveSupplier(supplier);
	         return "redirect:/suppliers";
	      }
	}
	
	@RequestMapping(value ="/suppliers", method = RequestMethod.GET)
	public String getSuppliers(ModelMap map) {
		map.addAttribute("allGroups", officeService.getAllGroups());
		map.addAttribute("searchFilter", new SearchFilter());
		return "suppliers";
	}
	
	@RequestMapping(value ="/search", method = RequestMethod.POST)
	public @ResponseBody List<Supplier> searchSuppliers(@RequestParam(value="searchId", required=true) String filterId){
		
		// 0 is reserved  for no filter
		if(filterId.equals("0")){
			return officeService.getAllSuppliers();
		} else {
			Group group = officeService.getGroupById(Long.parseLong(filterId));
			List<Supplier> suppliers = officeService.getAllSuppliersForGroup(group);
			return suppliers;
		}		
	}
	
	@RequestMapping(value ="/suppliers/{id}", method = RequestMethod.POST)
	public String updateSupplier(@ModelAttribute("supplier") Supplier supplier, BindingResult result) {
		
		if (result.hasErrors()) {
	         return "supplier";
	      } else {
	    	  officeService.saveSupplier(supplier);
	    	  return "redirect:/suppliers";
	      }
	}
	
	
	@RequestMapping(value ="/suppliers/{id}", method = RequestMethod.GET)
	public String editSupplier(@PathVariable Long id, ModelMap map ) {
		Supplier supplier = officeService.getSupplierById(id);
		map.addAttribute("supplier", supplier);
		map.addAttribute("groups", supplier.getGroups());
		map.addAttribute("allGroups", officeService.getAllGroups());
		map.addAttribute("message", EDIT_SUPPLIER);
		return "supplier";
	}
	
	
	@RequestMapping(value ="/delete", method = RequestMethod.POST)
	public String deleteSupplier(@RequestParam int id) {
		officeService.deleteSupplier(id);
		return "redirect:/suppliers";
	}
	
	/**
	 * Binder for Group object. 
	 * reason for implementation: form with selects
	 * @param binder
	 * @throws Exception
	 */
	@InitBinder
	protected void initBinder(WebDataBinder binder) throws Exception {
		binder.registerCustomEditor(Set.class, "groups", new CustomCollectionEditor(Set.class) {
			protected Object convertElement(Object element) {
				if (element instanceof Group) {
					LOG.debug("Converting from Group to Group: " + element);
					return element;
				}
				if (element instanceof String) {
					long id = Long.parseLong((String)element);
					Group group = officeService.getGroupById(id);
					LOG.debug("Looking up group for String id " + element + ": " + group);
					return group;
				}
				
				if (element instanceof Long) {
					Group group = officeService.getGroupById((Long)element);
					LOG.debug("Looking up group for Long id " + element + ": " + group);
					return group;
				}				
				
				LOG.error("Bider for group got unknown object type " + element.toString());
				return null;
			}
		});
	}
	
	

}
