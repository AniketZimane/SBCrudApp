package com.example.SpringBootFirstApp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface EmpRepo extends JpaRepository<Employee,Long>
{
    List<Employee> findByName(String name);

    List<Employee> findBySalary(String sal);

    List<Employee> findByDesignation(String desig);

    List<Employee>  findByNameAndDesignation(String name,String desig);

      @Query("from Employee where salary > :sal")
    List<Employee> findBySalaryGreaterThan(double sal);

    List<Employee> findByNameAndSalaryGreaterThan(String name,double sal);

    List<Employee> findByNameIsContaining(String name);

    List<Employee> findByNameNotContains(String Name);

    List<Employee> findByNameLike(String name);

    List<Employee> findByDesignationStartsWith(String Desig);

    List<Employee> findByDesignationEndsWith(String desig);

    List<Employee> findDistinctByNameAndDesignation(String name,String desig);

}
