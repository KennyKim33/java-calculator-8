package calculator.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class DelimiterParserTest {
    private DelimiterParser delimiterParser;

    @BeforeEach
    void setUp() {
        delimiterParser = new DelimiterParser();
    }

    @Test
    void 정상_기본_구분자_파싱_테스트() {
        String input = "1:2;3";

        String parsed = delimiterParser.parse(input);

        assertThat(parsed).isEqualTo("123");
    }

    @Test
    void 정상_커스텀_구분자_파싱_테스트() {
        String input = "//+\\n1+2:3";

        String parsed = delimiterParser.parse(input);

        assertThat(parsed).isEqualTo("123");
    }

    @Test
    void 커스텀_구분자_형식_오류() {
        String input = "//+1+2+3";

        assertThatThrownBy(() -> delimiterParser.parse(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("커스텀 구분자 지원 형식을 지켜주세요");
    }

    @Test
    void 커스텀_구분자_개수_오류_2개_이상() {
        String input = "//++\\n1++2++3";

        assertThatThrownBy(() -> delimiterParser.parse(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("커스텀 구분자는 1개의 문자만 가능합니다.");
    }

    @Test
    void 커스텀_구분자_개수_오류_0개() {
        String input = "//\\n1:2:3";

        assertThatThrownBy(() -> delimiterParser.parse(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("커스텀 구분자는 1개의 문자만 가능합니다.");
    }


    @Test
    void 커스텀_구분자와_기본_구분자_혼용() {
        String input = "//!\\n1!2:3;4";

        String parsed = delimiterParser.parse(input);

        assertThat(parsed).isEqualTo("1234");
    }
}