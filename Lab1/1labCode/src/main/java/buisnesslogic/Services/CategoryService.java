package buisnesslogic.Services;

import buisnesslogic.entity.Category;

import java.util.List;
import java.util.TreeSet;

public interface CategoryService {

    TreeSet<String> getAllAsString();
    List<String> getCategoriesByArea(Category categoryDto);
    Category getById(int id);
}
