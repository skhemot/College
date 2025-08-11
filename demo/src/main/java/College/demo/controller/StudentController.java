package College.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import College.demo.entity.Student;
import College.demo.service.StudentService;

//this is controller class 
@RestController
@RequestMapping("/Student")
public class StudentController {
	@Autowired
	private StudentService studserv;

	@GetMapping("/det")

	public List<Student> get() {
		List<Student> a = studserv.testdata();
		return a;
	}

	
}
