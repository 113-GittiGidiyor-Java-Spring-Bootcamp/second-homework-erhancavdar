package com.example.secondhomeworkerhancavdar.repositories;

import com.example.secondhomeworkerhancavdar.entity.Course;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import java.util.List;

public class CourseRepositoryImpl implements CourseRepository{
    EntityManager entityManager;

    @Autowired
    public CourseRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Course> findAll() {
        return entityManager.createQuery("FROM Course", Course.class).getResultList();
    }

    @Override
    public Course findById(long id) {
        return entityManager.find(Course.class, id);
    }

    @Override
    public Course save(Course course) {
        return entityManager.merge(course);
    }

    @Override
    public void delete(long id) {
        entityManager.remove(findById(id));
    }

    @Override
    public void update(Course course) {
        entityManager.merge(course);
    }
}
