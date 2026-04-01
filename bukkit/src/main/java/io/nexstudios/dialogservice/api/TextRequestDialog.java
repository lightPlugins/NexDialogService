package io.nexstudios.dialogservice.api;

/**
 * A request dialog with a text field for text input.
 * 
 * Example: Entering player names, writing messages, etc.
 */
public interface TextRequestDialog extends RequestDialog<String> {
    @Override
    TextRequestDialog title(String title);

    /**
     * Sets the body text of the dialog.
     */
    TextRequestDialog body(String body);

    @Override
    default TextRequestDialog description(String description) {
        return body(description);
    }

    @Override
    TextRequestDialog submitButton(String text);

    /**
     * Sets the placeholder text in the text field.
     */
    TextRequestDialog placeholder(String placeholder);
    
    /**
     * Sets the initial text in the text field.
     */
    TextRequestDialog initialValue(String initialValue);
    
    /**
     * Sets the maximum number of characters.
     */
    TextRequestDialog maxCharacters(int maxCharacters);
    
    /**
     * Sets the minimum number of characters.
     */
    TextRequestDialog minCharacters(int minCharacters);
}
