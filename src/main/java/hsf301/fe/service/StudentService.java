package hsf301.fe.service; // Inferred from the project structure

import java.util.List;
// Assuming hsf301.fe.pojo.Student is the correct import for Student
import hsf301.fe.pojo.Student;
// Assuming hsf301.fe.repository.IStudentRepository and hsf301.fe.repository.StudentRepository
// are the correct imports
import hsf301.fe.repository.IStudentRepository;
import hsf301.fe.repository.StudentRepository;
// Assuming IStudentService is an interface in the same or an imported package
import hsf301.fe.service.IStudentService;


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