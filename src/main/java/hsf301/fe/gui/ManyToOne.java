package hsf301.fe.gui;

import hsf301.fe.service.IStudentService;
import hsf301.fe.service.StudentService;
import hsf301.fe.pojo.Student;
import hsf301.fe.pojo.Book;


public class ManyToOne {
    public static void main(String[] args) {
        String fileName = "JPAs";
        IStudentService studentService = new StudentService(fileName);
        Student student = new Student("Lam", "Nguyen", 9);
        Book book = new Book("Java Persistence with Spring", "Catalin Tudose", "9781617299186");
        if (student.getBooks() == null) {
            student.setBooks(new java.util.HashSet<>());
        }
        student.getBooks().add(book);
        book.setStudent(student);

        studentService.save(student);

        System.out.println("Student and Book saved!");
    }
}