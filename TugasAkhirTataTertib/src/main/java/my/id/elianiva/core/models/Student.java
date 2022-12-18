package my.id.elianiva.core.models;

import java.util.ArrayList;
import java.util.List;

public class Student {
    public static final int NIM_LENGTH = 10;
    public static final int MAX_NAME_LENGTH = 20;
    public static final int MAX_AGE = 99;
    public static final int CLASS_LENGTH = 2;

    private String name;
    private String nim;
    private String classPlacement;
    private int age;
    private Gender gender;
    private final List<Rule> violatedRules;

    public Student(String name, String nim, String classPlacement, int age, Gender gender) {
        this.name = name;
        this.nim = nim;
        this.classPlacement = classPlacement;
        this.age = age;
        this.gender = gender;
        this.violatedRules = new ArrayList<Rule>();
    }

    public String getName() {
        return name;
    }

    public String getNim() {
        return nim;
    }

    public String getClassPlacement() {
        return classPlacement;
    }

    public int getAge() {
        return age;
    }

    public Gender getGender() {
        return gender;
    }

    public List<Rule> getViolatedRules() {
        return violatedRules;
    }

    public void update(String name, String nim, String classPlacement, int age, Gender gender) {
        this.name = name.trim().isEmpty() ? this.name : name;
        this.nim = nim.trim().isEmpty() ? this.nim : nim;
        this.classPlacement = classPlacement.trim().isEmpty() ? this.classPlacement : classPlacement;
        this.age = age == -1 ? this.age : age;
        this.gender = gender == null ? this.gender : gender;
    }

    public int getPoints() {
        return violatedRules.stream().map(Rule::getPoint).reduce(Integer::sum).orElse(0);
    }

    public void addViolatedRule(Rule rule) {
        violatedRules.add(rule);
    }
}
