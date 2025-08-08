package StudentDiary.demo.repositary;

import org.springframework.data.jpa.repository.JpaRepository;

import StudentDiary.demo.entity.StudentEntity;

public interface StudentRepositary extends JpaRepository<StudentEntity, Long>{

}
