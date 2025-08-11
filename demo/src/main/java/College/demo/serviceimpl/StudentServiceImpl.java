package College.demo.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import College.demo.entity.Student;
import College.demo.repositary.StudentRepositary;
import College.demo.service.StudentService;

@Component
public class StudentServiceImpl implements StudentService {

	@Autowired
	public StudentRepositary sturepo;

	@Override

	public List<Student> getall() {
		System.out.println("this is form service impl mtd");
		// TODO Auto-generated method stub
		List<Student> aa = sturepo.findAll();

		return aa;

	}

	@Override
	public List<Student> getById() {
		List<Student> 	aaa=sturepo.findById(Lond studentID)
		return aaa;
	}

}
