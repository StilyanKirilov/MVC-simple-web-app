package softuni.fundexamprep.service;

import softuni.fundexamprep.model.entity.Category;
import softuni.fundexamprep.model.entity.CategoryName;
import softuni.fundexamprep.model.service.CategoryServiceModel;

public interface CategoryService {

    void initCategories();

    CategoryServiceModel getCategoryByCategoryName(CategoryName categoryName);
}
