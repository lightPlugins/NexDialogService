package io.nexstudios.dialogservice.api;

import net.kyori.adventure.text.Component;

/**
 * A request dialog with a text field for text input.
 * 
 * Example: Entering player names, writing messages, etc.
 */
public interface TextRequestDialog extends RequestDialog<String> {
    @Override
    TextRequestDialog title(Component title);

    @Override
    default TextRequestDialog title(String title) {
        return title(Component.text(title));
    }

    /**
     * Sets the body text of the dialog.
     */
    TextRequestDialog body(Component body);

    /**
     * Convenience overload for plain string bodies.
     */
    default TextRequestDialog body(String body) {
        return body(Component.text(body));
    }

    @Override
    default TextRequestDialog description(Component description) {
        return body(description);
    }

    @Override
    default TextRequestDialog description(String description) {
        return description(Component.text(description));
    }

    @Override
    TextRequestDialog submitButton(Component text);

    @Override
    default TextRequestDialog submitButton(String text) {
        return submitButton(Component.text(text));
    }

    /**
     * Sets the placeholder text in the text field.
     */
    TextRequestDialog placeholder(Component placeholder);

    /**
     * Convenience overload for plain string placeholders.
     */
    default TextRequestDialog placeholder(String placeholder) {
        return placeholder(Component.text(placeholder));
    }

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
