package my.id.elianiva.usecase.exceptions;

public class StudentAlreadyExistsException extends Exception {
    public StudentAlreadyExistsException(String nim) {
        super("The student with a NIM of " + nim + " already exists");
    }
}
