package com.wis.libraryservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wis.libraryservice.entity.Category;


@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
