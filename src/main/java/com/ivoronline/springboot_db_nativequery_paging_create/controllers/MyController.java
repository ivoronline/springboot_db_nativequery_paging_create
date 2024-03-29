package com.ivoronline.springboot_db_nativequery_paging_create.controllers;

import com.ivoronline.springboot_db_nativequery_paging_create.dto.PersonsDTO;
import com.ivoronline.springboot_db_nativequery_paging_create.entities.Person;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.List;

@RestController
public class MyController {

  //PROPERTIES
  @PersistenceContext EntityManager entityManager;

  //================================================================
  // SELECT PERSON
  //================================================================
  // http://localhost:8080/SelectPerson?pageNumber=0&pageSize=2
  @RequestMapping("SelectPerson")
  PersonsDTO selectPerson(
    @RequestParam Integer pageNumber,
    @RequestParam Integer pageSize
  ) {

    //EXECUTE PAGING QUERY
    String       select = "SELECT * FROM Person LIMIT :pageSize OFFSET :pageNumber * :pageSize";
    Query        query  = entityManager.createNativeQuery(select, Person.class);
                 query.setParameter("pageNumber", pageNumber);
                 query.setParameter("pageSize"  , pageSize  );
    List<Person> persons = (List<Person>) query.getResultList();

    //EXECUTE COUNT QUERY
    //Only needed for Frontend to show Page Numbers or disable Next Button at last Page
    String       selectCount = "SELECT count(*) FROM Person";
    Query        queryCount  = entityManager.createNativeQuery(selectCount);
    BigInteger   count       = (BigInteger) queryCount.getSingleResult();
    System.out.println("count: " + count); //Display to Console

    //CREATE DTO
    PersonsDTO  personsDTO = new PersonsDTO();
                personsDTO.count   = count;
                personsDTO.persons = persons;

    //RETURN DTO
    return personsDTO;

  }

}


