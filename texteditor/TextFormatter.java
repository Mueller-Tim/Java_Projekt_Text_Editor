/**
 * The TextFormatter class is responsible for formatting the paragraphs.
 * It contains the specified maximum characters per line and generates
 * the formatted paragraphs.
 */
package texteditor;

import java.util.ArrayList;

public class TextFormatter {

	private int maxLineLength;

	/**
	 * Default constructor to set maxLineLength wo 0.
	 */
	public TextFormatter() {
		this.maxLineLength = 0;
	}

	/**
	 * Constructor with maxLineLength parameter.
	 * maxLineLength needs to be a positive integer.
	 *
	 * @param maxLineLength
	 */
	public TextFormatter(int maxLineLength) {
		if (maxLineLength >= 0) {
			this.maxLineLength = maxLineLength;
		} else {
			System.out.println("TextFormatter " + maxLineLength + " is not a positive integer for maxLineLength.");
			this.maxLineLength = 0;
		}
	}

	/**
	 * Sets maxLineLength to 0.
	 */
	public void formatRaw() {
		this.maxLineLength = 0;
	}

	/**
	 * Set maxLineLength. Needs to be a positive integer.
	 *
	 * @param maxLineLength
	 */
	public void setMaxLineLength(int maxLineLength) {
		if (maxLineLength >= 0) {
			this.maxLineLength = maxLineLength;
		}
	}
	
	public boolean isTextFormatRaw() {
		if(maxLineLength == 0) {
			return true;
		}else {
			return false;
		}
	}

	/**
	 * Format Paragraphs with maxLineLength or return the given paragraphs.
	 *
	 * @param paragraphs
	 * @return ArrayList<String>
	 */
	public ArrayList<String> formatParagraphs(ArrayList<String> paragraphs) {
		if (this.maxLineLength != 0) {
			return this.generateFormattedParagraphs(paragraphs);
		}else 

		return paragraphs;
	}

	private ArrayList<String> generateFormattedParagraphs(ArrayList<String> paragraphs) {
		ArrayList<String> formattedParagraphs = new ArrayList<>();
		for (String paragraph : paragraphs) {
			formattedParagraphs.addAll(this.generateLines(paragraph));
		}
		return formattedParagraphs;
	}

	private ArrayList<String> generateLines(String paragraph) {
		ArrayList<String> lines = new ArrayList<>();
		String[] words = paragraph.split(" ");
		String newLine = "";

		for (String word : words) {
			if (newLine.length() + word.length() > this.maxLineLength) {
				if (!newLine.isEmpty()) {
					lines.add(newLine.substring(0, newLine.length() - 1));
					newLine = "";
				}
				while (word.length() > this.maxLineLength) {
					lines.add(word.substring(0, this.maxLineLength));
					word = word.substring(this.maxLineLength);
				}
			}
			newLine += word + " ";
		}
		lines.add(newLine.substring(0, newLine.length() - 1));

		return lines;
	}

}
