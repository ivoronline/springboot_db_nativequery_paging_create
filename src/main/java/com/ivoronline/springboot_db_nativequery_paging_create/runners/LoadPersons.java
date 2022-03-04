package com.ivoronline.springboot_db_nativequery_paging_create.runners;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Component
public class LoadPersons implements CommandLineRunner {

  //PROPERTIES
  @PersistenceContext EntityManager entityManager;

  //=======================================================================================
  // RUN
  //=======================================================================================
  @Override
  @Transactional
  public void run(String... args) throws Exception {

    //CREATE QUERY
    String insert = "INSERT INTO PERSON (name, age) VALUES (:name, :age)";
    Query  query  = entityManager.createNativeQuery(insert);

    //INSERT TEST DATA
    insertPerson(query, "Person 1" ,  10);
    insertPerson(query, "Person 2" ,  20);
    insertPerson(query, "Person 3" ,  30);
    insertPerson(query, "Person 4" ,  40);
    insertPerson(query, "Person 5" ,  50);
    insertPerson(query, "Person 6" ,  60);
    insertPerson(query, "Person 7" ,  70);
    insertPerson(query, "Person 8" ,  80);
    insertPerson(query, "Person 9" ,  90);
    insertPerson(query, "Person 10", 100);
    insertPerson(query, "Person 11", 110);
    insertPerson(query, "Person 12", 120);

  }

  //=======================================================================================
  // INSERT PERSON
  //=======================================================================================
  void insertPerson(Query query, String name, Integer age) {
    query.setParameter("name", name);
    query.setParameter("age" , age );
    query.executeUpdate();
  }

}
