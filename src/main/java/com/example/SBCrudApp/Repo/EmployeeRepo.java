package com.example.SBCrudApp.Repo;

import com.example.SBCrudApp.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface EmployeeRepo extends JpaRepository<Employee, Long>
{
    List<Employee> findByName(String name);
    List<Employee> findByDesignation(String desig);
    List<Employee> findBySalary(double sal);

    List<Employee> findByNameAndDesignation(String name, String desig);

    List<Employee> findBySalaryGreaterThan(double sal);
    List<Employee> findBySalaryLessThan(double sal);

    List<Employee>findByNameLike(String name);
}
