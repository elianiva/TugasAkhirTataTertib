package my.id.elianiva.usecase.interfaces;

import my.id.elianiva.core.models.Student;
import my.id.elianiva.usecase.exceptions.StudentAlreadyExistsException;
import my.id.elianiva.usecase.exceptions.StudentNotFoundException;

import java.util.List;

public interface IStudentRepository {
    Student getStudentByNim(String nim) throws StudentNotFoundException;

    List<Student> getAllStudents();

    void addStudent(Student student) throws StudentAlreadyExistsException;

    void editStudent(String username, Student newStudent) throws StudentNotFoundException;

    void deleteStudent(String username) throws StudentNotFoundException;
}
