package hsf301.fe.service; // Inferred from the project structure

import java.util.List;
import hsf301.fe.pojo.Student;
import hsf301.fe.repository.IStudentRepository;
import hsf301.fe.repository.StudentRepository;


public class StudentService implements IStudentService {
    private IStudentRepository studentRepo = null;

    public StudentService(String fileName) {
        this.studentRepo = new StudentRepository(fileName);
    }

    @Override
    public void save(Student student) {
        studentRepo.save(student);
    }

    @Override
    public List<Student> findAll() {
        return studentRepo.findAll();
    }

    @Override
    public void delete(Long studentID) {
        studentRepo.delete(studentID);
    }

    @Override
    public Student findById(Long studentID) {
        return studentRepo.findById(studentID);
    }

    @Override
    public void update(Student student) {
        studentRepo.update(student);
    }
}