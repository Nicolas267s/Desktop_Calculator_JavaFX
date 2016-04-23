package Calculator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

import java.awt.*;
import java.awt.datatransfer.StringSelection;

public class Controller {

    private String inputText = "";
    private double num1 = 0;
    private double num2 = 0;
    private char action;

    /*TODO
    * Сделать так чтобы все цифры отображались с запятыми вместо точек.
    * Добавить остальные функции кнопок, кв.корень, проценты и пр.
    * */

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
        num1 = Double.parseDouble(inputText);
        textFieldInput.setText(num1 + " " + action);
        inputText = "";
        textFieldResults.setText(inputText);
    }

    @FXML
    private void buttonMinus(ActionEvent event) {
        action = '-';
        num1 = Double.parseDouble(inputText);
        inputText = "";
        textFieldResults.setText(inputText);
    }

    @FXML
    private void buttonMultiply(ActionEvent event) {
        action = '*';
        num1 = Double.parseDouble(inputText);
        inputText = "";
        textFieldResults.setText(inputText);
    }

    @FXML
    private void buttonDivide(ActionEvent event) {
        action = '/';
        num1 = Double.parseDouble(inputText);
        inputText = "";
        textFieldResults.setText(inputText);
    }

    @FXML
    private void buttonEquals(ActionEvent event) {
        // double requires dot symbol, but comma is more common in math,
        // so we will replace them before and after parsing.
        if (action != ' ') {
            inputText.replace(',', '.');

            if (action == '+') {
                num2 = Double.parseDouble(inputText);
                inputText = (num1 + num2) + "";

            } else if (action == '-') {
                num2 = Double.parseDouble(inputText);
                inputText = (num1 - num2) + "";

            } else if (action == '*') {
                num2 = Double.parseDouble(inputText);
                inputText = (num1 * num2) + "";

            } else if (action == '/') {
                num2 = Double.parseDouble(inputText);
                inputText = (num1 / num2) + "";
            }
            textFieldInput.setText((num1 + " " + action + " " + num2).replace('.', ','));
            textFieldResults.setText(inputText.replace('.', ','));
        }
    }

    @FXML
    private void buttonBackspace(ActionEvent event) {
        // Backspace, as it should, removes last digit of last input
        if (inputText.length() > 0) {
            inputText = inputText.substring(0, inputText.length() - 1);
            textFieldResults.setText(inputText);
        }
    }

    @FXML
    private void buttonCE(ActionEvent event) {
        // "CE" button removes only last input text.
        inputText = "";
        textFieldResults.setText(inputText);
    }

    @FXML
    private void buttonC(ActionEvent event) {
        // "C" button removes all input data.
        inputText = "";
        num1 = 0;
        num2 = 0;
        action = ' ';
        textFieldInput.setText(inputText);
        textFieldResults.setText(inputText);
    }

    @FXML
    private void buttonPositiveNegative(ActionEvent event) {
        // If last input is a second number, copy value to the second number and make it negative.
        // 12 * (+/-) == 12 * (-12)
        if (inputText.isEmpty() && num1 != 0) {
            num2 = num1 * -1;
        } else {
            inputText = "-" + inputText;
        }
    }

    @FXML
    private void buttonSquareRoot(ActionEvent event) {

    }

    @FXML
    private void buttonPercent(ActionEvent event) {

    }

    @FXML
    private void buttonFraction(ActionEvent event) {

    }

    @FXML
    private void button0(ActionEvent event) {
        inputText += "0";
        textFieldResults.setText(inputText);
    }

    @FXML
    private void button1(ActionEvent event) {
        inputText += "1";
        textFieldResults.setText(inputText);
    }

    @FXML
    private void button2(ActionEvent event) {
        inputText += "2";
        textFieldResults.setText(inputText);
    }

    @FXML
    private void button3(ActionEvent event) {
        inputText += "3";
        textFieldResults.setText(inputText);
    }

    @FXML
    private void button4(ActionEvent event) {
        inputText += "4";
        textFieldResults.setText(inputText);
    }

    @FXML
    private void button5(ActionEvent event) {
        inputText += "5";
        textFieldResults.setText(inputText);
    }

    @FXML
    private void button6(ActionEvent event) {
        inputText += "6";
        textFieldResults.setText(inputText);
    }

    @FXML
    private void button7(ActionEvent event) {
        inputText += "7";
        textFieldResults.setText(inputText);
    }

    @FXML
    private void button8(ActionEvent event) {
        inputText += "8";
        textFieldResults.setText(inputText);
    }

    @FXML
    private void button9(ActionEvent event) {
        inputText += "9";
        textFieldResults.setText(inputText);
    }

    @FXML
    private void buttonComma(ActionEvent event) {
        if (!inputText.contains(",")) {
            inputText += ",";
            textFieldResults.setText(inputText);
        }
    }
}
