package com.hnevkop.office.service;


import java.util.HashSet;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.hnevkop.office.model.Group;
import com.hnevkop.office.model.Supplier;
import com.hnevkop.office.repository.GroupRepository;
import com.hnevkop.office.repository.SupplierRepository;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="/testContext.xml")
@TransactionConfiguration(defaultRollback = true)
@Transactional
public class OfficeServiceTest {
	
	private static final String TEST_GROUP_DESCRIPTION = "Description";
	private static final String TEST_GROUP_NAME = "Test group";
	private static final String TEST_NUMBER = "123456789";
	private static final String TEST_EMAIL = "test email";
	private static final String TEST_ADDRESS = "test Address";
	private static final String TEST_NAME = "test name";

			
	@Autowired
	private SupplierRepository supplierRepository;
	
	@Autowired
	private GroupRepository groupRepository;
	

	private Supplier supplier;
	private Group group;
		
	
	@Before
	public void setUp(){
		
		supplier = new Supplier();
		supplier.setName(TEST_NAME);
		supplier.setAddress(TEST_ADDRESS);
		supplier.setEmail(TEST_EMAIL);
		supplier.setPhone(TEST_NUMBER);
		
		group = new Group();
		group.setName(TEST_GROUP_NAME);
		group.setDescription(TEST_GROUP_DESCRIPTION);
	 			
	}
	
	
	@Test
	public void createSupplierTest(){
		
		HashSet<Group> groups = new HashSet<Group>(0);
		groups.add(group);
		supplier.setGroups(groups);
		
		Group group_created = groupRepository.save(group);
		Supplier sup_created = supplierRepository.save(supplier);		
		
		// Check  the fields
		Assert.assertTrue(sup_created.getId()!=0);
		Assert.assertTrue(sup_created.getAddress().equals(TEST_ADDRESS));
		Assert.assertTrue(sup_created.getName().equals(TEST_NAME));
		Assert.assertTrue(sup_created.getEmail().equals(TEST_EMAIL));
		Assert.assertTrue(sup_created.getPhone().equals(TEST_NUMBER));
		
		//Check the group is associated
		Assert.assertTrue(!sup_created.getGroups().isEmpty());	
		Assert.assertTrue(sup_created.getGroups().contains(group_created));		
	}
	
	
	@Test
	public void updateSupplierTest(){
		Supplier sup_created = supplierRepository.save(supplier);
		
		String change_str = "New";
		sup_created.setAddress(change_str);
		sup_created.setEmail(change_str);
		sup_created.setName(change_str);
		sup_created.setPhone(change_str);
		
		Supplier updated_supplier = supplierRepository.save(sup_created);
		
		// Check  the fields
		Assert.assertTrue(updated_supplier.getId()==sup_created.getId());
		Assert.assertTrue(updated_supplier.getAddress().equals(change_str));
		Assert.assertTrue(updated_supplier.getName().equals(change_str));
		Assert.assertTrue(updated_supplier.getEmail().equals(change_str));
		Assert.assertTrue(updated_supplier.getPhone().equals(change_str));		
	}
	
	@Test
	public void updateSupplierGroupsTest(){
		
		String test_str = "New";
		
		// Default set
		HashSet<Group> groups = new HashSet<Group>(0);
		groups.add(group);
		supplier.setGroups(groups);
		
		groupRepository.save(group);
		supplierRepository.save(supplier);	
		
		// New set
		Group g1 = new Group();
		g1.setName(test_str);
		g1.setDescription(test_str);
		
		Group g2 = new Group();
		g2.setName(test_str);
		g2.setDescription(test_str);
		
		HashSet<Group> new_groups = new HashSet<Group>(0);
		new_groups.add(g1);
		new_groups.add(g2);
		
		supplier.setGroups(new_groups);
		
		groupRepository.save(g1);
		Supplier saved_supplier = supplierRepository.save(supplier);
		
		Assert.assertTrue(saved_supplier.getGroups().contains(g1));
		Assert.assertTrue(saved_supplier.getGroups().contains(g2));
		Assert.assertTrue(!saved_supplier.getGroups().contains(group));
		
	}
	
	
	@After
	public void tearDown() {
		//supplierRepository.deleteAll();
		//groupRepository.deleteAll();
	}
}
