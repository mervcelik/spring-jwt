package com.example.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	@Query(value = "from employee")
	Page<Employee> findAllPageable(Pageable pageable);  //Pageable ve Page paketinin import org.springframework.data.domain.Pageable; altından geldiğinden emin ol

}
