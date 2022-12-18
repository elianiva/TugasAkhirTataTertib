/// DISCLAIMER: there are a lot of questionable stuff and code smell that could've been done much better, I'm fully aware of that
///             It's just that I'm in no position to change that, there are a lot of limitations that I'm not allowed to do.
///             This is probably one of the ugliest code I've ever written :(

package my.id.elianiva;

import java.util.Scanner;

public class Main {
    static String LINE_PLUS = "+";
    static String LINE_HORIZONTAL = "-";
    static String LINE_VERTICAL = "|";
    static String USERNAME = "admin";
    static String PASSWORD = "admin123";

    // [nim, fullName, classPlacement, violatedRuleIndices, currentPunishment, violationsCount]
    static String[][] students = {
            {"1234560001", "Harimurti Suryono", "1A", "23", "12", "2"},
            {"1234560002", "Carla Andriani", "1B", "12", "11", "2"},
            {"1234560003", "Hana Astuti", "1C", "34", "22", "2"},
            {"1234560004", "Rini Padmasari", "1D", "4", "2", "1"},
            {"1234560005", "Karsana Nababan", "1E", "78", "23", "2"}
    };

    // [code, description, level]
    static String[][] rules = {
            {"R001", "Communicating in a disrespectful manner, whether written or written to students, lecturers, employees, or others", "5"},
            {"R002", "Eating, or drinking in the lecture theatre/laboratory/workshop", "4"},
            {"R003", "Students sporting punk-style hair, painted other than black and/or skinned", "4"},
            {"R004", "Violating the rules / regulations that apply in Polinema both in the Department / Study Programme", "3"},
            {"R005", "Not maintaining cleanliness in all areas of Polinema", "3"},
            {"R006", "Smoking outside the smoking area", "3"},
            {"R007", "Playing cards, online games in the campus area", "3"},
            {"R008", "Damaging facilities and infrastructure in the Polinema area", "2"},
            {"R009", "Accessing pornographic material in class or campus areas", "2"},
            {"R010", "Conducting practical political activities on campus", "2"},
            {"R012", "Using psychotropic substances and / or other addictive substances other addictive substances", "1"},
    };

    static String[] punishments = {
            "Oral reprimand accompanied by a statement not to repeat the act, affixed with stamp duty, signed by the student concerned and DPA",
            "A written reprimand accompanied by a statement not to repeat the act, affixed with a stamp duty",
            """
               a. Make a statement not to repeat the act, affixed with stamp duty, signed by the student concerned and DPA
                    b. Perform special tasks, such as being responsible for repairing or cleaning up, and other tasks. cleaning, and other tasks.""",
            """
                a. Compensation for damages or replacement of similar objects/goods and/or
                     b. Performing social service duties for a certain period of time and/or
                     c. Given a grade of D in the relevant course when committing the offence""",
            """
                a. Disabled (Academic/Terminal Leave) for two semesters and/or
                     b. Dismissed as a student."""
    };

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        clearScreen();
        renderTitle("Welcome! Please log in first!");
        login();
        clearScreen();
        renderTitle("Welcome back, " + USERNAME);
        while (true) {
            renderTitle("MAIN MENU");
            int chosenMenu = pickMenu("Main Menu: ", new String[]{
                    "Show Students",
                    "Show Rules",
                    "Exit"
            });
            boolean shouldContinue = routeMainMenu(chosenMenu);
            if (shouldContinue) continue;
            break;
        }
    }

    static void login() {
        while (true) {
            String username = getNonEmptyString("Username: ", "Username can't be empty!");
            String password = getNonEmptyString("Password: ", "Password can't be empty!");
            if (username.equals(USERNAME) && password.equals(PASSWORD)) {
                break;
            }
            clearScreen();
            System.out.println("Incorrect username and password!");
        }
    }

    static boolean routeMainMenu(int chosenMenu) {
        return switch (chosenMenu) {
            case 1 -> handleShowStudents();
            case 2 -> handleShowRules();
            case 3 -> exit();
            default -> handleInvalidMenu();
        };
    }

    static boolean routeStudentMenu(int chosenMenu) {
        return switch (chosenMenu) {
            case 1 -> handleShowStudentDetail();
            case 2 -> handleAddViolatedRuleToStudent();
            case 3 -> handleAddStudent();
            case 4 -> handleEditStudent();
            case 5 -> handleRemoveStudent();
            case 6 -> handleResetStudent();
            case 7 -> back();
            case 8 -> exit();
            default -> handleInvalidMenu();
        };
    }

    static boolean routeRuleMenu(int chosenMenu) {
        return switch (chosenMenu) {
            case 1 -> handleAddRule();
            case 2 -> handleEditRule();
            case 3 -> handleRemoveRule();
            case 4 -> back();
            case 5 -> exit();
            default -> handleInvalidMenu();
        };
    }

    static boolean exit() {
        clearScreen();
        renderTitle("Exiting...");
        System.exit(0);
        return false;
    }

    static boolean back() {
        clearScreen();
        return false;
    }

    static boolean handleInvalidMenu() {
        System.out.println("Invalid menu!");
        clearScreen();
        return true;
    }

    static boolean handleShowStudents() {
        clearScreen();
        while (true) {
            renderStudentsTable("Showing All Students", students);
            int chosenMenu = pickMenu("Students Table Menu: ", new String[]{
                    "Show Student Detail",
                    "Add Student Violated Rule",
                    "Add Student",
                    "Edit Student",
                    "Remove Student",
                    "Reset Student",
                    "Back",
                    "Exit",
            });
            boolean shouldContinue = routeStudentMenu(chosenMenu);
            if (shouldContinue) continue;
            break;
        }
        return true;
    }

    static boolean handleShowStudentDetail() {
        clearScreen();
        String nim;

        while (true) {
            renderTitle("Select Student");
            renderStudentsTable("Showing All Students", students);
            nim = getNonEmptyString("NIM: ", "NIM can't be empty!");

            if (has(students, nim, 0)) break;

            clearScreen();
            System.out.println("Student with the NIM of " + nim + " doesn't exist!");
        }

        clearScreen();
        renderTitle("Showing Details for Student " + nim);

        int studentIndex = -1;
        for (int i = 0; i < students.length; i++) {
            if (students[i][0].equals(nim)) {
                studentIndex = i;
                break;
            }
        }
        if (studentIndex == -1) {
            clearScreen();
            System.out.println("Failed to find the student with a nim of " + nim);
            return true;
        }

        String[] student = students[studentIndex];
        System.out.println("NIM\t\t: " + student[0]);
        System.out.println("Name\t\t: " + student[1]);
        System.out.println("Class\t\t: " + student[2]);

        if (student[3].length() > 0) {
            renderRulesTable("Rules that have been violated", filterRulesByIndices(rules, student[3]));
        }

        if (student[4].length() > 0) {
            renderPunishmentsList("Punishments", filterPunishmentsByIndices(punishments, student[4]));
        }

        getString("Press enter to continue...");

        clearScreen();
        return true;
    }

    static boolean handleAddViolatedRuleToStudent() {
        clearScreen();
        String nim, code;

        int studentIndex = -1;
        while (true) {
            renderTitle("Add Violated Rule to Student");
            renderStudentsTable("Showing All Students", students);
            nim = getNonEmptyStringWithLimit("Student's NIM: ", "NIM can't be empty!", 10, 10, false);

            if (!has(students, nim, 0)) {
                clearScreen();
                System.out.println("Student with the NIM of " + nim + " doesn't exist!");
                continue;
            }

            for (int i = 0; i < students.length; i++) {
                if (students[i][0].equals(nim)) {
                    studentIndex = i;
                    break;
                }
            }

            if (students[studentIndex][3].length() == 3) {
                System.out.println("This student has maxed out the violated rule limit.");
                System.out.println("Please make sure the student has done the punishments and reset the data after that.");
                getString("Press enter to continue...");
                return true;
            }

            break;
        }

        clearScreen();

        int ruleIndex = -1;
        while (true) {
            renderTitle("Add Violated Rule to Student");
            renderRulesTable("Showing All Rules", rules);
            code = getNonEmptyStringWithLimit("Rule's Code: ", "Code can't be empty!", 4, 4, false);

            if (!has(rules, code, 0)) {
                clearScreen();
                System.out.println("Rule with the code of " + code + " doesn't exist!");
                continue;
            }

            for (int i = 0; i < rules.length; i++) {
                if (rules[i][0].equals(code)) {
                    ruleIndex = i;
                    break;
                }
            }

            break;
        }

        String[] currentStudent = students[studentIndex];

        boolean isUpgraded = shouldUpgrade(currentStudent, rules[ruleIndex]);
        currentStudent[3] += toString(ruleIndex);
        currentStudent[4] += resolvePunishmentIndex(currentStudent[3], isUpgraded);
        currentStudent[5] = incrementString(currentStudent[5], Integer.MAX_VALUE);

        clearScreen();
        System.out.println("Rule have been added to the student successfully");

        return true;
    }

    static boolean handleAddStudent() {
        clearScreen();
        String nim, fullName, classPlacement;

        while (true) {
            renderTitle("Add New Student");
            nim = getNonEmptyStringWithLimit("NIM: ", "NIM can't be empty!", 10, 10, false);
            fullName = getNonEmptyStringWithLimit("Full Name: ", "Full Name can't be empty!", 1, 20, false);
            classPlacement = getNonEmptyStringWithLimit("Class: ", "Class can't be empty!", 1, 2, false);

            if (!has(students, nim, 0)) break;

            clearScreen();
            System.out.println("Student with the NIM of " + nim + " already exists!");
        }

        String[][] newStudents = new String[students.length + 1][2];
        for (int i = 0; i < students.length; i++) {
            newStudents[i] = students[i];
        }
        newStudents[newStudents.length - 1] = new String[]{nim, fullName, classPlacement, "", "", "0"};
        students = newStudents;

        clearScreen();
        System.out.println("Students have been succesfully added!");
        return true;
    }

    static boolean handleEditStudent() {
        clearScreen();
        String oldNim, nim, fullName, classPlacement;
        int studentIndex = -1;

        while (true) {
            renderTitle("Edit Student");
            renderStudentsTable("Showing Students to Edit", students);
            oldNim = getNonEmptyStringWithLimit("NIM: ", "NIM can't be empty!", 10, 10, false);

            if (has(students, oldNim, 0)) break;

            clearScreen();
            System.out.println("The student with the NIM of " + oldNim + " doesn't exists");
        }

        for (int i = 0; i < students.length; i++) {
            if (students[i][0].equals(oldNim)) {
                studentIndex = i;
                break;
            }
        }

        if (studentIndex == -1) {
            clearScreen();
            System.out.println("Failed to find student to edit");
            return true;
        }

        String[] student = students[studentIndex];

        clearScreen();
        renderTitle("New Student Data");
        nim = getNonEmptyStringWithLimit("NIM (old: " + student[0] + "): ", "NIM can't be empty!", 10, 10, true);
        fullName = getNonEmptyStringWithLimit("Full Name (old: " + student[1] + "): ", "Full Name can't be empty!", 1, 20, true);
        classPlacement = getNonEmptyStringWithLimit("Class (old: " + student[2] + "): ", "Class can't be empty!", 1, 2, true);

        students[studentIndex][0] = nim.isEmpty() ? student[0] : nim;
        students[studentIndex][1] = fullName.isEmpty() ? student[1] : fullName;
        students[studentIndex][2] = classPlacement.isEmpty() ? student[2] : classPlacement;

        clearScreen();
        System.out.println("Students have been succesfully added!");
        return true;
    }

    static boolean handleRemoveStudent() {
        clearScreen();
        String nim;

        while (true) {
            renderTitle("Remove Student");
            renderStudentsTable("Showing Students to Remove", students);
            nim = getNonEmptyString("NIM: ", "NIM can't be empty!");

            if (has(students, nim, 0)) break;

            clearScreen();
            System.out.println("Student with the NIM of " + nim + " doesn't exist!");
        }

        String[][] filteredStudents = new String[students.length - 1][4];
        int count = 0;
        for (String[] student : students) {
            if (student[0].equals(nim)) continue;
            filteredStudents[count] = student;
            count++;
        }
        students = filteredStudents;

        clearScreen();
        System.out.println("Students " + nim + " have been successfully removed!");
        return true;
    }

    static boolean handleResetStudent() {
        clearScreen();
        String nim;


        int studentIndex = -1;
        while (true) {
            renderTitle("Reset Student");
            renderStudentsTable("Showing Students to Reset", students);
            nim = getNonEmptyStringWithLimit("Student's NIM: ", "NIM can't be empty!", 10, 10, false);

            if (!has(students, nim, 0)) {
                clearScreen();
                System.out.println("Student with the NIM of " + nim + " doesn't exist!");
                continue;
            }

            for (int i = 0; i < students.length; i++) {
                if (students[i][0].equals(nim)) {
                    studentIndex = i;
                    break;
                }
            }

            break;
        }

        students[studentIndex][3] = "";
        students[studentIndex][4] = "";

        clearScreen();
        System.out.println("Students have been successfully reset!");
        return true;
    }

    static boolean handleShowRules() {
        clearScreen();
        while (true) {
            renderRulesTable("Showing All Rules", rules);
            int chosenMenu = pickMenu("Rules Table Menu: ", new String[]{
                    "Add Rule",
                    "Edit Rule",
                    "Remove Rule",
                    "Back",
                    "Exit",
            });
            boolean shouldContinue = routeRuleMenu(chosenMenu);
            if (shouldContinue) continue;
            break;
        }
        return true;
    }

    static boolean handleAddRule() {
        clearScreen();
        String code, description;
        int level;

        while (true) {
            renderTitle("Add New Rule");
            code = getNonEmptyStringWithLimit("Code: ", "Code can't be empty!", 4, 4, false);
            description = getNonEmptyStringWithLimit("Description: ", "Description can't be empty!", 10, 120, false);
            level = getIntegerWithRange("Level: ", 1, 5, false);

            if (!has(rules, code, 0)) break;

            clearScreen();
            System.out.println("Rule with the code of " + code + " already exists!");
        }

        String[][] newRules = new String[rules.length + 1][2];
        for (int i = 0; i < rules.length; i++) {
            newRules[i] = rules[i];
        }
        newRules[newRules.length - 1] = new String[]{code, description, toString(level)};
        rules = newRules;

        clearScreen();
        System.out.println("New rule have been successfully added!");
        return true;
    }

    static boolean handleEditRule() {
        clearScreen();
        String oldCode, code, description;
        int level;
        int ruleIndex = -1;

        while (true) {
            renderTitle("Edit Rule");
            renderRulesTable("Showing Rules to Edit", rules);
            oldCode = getNonEmptyStringWithLimit("Code: ", "Code can't be empty!", 4, 4, false);

            if (has(rules, oldCode, 0)) break;

            clearScreen();
            System.out.println("The rule with the code of " + oldCode + " doesn't exists");
        }

        for (int i = 0; i < rules.length; i++) {
            if (rules[i][0].equals(oldCode)) {
                ruleIndex = i;
                break;
            }
        }

        if (ruleIndex == -1) {
            clearScreen();
            System.out.println("Failed to find rule to edit");
            return true;
        }

        String[] rule = rules[ruleIndex];

        clearScreen();
        renderTitle("New Rule Detail");
        while (true) {
            code = getNonEmptyStringWithLimit("Code (old: " + rule[0] + "): ", "Code can't be empty!", 4, 4, true);
            if (!has(rules, code, 0)) break;
            System.out.println("There's a rule with the same code already! Please try another one.");
        }
        description = getNonEmptyStringWithLimit("Description (old: " + rule[1] + "): ", "Description can't be empty!", 10, 120, true);
        level = getIntegerWithRange("Level (old: " + rule[2] + "): ", 1, 5, true);

        rules[ruleIndex][0] = code.isEmpty() ? rule[0] : code;
        rules[ruleIndex][1] = description.isEmpty() ? rule[1] : description;
        rules[ruleIndex][2] = level == -1 ? rule[2] : String.format("%s", level);

        clearScreen();
        System.out.println("Rule " + oldCode + " have been succesfully edited!");
        return true;
    }

    static boolean handleRemoveRule() {
        clearScreen();
        String code;

        while (true) {
            renderTitle("Remove Rule");
            renderRulesTable("Showing Rules to Remove", rules);
            code = getNonEmptyString("Code: ", "Code can't be empty!");

            if (has(rules, code, 0)) break;

            clearScreen();
            System.out.println("Rule with the code of " + code + " doesn't exist!");
        }

        String[][] filteredRules = new String[rules.length - 1][3];
        int count = 0;
        for (int i = 0; i < rules.length; i++) {
            if (rules[i][0].equals(code)) continue;
            filteredRules[count] = rules[i];
            count++;
        }
        rules = filteredRules;

        clearScreen();
        System.out.println("Rule " + code + " have been successfully removed!");
        return true;
    }

    static void renderTitle(String title) {
        int paddingSize = 4;
        int titleLength = title.length();

        String horizontalBorder = LINE_PLUS + LINE_HORIZONTAL.repeat(titleLength + paddingSize * 2) + LINE_PLUS;

        System.out.println(horizontalBorder);
        System.out.println(LINE_VERTICAL + " ".repeat(paddingSize) + title + " ".repeat(paddingSize) + LINE_VERTICAL);
        System.out.println(horizontalBorder);
    }

    static void renderStudentsTable(String title, String[][] students) {
        renderTitle(title);
        final String tableLine = String.format(
                "%s%s%s%s%s%s%s%s%s%s%s",
                LINE_PLUS, LINE_HORIZONTAL.repeat(6), LINE_PLUS, LINE_HORIZONTAL.repeat(14),
                LINE_PLUS, LINE_HORIZONTAL.repeat(24), LINE_PLUS, LINE_HORIZONTAL.repeat(9),
                LINE_PLUS, LINE_HORIZONTAL.repeat(14), LINE_PLUS
        );
        System.out.println(tableLine);
        System.out.printf("%s  No. %s     NIM      %s       Full Name        %s  Class  %s  Violations  %s\n", LINE_VERTICAL, LINE_VERTICAL, LINE_VERTICAL, LINE_VERTICAL, LINE_VERTICAL, LINE_VERTICAL);
        System.out.println(tableLine);
        for (int i = 0; i < students.length; i++) {
            String[] student = students[i];
            System.out.printf(
                    "%s  %-2s  %s  %-10s  %s  %-20s  %s  %5s  %s   %8s   %s\n",
                    LINE_VERTICAL, (i + 1) + ".", LINE_VERTICAL, student[0], LINE_VERTICAL, student[1],
                    LINE_VERTICAL, student[2], LINE_VERTICAL, student[5], LINE_VERTICAL);
        }
        System.out.println(tableLine);
    }

    static void renderRulesTable(String title, String[][] rules) {
        renderTitle(title);
        final String tableLine = String.format(
                "%s%s%s%s%s%s%s%s%s%s%s",
                LINE_PLUS, LINE_HORIZONTAL.repeat(7), LINE_PLUS, LINE_HORIZONTAL.repeat(10),
                LINE_PLUS, LINE_HORIZONTAL.repeat(124), LINE_PLUS, LINE_HORIZONTAL.repeat(9),
                LINE_PLUS, LINE_HORIZONTAL.repeat(12), LINE_PLUS);
        System.out.println(tableLine);
        System.out.printf(
                "%s  No.  %s    ID    %s  %sDescription%s   %s  Level  %s  Violator  %s\n",
                LINE_VERTICAL, LINE_VERTICAL, LINE_VERTICAL, " ".repeat(54), " ".repeat(54), LINE_VERTICAL, LINE_VERTICAL, LINE_VERTICAL);
        System.out.println(tableLine);
        for (int i = 0; i < rules.length; i++) {
            String[] rule = rules[i];
            System.out.printf(
                    "%s  %3s  %s   %-5s  %s  %-120s  %s  %5s  %s  %8s  %s\n",
                    LINE_VERTICAL, (i + 1) + ".", LINE_VERTICAL, rule[0], LINE_VERTICAL, rule[1],
                    LINE_VERTICAL, rule[2], LINE_VERTICAL, countViolator(rule[0]), LINE_VERTICAL);
        }
        System.out.println(tableLine);
    }

    static void renderPunishmentsList(String title, String[] punishments) {
        renderTitle(title);
        for (int i = 0; i < punishments.length; i++) {
            String punishment = punishments[i];
            System.out.printf("%3s  %-120s\n", (i + 1) + ".)", punishment);
        }
    }

    static int pickMenu(String menuTitle, String[] menus) {
        System.out.println(menuTitle);
        for (int i = 0; i < menus.length; i++) {
            System.out.printf("%d. %s\n", i + 1, menus[i]);
        }
        return getIntegerWithRange("Select menu: ", 1, menus.length, false);
    }

    static String getString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    static String getNonEmptyString(String prompt, String warning) {
        while (true) {
            System.out.print(prompt);
            String userInput = scanner.nextLine().trim();
            if (!userInput.isEmpty()) return userInput;
            System.out.println(warning);
        }
    }

    static String getNonEmptyStringWithLimit(String prompt, String warning, int min, int max, boolean allowEmpty) {
        while (true) {
            String userInput = allowEmpty ? getString(prompt) : getNonEmptyString(prompt, warning);
            if (allowEmpty && userInput.isEmpty()) return userInput;
            if (userInput.length() >= min && userInput.length() <= max) return userInput;
            System.out.println("The input can't be shorter than " + min + " or longer than " + max);
        }
    }

    static int getIntegerWithRange(String prompt, int min, int max, boolean allowEmpty) {
        while (true) {
            System.out.print(prompt);
            String userInputStr = scanner.nextLine();
            if (userInputStr.isEmpty()) {
                if (allowEmpty) return -1;
                System.out.println("Input can't be empty!");
                continue;
            }

            int userInput = Integer.parseInt(userInputStr);
            if (userInput >= min && userInput <= max) return userInput;

            System.out.println("The input can't be lower than " + min + " or greater than " + max);
        }
    }

    static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    static boolean has(String[][] items, String needle, int fieldIndex) {
        for (String[] item : items) {
            if (item[fieldIndex].equals(needle)) return true;
        }
        return false;
    }

    static String toString(int number) {
        return String.format("%d", number);
    }

    static String toString(char character) {
        return String.format("%c", character);
    }

    static boolean shouldUpgrade(String[] student, String[] nextRule) {
        String ruleIndices = student[3];
        int length = ruleIndices.length();
        if (length < 2) return false;

        boolean lastThreeRuleAreSame = true;
        String previousLevel = "";
        for (int i = length - 1; i > length - 3; i--) {
            String[] currentRule = rules[Integer.parseInt(toString(ruleIndices.charAt(i)))];
            if (!previousLevel.isEmpty() && !currentRule[2].equals(previousLevel)) {
                lastThreeRuleAreSame = false;
                break;
            }
            previousLevel = currentRule[2];
        }
        if (!previousLevel.equals(nextRule[2])) {
            lastThreeRuleAreSame = false;
        }

        return lastThreeRuleAreSame;
    }

    static String incrementString(String previous, int limit) {
        int prev = Integer.parseInt(previous);
        int now = prev + 1;
        return now < limit ? toString(now) : previous;
    }

    static String resolvePunishmentIndex(String currentLevel, boolean isUpgraded) {
        int lastRuleIndex = Integer.parseInt(toString(currentLevel.charAt(currentLevel.length() - 1)));
        int lastLevel = Integer.parseInt(rules[lastRuleIndex][2]);

        if (!isUpgraded || lastLevel == 1) return toString(punishments.length - lastLevel);

        return toString(punishments.length - (lastLevel - 1));
    }

    static String[][] filterRulesByIndices(String[][] rules, String indices) {
        String[][] filteredRules = new String[indices.length()][3];
        for (int i = 0; i < indices.length(); i++) {
            int index = Integer.parseInt(String.format("%c", indices.charAt(i))) - 1;
            filteredRules[i] = rules[index];
        }
        return filteredRules;
    }

    static String[] filterPunishmentsByIndices(String[] punishments, String indices) {
        String[] filteredPunishments = new String[indices.length()];
        for (int i = 0; i < indices.length(); i++) {
            int index = Integer.parseInt(String.format("%c", indices.charAt(i)));
            filteredPunishments[i] = punishments[index];
        }
        return filteredPunishments;
    }

    static int countViolator(String ruleCode) {
        int count = 0;

        for (String[] student : students) {
            for (int i = 0; i < student[3].length(); i++) {
                int currentIdx = Integer.parseInt(toString(student[3].charAt(i)));
                String[] rule = rules[currentIdx];
                if (rule[0].equals(ruleCode)) {
                    count++;
                }
            }
        }

        return count;
    }
}
