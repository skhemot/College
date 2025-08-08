package StudentDiary.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import StudentDiary.demo.entity.StudentEntity;
import StudentDiary.demo.repositary.StudentRepositary;

@RestController
public class StudentController {
	@Autowired
	StudentRepositary studentrepositary;

	// localhost:8080/Students
	@GetMapping("/Students")
	public List<StudentEntity> getAllStudent() {
		List<StudentEntity> students = studentrepositary.findAll();
		return students;
	}

	// localhost:8080/Students/{id}
	@GetMapping("/Students/{id}")
	public StudentEntity getStudent(@PathVariable long id) {
		StudentEntity student = studentrepositary.findById(id).get();
		return student;
	}

	// localhost:8080/Students/add
	@PostMapping("/Students/add")
	public void addStudent(@RequestBody StudentEntity studentEntity) {
		studentrepositary.save(studentEntity);
		

	}

	// localhost:8080/Students/put
	@PutMapping("/Students/Put/{id}")
	public StudentEntity putstudent(@PathVariable  long id) {
		StudentEntity student = studentrepositary.findById(id).get();
		student.setName("Shub");
		student.setSurname("Chaari");
		student.setAddress("Nag");
		studentrepositary.save(student);
		return student;

	}

	//// localhost:8080/Students/delete
	@DeleteMapping("Student/delete/{id}")
	public void deletestudent(@PathVariable long id) {
		StudentEntity student = studentrepositary.findById(id).get();
		studentrepositary.delete(student);
	}
}
