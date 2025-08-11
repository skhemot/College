package College.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import College.demo.entity.Student;

@Service
public interface StudentService {
	public List<Student> getall();
	public List<Student> getById();

}
