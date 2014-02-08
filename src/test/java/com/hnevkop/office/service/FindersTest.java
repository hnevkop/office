package com.hnevkop.office.service;

import java.sql.Connection;
import java.sql.Statement;

import javax.sql.DataSource;

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
public class FindersTest {
	
	static  boolean  initialized = false;

	@Autowired
	private SupplierRepository supplierRepository;
	
	@Autowired
	private GroupRepository groupRepository;
	
    @Autowired
    private DataSource dataSource;

    
	@Test
	public void dataLoadedTestCheck() throws Exception{		
		int initCollectionSize = supplierRepository.findAllAuppliers().size();
		Assert.assertTrue(initCollectionSize>0);
	}

	
	@Test
	public void findAllTest() throws Exception{		
		int result = supplierRepository.findAllAuppliers().size();
		Assert.assertTrue(result==2);
	}
	
	@Test
	public void findSuppliersForGroupTest() throws Exception{
		// Group 1
		int result = supplierRepository.findSuppliersForGroup(1).size();
		Assert.assertEquals(2, result);
		// Group 2
		result = supplierRepository.findSuppliersForGroup(2).size();
		Assert.assertEquals(1, result);
		
		// Group 3 - none
		result = supplierRepository.findSuppliersForGroup(3).size();
		Assert.assertEquals(0, result);
	}
	
	
	@Test
	public void findSuppliersForNonExistingGroupTest() throws Exception{
		// Non-existing group
		int result = supplierRepository.findSuppliersForGroup(10).size();
		Assert.assertEquals(0, result);
	}
	
	@Test
	public void findSuppliersForIdTest() throws Exception{
		// Non-existing group
		Supplier supplier = supplierRepository.findSupplierById(1);
		Assert.assertEquals(1,supplier.getId());
	}

	
	@Test
	public void findAllGroupsTest() throws Exception{
		int result = groupRepository.findAllGroups().size();
		Assert.assertEquals(3,result);
	}
	
	@Test
	public void findGroupByIdTest() throws Exception{
		Group group = groupRepository.findGroupById(1);
		Assert.assertEquals(1,group.getId());
		
		group = groupRepository.findGroupById(10);
		Assert.assertNull(group);
	}

	@Before
	public void setUp() throws Exception{
		if(!initialized){
			loadData();
		}
	}
		
	public void loadData()  throws Exception{
		final Connection connection = dataSource.getConnection();
	    final Statement statement = connection.createStatement();
	    statement.executeUpdate("insert into GROUPS (ID, NAME, DESCRIPTION) values ('1', 'Cleaners', 'Cleaning the office and related')");
	    statement.executeUpdate("insert into GROUPS (ID, NAME, DESCRIPTION) values ('2', 'Office Supply', 'Office supply e.g. paper, pens, etc.')");
	    statement.executeUpdate("insert into GROUPS (ID, NAME, DESCRIPTION) values ('3', 'Your desk', 'Security')");
	    statement.executeUpdate("insert into SUPPLIER (ID, NAME, ADDRESS, EMAIL, PHONE) values ('1', 'Fast Office', 'Na porici 1, Praha 4, Czech Republic', 'info@fastoffice.com', '420123555')");
	    statement.executeUpdate("insert into SUPPLIER (ID, NAME, ADDRESS, EMAIL, PHONE) values ('2', 'Office Depot', 'Vysehradska 1024/5, Praha 4, Czech Republic', 'info@depot.com', '420123444')");
	    statement.executeUpdate("insert into SUPPLIER_GROUPS(SUPPLIER_ID,GROUP_ID) values(1,1)");
	    statement.executeUpdate("insert into SUPPLIER_GROUPS(SUPPLIER_ID,GROUP_ID) values(2,2)");
	    statement.executeUpdate("insert into SUPPLIER_GROUPS(SUPPLIER_ID,GROUP_ID) values(2,1)");
	    statement.close();
	    connection.close();
		initialized = true;
	}
	
	@After
	public void tearDown() {
		supplierRepository.deleteAll();
		groupRepository.deleteAll();
	}
	

}
