package Calculator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.text.DecimalFormat;

/*TODO
    * Добавить действия и команды к контекстное меню.
    * Переименовать некоторые методы и поля.
    * Добавить возможность вставлять и копировать результат. Реализовать методы copy/paste.
    * Пересмотреть архитектуру методов связанных с знаками.
    * Исправить: При нажатии на знак( + - * / ), сразу после первой операции, меняется знак первой операции.
    *           Это происходит потому что знак заменяется раньше, чем выполняется метод actionEquals
    */

public class Controller {

    // Texts to display
    private String inputText = "";
    private String storedOperands = "";

    // Operands
    private Double num1 = null;
    private Double num2 = null;
    private Double result = null;

    // To check if buttonEquals was pressed already in this operation.
    private boolean equalsPressed = false;

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
        action = '-';
        saveNum1AndUpdateText();
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
        if (inputText.isEmpty()) {
            inputText = "0";
        }
        // Replace the commas to dots for parsing.
        inputText = inputText.replace(",", ".");
        if (num1 == null) {
            num1 = Double.parseDouble(inputText);
            // Saving input operands to a variable and format them with DecimalFormat.
            storedOperands = (decimalFormat.format(num1) + " " + action);
            textFieldInput.setText(storedOperands);
            inputText = "0";
            textFieldResults.setText("0");
        } else {
            actionEquals();
            storedOperands = (decimalFormat.format(result) + " " + action);
            textFieldInput.setText(storedOperands);
            inputText = "0";
            textFieldResults.setText("0");
            num2 = null;
        }

    }

    // This method represents the completion of the last action.
    public void actionEquals() {
        if (result != null) {
            num1 = result;
            if (num2 == null) {
                num2 = Double.parseDouble(inputText);
            }
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
                if (num2 == 0) {
                    inputText = "0";
                } else {
                    result = num1 * num2;
                    inputText = decimalFormat.format(result);
                }
                break;
            case '/':
                if (num2 == 0) {
                    inputText = "Can not divide by zero";
                } else {
                    result = num1 / num2;
                    inputText = decimalFormat.format(result);
                }
                break;
        }
    }

    @FXML
    private void buttonEquals(ActionEvent event) {
        // double requires dot symbol, but comma is more common in math,
        // so we will replace them before parsing.
        // Decimal format do it automatically after parsing.
        if (action != ' ' && !inputText.isEmpty()) {
            inputText = inputText.replaceAll(",", ".");

            actionEquals();

            storedOperands = (decimalFormat.format(num1) + " " + action + " " + decimalFormat.format(num2));
            textFieldInput.setText(storedOperands);
            textFieldResults.setText(inputText);
            equalsPressed = true;
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
        textFieldInput.setText("");
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
        if (inputText.isEmpty()) {
            inputText = "0";
        } else if (inputText.equals("-")) {
            textFieldInput.setText("√-");
            textFieldResults.setText("Does not exist");
            return;
        }

        inputText = inputText.replaceAll(",", ".");
        double temp = Double.parseDouble(inputText);
        inputText = "" + decimalFormat.format(Math.sqrt(temp));

        if (num1 != null && action != ' ') {
            storedOperands = decimalFormat.format(num1) + " " + action + " √" + decimalFormat.format(temp);
            num2 = Math.sqrt(temp);
        } else {
            storedOperands = "√" + decimalFormat.format(temp);
        }

        if (inputText.equals("NaN") || temp < 0) {
            textFieldInput.setText(storedOperands);
            textFieldResults.setText("Does not exist");

        } else {
            textFieldInput.setText(storedOperands);
            textFieldResults.setText(inputText);
        }

    }

    @FXML
    private void buttonPercent(ActionEvent event) {
        // This button changes the last input number to
        // it's percent value of the first number(num1)
        if (!inputText.isEmpty() || !inputText.equals("0")) {
            if (num1 != null) {
                inputText = inputText.replaceAll(",", ".");
                double temp = Double.parseDouble(inputText);

                storedOperands = textFieldInput.getText() + " " + inputText + "%";
                inputText = "" + decimalFormat.format(num1 / 100 * temp);

                textFieldInput.setText(storedOperands);
                textFieldResults.setText(inputText);
            }
        }
    }

    @FXML
    private void buttonFraction(ActionEvent event) {
        // This button changes the last input number to
        // a result of 1 divided by the last entered number.
        if (!inputText.isEmpty() && !inputText.equals("0")) {
            storedOperands = " 1/" + inputText;
            inputText = inputText.replaceAll(",", ".");

            double temp = 1.0 / (Double.parseDouble(inputText));
            inputText = decimalFormat.format(temp);

            if (num1 != null) {
                textFieldInput.setText(textFieldInput.getText() + storedOperands);
            } else {
                textFieldInput.setText(storedOperands);
            }
            textFieldResults.setText(inputText);

        } else if (inputText.isEmpty() || inputText.equals("0")) {
            if (num1 != null) {
                textFieldInput.setText(textFieldInput.getText() + " 1/0");
            } else {
                textFieldInput.setText(" 1/0");
            }
            textFieldResults.setText("Can not divide by zero");
        }
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

    // This method removes unnecessary zeros in front of the first none zero digit.
    private void removeFirstDigitIfZero() {
        if (!inputText.isEmpty() && inputText.charAt(0) == '0' && !inputText.contains(",")) {
            inputText = inputText.substring(1);
            removeFirstDigitIfZero();
        }
    }

    /* For every numeric button, if button equals was pressed in last operation, clear all and start input next one.*/
    @FXML
    private void button0(ActionEvent event) {
        if (equalsPressed) {
            buttonC(event);
            equalsPressed = false;
        }
        if (inputText.isEmpty() || inputText.equals("0") || inputText.equals("-")) {
            inputText = "0";
            textFieldResults.setText(inputText);
        } else {
            inputText += "0";
            textFieldResults.setText(inputText);
        }
    }

    @FXML
    private void button1(ActionEvent event) {
        if (equalsPressed) {
            buttonC(event);
            equalsPressed = false;
        }
        removeFirstDigitIfZero();
        inputText += "1";
        textFieldResults.setText(inputText);
    }

    @FXML
    private void button2(ActionEvent event) {
        if (equalsPressed) {
            buttonC(event);
            equalsPressed = false;
        }
        removeFirstDigitIfZero();
        inputText += "2";
        textFieldResults.setText(inputText);
    }

    @FXML
    private void button3(ActionEvent event) {
        if (equalsPressed) {
            buttonC(event);
            equalsPressed = false;
        }
        removeFirstDigitIfZero();
        inputText += "3";
        textFieldResults.setText(inputText);
    }

    @FXML
    private void button4(ActionEvent event) {
        if (equalsPressed) {
            buttonC(event);
            equalsPressed = false;
        }
        removeFirstDigitIfZero();
        inputText += "4";
        textFieldResults.setText(inputText);
    }

    @FXML
    private void button5(ActionEvent event) {
        if (equalsPressed) {
            buttonC(event);
            equalsPressed = false;
        }
        removeFirstDigitIfZero();
        inputText += "5";
        textFieldResults.setText(inputText);
    }

    @FXML
    private void button6(ActionEvent event) {
        if (equalsPressed) {
            buttonC(event);
            equalsPressed = false;
        }
        removeFirstDigitIfZero();
        inputText += "6";
        textFieldResults.setText(inputText);
    }

    @FXML
    private void button7(ActionEvent event) {
        if (equalsPressed) {
            buttonC(event);
            equalsPressed = false;
        }
        removeFirstDigitIfZero();
        inputText += "7";
        textFieldResults.setText(inputText);
    }

    @FXML
    private void button8(ActionEvent event) {
        if (equalsPressed) {
            buttonC(event);
            equalsPressed = false;
        }
        removeFirstDigitIfZero();
        inputText += "8";
        textFieldResults.setText(inputText);
    }

    @FXML
    private void button9(ActionEvent event) {
        if (equalsPressed) {
            buttonC(event);
            equalsPressed = false;
        }
        removeFirstDigitIfZero();
        inputText += "9";
        textFieldResults.setText(inputText);
    }
}
