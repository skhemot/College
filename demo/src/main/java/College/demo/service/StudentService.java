package College.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import College.demo.entity.Student;
import College.demo.entity.TeacherEntity;
import jakarta.transaction.Transactional;

@Transactional
@Service
public interface StudentService {
	public List<Student> getall();

	public List<TeacherEntity> getallT();

	public Student getStudentById(Long id);

	public TeacherEntity getteacherid(Long id);

	public void savedetails(Student student);

	public void savedetailt(TeacherEntity teacherEntity);

	public void updatedetils(Long id, Student student);

	public void updatedetilst(Long id, TeacherEntity teacher);

	public void deleteTeacher(Long id);
	public void deleteStudent(Long id);
}
