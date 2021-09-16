package ua.com.foxminded.university.service;

import java.util.List;

import ua.com.foxminded.university.model.Group;

public interface GroupService extends GenericService<Group>{
    public Group getByIdWithDetail(int id);
    public List<Group> getByCourseId(int curseId);
    public int updateStudents(Group item);
}
