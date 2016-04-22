package Calculator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class Controller {

    private String inputText = "";
    private double num1 = 0;
    private double num2 = 0;
    private char action;

    @FXML
    private TextField textField;

    @FXML
    private void keyPressed(KeyEvent keyEvent) {

    }

    @FXML
    private void keyTyped(KeyEvent keyEvent) {

    }

    @FXML
    private void buttonPlus(ActionEvent event) {

    }

    @FXML
    private void buttonMinus(ActionEvent event) {

    }

    @FXML
    private void buttonMultiply(ActionEvent event) {

    }

    @FXML
    private void buttonDivide(ActionEvent event) {

    }

    @FXML
    private void buttonEquals(ActionEvent event) {

    }

    @FXML
    private void buttonBackspace(ActionEvent event) {
        if (inputText.length() > 0) {
            inputText = inputText.substring(0, inputText.length()-1);
            textField.setText(inputText);
        }
    }

    @FXML
    private void buttonCE(ActionEvent event) {

    }

    @FXML
    private void buttonC(ActionEvent event) {

    }

    @FXML
    private void buttonPositiveNegative(ActionEvent event) {

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
        textField.setText(inputText);
    }

    @FXML
    private void button1(ActionEvent event) {
        inputText += "1";
        textField.setText(inputText);
    }

    @FXML
    private void button2(ActionEvent event) {
        inputText += "2";
        textField.setText(inputText);
    }

    @FXML
    private void button3(ActionEvent event) {
        inputText += "3";
        textField.setText(inputText);
    }

    @FXML
    private void button4(ActionEvent event) {
        inputText += "4";
        textField.setText(inputText);
    }

    @FXML
    private void button5(ActionEvent event) {
        inputText += "5";
        textField.setText(inputText);
    }

    @FXML
    private void button6(ActionEvent event) {
        inputText += "6";
        textField.setText(inputText);
    }

    @FXML
    private void button7(ActionEvent event) {
        inputText += "7";
        textField.setText(inputText);
    }

    @FXML
    private void button8(ActionEvent event) {
        inputText += "8";
        textField.setText(inputText);
    }

    @FXML
    private void button9(ActionEvent event) {
        inputText += "9";
        textField.setText(inputText);
    }

    @FXML
    private void buttonComma(ActionEvent event) {
        inputText += ",";
        textField.setText(inputText);
    }
}
