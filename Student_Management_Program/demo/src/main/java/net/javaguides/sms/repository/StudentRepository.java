package net.javaguides.sms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.javaguides.sms.entity.Student;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

//@Repository
//@Transactional on service layer
public interface StudentRepository extends JpaRepository<Student, Long> {


}
