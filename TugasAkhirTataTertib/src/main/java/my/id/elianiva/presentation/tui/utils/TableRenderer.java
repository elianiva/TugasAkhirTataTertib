package my.id.elianiva.presentation.tui.utils;

import my.id.elianiva.core.models.Rule;
import my.id.elianiva.core.models.Student;
import my.id.elianiva.core.models.User;

import java.util.List;

import static my.id.elianiva.presentation.tui.utils.ScreenUtils.*;

public class TableRenderer {
    public static void renderUsersTable(List<User> users) {
        final String tableLine = String.format("%s%s%s%s%s", LINE_PLUS, LINE_HORIZONTAL.repeat(6), LINE_PLUS, LINE_HORIZONTAL.repeat(20), LINE_PLUS);
        System.out.println(tableLine);
        System.out.printf("%s  No. %s    Username        %s\n", LINE_VERTICAL, LINE_VERTICAL, LINE_VERTICAL);
        System.out.println(tableLine);
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            System.out.printf("%s  %-3s %s    %-10s      %s\n", LINE_VERTICAL, (i + 1) + ".", LINE_VERTICAL, user.getUsername(), LINE_VERTICAL);
        }
        System.out.println(tableLine);
    }

    public static void renderStudentsTable(List<Student> students) {
        final String tableLine = String.format(
                "%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s",
                LINE_PLUS, LINE_HORIZONTAL.repeat(6), LINE_PLUS, LINE_HORIZONTAL.repeat(14),
                LINE_PLUS, LINE_HORIZONTAL.repeat(24), LINE_PLUS, LINE_HORIZONTAL.repeat(7),
                LINE_PLUS, LINE_HORIZONTAL.repeat(9), LINE_PLUS, LINE_HORIZONTAL.repeat(10),
                LINE_PLUS, LINE_HORIZONTAL.repeat(10), LINE_PLUS
        );
        System.out.println(tableLine);
        System.out.printf("%s  No. %s     NIM      %s          Name          %s  Age  %s  Class  %s  Gender  %s  Points  %s\n", LINE_VERTICAL, LINE_VERTICAL, LINE_VERTICAL, LINE_VERTICAL, LINE_VERTICAL, LINE_VERTICAL, LINE_VERTICAL, LINE_VERTICAL);
        System.out.println(tableLine);
        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            System.out.printf(
                    "%s  %-2s  %s  %-10s  %s  %-20s  %s  %3s  %s  %-4s   %s  %-6s  %s  %6s  %s\n",
                    LINE_VERTICAL, (i + 1) + ".", LINE_VERTICAL, student.getNim(), LINE_VERTICAL, student.getName(),
                    LINE_VERTICAL, student.getAge(), LINE_VERTICAL, student.getClassPlacement(), LINE_VERTICAL, student.getGender(),
                    LINE_VERTICAL, student.getPoints(), LINE_VERTICAL);
        }
        System.out.println(tableLine);
    }

    public static void renderRulesTable(List<Rule> rules) {
        final String tableLine = String.format(
                "%s%s%s%s%s%s%s%s%s",
                LINE_PLUS, LINE_HORIZONTAL.repeat(6), LINE_PLUS, LINE_HORIZONTAL.repeat(10),
                LINE_PLUS, LINE_HORIZONTAL.repeat(64), LINE_PLUS, LINE_HORIZONTAL.repeat(9), LINE_PLUS
        );
        System.out.println(tableLine);
        System.out.printf(
                "%s  No. %s    ID    %s                        Description                             %s  Point  %s\n",
                LINE_VERTICAL, LINE_VERTICAL, LINE_VERTICAL, LINE_VERTICAL, LINE_VERTICAL);
        System.out.println(tableLine);
        for (int i = 0; i < rules.size(); i++) {
            Rule rule = rules.get(i);
            System.out.printf(
                    "%s  %-2s  %s  %-6s  %s  %-60s  %s  %5s  %s\n",
                    LINE_VERTICAL, (i + 1) + ".", LINE_VERTICAL, rule.getId(), LINE_VERTICAL, rule.getDescription(),
                    LINE_VERTICAL, rule.getPoint(), LINE_VERTICAL);
        }
        System.out.println(tableLine);
    }
}
