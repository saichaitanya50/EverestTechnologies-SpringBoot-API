package com.pro.EverestTechnologies.repository;

import com.pro.EverestTechnologies.model.StudentMongo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentMongoRepository extends MongoRepository<StudentMongo, String> {
    // Additional query methods (if needed) can be added here
}