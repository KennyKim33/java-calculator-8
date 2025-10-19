package calculator.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SumCalculatorTest {
    private SumCalculator sumCalculator;

    @BeforeEach
    void setUp() {
        sumCalculator = new SumCalculator();
    }

    @Test
    void 정상_합계() {
        String input = "123";

        int result = sumCalculator.sum(input);

        assertThat(result).isEqualTo(6);
    }

    @Test
    void 단일_숫자_합계() {
        String input = "5";

        int result = sumCalculator.sum(input);

        assertThat(result).isEqualTo(5);
    }

    @Test
    void 빈_문자열_예외() {
        String input = "";

        assertThatThrownBy(() -> sumCalculator.sum(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("잘못 입력하였습니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"1a3", "abc", "12:3", "1;2", "1+2", "1.2", "1 2", "//1"})
    void 숫자_외_문자_포함_예외(String input) {

        assertThatThrownBy(() -> sumCalculator.sum(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("잘못 입력하였습니다.");

    }

}