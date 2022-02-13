package net.javaguides.sms;

import net.javaguides.sms.entity.Student;
import net.javaguides.sms.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StudentManagementSystemApplication implements CommandLineRunner{

	public static void main(String[] args)  {
		SpringApplication.run(StudentManagementSystemApplication.class, args);
	}

	@Autowired
	private StudentRepository studentRepository;

	//This run method executes when we run our Spring Boot app
	@Override
	public void run(String... args) throws Exception {

//		Student student1 = new Student("Marcell", "Monkey", "Marcell@monkey.com");
//		studentRepository.save(student1);
//
//		Student student2 = new Student("Steph", "Youky", "ilove@peter.com");
//		studentRepository.save(student2);
//
//		Student student3 = new Student("peti", "koncz", "king@peter.com");
//		studentRepository.save(student3);
	}
}
