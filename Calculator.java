import java.io.*;

/** Methods for performing arithmetic calculations. */
class Arithmetic {
  public static float add(float num1, float num2) {
    return num1 + num2;
  }
  public static float subtract(float num1, float num2) {
    return num1 - num2;
  }
  public static float multiply(float num1, float num2) {
    return num1 * num2;
  }
  public static float divide(float num1, float num2) {
    return num1 / num2;
  }
  public static float mod(float num1, float num2) {
    return num1 % num2;
  }
  public static float square(float num1) {
    return num1*num1;
  }
  public static float cube(float num1) {
    return num1*num1*num1;
  }
}

/** The calculator program. */
public class Calculator {
  public static void main(String[] args) {
    // main while loop that drives the calculator 
    while (true) {
      // prompt user to enter equation
      String input = getUserInput("Enter your equation:");
      if (input == null) {
        // if user doesn't input anything, asks to enter again
        System.out.println("Please enter an equation.");
        continue;
      }
      // splits "enter your equation" input into variable tokens
      class helperFunctions{
        public static String[] tokenize(String input){
          String[] tokens = input.split(" ");
          return tokens;
        }
        public static String extractOperator(String[] tokens){
          String operator = tokens[0];
          return operator; 
        }
      }
      String[] tokens = input.split(" ");
      // @ [0] if equation is q, will quit the program 
      String operator = tokens[0];
      if (operator.toLowerCase().equals("q")) {
        System.out.println("Quitting the program. Goodbye!");
        break;
      }
      // recognizing first and second numbers as floats
      Float num1, num2;
      try {
        // grabs first number 
        num1 = Float.parseFloat(tokens[1]);
        // grabs second number 
        if (tokens.length >= 3) {
          num2 = Float.parseFloat(tokens[2]);
        } else {
          num2 = 0f;
        }
      } catch (ArrayIndexOutOfBoundsException e) {
        System.out.println("Error: enter at least 1 number.");
        continue;
      } catch (NumberFormatException e) {
        System.out.println("Error: not able to parse the numbers you entered.");
        continue;
      }

      Float result;
      switch (operator) {
        case "+":
          result = Arithmetic.add(num1, num2);
          break;
        
        case "-":
          result = Arithmetic.subtract(num1, num2);
          break;

        case "*":
          result = Arithmetic.multiply(num1, num2);
          break;

        case "/":
          result = Arithmetic.divide(num1, num2);
          break;

        case "%":
          result = Arithmetic.mod(num1, num2);
          break;

        case "square":
          result = Arithmetic.square(num1);
          break;

        case "cube":
          result = Arithmetic.cube(num1);
          break;

        default:
          result = null;
          break;
      }

      if (result == null) {
        System.out.println("Invalid operator.");
      } else {
        System.out.println("=> " + result);
      }
    }
  }

  /** Works exactly like Python's input() function. */
  static String getUserInput(String prompt) {
    String inputLine = null;
    System.out.print(prompt + " ");
    try {
      BufferedReader is = new BufferedReader(new InputStreamReader(System.in));
      inputLine = is.readLine();
      if (inputLine.length() == 0) {
        return null;
      }
    } catch (IOException e) {
      System.out.println("IOException: " + e);
    }
    return inputLine;
  }
}
