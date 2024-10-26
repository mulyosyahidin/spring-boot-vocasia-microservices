package com.vocasia.catalog.repository;

import com.vocasia.catalog.entity.Category;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CategoryRepository extends MongoRepository<Category, ObjectId> {

    Category findById(Long id);

}
