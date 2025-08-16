package College.demo.repositary;

import org.springframework.data.jpa.repository.JpaRepository;

import College.demo.entity.TeacherEntity;

public interface TeacherRepo extends JpaRepository<TeacherEntity, Long>{

}
