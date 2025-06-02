package hsf301.fe.dao;

import hsf301.fe.pojo.Student;
import java.util.List; // Though not used in the visible snippet, it's present

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class StudentDAO {

    private static EntityManagerFactory emf; // Made static as per image
    private EntityManager em;

    public StudentDAO(String persistanceName) {
        emf = Persistence.createEntityManagerFactory(persistanceName);
    }

    public void save(Student student) {
        try {
            em = emf.createEntityManager();
            em.getTransaction().begin();
            em.merge(student); // Using merge for save/update behavior
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
            System.out.println("Error " + ex.getMessage());
        } finally {
            em.close();
        }
    }

    public List<Student> getStudents() {
        List<Student> students = null;
        try {
            em = emf.createEntityManager();
            em.getTransaction().begin();
            students = em.createQuery("from Student", Student.class).getResultList();
        } catch (Exception ex) {
            System.out.println("Error " + ex.getMessage());

        } finally {
            em.close();
        }
        return students;
    }
    public void delete(Student student) {
        // Kiểm tra xem student có null không trước khi cố gắng lấy ID
        if (student == null || student.getId() == 0) { // Hoặc student.getId() == null nếu ID là kiểu đối tượng Long
            System.out.println("Error: Student object is null or has no ID.");
            return;
        }

        EntityManager em = null; // Khai báo ở đây để có thể truy cập trong finally
        try {
            em = emf.createEntityManager();
            em.getTransaction().begin();

            // Tìm thực thể trong persistence context bằng ID lấy từ đối tượng student
            // Giả sử Student có phương thức getId() trả về kiểu phù hợp (ví dụ int hoặc Long)
            Student managedStudent = em.find(Student.class, student.getId());

            if (managedStudent != null) {
                em.remove(managedStudent); // Xóa thực thể đã được quản lý
                em.getTransaction().commit();
            } else {
                // Nếu không tìm thấy, có thể không cần commit hoặc rollback,
                // nhưng nên thông báo rằng không tìm thấy đối tượng để xóa.
                System.out.println("Student with ID " + student.getId() + " not found in database.");
                // Nếu đã begin transaction, bạn có thể muốn rollback ở đây nếu không có gì khác để commit.
                if (em.getTransaction().isActive()) {
                    em.getTransaction().rollback();
                }
            }
        } catch (Exception ex) {
            System.out.println("Error deleting student: " + ex.getMessage());
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback(); // Rollback transaction nếu có lỗi
            }
        } finally {
            if (em != null) {
                em.close(); // Luôn đóng EntityManager
            }
        }
    }
    public Student findById(Long studentID) {
        Student student = null;
        try {
            em = emf.createEntityManager();
            em.getTransaction().begin();
            student = em.find(Student.class, studentID);
        } catch (Exception ex) {
            System.out.println("Error " + ex.getMessage());
        } finally {
            em.close();
        }
        return student;
    }
    public void update(Student student) {
        try {
            em = emf.createEntityManager();
            em.getTransaction().begin();
            Student s = em.find(Student.class, student.getId());
            if (s != null) {
                s.setFirstName(student.getFirstName());
                s.setLastName(student.getLastName());
                s.setMarks(student.getMarks());
                em.getTransaction().commit();
            }
        } catch (Exception ex) {
            System.out.println("Error " + ex.getMessage());
        } finally {
            em.close();
        }
    }
}
