package my.id.elianiva.usecase.exceptions;

public class StudentNotFoundException extends Exception {
    public StudentNotFoundException(String nim) {
        super("The student with a NIM of " + nim + " couldn't be found");
    }
}
