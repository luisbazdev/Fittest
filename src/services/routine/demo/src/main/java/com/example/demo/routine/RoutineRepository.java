package com.example.demo.routine;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface RoutineRepository extends MongoRepository<Routine, String>{
    @Query("{user_id: '?0'}")
    List<Routine> findByUserId(Integer userId);
}
