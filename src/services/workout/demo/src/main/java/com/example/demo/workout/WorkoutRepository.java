package com.example.demo.workout;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface WorkoutRepository extends MongoRepository<Workout, String> {
}
