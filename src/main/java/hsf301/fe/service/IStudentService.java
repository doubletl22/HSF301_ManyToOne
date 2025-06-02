package hsf301.fe.service;

import hsf301.fe.pojo.Student;
import java.util.List;

public interface IStudentService {
    public List<Student> findAll();

    public void save(Student student);

    public void delete(Long studentID);

    public Student findById(Long studentId);

    public void update(Student student);
}
