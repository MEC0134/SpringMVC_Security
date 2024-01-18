package com.luv2ocde.springboot.demosecurity.Dao;

import com.luv2ocde.springboot.demosecurity.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    // that's it ... no need to write any code LOL!

}
