package texteditor;

import java.util.ArrayList;

/**
 * The TextEditor class is the core of the application. It contains the main
 * method and starts the application. The class is responsible for executing the
 * functions of the different classes by processing the commands of user inputs.
 */
public class TextEditor {
	private ArrayList<String> paragraphs;
	private InputManager inputManager;
	private OutputManager outputManager;
	private TextProcessor textProcessor;
	private TextFormatter textFormatter;

	/**
	 * Default constructor which creates a new paragraphs list and an object for
	 * every data field.
	 */
	public TextEditor() {
		paragraphs = new ArrayList<>();
		inputManager = new InputManager();
		outputManager = new OutputManager();
		textProcessor = new TextProcessor();
		textFormatter = new TextFormatter();
	}

	/**
	 * Main method to start the application
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		TextEditor editor = new TextEditor();
		editor.start();
	}

	private void start() {
		outputManager.printGreeting();
		handleInput();
	}

	private void handleInput() {
		outputManager.printInputRequest();
		processInput(inputManager.getCommandInput());
	}

	private void processInput(UserInput userInput) {
		switch (userInput.getCommand()) {
		case PRINT:
			if (textFormatter.isTextFormatRaw()) {
				outputManager.printParagraphsRaw(this.paragraphs);
			} else {
				outputManager.printParagraphsFixed(textFormatter.formatParagraphs(paragraphs));
			}
			break;
		case DEL:
			deleteParagraph(userInput);
			break;
		case ADD:
			addParagraph(userInput);
			break;
		case DUMMY:
			addDummyText(userInput);
			break;
		case FORMAT_RAW:
			textFormatter.formatRaw();
			break;
		case FORMAT_FIX:
			setFormatting(userInput);
			break;
		case INDEX:
			outputManager.printIndex(textProcessor.getWordIndex(paragraphs));
			break;
		case REPLACE:
			replaceWord(userInput);
			break;
		case ERROR:
			outputManager.printInvalidCommandError();
			break;
		case EXIT:
			quitProgram();
			break;
		default:
			outputManager.printInvalidCommandError();
			break;
		}
		handleInput();
	}

	private void replaceWord(UserInput userInput) {
		if (paragraphs.size() > 0) {
			if (isProcessingNumberValid(userInput.getArgumentNumber())) {
				outputManager.printReplaceeRequest();
				String replacee = inputManager.getPlainTextInput();
				if (!replacee.isEmpty()) {
					outputManager.printReplacementRequest();
					String replacement = inputManager.getPlainTextInput();
					textProcessor.replaceString(paragraphs, getValidProcessingNumber(userInput.getArgumentNumber()),
							replacee, replacement);
				} else {
					outputManager.printInvalidArgumentError();
				}
			} else {
				outputManager.printInvalidArgumentError();
			}
		} else {
			outputManager.printNothingToReplaceError();
		}
	}

	private void setFormatting(UserInput userInput) {
		if (userInput.getArgumentNumber() != null && userInput.getArgumentNumber() > 0) {
			textFormatter.setMaxLineLength(userInput.getArgumentNumber());
		} else {
			outputManager.printInvalidArgumentError();
		}
	}

	private void deleteParagraph(UserInput userInput) {
		if (isProcessingNumberValid(userInput.getArgumentNumber())) {
			paragraphs = textProcessor.deleteParagraph(paragraphs,
					getValidProcessingNumber(userInput.getArgumentNumber()));
		} else {
			outputManager.printInvalidArgumentError();
		}
	}

	private void addParagraph(UserInput userInput) {
		if (isInsertionNumberValid(userInput.getArgumentNumber())) {
			outputManager.printArgumentRequest();
			textProcessor.addParagraph(paragraphs, getValidInsertionNumber(userInput.getArgumentNumber()),
					inputManager.getPlainTextInput());
		} else {
			outputManager.printInvalidArgumentError();
		}
	}

	private void addDummyText(UserInput userInput) {
		if (isInsertionNumberValid(userInput.getArgumentNumber())) {
			textProcessor.addDummyParagraph(paragraphs, getValidInsertionNumber(userInput.getArgumentNumber()));
		} else {
			outputManager.printInvalidArgumentError();
		}
	}

	private int getValidProcessingNumber(Integer paragraphNumber) {
		return paragraphNumber != null ? paragraphNumber : paragraphs.size() - 1;
	}

	private int getValidInsertionNumber(Integer paragraphNumber) {
		return paragraphNumber != null ? paragraphNumber : paragraphs.size();
	}

	private boolean isProcessingNumberValid(Integer argumentNumber) {
		if (argumentNumber == null) {
			return !paragraphs.isEmpty();
		} else {
			return argumentNumber < paragraphs.size() && argumentNumber >= 0;
		}
	}

	private boolean isInsertionNumberValid(Integer argumentNumber) {
		return argumentNumber == null || (argumentNumber <= paragraphs.size() && argumentNumber >= 0);
	}

	private void quitProgram() {
		outputManager.printExitMessage();
		inputManager.closeScanner();
		System.exit(0);
	}
}
