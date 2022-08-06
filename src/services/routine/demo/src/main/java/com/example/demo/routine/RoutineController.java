package com.example.demo.routine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/routines")
public class RoutineController {
    private final RoutineRepository routineRepository;

    @Autowired
    public RoutineController(RoutineRepository routineRepository){
        this.routineRepository = routineRepository;
    }

    @GetMapping
    public List<Routine> findAll(){
        return routineRepository.findAll();
    }

    @PostMapping
    public Routine insertOne(@RequestBody Routine routine){
        return routineRepository.insert(routine);
    }

    @DeleteMapping
    public void deleteOne(@RequestBody String id){
        routineRepository.deleteById(id);
    }

    // @GetMapping
    // public List<Routine> findAllByUserId(){ return routineRepository.findByUserId(1); }
}
