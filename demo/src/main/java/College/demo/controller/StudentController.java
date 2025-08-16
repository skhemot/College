package College.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import College.demo.entity.Student;
import College.demo.entity.TeacherEntity;
import College.demo.service.StudentService;

//this is controller class 

@CrossOrigin(origins = "http://localhost:3000")

@RestController
@RequestMapping("/College")
public class StudentController {
	@Autowired
	private StudentService studserv;

	@GetMapping("/getAllStudents")

	public List<Student> getAll() {
		// List<Student> a = studserv.getall();
		return studserv.getall();
	}

	@GetMapping("/getAllTeacher")
	public List<TeacherEntity> getallT() {

		return studserv.getallT();
	}

	@GetMapping("/getTeacherById/{id}")
	public TeacherEntity getbyidt(@PathVariable Long id) {
		return studserv.getteacherid(id);
	}

	@GetMapping("/getStudentById/{id}")
	public Student getById(@PathVariable Long id) {

		return studserv.getStudentById(id);
	}

	@PostMapping("/createStudent")

	public void SaveDetails(@RequestBody Student student) {
		studserv.savedetails(student);
	}

	@PostMapping("/createTeacher")
	public void Savedetails(@RequestBody TeacherEntity teacherEntity) {
		studserv.savedetailt(teacherEntity);
	}

	@PutMapping("/updateStudent/{id}")
	public void updatedetails(@PathVariable Long id, @RequestBody Student student) {
		studserv.updatedetils(id, student);

	}

	@PutMapping("/updateTeacher/{id}")
	public void updatedetailst(@PathVariable Long id, @RequestBody TeacherEntity teacher) {
		studserv.updatedetilst(id, teacher);

	}

	@DeleteMapping("deleteTeacher/{id}")
	public void deleteTeacherById(@PathVariable Long id) {
		studserv.deleteTeacher(id);

	}
	@DeleteMapping("deleteStudent/{id}")
	public void deleteStudentByID(@PathVariable Long id) {
		studserv.deleteStudent(id);
	}
}
