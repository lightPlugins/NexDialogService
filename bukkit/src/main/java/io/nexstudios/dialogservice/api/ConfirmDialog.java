package io.nexstudios.dialogservice.api;

import java.util.concurrent.CompletableFuture;
import org.bukkit.entity.Player;

/**
 * A simple confirmation dialog for yes/no confirmations.
 * The layout is fixed, but the content is customizable.
 * 
 * Example: Accepting invitations, confirming actions, etc.
 */
public interface ConfirmDialog {
    
    /**
     * Sets the title of the dialog.
     */
    ConfirmDialog title(String title);
    
    /**
     * Sets the body text of the dialog.
     */
    ConfirmDialog body(String body);
    
    /**
     * Sets the description text of the dialog.
     * This is an alias for {@link #body(String)}.
     */
    default ConfirmDialog description(String description) {
        return body(description);
    }
    
    /**
     * Sets the text of the confirmation button (default: "Yes").
     */
    ConfirmDialog confirmButton(String text);
    
    /**
     * Sets the text of the cancel button (default: "No").
     */
    ConfirmDialog cancelButton(String text);
    
    /**
     * Shows the dialog to a player.
     * @param player The player to show the dialog to
     * @return A CompletableFuture with the result (true = confirmed, false = cancelled)
     */
    CompletableFuture<Boolean> show(Player player);
}
