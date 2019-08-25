package com.gui.desktop_calculator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.text.DecimalFormat;

/**
 * @TODO:
 *    - Добавить действия и команды к контекстное меню.
 *    - Добавить возможность вставлять и копировать результат. (Реализовать методы copy/paste)
 */
public class Controller {

   // Operands
   private Double num1 = 0.0;
   private Double num2 = 0.0;
   private Double result = 0.0;

   // Special chars for displaying results
   private final char MULTIPLY = '×';
   private final char DIVIDE = '÷';
   private final char EQUALS = '=';
   private final char SQUARE_ROOT = '√';

   // To check if buttonEquals was pressed already in this operation.
   private boolean equalsPerformed = false;

   // Check if first operand already inputted.
   private boolean num1Inputted = false;

   // Value with a "space" means that there is no action.
   private char action = ' ';

   private String memory = "0";

   private DecimalFormat decimalFormat = new DecimalFormat("#.#######################################################");
   private ActionEvent keyPressedEvent = new ActionEvent();

   private Model model = new Model();

   /* Texts on display */
   @FXML
   // This field displays input mathematical expression.
   private TextField expressionField;
   @FXML
   // This field displays last input numbers and the calculation results.
   private TextField inputOutputField;
   @FXML
   // This field displays current value saved in memory.
   private TextField memoryField;

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
      // TODO
      StringSelection ss = new StringSelection(inputOutputField.getText());
      Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
      System.out.println("COPY");
   }

   @FXML
   private void pasteFromClipboard() {
      //TODO
      System.out.println("PASTE");
   }

   /** Clear memory */
   @FXML
   private void buttonMC() {
      memory = "0";
      memoryField.setText("");
   }

   /** Get the number from memory field to the input field */
   @FXML
   private void buttonMR() {
      inputOutputField.setText(memory);
      memoryField.setText(memory);
   }

   /** Subtract the entered number from the number in memory */
   @FXML
   private void buttonM_minus() {
      double m = Double.parseDouble(memory);
      double n = Double.parseDouble(inputOutputField.getText());
      memory = decimalFormat.format(m - n);
      memoryField.setText(memory);
   }

   /** Add the entered number to the number in memory */
   @FXML
   private void buttonM_plus() {
      double m = Double.parseDouble(memory);
      double n = Double.parseDouble(inputOutputField.getText());
      memory = decimalFormat.format(m + n);
      memoryField.setText(memory);
   }

   @FXML
   private void buttonPlus(ActionEvent event) {
      saveInput('+');
      updateTextOnDisplay();
   }

   @FXML
   private void buttonMinus(ActionEvent event) {
      saveInput('-');
      updateTextOnDisplay();
   }

   @FXML
   private void buttonMultiply(ActionEvent event) {
      saveInput('*');
      updateTextOnDisplay();
   }

   @FXML
   private void buttonDivide(ActionEvent event) {
      saveInput('/');
      updateTextOnDisplay();
   }

   private void saveInput(char actionMark) {
      // double requires dot symbol, but comma is more common in math,
      // so we will replace them before parsing.
      // Decimal format do it automatically after parsing.
      double temp = Double.parseDouble(inputOutputField.getText().replace(",", "."));

      if (!num1Inputted) {
         num1 = temp;
         num1Inputted = true;
      }
      if (equalsPerformed) {
         num1 = result;
      } else {
         num2 = temp;
      }

      if (actionMark == EQUALS) {
         equalsPerformed = true;
      } else {
         action = actionMark;
      }
   }

   private void updateTextOnDisplay() {
      // Saving input operands to a variable and format them with DecimalFormat.

      StringBuilder expression = new StringBuilder();
      if (num1Inputted) {
         expression.append(decimalFormat.format(num1));
         if (action != ' ' && num1Inputted) {
            if (action == '*') {
               expression.append(" ");
               expression.append(MULTIPLY);
               expression.append(" ");

            } else if (action == '/') {
               expression.append(" ");
               expression.append(DIVIDE);
               expression.append(" ");

            } else {
               expression.append(" ");
               expression.append(action);
               expression.append(" ");
            }
         }
         if (equalsPerformed) {
            if (num2 < 0) {
               expression.append("(");
               expression.append(decimalFormat.format(num2));
               expression.append(")");
            } else {
               expression.append(decimalFormat.format(num2));
            }
         }
      }

      expressionField.setText(expression.toString());
      if (equalsPerformed) {
         inputOutputField.setText(decimalFormat.format(result));
      } else {
         inputOutputField.setText("0");
      }
   }

   @FXML
   private void buttonEquals(ActionEvent event) {
      saveInput('=');
      if (action != ' ') {
         System.out.println(action);
         result = model.performActionEquals(num1, num2, action);
         updateTextOnDisplay();
      }
   }

   @FXML
   private void buttonBackspace(ActionEvent event) {
      // Backspace removes last digit of last input
      if (inputOutputField.getText().length() > 1) {
         inputOutputField.setText(inputOutputField.getText().substring(0, inputOutputField.getText().length() - 1));

      } else {
         inputOutputField.setText("0");
      }
   }

   @FXML
   /**
    * Clear only input field
    */
   private void buttonCE(ActionEvent event) {
      inputOutputField.setText("0");
   }

   @FXML
   /**
    * Clear all fields and variables exept memory
    */
   private void buttonC(ActionEvent event) {
      expressionField.setText("");
      inputOutputField.setText("0");

      num1 = 0.0;
      num2 = 0.0;
      result = 0.0;
      action = ' ';

      num1Inputted = false;
      equalsPerformed = false;
   }

   @FXML
   /**
    * Makes the last input number to negative or positive.
    */
   private void buttonPositiveNegative(ActionEvent event) {
      if (inputOutputField.getText().equals("0")) {
         inputOutputField.setText("-");

      } else if (inputOutputField.getText().equals("-")) {
         inputOutputField.setText("0");

      } else if (inputOutputField.getText().charAt(0) == '-' && inputOutputField.getText().length() > 1) {
         inputOutputField.setText(inputOutputField.getText().substring(1));

      } else {
         inputOutputField.setText("-" + inputOutputField.getText());
      }
   }

   @FXML
   private void buttonPow(ActionEvent event) {
      saveInput('^');
      updateTextOnDisplay();
   }

   @FXML
   /**
    * Changes the last input number to it's square root value.
    */
   private void buttonSquareRoot(ActionEvent event) {
      if (inputOutputField.getText().contains("-")) {
         expressionField.setText(SQUARE_ROOT + inputOutputField.getText());
         inputOutputField.setText("Does not exist");
         return;
      }

      double temp = model.calculateTheSquareRoot(Double.parseDouble(inputOutputField.getText().replace(",", ".")));

      if (num1Inputted && (action != ' ' && !equalsPerformed)) {
         expressionField.setText(expressionField.getText() + SQUARE_ROOT + inputOutputField.getText());
         num2 = temp;
      } else {
         expressionField.setText(SQUARE_ROOT + inputOutputField.getText());
      }

      inputOutputField.setText(decimalFormat.format(temp));
   }

   @FXML
   /**
    * Changes the last input number(num2) to it's percent value of the first number(num1)
    */
   private void buttonPercent(ActionEvent event) {
      if (num1Inputted) {
         double temp = Double.parseDouble(inputOutputField.getText().replace(",", "."));

         num2 = model.calculatePercents(num1, temp);

         expressionField.setText(expressionField.getText() + decimalFormat.format(num2));
         inputOutputField.setText(decimalFormat.format(num2));
      }

   }

   @FXML
   /**
    * Changes the last input number to a result of 1 divided by the last entered number.
    */
   private void buttonFraction(ActionEvent event) {
      if (inputOutputField.getText().equals("0")) {
         expressionField.setText(inputOutputField.getText() + " 1/0");
         inputOutputField.setText("Can not divide by zero");

      } else {
         double temp = model.calculateFraction(Double.parseDouble(inputOutputField.getText().replace(",", ".")));
         expressionField.setText(expressionField.getText() + "1/" + inputOutputField.getText());
         inputOutputField.setText(decimalFormat.format(temp));
      }
   }

   @FXML
   private void buttonComma(ActionEvent event) {
      // If input doesn't yet contains comma, add comma.
      checkWhatActionButtonPressed(event);
      if (!inputOutputField.getText().contains(",")) {
         inputOutputField.setText(inputOutputField.getText() + ",");
      }
   }

   /** Removes unnecessary zeros in front of the first none zero digit. */
   private void removeFirstDigitIfZero() {
      if (inputOutputField.getText().equals("0")) {
         inputOutputField.setText("");
      } else if (inputOutputField.getText().charAt(0) == '0'
              && !inputOutputField.getText().contains(",")
              && inputOutputField.getText().length() > 1) {
         inputOutputField.setText(inputOutputField.getText().substring(1));
         removeFirstDigitIfZero();
      }
   }

   private void checkWhatActionButtonPressed(ActionEvent event) {
      if (equalsPerformed) {
         buttonC(event);
         equalsPerformed = false;
      }
   }

   /* For every numeric button, if button equals was pressed in last operation, clear all and start input next one.*/
   @FXML
   private void button0(ActionEvent event) {
      checkWhatActionButtonPressed(event);
      if (inputOutputField.getText().equals("0") || inputOutputField.getText().equals("-")) {
         inputOutputField.setText("0");
      } else {
         inputOutputField.setText(inputOutputField.getText() + "0");
      }
   }

   @FXML
   private void button1(ActionEvent event) {
      checkWhatActionButtonPressed(event);
      removeFirstDigitIfZero();
      inputOutputField.setText(inputOutputField.getText() + "1");
   }

   @FXML
   private void button2(ActionEvent event) {
      checkWhatActionButtonPressed(event);
      removeFirstDigitIfZero();
      inputOutputField.setText(inputOutputField.getText() + "2");
   }

   @FXML
   private void button3(ActionEvent event) {
      checkWhatActionButtonPressed(event);
      removeFirstDigitIfZero();
      inputOutputField.setText(inputOutputField.getText() + "3");
   }

   @FXML
   private void button4(ActionEvent event) {
      checkWhatActionButtonPressed(event);
      removeFirstDigitIfZero();
      inputOutputField.setText(inputOutputField.getText() + "4");
   }

   @FXML
   private void button5(ActionEvent event) {
      checkWhatActionButtonPressed(event);
      removeFirstDigitIfZero();
      inputOutputField.setText(inputOutputField.getText() + "5");
   }

   @FXML
   private void button6(ActionEvent event) {
      checkWhatActionButtonPressed(event);
      removeFirstDigitIfZero();
      inputOutputField.setText(inputOutputField.getText() + "6");
   }

   @FXML
   private void button7(ActionEvent event) {
      checkWhatActionButtonPressed(event);
      removeFirstDigitIfZero();
      inputOutputField.setText(inputOutputField.getText() + "7");
   }

   @FXML
   private void button8(ActionEvent event) {
      checkWhatActionButtonPressed(event);
      removeFirstDigitIfZero();
      inputOutputField.setText(inputOutputField.getText() + "8");
   }

   @FXML
   private void button9(ActionEvent event) {
      checkWhatActionButtonPressed(event);
      removeFirstDigitIfZero();
      inputOutputField.setText(inputOutputField.getText() + "9");
   }
}
