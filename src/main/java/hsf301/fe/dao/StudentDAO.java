package hsf301.fe.dao;

import hsf301.fe.pojo.Student;
import java.util.List; // Though not used in the visible snippet, it's present

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class StudentDAO {

    private static EntityManagerFactory emf;
    private EntityManager em;

    public StudentDAO(String persistanceName) {
        emf = Persistence.createEntityManagerFactory(persistanceName);
    }

    public void save(Student student) {
        try {
            em = emf.createEntityManager();
            em.getTransaction().begin();
            em.merge(student);
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
        if (student == null || student.getId() == 0) {
            System.out.println("Error: Student object is null or has no ID.");
            return;
        }

        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            em.getTransaction().begin();

            Student managedStudent = em.find(Student.class, student.getId());

            if (managedStudent != null) {
                em.remove(managedStudent);
                em.getTransaction().commit();
            } else {
                System.out.println("Student with ID " + student.getId() + " not found in database.");
                if (em.getTransaction().isActive()) {
                    em.getTransaction().rollback();
                }
            }
        } catch (Exception ex) {
            System.out.println("Error deleting student: " + ex.getMessage());
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        } finally {
            if (em != null) {
                em.close();
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
