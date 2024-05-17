package com.cascade;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import manyMapping.Person;
import manyMapping.Skills;

public class CascadeExample {

	public static void main(String[] args) {
		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		Session session = factory.openSession();
		Person person = new Person();
		person.setId(101013);
		person.setName("Deep");
		
		Skills skills = new Skills(1112, "Java", person);
		Skills skills2 = new Skills(2223, "SQL", person);
		Skills skills3 = new Skills(3334, "Hibernate", person);
		
		List<Skills> list = new ArrayList<Skills>();
		list.add(skills);
		list.add(skills2);
		list.add(skills3);
		
		
		person.setSkills(list);
		
		Transaction tx = session.beginTransaction();
		session.save(person);
//		session.save(skills);
//		session.save(skills2);
//		session.save(skills3);
		tx.commit();
		
		
		session.close();
		factory.close();
	}

}
