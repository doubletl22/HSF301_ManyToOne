package hsf301.fe.repository;

import java.util.List;
// Assuming hsf301.fe.dao.StudentDAO is the correct import for StudentDAO
import hsf301.fe.dao.StudentDAO;
// Assuming hsf301.fe.pojo.Student is the correct import for Student
import hsf301.fe.pojo.Student;
// Assuming IStudentRepository is an interface in the same or an imported package
// import hsf301.fe.repository.IStudentRepository;


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
    public void delete(Long studentID) { // Phương thức này nhận Long studentID
        if (studentID == null) {
            System.out.println("Error: Student ID for deletion is null.");
            return;
        }
        // 1. Sử dụng findById để lấy đối tượng Student từ studentID
        Student studentToDelete = this.findById(studentID); // Hoặc studentDAO.findById(studentID) nếu bạn muốn gọi trực tiếp DAO

        if (studentToDelete != null) {
            // 2. Gọi studentDAO.delete() với đối tượng Student đã tìm được
            studentDAO.delete(studentToDelete);
        } else {
            // Xử lý trường hợp không tìm thấy sinh viên với ID cung cấp
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