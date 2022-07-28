package com.alkemy.ong.repository;

import com.alkemy.ong.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("SELECT c FROM categories c WHERE c.name = :name")
    public Category searchCategoryForName(@Param("name") String name);
}
