# Team NULL TextEditor

## Introduction
This is a minimal, cli based text editor, which allows runtime storage and basic editing as well custom formatting of a text.
## Features
### Add text
Content may be added either by providing a custom text using command line by way of the **ADD** command or inserting a predefined text using the **DUMMY** command. Both commands accept an optional parameter, stating at which paragraph number to insert the text. When no parameter is provided the content gets appended at the end.
### Edit text
The **REPLACE** command allows the finding and replacing of a given string. It takes an optional numberic argument stating which paragraph is being edited (default = last paragraph).
### Formatting
You can define how many characters per line are being printed by using the **FORMAT FIX** command. This command requires a numeric argument greater than 0. To clear the formatting settings you may use the **FORMAT RAW** command.
### Output
You can either print the text using the **PRINT** command or view the word index using the command **INDEX**. **PRINT** prints the current state of the text according to the provided formatting settings to the command line. The **INDEX** command counts the words and generates a index of all the words occurring 4 or more times. This index contains a list with these words alongside the paragraph numbers where they occur.
### Quitting
By way of the **EXIT** command you can quit the program. 

## How to run
`javac TextEditor.java` \
`java TextEditor`

## Example Usage
Add a custom text \
`> ADD` \
`: This is a custom Text` 
<br><br>
Insert the predefined text at line 1 \
`> DUMMY 1` 
<br><br>
Set max line number to 20 \
`> FORMAT FIX 20` 
<br><br>
Print the formatted text \
`> Print`

## Contributors
[Tim Müller](https://github.zhaw.ch/muellti3) \
[Raphael Meierhans](https://github.zhaw.ch/meierr06)\
[Ivan Starčević](https://github.zhaw.ch/starciva) \
[Philipp Kiss](https://github.zhaw.ch/kisphi01)

