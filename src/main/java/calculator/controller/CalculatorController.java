package calculator.controller;

import calculator.service.DelimiterParser;
import calculator.service.SumCalculator;
import calculator.view.InputView;
import calculator.view.OutputView;

public class CalculatorController {
    private final DelimiterParser delimiterParser;
    private final SumCalculator sumCalculator;

    private CalculatorController(DelimiterParser delimiterParser, SumCalculator sumCalculator) {
        this.delimiterParser = delimiterParser;
        this.sumCalculator = sumCalculator;
    }

    public static CalculatorController of() {
        DelimiterParser parser = new DelimiterParser();
        SumCalculator calculator = new SumCalculator();
        return new CalculatorController(parser, calculator);
    }

    public void run() {
        String input = InputView.requestInput();
        String parsed = delimiterParser.parse(input);
        int sum = sumCalculator.sum(parsed);
        OutputView.showResult(sum);
    }
}
