package com.demo.hbem.HbEntityManager;


import java.util.Iterator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistence");
    	EntityManager entityManager =  entityManagerFactory.createEntityManager();
    	 
    	
    	entityManager.getTransaction().begin();
    	/*
    	Student student = new Student();
    	student.setId(101);
    	student.setName("Bhavesh");
    	student.setMarks(86);
    	*/
    	
    	//entityManager.getTransaction().commit();
    	
    	//entityManager.persist(student);
//    	
//    	
//    	Student s =entityManager.find(Student.class, 1);
//    	System.out.println(s);
    	
    	Iterator<Student> itr = entityManager.createQuery("FROM Student").getResultList().iterator();
    	while(itr.hasNext()) {
    		Student s =itr.next();
    		System.out.println(s);
    	}
    	entityManager.getTransaction().commit();
    }
}
