package hsf301.fe.repository;

import java.util.List;
import hsf301.fe.dao.StudentDAO;
import hsf301.fe.pojo.Student;

public class StudentRepository implements IStudentRepository {
    private StudentDAO studentDAO = null;

    public StudentRepository(String fileConfig) {
        studentDAO = new StudentDAO(fileConfig);
    }

    @Override
    public void save(Student student) {
        // TODO Auto-generated method stub
        studentDAO.save(student);
    }

    @Override
    public List<Student> findAll() {
        // TODO Auto-generated method stub
        return studentDAO.getStudents();
    }

    @Override
    public void delete(Long studentID) {
        if (studentID == null) {
            System.out.println("Error: Student ID for deletion is null.");
            return;
        }
        Student studentToDelete = this.findById(studentID);

        if (studentToDelete != null) {
            studentDAO.delete(studentToDelete);
        } else {
            System.out.println("Student with ID " + studentID + " not found. Nothing to delete.");
        }
    }


    @Override
    public Student findById(Long studentID) {
        // TODO Auto-generated method stub
        return studentDAO.findById(studentID);
    }

    @Override
    public void update(Student student) {
        studentDAO.update(student);
    }
}