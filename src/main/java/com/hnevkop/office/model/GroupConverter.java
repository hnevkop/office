package com.hnevkop.office.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.hnevkop.office.service.OfficeService;

@Component
public class GroupConverter implements Converter<String, Group> {
	
	@Autowired
	private OfficeService officeService;
	@Override
	public Group convert(final String arg0) {
		System.out.println(arg0);
		long id = Long.parseLong(arg0);
		return officeService.findGroupById(id);
	}
	
}