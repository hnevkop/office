package com.hnevkop.office.model;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.hnevkop.office.service.OfficeService;

@Component
public class GroupsConverter implements Converter<String, Set> {

	@Autowired
	private OfficeService officeService;
	
	@Override
	public Set convert(String arg0) {
		System.out.println(arg0);
		long id = Long.parseLong(arg0);
		return officeService.getAllGroups();
	}

}
