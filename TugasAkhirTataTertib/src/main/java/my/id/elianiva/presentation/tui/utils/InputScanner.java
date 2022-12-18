package my.id.elianiva.presentation.tui.utils;

import java.io.InputStream;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.function.Function;
import java.util.regex.Pattern;

public class InputScanner {
    private final Scanner scanner;

    public InputScanner(InputStream source) {
        this.scanner = new Scanner(source).useDelimiter(Pattern.compile("\r\n|\n"));
    }

    public void close() {
        scanner.close();
    }

    /**
     * Gets a trimmed string
     * @param prompt The prompt text for the user
     * @return String
     */
    public String getString(String prompt) {
        System.out.print(prompt);
        return scanner.next().trim();
    }

    /**
     * Gets a non-empty string
     * @param prompt The prompt text for the user
     * @param warning The warning when the input is empty
     * @return String
     */
    public String getNonEmptyString(String prompt, String warning) {
        while (true) {
            String userInput = getString(prompt);
            if (!userInput.trim().isEmpty()) {
                return userInput;
            }
            System.out.println(warning);

        }
    }

    /**
     * Same as {@link InputScanner#getNonEmptyStringWithLimit(String, String, int, int, boolean)} with allowEmpty set to false
     */
    public String getNonEmptyStringWithLimit(String prompt, String warning, int minLimit, int maxLimit) {
        return getNonEmptyStringWithLimit(prompt, warning, minLimit, maxLimit, false);
    }

    /**
     * Gets a non empty string with minimum and maximum constraint. When allowEmpty is true, empty input is allowed
     * but if and only if it's completely empty. Otherwise, the normal constraint applies.
     * @param prompt The prompt text for the user
     * @param warning The warning when the input doesn't match the constraint
     * @param minLimit The minimum length for the string
     * @param maxLimit The maximum length for the string
     * @param allowEmpty Whether to allow empty input or not
     * @return String
     * @see InputScanner#getPositiveInteger(String, int)
     */
    public String getNonEmptyStringWithLimit(String prompt, String warning, int minLimit, int maxLimit, boolean allowEmpty) {
        while (true) {
            String userInput = allowEmpty ? getString(prompt) : getNonEmptyString(prompt, warning);
            if (userInput.length() > maxLimit) {
                System.out.println("Can't be longer than " + maxLimit + " characters!");
                continue;
            }
            if (userInput.length() < minLimit) {
                // allow 0-length input when we allow it because we'll need it for the edit form
                if (allowEmpty && userInput.length() == 0) {
                    return userInput;
                }

                System.out.println("Can't be shorter than " + minLimit + " characters!");
                continue;
            }
            return userInput;
        }
    }

    /**
     * Same as {@link InputScanner#getPositiveInteger(String, int, boolean)} with allowEmpty set to false
     */
    public int getPositiveInteger(String prompt, int limit) {
        return getPositiveInteger(prompt, limit, false);
    }

    /**
     * Gets a positive integer using a Scanner. When empty input is provided, it will return -1
     * @param prompt The prompt text for the user
     * @param limit  The upper limit for the integer
     * @param allowEmpty Whether to allow empty input or not
     * @return int?
     * @see InputScanner#getPositiveInteger(String, int)
     */
    public int getPositiveInteger(String prompt, int limit, boolean allowEmpty) {
        while (true) {
            System.out.print(prompt);
            String userInput = scanner.next();

            if (userInput.isEmpty()) {
                if (allowEmpty) return -1;
                System.out.println("Input can't be empty!");
                continue;
            }

            int userInputNumber;
            try {
                userInputNumber = Integer.parseInt(userInput, 10);
            } catch (InputMismatchException e) {
                System.out.println("Please insert the number correctly!");
                continue;
            }

            if (userInputNumber < 0 || userInputNumber > limit) {
                System.out.println("Input value can't be lower than 1 or greater than " + limit);
                continue;
            }

            return userInputNumber;
        }
    }

    /**
     * Same as {@link InputScanner#getEnumOption(String, String, Function, boolean)} with allowEmpty set to false
     */
    public <TEnum> TEnum getEnumOption(String prompt, String warning, Function<String, TEnum> resolver) {
        return getEnumOption(prompt, warning, resolver, false);
    }

    /**
     * Gets a string and then resolve it to the provided {@link TEnum} using the provided resolver
     * @param prompt The prompt text for the user
     * @param warning The warning when the resolver failed to resolve the type
     * @param resolver The resolver used to parse the string into the desired {@link TEnum}
     * @param allowEmpty Whether to allow empty input or not
     * @return {@link TEnum}
     * @param <TEnum> The type to resolve
     */
    public <TEnum> TEnum getEnumOption(String prompt, String warning, Function<String, TEnum> resolver, boolean allowEmpty) {
        while (true) {
            String userInput = getString(prompt);
            if (userInput.isEmpty() && allowEmpty) return null;
            TEnum resolvedEnum = resolver.apply(userInput);
            if (resolvedEnum != null) {
                return resolvedEnum;
            }
            System.out.println(warning);
        }
    }
}
