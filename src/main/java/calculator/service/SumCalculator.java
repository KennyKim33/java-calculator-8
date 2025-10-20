package calculator.service;

public class SumCalculator {
    private static final String CALCULATE_REGEX = "^[1-9]\\d*$";

    public int sum(String parsedInput) {
        validateOnlyDigits(parsedInput);

        return parsedInput.chars().map(Character::getNumericValue)
                .sum();
    }

    private void validateOnlyDigits(String parsedInput) {
        if (parsedInput.isEmpty() || !parsedInput.matches(CALCULATE_REGEX)) {
            throw new IllegalArgumentException("잘못 입력하였습니다.");
        }
    }
}
