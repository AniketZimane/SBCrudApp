package com.example.SpringBootFirstApp;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
public class Employee
{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "emp_seq")
    @SequenceGenerator(name = "emp_seq")
    long id;
    String name;
    String designation;
    double salary;

    String ext;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Date date;

    public Employee(String name, String designation, double salary, String ext, Date date) {
        this.name = name;
        this.designation = designation;
        this.salary = salary;
        this.ext = ext;
        this.date = date;
    }

    public Employee() {
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

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", designation='" + designation + '\'' +
                ", salary=" + salary +
                ", ext='" + ext + '\'' +
                ", date=" + date +
                '}';
    }
}
