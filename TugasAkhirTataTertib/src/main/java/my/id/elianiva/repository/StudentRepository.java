package my.id.elianiva.repository;

import my.id.elianiva.core.models.Gender;
import my.id.elianiva.core.models.Student;
import my.id.elianiva.usecase.exceptions.StudentAlreadyExistsException;
import my.id.elianiva.usecase.exceptions.StudentNotFoundException;
import my.id.elianiva.usecase.interfaces.IStudentRepository;

import java.util.ArrayList;
import java.util.List;

public class StudentRepository implements IStudentRepository {
    private final List<Student> students = new ArrayList<Student>();

    public StudentRepository() {
        students.add(new Student("Hilman Sugiyanto", "2241720001", "1A", 18, Gender.MALE));
        students.add(new Student("Mirza Aisi", "2241720002", "1E", 19, Gender.FEMALE));
        students.add(new Student("Cardito Nurullah", "2241720003", "1F", 20, Gender.FEMALE));
        students.add(new Student("Ilham Imran", "2241720004", "1A", 21, Gender.MALE));
    }

    @Override
    public Student getStudentByNim(String nim) throws StudentNotFoundException {
        Student student = students.stream().filter(s -> s.getNim().equals(nim)).findFirst().orElse(null);
        if (student == null) {
            throw new StudentNotFoundException(nim);
        }
        return student;
    }

    @Override
    public List<Student> getAllStudents() {
        return students.stream().sorted((s1, s2) -> {
            Double s1Number = Double.parseDouble(s1.getNim());
            Double s2Number = Double.parseDouble(s2.getNim());
            return s1Number.compareTo(s2Number);
        }).toList();
    }

    @Override
    public void addStudent(Student student) throws StudentAlreadyExistsException {
        String newNim = student.getNim();
        Student existingStudent = students.stream().filter(s -> s.getNim().equals(newNim)).findFirst().orElse(null);
        if (existingStudent != null) {
            throw new StudentAlreadyExistsException(newNim);
        }
        students.add(student);
    }

    @Override
    public void editStudent(String nim, Student newStudent) throws StudentNotFoundException {
        Student existingStudent = students.stream().filter(s -> s.getNim().equals(nim)).findFirst().orElse(null);
        if (existingStudent == null) {
            throw new StudentNotFoundException(nim);
        }
        int oldStudentIndex = students.indexOf(existingStudent);
        students.set(oldStudentIndex, newStudent);
    }

    @Override
    public void deleteStudent(String nim) throws StudentNotFoundException {
        Student existingStudent = students.stream().filter(s -> s.getNim().equals(nim)).findFirst().orElse(null);
        if (existingStudent == null) {
            throw new StudentNotFoundException(nim);
        }
        students.remove(existingStudent);
    }
}
