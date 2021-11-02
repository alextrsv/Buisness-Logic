package buisnesslogic.Services.ServicesImpl;

import buisnesslogic.Repositories.CategoryRepository;
import buisnesslogic.Services.CategoryService;
import buisnesslogic.model.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;


    @Override
    public TreeSet<String> getAllAsString() {
        TreeSet<String> areas = new TreeSet<>();
        for (Category cat: categoryRepository.findAll()) {
            areas.add(new String(cat.getArea()));
        }
        return areas;
    }

    @Override
    public List<String> getCategoriesByArea(Category categoryDto) {
        List<String> cats = new ArrayList<>();
        for (Category cat: categoryRepository.findAll()) {
            if (cat.getArea().equals(categoryDto.getArea()))
                cats.add(cat.getTitle());
        }
        return cats;
    }

    @Override
    public Category getById(int id) {
        return categoryRepository.findById(id).get();
    }

}
