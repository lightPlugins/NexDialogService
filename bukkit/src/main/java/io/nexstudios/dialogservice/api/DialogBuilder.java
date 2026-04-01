package io.nexstudios.dialogservice.api;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.Map;
import org.bukkit.entity.Player;

/**
 * Main builder for the DialogAPI.
 * Provides all features of the Paper DialogAPI.
 */
public interface DialogBuilder {
    
    /**
     * Sets the title of the dialog.
     */
    DialogBuilder title(String title);
    
    /**
     * Sets the body/description of the dialog.
     */
    DialogBuilder body(String body);
    
    /**
     * Adds a slider to the dialog.
     */
    DialogBuilder slider(String key, float min, float max, float initial);
    
    /**
     * Adds a text input field to the dialog.
     */
    DialogBuilder textInput(String key, String placeholder, String initialValue, int minChars, int maxChars);
    
    /**
     * Adds a toggle/checkbox to the dialog.
     */
    DialogBuilder toggle(String key, String label, boolean initialValue);
    
    /**
     * Adds a button with callback to the dialog.
     */
    DialogBuilder button(String text, boolean isSubmitButton, Consumer<Map<String, Object>> callback);
    
    /**
     * Shows the dialog to a player.
     * @param player The player to show the dialog to
     * @return A CompletableFuture with the dialog result
     */
    CompletableFuture<Object> show(Player player);
}

