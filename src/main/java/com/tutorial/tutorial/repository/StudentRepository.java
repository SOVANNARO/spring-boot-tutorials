package com.tutorial.tutorial.repository;

import com.tutorial.tutorial.entitiy.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {

}
