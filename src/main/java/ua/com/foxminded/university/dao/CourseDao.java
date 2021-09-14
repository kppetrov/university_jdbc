package ua.com.foxminded.university.dao;

import ua.com.foxminded.university.model.Course;

public interface CourseDao extends GenericDao<Course> {
    public Course getByIdWithDetail(int id);
    public int updateGroups(Course item);
}
