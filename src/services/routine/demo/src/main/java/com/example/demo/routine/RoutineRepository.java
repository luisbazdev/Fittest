package com.example.demo.routine;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface RoutineRepository extends MongoRepository<Routine, String>{
    @Query("{userId: ?0}")
    List<Routine> findByUserId(Integer userId);

    List<Routine> findTop20ByTypeAndSharedOrderByCreatedAtDesc(String type, Boolean shared);

    Page<Routine> findByTypeAndSharedIsTrue(String type, Pageable pageable);

    Page<Routine> findBySharedIsTrue(Pageable pageable);
}
