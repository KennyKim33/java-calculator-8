package calculator.service;

import java.util.regex.Pattern;

public class DelimiterParser {
    private static final String DEFAULT_DELIMITER = "[:,]";
    private static final String CUSTOM_DELIMITER_PREFIX = "//";
    private static final String CUSTOM_DELIMITER_SUFFIX = "\\n";

    public String parse(String input) {
        String delimiter = getDelimiter(input);
        String expressionPart = getExpression(input);
        return String.join("", expressionPart.split(delimiter));
    }

    private String getExpression(String input) {
        if (hasCustomDelimiter(input)) {
            int delimiterEndIndex = input.indexOf(CUSTOM_DELIMITER_SUFFIX);
            return input.substring(delimiterEndIndex + CUSTOM_DELIMITER_SUFFIX.length());
        }
        return input;
    }

    private boolean hasCustomDelimiter(String input) {
        return input.startsWith(CUSTOM_DELIMITER_PREFIX);
    }

    private String getDelimiter(String input) {
        if (hasCustomDelimiter(input)) {
            String customDelimiter = extractCustomDelimiter(input);
            return buildDelimiterPattern(customDelimiter);
        }
        return DEFAULT_DELIMITER;
    }

    private String extractCustomDelimiter(String input) {
        int delimiterEndIndex = input.indexOf(CUSTOM_DELIMITER_SUFFIX);

        validateCustomDelimiterFormat(delimiterEndIndex);

        return input.substring(2, delimiterEndIndex);
    }

    private String buildDelimiterPattern(String customDelimiter) {
        StringBuilder sb = new StringBuilder(DEFAULT_DELIMITER);

        validateDelimiterCount(customDelimiter);

        sb.insert(1, Pattern.quote(customDelimiter));

        return sb.toString();

    }

    private void validateCustomDelimiterFormat(int delimiterEndIndex) {
        if (delimiterEndIndex == -1) {
            throw new IllegalArgumentException("커스텀 구분자 지원 형식을 지켜주세요");
        }
    }

    private void validateDelimiterCount(String customDelimiter) {
        if (customDelimiter.length() != 1) {
            throw new IllegalArgumentException("커스텀 구분자는 1개의 문자만 가능합니다.");
        }
    }
}
