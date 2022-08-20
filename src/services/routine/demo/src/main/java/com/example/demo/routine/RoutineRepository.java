package com.example.demo.routine;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface RoutineRepository extends MongoRepository<Routine, String>{
    @Query("{userId: ?0}")
    List<Routine> findByUserId(Integer userId);

    @Query("{type: Weighted, shared: true}")
    List<Routine> findWeightedRoutines();

    @Query("{type: Calisthenics, shared: true}")
    List<Routine> findCalisthenicsRoutines();

    @Query("{type: 'Weighted Calisthenics', shared: true}")
    List<Routine> findWeightedCalisthenicsRoutines();

    @Query("{type: Hybrid, shared: true}")
    List<Routine> findHybridRoutines();
}
