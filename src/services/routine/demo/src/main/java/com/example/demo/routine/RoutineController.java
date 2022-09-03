package com.example.demo.routine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import com.example.demo.jwt.JwtTokenUtil;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/routine")
@CrossOrigin(origins = {"http://localhost:8686", "http://localhost:5173"}, allowCredentials = "true")
public class RoutineController {
    private final RoutineRepository routineRepository;
    private final JwtTokenUtil jwtTokenUtil;

    @Autowired
    public RoutineController(RoutineRepository routineRepository, JwtTokenUtil jwtTokenUtil){
        this.routineRepository = routineRepository;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @GetMapping
    public Page<Routine> getAll(
        @RequestParam(name="type", required = false) String type,
        @RequestParam(name="page", defaultValue = "0", required = true) int page,
        @RequestParam(name="size", defaultValue = "20") int size){

        Pageable paging = PageRequest.of(page, size);

        if(type != null){
            return routineRepository.findByTypeAndSharedIsTrue(type, paging);
        }
	        
        return routineRepository.findBySharedIsTrue(paging);
    }

    @GetMapping(value="/me")
    public Page<Routine> getAllByUserId(
        @RequestHeader("Authorization") String AuthorizationHeader,
        @RequestParam(name="page", defaultValue = "0", required = true) int page,
        @RequestParam(name="size", defaultValue = "20") int size){
        String id = jwtTokenUtil.getUserIdFromToken(jwtTokenUtil.GetTokenFromAuthorizationHeader(AuthorizationHeader));

        Pageable paging = PageRequest.of(page, size);

        return routineRepository.findByUserId(Integer.valueOf(id), paging);
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
