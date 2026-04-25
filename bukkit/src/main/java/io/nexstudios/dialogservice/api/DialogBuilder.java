package io.nexstudios.dialogservice.api;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.Map;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;

/**
 * Main builder for the DialogAPI.
 * Provides all features of the Paper DialogAPI.
 */
public interface DialogBuilder {
    
    /**
     * Sets the title of the dialog.
     */
    DialogBuilder title(Component title);

    /**
     * Convenience overload for plain string titles.
     */
    default DialogBuilder title(String title) {
        return title(Component.text(title));
    }

    /**
     * Sets the body/description of the dialog.
     */
    DialogBuilder body(Component body);

    /**
     * Convenience overload for plain string bodies.
     */
    default DialogBuilder body(String body) {
        return body(Component.text(body));
    }

    /**
     * Adds a slider to the dialog.
     */
    DialogBuilder slider(String key, Component label, float min, float max, float initial);

    /**
     * Convenience overload for string slider labels.
     */
    default DialogBuilder slider(String key, String label, float min, float max, float initial) {
        return slider(key, Component.text(label), min, max, initial);
    }

    /**
     * Adds a text input field to the dialog.
     */
    DialogBuilder textInput(String key, Component placeholder, String initialValue, int minChars, int maxChars);

    /**
     * Convenience overload for string text input placeholders.
     */
    default DialogBuilder textInput(String key, String placeholder, String initialValue, int minChars, int maxChars) {
        return textInput(key, Component.text(placeholder), initialValue, minChars, maxChars);
    }

    /**
     * Adds a toggle/checkbox to the dialog.
     */
    DialogBuilder toggle(String key, Component label, boolean initialValue);

    /**
     * Convenience overload for string toggle labels.
     */
    default DialogBuilder toggle(String key, String label, boolean initialValue) {
        return toggle(key, Component.text(label), initialValue);
    }

    /**
     * Adds a button with callback to the dialog.
     */
    DialogBuilder button(Component text, boolean isSubmitButton, Consumer<Map<String, Object>> callback);

    /**
     * Convenience overload for plain string button labels.
     */
    default DialogBuilder button(String text, boolean isSubmitButton, Consumer<Map<String, Object>> callback) {
        return button(Component.text(text), isSubmitButton, callback);
    }

    /**
     * Shows the dialog to a player.
     * @param player The player to show the dialog to
     * @return A CompletableFuture with the dialog result
     */
    CompletableFuture<Object> show(Player player);
}

