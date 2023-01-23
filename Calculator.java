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

  public static String[] tokenize(String input){
    String[] tokens = input.split(" ");
    return tokens;
  }
  public static String extractOperator(String[] tokens){
    String operator = tokens[0];
    return operator; 
  }
  public static boolean shouldQuit(String operator){
    boolean quit = operator.toLowerCase().equals("q");
    return quit; 
  }
  public static Float[] extractNums(String[] tokens){
    Float[] nums = new Float[2];
    nums[0] = Float.parseFloat(tokens[1]);

    if (tokens.length >= 3) {
      nums[1] = Float.parseFloat(tokens[2]);
    } else {
      nums[1]= 0f;
    }
    return nums; 
  }
  
  public static void main(String[] args) {

    while (true) {

      String input = getUserInput("Enter your equation:");
      if (input == null) {

        System.out.println("Please enter an equation.");
        continue;
      }

      String[] tokens = tokenize(input); 
    
      String operator = extractOperator(tokens); 
      if (shouldQuit(operator)) {
        System.out.println("Quitting the program. Goodbye!");
        break;
      }
      Float[] nums;
      try {
        nums = extractNums(tokens);
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
          result = Arithmetic.add(nums[0], nums[1]);
          break;
        
        case "-":
          result = Arithmetic.subtract(nums[0], nums[1]);
          break;

        case "*":
          result = Arithmetic.multiply(nums[0], nums[1]);
          break;

        case "/":
          result = Arithmetic.divide(nums[0], nums[1]);
          break;

        case "%":
          result = Arithmetic.mod(nums[0], nums[1]);
          break;

        case "square":
          result = Arithmetic.square(nums[0]);
          break;

        case "cube":
          result = Arithmetic.cube(nums[0]);
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
