package com.hnevkop.office.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Entity implementation class for Entity: Supplier
 * @author Premysl Hnevkovsky
 *
 */
@Entity
@Table(name="SUPPLIER")
@NamedQueries({ @NamedQuery(name = Supplier.FIND_ALL, query = "select s from Supplier s ") ,
	            @NamedQuery(name = Supplier.FIND_BY_GROUP, query = "select s FROM Supplier s join s.groups g where g.id = :groupId") })
public class Supplier {
	
	private static final long serialVersionUID = 1L;

	public static final String FIND_ALL = "Supplier.findAll";
	public static final String FIND_BY_GROUP = "Supplier.findSupplierByGroup";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotNull
	private String name;
	
	private String address;
	private String email;
	private String phone;
	
	public Supplier() {
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
		  
	 //Change to LAZY with Transactions or initialize collections etc. 
	 // Otherwise will jackson complain when serialize groups to suppliers table.
	@ManyToMany(cascade = CascadeType.MERGE, fetch= FetchType.EAGER)
	 @JoinTable(
	            name="SUPPLIER_GROUPS",
	            joinColumns = @JoinColumn( name="SUPPLIER_ID",referencedColumnName="id"),
	            inverseJoinColumns = @JoinColumn( name="GROUP_ID", referencedColumnName="id")
	    )
	private Set<Group> groups = new HashSet<Group>(0);
	 
	public Set<Group> getGroups() {
		return groups;
	}

	public void setGroups(Set<Group> groups) {
		if(groups == null) {
			// if there is no group - testing purposes. Group is mandatory in normal cases.
			this.groups = new HashSet<Group>(0);
		} else {
			this.groups = groups;
		}
	}
	
	
	public void addGroup(Group group) {
		if (group != null) {
			getGroups().add(group);
		}

	}

	public void removeGroup(Group g) {
		getGroups().remove(g);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((groups == null) ? 0 : groups.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Supplier other = (Supplier) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (groups == null) {
			if (other.groups != null)
				return false;
		} else if (!groups.equals(other.groups))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		return true;
	}

	
}
