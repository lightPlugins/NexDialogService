package io.nexstudios.dialogservice.api;

/**
 * A request dialog with a slider for numeric input.
 * 
 * Example: Sending money amounts, selecting item quantities, etc.
 */
public interface SliderRequestDialog extends RequestDialog<Double> {

    @Override
    SliderRequestDialog title(String title);

    /**
     * Sets the body text of the dialog.
     */
    SliderRequestDialog body(String body);

    @Override
    default SliderRequestDialog description(String description) {
        return body(description);
    }

    @Override
    SliderRequestDialog submitButton(String text);

    /**
     * Sets the minimum value of the slider.
     */
    SliderRequestDialog minValue(double minValue);
    
    /**
     * Sets the maximum value of the slider.
     */
    SliderRequestDialog maxValue(double maxValue);
    
    /**
     * Sets the initial value of the slider.
     */
    SliderRequestDialog initialValue(double initialValue);
    
    /**
     * Sets the step value (e.g. 0.5 for 0.5-steps, 1.0 for whole numbers).
     */
    SliderRequestDialog step(double step);
}
