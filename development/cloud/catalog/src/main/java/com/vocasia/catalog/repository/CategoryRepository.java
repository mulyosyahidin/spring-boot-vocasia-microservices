package com.vocasia.catalog.repository;

import com.vocasia.catalog.entity.Category;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CategoryRepository extends MongoRepository<Category, ObjectId> {

    Category findById(Long id);
    List<Category> findAllByType(String type);

}
