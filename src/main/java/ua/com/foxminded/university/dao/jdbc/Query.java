package ua.com.foxminded.university.dao.jdbc;

public class Query {    
    public static final String CLASSROOM_GET_ALL = "select id as classroom_id, name as classroom_name from classrooms";
    public static final String CLASSROOM_GET_BY_ID = "select id as classroom_id, name as classroom_name from classrooms where id = :id";
    public static final String CLASSROOM_INSERT = "insert into classrooms (name) values (:name)";
    public static final String CLASSROOM_UPDATE = "update classrooms set name = :name where id = :id";
    public static final String CLASSROOM_DELETE = "delete from classrooms where id = :id";

    public static final String COURSE_GET_ALL = "select id as course_id, name as course_name from courses";
    public static final String COURSE_GET_BY_ID = "select id as course_id, name as course_name from courses where id = :id";
    public static final String COURSE_INSERT = "insert into courses (name) values (:name)";
    public static final String COURSE_UPDATE = "update courses set name = :name where id = :id";
    public static final String COURSE_DELETE = "delete from courses where id = :id";    
    public static final String COURSE_REMOVE_GROUP_FROM_COURSE = "delete from course_group where course_id = :course_id and not group_id in(:group_ids)";
    public static final String COURSE_ADD_GROUP_TO_COURSE = 
            "insert into course_group (course_id, group_id) "
            + "select :course_id, g.id "
            + "from groups as g "
            + "left join course_group as cg "
            + "on g.id = cg.group_id and cg.course_id = :course_id "
            + "where g.id in (:group_ids) and cg.group_id is null";
    
    public static final String GROUP_GET_ALL = "select g.id as group_id, g.name as group_name from groups as g ";
    public static final String GROUP_GET_BY_ID = "select g.id as group_id, g.name as group_name from groups as g where g.id = :id";
    public static final String GROUP_GET_BY_NAME = "select g.id as group_id, g.name as group_name from groups as g where g.name = :name";
    public static final String GROUP_GET_BY_COURSE_ID = 
            "select "
                + "g.id as group_id, "
                + "g.name as group_name "
            + "from groups as g "
                + "left join course_group as cg "
                    + "on g.id = cg.group_id "
            + "where cg.course_id = :course_id";
    public static final String GROUP_GET_BY_ID_DETAIL = 
            "select "
                + "g.id as group_id, "
                + "g.name as group_name, "
                + "s.id as student_id, "
                + "s.first_name, "
                + "s.last_name, "
                + "s.gender, "
                + "s.birthdate "
            + "from groups as g "
                + "left join students s "
                + "on g.id = s.group_id "
                + "where g.id = :id";
    public static final String GROUP_INSERT = "insert into groups (name) values (:name)";
    public static final String GROUP_UPDATE = "update groups set name = :name where id = :id";
    public static final String GROUP_DELETE = "delete from groups where id = :id";
    public static final String GROUP_REMOVE_STUDENTS_FROM_GROUP = 
            "update students "
          + "set group_id = null "
          + "where group_id is not distinct from :group_id and not id in(:students_ids)";
    public static final String GROUP_ADD_STUDENTS_TO_GROUP = 
            "update students " 
          + "set group_id = :group_id " 
          + "where group_id is distinct from :group_id and id in(:students_ids)";
    
    public static final String LESSON_GET_ALL = 
            "select "
                + "l.id as lesson_id, "
                + "l.date as lesson_date, "
                + "c.id as course_id, "
                + "c.name as course_name, "
                + "p.id as period_id, "
                + "p.name as period_name, "
                + "p.start_time, "
                + "p.end_time, "
                + "cr.id as classroom_id, "
                + "cr.name as classroom_name, "
                + "t.id as teacher_id, "
                + "t.first_name, "
                + "t.last_name, "
                + "t.gender, "
                + "t.birthdate "
            + "from lessons l "
                + "left join courses as c "
                    + "on l.course_id = c.id "
                + "left join periods as p "
                    + "on l.period_id = p.id "
                + "left join classrooms as cr "
                    + "on l.classroom_id = cr.id "
                + "left join teachers as t "
                    + "on l.teacher_id = t.id";
    public static final String LESSON_GET_BY_ID = LESSON_GET_ALL + " where l.id = :id";
    public static final String LESSON_GET_BY_COURSE_ID = LESSON_GET_ALL + " where l.course_id = :course_id";  
    public static final String LESSON_GET_BY_TEACHER_ID = LESSON_GET_ALL + " where l.teacher_id = :teacher_id";
    public static final String LESSON_GET_BY_GROUP_ID = 
            LESSON_GET_ALL
            + " left join course_group as cg "
                    + "on l.course_id = cg.course_id "
            + " where cg.group_id = :group_id";
    public static final String LESSON_INSERT = 
            "insert into lessons (date, course_id, period_id, classroom_id, teacher_id) "
          + "values (:date, :course_id, :period_id, :classroom_id, :teacher_id)";
    public static final String LESSON_UPDATE = 
            "update lessons "
               + "set "
                   + "date = :date, "
                   + "course_id = :course_id, "
                   + "period_id = :period_id, "
                   + "classroom_id = :classroom_id, "
                   + "teacher_id = :teacher_id "
               + "where id = :id";
    public static final String LESSON_DELETE = "delete from lessons where id = :id";
    
    public static final String PERIOD_GET_ALL = "select id as period_id, name as period_name, start_time, end_time from periods";
    public static final String PERIOD_GET_BY_ID = "select id as period_id, name as period_name, start_time, end_time from periods where id = :id";
    public static final String PERIOD_INSERT = "insert into periods (name, start_time, end_time) values (:name, :start_time, :end_time)";
    public static final String PERIOD_UPDATE = "update periods set name = :name, start_time = :start_time, end_time = :end_time where id = :id";
    public static final String PERIOD_DELETE = "delete from periods where id = :id";
    
    public static final String STUDENT_GET_ALL = "select id as student_id, first_name, last_name, gender, birthdate from students";
    public static final String STUDENT_GET_BY_ID = "select id as student_id, first_name, last_name, gender, birthdate from students where id = :id";
    public static final String STUDENT_INSERT = "insert into students (first_name, last_name, gender, birthdate) "
                                       + "values (:first_name, :last_name, :gender, :birthdate)";
    public static final String STUDENT_UPDATE = "update students "
                                       + "set first_name = :first_name, last_name = :last_name, gender = :gender, birthdate = :birthdate "
                                       + "where id = :id";
    public static final String STUDENT_DELETE = "delete from students where id = :id";
    
    public static final String TEACHER_GET_ALL = "select id as teacher_id, first_name, last_name, gender, birthdate from teachers";
    public static final String TEACHER_GET_BY_ID = "select id as teacher_id, first_name, last_name, gender, birthdate from teachers where id = :id";
    public static final String TEACHER_INSERT = "insert into teachers (first_name, last_name, gender, birthdate) "
                                       + "values (:first_name, :last_name, :gender, :birthdate)";
    public static final String TEACHER_UPDATE = "update teachers "
                                       + "set first_name = :first_name, last_name = :last_name, gender = :gender, birthdate = :birthdate "
                                       + "where id = :id";
    public static final String TEACHER_DELETE = "delete from teachers where id = :id";
    
    private Query() {
    }   
}
