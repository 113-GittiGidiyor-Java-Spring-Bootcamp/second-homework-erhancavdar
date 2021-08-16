package com.example.secondhomeworkerhancavdar.repositories;

import com.example.secondhomeworkerhancavdar.entity.Instructor;
import com.example.secondhomeworkerhancavdar.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import java.util.List;

public class StudentRepositoryImpl implements StudentRepository{
    EntityManager entityManager;

    @Autowired
    public StudentRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    @Override
    public List<Student> findAll() {
        return entityManager.createQuery("FROM Student", Student.class).getResultList();
    }

    @Override
    public Student findById(long id) {
        return entityManager.find(Student.class, id);
    }

    @Override
    public Student save(Student student) {
        return entityManager.merge(student);
    }

    @Override
    public void delete(long id) {
        entityManager.remove(findById(id));
    }

    @Override
    public void update(Student student) {
        entityManager.merge(student);
    }
}
