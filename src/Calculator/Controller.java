package Calculator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.text.DecimalFormat;

/*TODO
    * Добавить остальные функции кнопок, кв.корень, проценты и пр.
    * Исправить ошибки, и наладить работу при повторном нажатии на + - * /. Строка 177
    * Сделать, чтобы при нажатии на цифры после вывода результата, сначала затирался этот результат.
    * * */

public class Controller {

    // Texts to display
    private String inputText = "";
    private String storedOperands = "";

    // Operands
    private Double num1 = null;
    private Double num2 = null;
    private Double result = null;

    // Value with one space means that there is no action.
    private char action = ' ';

    private DecimalFormat decimalFormat = new DecimalFormat("#.#######################################################");

    private ActionEvent keyPressedEvent = new ActionEvent();

    @FXML
    private TextField textFieldResults;

    @FXML
    private TextField textFieldInput;

    @FXML
    private void keyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case BACK_SPACE:
                buttonBackspace(keyPressedEvent);
                break;
            case DELETE:
                buttonCE(keyPressedEvent);
                break;
            case ENTER:
                buttonEquals(keyPressedEvent);
                break;
            case ESCAPE:
                buttonC(keyPressedEvent);
                break;
        }
    }

    @FXML
    private void keyTyped(KeyEvent keyEvent) {
        switch (keyEvent.getCharacter()) {
            case "0":
                button0(keyPressedEvent);
                break;
            case "1":
                button1(keyPressedEvent);
                break;
            case "2":
                button2(keyPressedEvent);
                break;
            case "3":
                button3(keyPressedEvent);
                break;
            case "4":
                button4(keyPressedEvent);
                break;
            case "5":
                button5(keyPressedEvent);
                break;
            case "6":
                button6(keyPressedEvent);
                break;
            case "7":
                button7(keyPressedEvent);
                break;
            case "8":
                button8(keyPressedEvent);
                break;
            case "9":
                button9(keyPressedEvent);
                break;
            case ",":
                buttonComma(keyPressedEvent);
                break;
            case ".":
                buttonComma(keyPressedEvent);
                break;
            case "+":
                buttonPlus(keyPressedEvent);
                break;
            case "-":
                buttonMinus(keyPressedEvent);
                break;
            case "*":
                buttonMultiply(keyPressedEvent);
                break;
            case "/":
                buttonDivide(keyPressedEvent);
                break;
            case "=":
                buttonEquals(keyPressedEvent);
                break;
            case "r":
                buttonFraction(keyPressedEvent);
                break;
            case "s":
                buttonSquareRoot(keyPressedEvent);
                break;
            case "p":
                buttonPercent(keyPressedEvent);
                break;
            case "n":
                buttonPositiveNegative(keyPressedEvent);
                break;
            case "e":
                buttonEquals(keyPressedEvent);
                break;
            case "c":
                buttonC(keyPressedEvent);
                break;
            case "d":
                buttonCE(keyPressedEvent);
                break;
            default:
                break;
        }
    }

    @FXML
    private void copyToClipboard(ActionEvent event) {
        StringSelection ss = new StringSelection(inputText);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
        System.out.println("COPY");
    }

    @FXML
    private void pasteFromClipboard() {
        System.out.println("PASTE");
    }

    @FXML
    private void buttonPlus(ActionEvent event) {
        action = '+';
        saveNum1AndUpdateText();
    }

    @FXML
    private void buttonMinus(ActionEvent event) {
        if (inputText.isEmpty() || inputText.equals("0") || inputText.equals("-")) {
            buttonPositiveNegative(event);
        } else {
            action = '-';
            saveNum1AndUpdateText();
        }
    }

    @FXML
    private void buttonMultiply(ActionEvent event) {
        action = '*';
        saveNum1AndUpdateText();
    }

    @FXML
    private void buttonDivide(ActionEvent event) {
        action = '/';
        saveNum1AndUpdateText();
    }

    private void saveNum1AndUpdateText() {
        if (!inputText.isEmpty()) {
            // Replace the commas to dots for parsing, and vice versa.
            inputText = inputText.replace(",", ".");
            num1 = Double.parseDouble(inputText);
            inputText = inputText.replace(".", ",");

            // Saving input operands to a variable to switch dots&commas.
            storedOperands = (decimalFormat.format(num1) + " " + action).replace('.', ',');
            textFieldInput.setText(storedOperands);
            inputText = "";
            textFieldResults.setText("0");
        }
    }

    @FXML
    private void buttonEquals(ActionEvent event) {
        // double requires dot symbol, but comma is more common in math,
        // so we will replace them before and after parsing.
        if (action != ' ' && !inputText.isEmpty()) {
            inputText = inputText.replaceAll(",", ".");

            if (result != null) {
                num1 = result;
            } else {
                num2 = Double.parseDouble(inputText);
            }

            switch (action) {
                case '+':
                    result = num1 + num2;
                    inputText = decimalFormat.format(result);
                    break;
                case '-':
                    result = num1 - num2;
                    inputText = decimalFormat.format(result);
                    break;
                case '*':
                    result = num1 * num2;
                    inputText = decimalFormat.format(result);
                    break;
                case '/':
                    result = num1 / num2;
                    inputText = decimalFormat.format(result);
                    break;

            }
            inputText = inputText.replace('.', ',');
            storedOperands = (decimalFormat.format(num1) + " " + action + " " + decimalFormat.format(num2)).replace('.', ',');
            textFieldInput.setText(storedOperands);
            textFieldResults.setText(inputText);
        }
    }

    @FXML
    private void buttonBackspace(ActionEvent event) {
        // Backspace, works as it should, removes last digit of last input
        if (inputText.length() > 0) {
            inputText = inputText.substring(0, inputText.length() - 1);
            if (inputText.isEmpty()) {
                textFieldResults.setText("0");
            } else {
                textFieldResults.setText(inputText);
            }
        }
    }

    @FXML
    private void buttonCE(ActionEvent event) {
        // "CE" button removes only last input text.
        inputText = "";
        textFieldResults.setText("0");
    }

    @FXML
    private void buttonC(ActionEvent event) {
        // "C" button removes all input data.
        inputText = "";
        num1 = null;
        num2 = null;
        result = null;
        action = ' ';
        textFieldInput.setText(inputText);
        textFieldResults.setText("0");
    }

    @FXML
    private void buttonPositiveNegative(ActionEvent event) {
        // This button makes last input number to negative or positive.
        if (inputText.equals("0") || inputText.isEmpty()) {
            inputText = "-";
            textFieldResults.setText(inputText);

        } else if (inputText.equals("-")) {
            inputText = "0";
            textFieldResults.setText(inputText);

        } else if (inputText.charAt(0) == '-' && inputText.length() > 1) {
            inputText = inputText.substring(1);
            textFieldResults.setText(inputText);

        } else {
            inputText = "-" + inputText;
            textFieldResults.setText(inputText);
        }
    }

    @FXML
    private void buttonSquareRoot(ActionEvent event) {
        // This button changes the last input number to it's square root.
        if (!inputText.isEmpty() || !inputText.equals("0")) {
            storedOperands = " √" + inputText;
            inputText = inputText.replaceAll(",", ".");

            double temp = Math.sqrt(Double.parseDouble(inputText));

            inputText = "" + temp;
            if (inputText.equals("NaN")) {
                textFieldInput.setText(storedOperands);
                textFieldResults.setText("Does not exist");
            } else {
                inputText = decimalFormat.format(temp);
                if (num1 != null) {
                    textFieldInput.setText(textFieldInput.getText() + storedOperands);
                } else {
                    textFieldInput.setText(storedOperands);
                }
                textFieldResults.setText(inputText);
            }
        }
    }

    @FXML
    private void buttonPercent(ActionEvent event) {
        // This button changes the last input number to it'
    }

    @FXML
    private void buttonFraction(ActionEvent event) {

    }

    @FXML
    private void buttonComma(ActionEvent event) {
        if (inputText.isEmpty()) {
            inputText += "0,";
            textFieldResults.setText(inputText);
        } else if (!inputText.contains(",")) {
            inputText += ",";
            textFieldResults.setText(inputText);
        }
    }

    @FXML
    private void button0(ActionEvent event) {
        if (inputText.isEmpty() || inputText.equals("0")) {
            inputText = "0";
            textFieldResults.setText(inputText);
        } else {
            inputText += "0";
            textFieldResults.setText(inputText);
        }
    }

    private void removeFirstDigitIfZero() {
        if (!inputText.isEmpty() && inputText.charAt(0) == '0' && !inputText.contains(",")) {
            inputText = inputText.substring(1);
            removeFirstDigitIfZero();
        }
    }

    @FXML
    private void button1(ActionEvent event) {
        removeFirstDigitIfZero();
        inputText += "1";
        textFieldResults.setText(inputText);
    }

    @FXML
    private void button2(ActionEvent event) {
        removeFirstDigitIfZero();
        inputText += "2";
        textFieldResults.setText(inputText);
    }

    @FXML
    private void button3(ActionEvent event) {
        removeFirstDigitIfZero();
        inputText += "3";
        textFieldResults.setText(inputText);
    }

    @FXML
    private void button4(ActionEvent event) {
        removeFirstDigitIfZero();
        inputText += "4";
        textFieldResults.setText(inputText);
    }

    @FXML
    private void button5(ActionEvent event) {
        removeFirstDigitIfZero();
        inputText += "5";
        textFieldResults.setText(inputText);
    }

    @FXML
    private void button6(ActionEvent event) {
        removeFirstDigitIfZero();
        inputText += "6";
        textFieldResults.setText(inputText);
    }

    @FXML
    private void button7(ActionEvent event) {
        removeFirstDigitIfZero();
        inputText += "7";
        textFieldResults.setText(inputText);
    }

    @FXML
    private void button8(ActionEvent event) {
        removeFirstDigitIfZero();
        inputText += "8";
        textFieldResults.setText(inputText);
    }

    @FXML
    private void button9(ActionEvent event) {
        removeFirstDigitIfZero();
        inputText += "9";
        textFieldResults.setText(inputText);
    }
}
