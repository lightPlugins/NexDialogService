package io.nexstudios.dialogservice.api;

import net.kyori.adventure.text.Component;

/**
 * A request dialog with checkboxes for multiple binary options.
 * 
 * Example: Selecting options, accepting conditions, etc.
 */
public interface CheckboxRequestDialog extends RequestDialog<Boolean> {

    @Override
    CheckboxRequestDialog title(Component title);

    @Override
    default CheckboxRequestDialog title(String title) {
        return title(Component.text(title));
    }

    /**
     * Sets the body text of the dialog.
     */
    CheckboxRequestDialog body(Component body);

    /**
     * Convenience overload for plain string bodies.
     */
    default CheckboxRequestDialog body(String body) {
        return body(Component.text(body));
    }

    @Override
    default CheckboxRequestDialog description(Component description) {
        return body(description);
    }

    @Override
    default CheckboxRequestDialog description(String description) {
        return description(Component.text(description));
    }

    @Override
    CheckboxRequestDialog submitButton(Component text);

    @Override
    default CheckboxRequestDialog submitButton(String text) {
        return submitButton(Component.text(text));
    }

    /**
     * Sets the label of the checkbox.
     * This is the text displayed next to the checkbox.
     */
    CheckboxRequestDialog checkboxLabel(Component label);

    /**
     * Convenience overload for plain string labels.
     */
    default CheckboxRequestDialog checkboxLabel(String label) {
        return checkboxLabel(Component.text(label));
    }

    /**
     * Sets whether the checkbox is checked by default.
     */
    CheckboxRequestDialog checked(boolean checked);
}
