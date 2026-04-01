package io.nexstudios.dialogservice.api;

/**
 * A request dialog with checkboxes for multiple binary options.
 * 
 * Example: Selecting options, accepting conditions, etc.
 */
public interface CheckboxRequestDialog extends RequestDialog<Boolean> {

    @Override
    CheckboxRequestDialog title(String title);

    /**
     * Sets the body text of the dialog.
     */
    CheckboxRequestDialog body(String body);

    @Override
    default CheckboxRequestDialog description(String description) {
        return body(description);
    }

    @Override
    CheckboxRequestDialog submitButton(String text);

    /**
     * Sets the label of the checkbox.
     * This is the text displayed next to the checkbox.
     */
    CheckboxRequestDialog checkboxLabel(String label);
    
    /**
     * Sets whether the checkbox is checked by default.
     */
    CheckboxRequestDialog checked(boolean checked);
}
