package buisnesslogic.Services;

import buisnesslogic.model.entity.Category;

import java.util.List;
import java.util.TreeSet;

public interface CategoryService {

    TreeSet<String> getAllAsString();
    List<String> getCategoriesByArea(Category categoryDto);
    Category getById(int id);
}
