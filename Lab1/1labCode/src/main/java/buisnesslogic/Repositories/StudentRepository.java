package buisnesslogic.Repositories;


import buisnesslogic.entity.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends CrudRepository<Student, Integer> {

    @Query("select s from Student s where s.email = :email ")
    Student findByEmail(@Param("email") String email);
}
