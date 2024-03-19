package texteditor;

/**
 * The UserInput class is responsible for user inputs.
 * It extracts the command and paragraph number of inputs.
 */
public class UserInput {
	private Command command;
	private Integer argumentNumber;
	private String paragraph;

	/**
	 * Constructor with command and paragraphNumber as parameters.
	 *
	 * @param command
	 * @param paragraphNumber
	 */
	public UserInput(Command command, Integer paragraphNumber) {
		this.command = command;
		this.argumentNumber = paragraphNumber;
	}

	/**
	 * Constructor with command and paragraph as parameters.
	 *
	 * @param command
	 * @param paragraph
	 */
	public UserInput(Command command, String paragraph) {
		this.command = command;
		this.paragraph = paragraph;
	}

	/**
	 * Constructor with command, paragraph and paragraphNumber as parameters.
	 *
	 * @param command
	 * @param paragraph
	 * @param paragraphNumber
	 */
	public UserInput(Command command, String paragraph, Integer paragraphNumber) {
		this.command = command;
		this.paragraph = paragraph;
		this.argumentNumber = paragraphNumber;
	}

	/**
	 * Constructor with command as parameter.
	 *
	 * @param command
	 */
	public UserInput(Command command) {
		this.command = command;
	}

	/**
	 * Getter for command.
	 *
	 * @return
	 */
	public Command getCommand() {
		return command;
	}

	/**
	 * Getter for argumentNumber.
	 *
	 * @return
	 */
	public Integer getArgumentNumber() {
		return argumentNumber;
	}

	/**
	 * Getter for paragraph.
	 *
	 * @return
	 */
	public String getParagraph() {
		return paragraph;
	}
}
