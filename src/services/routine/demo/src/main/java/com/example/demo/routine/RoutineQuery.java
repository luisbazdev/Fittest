package com.example.demo.routine;

import com.example.demo.exercise.Exercise;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoutineQuery {
    private List<Routine> weighted;
    private List<Routine> calisthenics;
    private List<Routine> weighted_calisthenics;
    private List<Routine> hybrid;

    private final RoutineRepository routineRepository;

    @Autowired
    public RoutineQuery(RoutineRepository routineRepository){
        this.routineRepository = routineRepository;
    }

    public Map findRoutines(){
        setRoutines();

        HashMap map = new HashMap<>();
        map.put("weighted", getWeighted());
        map.put("calisthenics", getCalisthenics());
        map.put("weighted calisthenics", getWeightedCalisthenics());
        map.put("hybrid", getHybrid());

        return map;
    }

    public void setRoutines(){
        this.weighted = routineRepository.findWeightedRoutines();
        this.calisthenics = routineRepository.findCalisthenicsRoutines();
        this.weighted_calisthenics = routineRepository.findWeightedCalisthenicsRoutines();
        this.hybrid = routineRepository.findHybridRoutines();
    }

    public List<Routine> getWeighted(){
        return this.weighted;
    }
    
    public List<Routine> getCalisthenics(){
        return this.calisthenics;
    }

    public List<Routine> getWeightedCalisthenics(){
        return this.weighted_calisthenics;
    }

    public List<Routine> getHybrid(){
        return this.hybrid;
    }
}
