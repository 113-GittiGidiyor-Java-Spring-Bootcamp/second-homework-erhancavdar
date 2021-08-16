package com.example.secondhomeworkerhancavdar.repositories;

import com.example.secondhomeworkerhancavdar.entity.Course;
import com.example.secondhomeworkerhancavdar.entity.Instructor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import java.util.List;

public class InstructorRepositoryImpl implements InstructorRepository{
    EntityManager entityManager;

    @Autowired
    public InstructorRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    @Override
    public List<Instructor> findAll() {
        return entityManager.createQuery("FROM Instructor", Instructor.class).getResultList();
    }

    @Override
    public Instructor findById(long id) {
        return entityManager.find(Instructor.class, id);
    }

    @Override
    public Instructor save(Instructor instructor) {
        return entityManager.merge(instructor);
    }

    @Override
    public void delete(long id) {
        entityManager.remove(findById(id));
    }

    @Override
    public void update(Instructor instructor) {
        entityManager.merge(instructor);
    }
}
