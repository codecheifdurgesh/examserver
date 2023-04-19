package com.exam.examserver.Repository;

import com.exam.examserver.Entity.exam.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category,Long> {
}
