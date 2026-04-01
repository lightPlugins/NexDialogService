package io.nexstudios.dialogservice.api;

import java.util.concurrent.CompletableFuture;
import org.bukkit.entity.Player;

/**
 * Base interface for request dialogs.
 * Allows external plugins to receive input from players.
 */
public interface RequestDialog<T> {
    
    /**
     * Sets the title of the dialog.
     */
    RequestDialog<T> title(String title);
    
    /**
     * Sets the description/content of the dialog.
     */
    RequestDialog<T> description(String description);
    
    /**
     * Sets the text of the submit button (default: "Submit").
     */
    RequestDialog<T> submitButton(String text);
    
    /**
     * Shows the dialog to a player.
     * @param player The player to show the dialog to
     * @return A CompletableFuture with the player's input (null if cancelled)
     */
    CompletableFuture<T> show(Player player);
}
