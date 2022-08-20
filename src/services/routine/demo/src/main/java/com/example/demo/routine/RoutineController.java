package com.example.demo.routine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import com.example.demo.jwt.JwtTokenUtil;

import io.jsonwebtoken.Claims;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/routine")
@CrossOrigin(origins = {"http://localhost:8686", "http://localhost:5173"}, allowCredentials = "true")
public class RoutineController {
    private final RoutineQuery routineQuery;
    private final RoutineRepository routineRepository;
    private final JwtTokenUtil jwtTokenUtil;

    @Autowired
    public RoutineController(RoutineQuery routineQuery, RoutineRepository routineRepository, JwtTokenUtil jwtTokenUtil){
        this.routineQuery = routineQuery;
        this.routineRepository = routineRepository;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @GetMappingasd
    public Map findAll(){
        return routineQuery.findRoutines();
    } 

    @GetMapping(value="/me")
    public List<Routine> findByUserId(@RequestHeader("Authorization") String AuthorizationHeader){
        String id = jwtTokenUtil.getUserIdFromToken(jwtTokenUtil.GetTokenFromAuthorizationHeader(AuthorizationHeader));
        return routineRepository.findByUserId(Integer.valueOf(id));
    }

    @PostMapping
    public Routine insertOne(@RequestHeader("Authorization") String AuthorizationHeader, @RequestBody Routine routine){
        String id = jwtTokenUtil.getUserIdFromToken(jwtTokenUtil.GetTokenFromAuthorizationHeader(AuthorizationHeader));
        routine.setUserId(Integer.valueOf(id));
        return routineRepository.insert(routine);
    }

    // @DeleteMapping
    // public void deleteOne(@RequestBody String id){
    //     routineRepository.deleteById(id);
    // }
}
