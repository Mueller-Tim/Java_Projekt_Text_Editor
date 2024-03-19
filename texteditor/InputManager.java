package texteditor;

import java.util.Scanner;

/**
 * The InputManager class is responsible for the inputs in the application.
 * It creates a new input with a scanner object and validates the input.
 * It also checks a paragraph input for illegal characters.
 */
public class InputManager {
	private Scanner scanner;
	private final String illegalTextCharsPattern = "[^a-zA-ZäöüÄÖÜ0-9.,:;\\-!?’()\"%@+*\\[\\]{}/&#$\s]";

	/**
	 * Constructor which creates a new scanner object.
	 */
	public InputManager() {
		scanner = new Scanner(System.in);
	}

	
	/**
	 * Creates an input on the console for the command
	 * and builds an UserInput object.
	 * @return user input
	 */
	public UserInput getCommandInput() {
		String input = scanner.nextLine();
		input = eliminateMultipleWhiteSpaces(input);
		return buildUserInput(input.toUpperCase().split(" "));
	}
	
	private String eliminateMultipleWhiteSpaces(String input) {
		String inputFormatted = input.replaceAll("\t", " ").trim().replaceAll(" +", " ");
		return inputFormatted;
	}

	/**
	 * Creates an input on the console for the paragraph text
	 * and filters out illegal characters.
	 * @return paragraph text
	 */
	public String getPlainTextInput() {
		return this.filterIllegalChars(scanner.nextLine());
	}

	private String filterIllegalChars(String text) {
		return text.replaceAll(this.illegalTextCharsPattern, "");
	}

	private UserInput buildUserInput(String[] input) {
		UserInput userInput;
		Integer index;
		switch (input[0]) {
		case "PRINT":
			userInput = new UserInput(Command.PRINT);
			break;
		case "ADD":
			index = validateNumberArgument(input, 1);
			userInput = new UserInput(Command.ADD, index != null ? mapUserIndexToLogicalIndex(index) : null);
			break;
		case "INDEX":
			userInput = new UserInput(Command.INDEX);
			break;
		case "EXIT":
			userInput = new UserInput(Command.EXIT);
			break;
		case "DEL":
			index = validateNumberArgument(input, 1);
			userInput = new UserInput(Command.DEL, index != null ? mapUserIndexToLogicalIndex(index) : null);
			break;
		case "DUMMY":
			index = validateNumberArgument(input, 1);
			userInput = new UserInput(Command.DUMMY, index != null ? mapUserIndexToLogicalIndex(index) : null);
			break;
		case "FORMAT":
			userInput = formatInputValidation(input);
			break;
		case "REPLACE":
			index = validateNumberArgument(input, 1);
			userInput = new UserInput(Command.REPLACE, index != null ? mapUserIndexToLogicalIndex(index) : null);
			break;
		default:
			userInput = new UserInput(Command.ERROR);
		}
		
		return userInput;
	}

	private Integer validateNumberArgument(String[] input, int index) {
		if (hasNthArgument(input, index)) {
			try {
				return Integer.parseInt((input[index]));
			} catch (NumberFormatException e) {
				return null;
			}
		}
		return null;
	}

	private boolean hasNthArgument(String[] input, int index) {
		return input.length >= index+1;
	}
	
	private Integer mapUserIndexToLogicalIndex(Integer userIndex) {
		return userIndex-1;
	}

	private UserInput formatInputValidation(String[] input) {
		if (hasNthArgument(input, 1)) {
			if (input[1].equals("FIX")) {
				return new UserInput(Command.FORMAT_FIX, validateNumberArgument(input, 2));
			} else if (input[1].equals("RAW")) {
				return new UserInput(Command.FORMAT_RAW);
			}
		}
		return new UserInput(Command.ERROR);
	}

	/**
	 * close this objects scanner
	 */
	public void closeScanner() {
		scanner.close();
	}
}
