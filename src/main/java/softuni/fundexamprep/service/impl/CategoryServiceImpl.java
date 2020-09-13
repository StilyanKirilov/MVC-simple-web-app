package softuni.fundexamprep.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.fundexamprep.model.entity.Category;
import softuni.fundexamprep.model.entity.CategoryName;
import softuni.fundexamprep.model.service.CategoryServiceModel;
import softuni.fundexamprep.repository.CategoryRepository;
import softuni.fundexamprep.service.CategoryService;

import java.util.Arrays;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void initCategories() {

        if (this.categoryRepository.count() == 0) {

            Arrays.stream(CategoryName.values())
                    .forEach(categoryName -> {
                        this.categoryRepository
                                .save(new Category(categoryName, String.format("Description for %s",
                                        categoryName.name())));
                    });
        }
    }

    @Override
    public CategoryServiceModel getCategoryByCategoryName(CategoryName categoryName) {
        return this.categoryRepository.getCategoryByCategoryName(categoryName)
                .map(category -> this.modelMapper.map(category, CategoryServiceModel.class))
                .orElse(null);
    }


}
