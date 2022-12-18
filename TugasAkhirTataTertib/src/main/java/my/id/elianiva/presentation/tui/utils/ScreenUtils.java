package my.id.elianiva.presentation.tui.utils;

public class ScreenUtils {
    public static final String LINE_PLUS = "+";
    public static final String LINE_HORIZONTAL = "-";
    public static final String LINE_VERTICAL = "|";

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void renderTitle(String title) {
        int paddingSize = 4;
        int titleLength = title.length();

        String horizontalBorder = LINE_PLUS + LINE_HORIZONTAL.repeat(titleLength + paddingSize * 2) + LINE_PLUS;

        System.out.println(horizontalBorder);
        System.out.println(LINE_VERTICAL + " ".repeat(paddingSize) + title + " ".repeat(paddingSize) + LINE_VERTICAL);
        System.out.println(horizontalBorder);
    }
}
