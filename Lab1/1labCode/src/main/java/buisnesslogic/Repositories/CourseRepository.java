package buisnesslogic.Repositories;

import buisnesslogic.entity.Course;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends CrudRepository<Course, Integer> {

}
