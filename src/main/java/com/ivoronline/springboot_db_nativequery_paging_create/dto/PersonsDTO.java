package com.ivoronline.springboot_db_nativequery_paging_create.dto;

import com.ivoronline.springboot_db_nativequery_paging_create.entities.Person;
import java.math.BigInteger;
import java.util.List;

public class PersonsDTO {
  public BigInteger   count;
  public List<Person> persons;
}
