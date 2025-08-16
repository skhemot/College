package College.demo.serviceimpl;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import College.demo.entity.Student;
import College.demo.entity.TeacherEntity;
import College.demo.repositary.StudentRepositary;
import College.demo.repositary.TeacherRepo;
import College.demo.service.StudentService;

@Component
public class StudentServiceImpl implements StudentService {

	@Autowired
	public StudentRepositary sturepo;

	@Override

	public List<Student> getall() {
		System.out.println("this is form service impl mtd");
		// TODO Auto-generated method stub
		// List<Student> aa = sturepo.findAll();

		return sturepo.findAll();

	}

	@Autowired
	public TeacherRepo teacherrepo;

	@Override
	public List<TeacherEntity> getallT() {
		// TODO Auto-generated method stub
		return teacherrepo.findAll();
	}

//	@Override
//	public Student getbyid(Long id) {
//		Student	aa=sturepo.findAllById(id);
//		return aa;
//	}

	@Override
	public Student getStudentById(Long id) {
	    return sturepo.findById(id)
	            .orElseThrow(() -> new NoSuchElementException("Student not found with ID: " + id));
	}

	@Override
	public TeacherEntity getteacherid(Long id) {
		
		return teacherrepo.findById(id)
	            .orElseThrow(() -> new NoSuchElementException("Student not found with ID: " + id)) ;
	}

	@Override
	public void savedetails(Student student) {
	sturepo.save(student);
		
	}

	@Override
	public void savedetailt(TeacherEntity teacherEntity) {
		// TODO Auto-generated method stub
		teacherrepo.save(teacherEntity);
	}

	@Override
	public void updatedetils(Long id, Student student) {
		// TODO Auto-generated method stub
	    Student existing = sturepo.findById(id)
	            .orElseThrow(() -> new RuntimeException("Student not found with id " + id));

	    existing.setName(student.getName());
	    existing.setAge(student.getAge());
	    existing.setResult(student.getResult());
	    existing.setMarks(student.getMarks());
	    existing.setSurname(student.getSurname());
	    sturepo.save(existing);
	}

	@Override
	public void updatedetilst(Long id, TeacherEntity teacher) {
		TeacherEntity existingt = teacherrepo.findById(id)
				.orElseThrow(() -> new RuntimeException("Student not found with id " + id));
		existingt.setName(teacher.getName());
		existingt.setAddress(teacher.getAddress());
		existingt.setAge(teacher.getAge());
		teacherrepo.save(existingt);
	}

	@Override
	public void deleteTeacher(Long id) {
		teacherrepo.deleteById(id);
		
	}

	@Override
	public void deleteStudent(Long id) {
	sturepo.deleteById(id);
		
	}

}
