package softuni.fundexamprep.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.fundexamprep.model.entity.Category;
import softuni.fundexamprep.model.entity.CategoryName;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {
    Optional<Category> getCategoryByCategoryName(CategoryName categoryName);
}
