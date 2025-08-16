package College.demo.repositary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import College.demo.entity.Student;

@Repository
public interface StudentRepositary extends JpaRepository<Student, Long> {

}

