package buisnesslogic.Repositories;

import buisnesslogic.model.entity.Category;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CategoryRepository extends CrudRepository<Category, Integer> {

    @Query("select c from Category c where c.title = :title ")
    Category findByTitle(@Param("title") String title);


    @Query("select  c from Category c where c.area = :area ")
    List<Category> findAreaByName(@Param("area") String area);




}
