package com.jitendra.graphql.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jitendra.graphql.Entity.Person;

@Repository
public interface PersonRepo extends JpaRepository<Person,Long>{

}
