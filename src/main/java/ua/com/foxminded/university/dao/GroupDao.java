package ua.com.foxminded.university.dao;

import java.util.List;

import ua.com.foxminded.university.model.Group;

public interface GroupDao extends GenericDao<Group> {
    public Group getByIdWithDetail(int id);
    public List<Group> getByCourseId(int curseId);
    public int updateStudents(Group item);
}
