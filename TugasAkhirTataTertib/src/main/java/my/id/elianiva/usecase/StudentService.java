package my.id.elianiva.usecase;

import my.id.elianiva.core.models.Gender;
import my.id.elianiva.core.models.Rule;
import my.id.elianiva.core.models.Student;
import my.id.elianiva.usecase.exceptions.RuleNotFoundException;
import my.id.elianiva.usecase.exceptions.StudentAlreadyExistsException;
import my.id.elianiva.usecase.exceptions.StudentNotFoundException;
import my.id.elianiva.usecase.interfaces.IRuleRepository;
import my.id.elianiva.usecase.interfaces.IStudentRepository;

import java.util.List;

public class StudentService {
    private final IStudentRepository studentRepository;
    private final IRuleRepository ruleRepository;

    public StudentService(IStudentRepository studentRepository, IRuleRepository ruleRepository) {
        this.studentRepository = studentRepository;
        this.ruleRepository = ruleRepository;
    }

    public Student getStudentByNim(String nim) throws StudentNotFoundException {
        return studentRepository.getStudentByNim(nim);
    }

    public List<Student> getAllStudents() {
        return studentRepository.getAllStudents();
    }

    public void addStudent(String name, String nim, String classPlacement, int age, Gender gender) throws StudentAlreadyExistsException {
        studentRepository.addStudent(new Student(name, nim, classPlacement, age, gender));
    }

    public void deleteStudent(String nim) throws StudentNotFoundException {
        studentRepository.deleteStudent(nim);
    }

    public void editStudent(String nim, String newName, String newNim, String newClassPlacement, int newAge, Gender newGender) throws StudentNotFoundException {
        Student student = studentRepository.getStudentByNim(nim);
        student.update(newName, newNim, newClassPlacement, newAge, newGender);
        studentRepository.editStudent(nim, student);
    }

    public void addViolatedRule(String nim, String ruleId) throws StudentNotFoundException, RuleNotFoundException {
        Student student = studentRepository.getStudentByNim(nim);
        Rule rule = ruleRepository.getRuleById(ruleId);
        student.addViolatedRule(rule);
    }
}
