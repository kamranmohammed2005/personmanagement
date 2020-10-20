package com.mars.india.service;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import com.mars.india.domain.Address;
import com.mars.india.domain.Person;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("addressService")
@Transactional
public class AddressService {

	protected static Logger logger = Logger.getLogger("service");
	
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	public List<Address> getAll(Integer personId) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM Person as p LEFT JOIN FETCH  p.addresses WHERE p.id="+personId);
		Person person = (Person) query.uniqueResult();
		// Retrieve all
		return  new ArrayList<Address>(person.getAddresses());
	}
	
	public List<Address> getAll() {
		
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		
		// Create a Hibernate query (HQL)
		Query query = session.createQuery("FROM  Address");
		
		// Retrieve all
		return  query.list();
	}
	
	public Address get( Integer id ) {
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		
		Address address = (Address) session.get(Address.class, id);
		
		// Persists to db
		return address;
	}
	
	public void add(Integer personId, Address address) {
		
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		
		// Persists to db
		session.save(address);
		
		// Add to person as well
		// Retrieve existing person via id
		Person existingPerson = (Person) session.get(Person.class, personId);
		
		// Assign updated values to this person
		existingPerson.getAddresses().add(address);

		// Save updates
		session.save(existingPerson);
	}
	
	public void delete(Integer id) {
		
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		
    	// Delete reference to foreign key address first
		// We need a SQL query instead of HQL query here to access the third table
    	Query query = session.createSQLQuery("DELETE FROM PERSON_ADDRESS " +
    			"WHERE Addresses_ID="+id);
    	
    	query.executeUpdate();
    	
		Address address = (Address) session.get(Address.class, id);
		
		// Delete 
		session.delete(address);
	}
	
	
	public void edit(Address address) {
		logger.debug("Editing existing address");
		
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		
		Address existingAddress = (Address) session.get(Address.class, address.getId());
		
		
		existingAddress.setStreet_no(address.getStreet_no());
		existingAddress.setStreet_name(address.getStreet_name());
		existingAddress.setCity(address.getCity());
		existingAddress.setCountry(address.getCountry());
		
		// Save updates
		session.save(existingAddress);
	}
}
