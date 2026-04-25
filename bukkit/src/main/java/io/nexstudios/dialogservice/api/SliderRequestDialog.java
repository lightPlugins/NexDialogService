package io.nexstudios.dialogservice.api;

import net.kyori.adventure.text.Component;

/**
 * A request dialog with a slider for numeric input.
 * 
 * Example: Sending money amounts, selecting item quantities, etc.
 */
public interface SliderRequestDialog extends RequestDialog<Double> {

    @Override
    SliderRequestDialog title(Component title);

    @Override
    default SliderRequestDialog title(String title) {
        return title(Component.text(title));
    }

    /**
     * Sets the body text of the dialog.
     */
    SliderRequestDialog body(Component body);

    /**
     * Convenience overload for plain string bodies.
     */
    default SliderRequestDialog body(String body) {
        return body(Component.text(body));
    }

    @Override
    default SliderRequestDialog description(Component description) {
        return body(description);
    }

    @Override
    default SliderRequestDialog description(String description) {
        return description(Component.text(description));
    }

    @Override
    SliderRequestDialog submitButton(Component text);

    @Override
    default SliderRequestDialog submitButton(String text) {
        return submitButton(Component.text(text));
    }

    /**
     * Sets the label shown above the slider input.
     */
    SliderRequestDialog label(Component label);

    /**
     * Convenience overload for plain string labels.
     */
    default SliderRequestDialog label(String label) {
        return label(Component.text(label));
    }

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
