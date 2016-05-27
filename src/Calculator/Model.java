package Calculator;

public class Model {

    // This method represents the completion of the last action.
    public double performActionEquals(double num1, double num2, char action) {
        // double requires dot symbol, but comma is more common in math,
        // so we will replace them before parsing.
        // Decimal format do it automatically after parsing.
        switch (action) {
            case '+':
                return num1 + num2;
            case '-':
                return num1 - num2;
            case '*':
                return num1 * num2;
            case '/':
                if (num2 == 0) {
                    return 0;
                } else {
                    return num1 / num2;
                }
            default:
                System.out.println("CALCULATION ERROR");
                return -1.999999999999999999999;
        }
    }

    public double calculateTheSquareRoot(double value) {
        return Math.sqrt(value);
    }

    public double calculatePercents(double wholeValue, double percents) {
        return wholeValue / 100 * percents;
    }

    public double calculateFraction(double denominator) {
        return 1 / denominator;
    }
}
