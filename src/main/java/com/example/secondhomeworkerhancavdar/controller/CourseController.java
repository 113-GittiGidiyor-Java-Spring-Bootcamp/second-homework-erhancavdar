package com.example.secondhomeworkerhancavdar.controller;

import com.example.secondhomeworkerhancavdar.entity.Course;
import com.example.secondhomeworkerhancavdar.entity.ResponseMessage;
import com.example.secondhomeworkerhancavdar.entity.Student;
import com.example.secondhomeworkerhancavdar.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/courses")
public class CourseController {
    CourseService courseService;
    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public ResponseEntity<List<Course>> findAll() {
        return ResponseEntity.ok(courseService.findAll());
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Course course) {
        if (course == null) {
            return new ResponseEntity<>(new ResponseMessage("Null input."), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(new ResponseMessage(courseService.save(course) + "\n Successfully saved."));
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody Course course) {
        if (course == null) {
            return new ResponseEntity<>(new ResponseMessage("Null input."), HttpStatus.BAD_REQUEST);
        }
        courseService.update(course);
        return ResponseEntity.ok(new ResponseMessage(course + "\n Successfully updated."));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable long id) {
        if (courseService.findById(id) == null) {
            return new ResponseEntity<>(new ResponseMessage("Could not find entity."), HttpStatus.BAD_REQUEST);
        }
        courseService.delete(id);
        return ResponseEntity.ok(new ResponseMessage("Successfully deleted."));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable long id) {
        if (courseService.findById(id) == null) {
            return new ResponseEntity<>(new ResponseMessage("Could not find entity."), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(courseService.findById(id));
    }
}
