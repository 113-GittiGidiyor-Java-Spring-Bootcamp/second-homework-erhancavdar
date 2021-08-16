package com.example.secondhomeworkerhancavdar.controller;

import com.example.secondhomeworkerhancavdar.entity.Instructor;
import com.example.secondhomeworkerhancavdar.entity.ResponseMessage;
import com.example.secondhomeworkerhancavdar.service.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/instructors")
public class InstructorController {
    InstructorService instructorService;

    @Autowired
    public InstructorController(InstructorService instructorService) {
        this.instructorService = instructorService;
    }

    @GetMapping
    public ResponseEntity<List<Instructor>> findAll() {
        return ResponseEntity.ok(instructorService.findAll());
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Instructor instructor) {
        if (instructor == null) {
            return new ResponseEntity<>(new ResponseMessage("Null input."), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(new ResponseMessage(instructorService.save(instructor) + "\n Successfully saved."));
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody Instructor instructor) {
        if (instructor == null) {
            return new ResponseEntity<>(new ResponseMessage("Null input."), HttpStatus.BAD_REQUEST);
        }
        instructorService.update(instructor);
        return ResponseEntity.ok(new ResponseMessage(instructor + "\n Successfully updated."));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable long id) {
        if (instructorService.findById(id) == null) {
            return new ResponseEntity<>(new ResponseMessage("Could not find entity."), HttpStatus.BAD_REQUEST);
        }
        instructorService.delete(id);
        return ResponseEntity.ok(new ResponseMessage("Successfully deleted."));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable long id) {
        if (instructorService.findById(id) == null) {
            return new ResponseEntity<>(new ResponseMessage("Could not find entity."), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(instructorService.findById(id));
    }
}
