package com.daemoing.daemo.repository;

import com.daemoing.daemo.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findCategoryByParentCategoryAndChildCategory(String parentCategory, String childCategory);

}