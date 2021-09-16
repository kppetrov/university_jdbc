package ua.com.foxminded.university.service;

import ua.com.foxminded.university.model.Course;

public interface CourseService extends GenericService<Course>{
    public Course getByIdWithDetail(int id);
    public int updateGroups(Course item);
}
