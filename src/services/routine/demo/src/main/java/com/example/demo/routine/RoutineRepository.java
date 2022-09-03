package com.example.demo.routine;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface RoutineRepository extends MongoRepository<Routine, String>{
    Page<Routine> findByUserId(Integer userId, Pageable pageable);

    Page<Routine> findByTypeAndSharedIsTrue(String type, Pageable pageable);

    Page<Routine> findBySharedIsTrue(Pageable pageable);
}
