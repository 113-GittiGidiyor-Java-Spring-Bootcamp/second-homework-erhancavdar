package com.example.secondhomeworkerhancavdar.controller;

import com.example.secondhomeworkerhancavdar.entity.ResponseMessage;
import com.example.secondhomeworkerhancavdar.entity.Student;
import com.example.secondhomeworkerhancavdar.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/students")
public class StudentController {
    StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<List<Student>> findAll() {
        return ResponseEntity.ok(studentService.findAll());
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Student student) {
        if (student == null) {
            return new ResponseEntity<>(new ResponseMessage("Null input."), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(new ResponseMessage(studentService.save(student) + " Successfully saved."));
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody Student student) {
        if (student == null) {
            return new ResponseEntity<>(new ResponseMessage("Null input."), HttpStatus.BAD_REQUEST);
        }
        studentService.update(student);
        return ResponseEntity.ok(new ResponseMessage(student + " Successfully updated."));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable long id) {
        if (studentService.findById(id) == null) {
            return new ResponseEntity<>(new ResponseMessage("Could not find entity."), HttpStatus.BAD_REQUEST);
        }
        studentService.delete(id);
        return ResponseEntity.ok(new ResponseMessage("Successfully deleted."));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable long id) {
        if (studentService.findById(id) == null) {
            return new ResponseEntity<>(new ResponseMessage("Could not find entity."), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(studentService.findById(id));
    }
}
