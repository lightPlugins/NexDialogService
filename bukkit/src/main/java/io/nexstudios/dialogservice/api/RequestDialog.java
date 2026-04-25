package io.nexstudios.dialogservice.api;

import java.util.concurrent.CompletableFuture;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;

/**
 * Base interface for request dialogs.
 * Allows external plugins to receive input from players.
 */
public interface RequestDialog<T> {
    
    /**
     * Sets the title of the dialog.
     */
    RequestDialog<T> title(Component title);

    /**
     * Convenience overload for plain string titles.
     */
    default RequestDialog<T> title(String title) {
        return title(Component.text(title));
    }

    /**
     * Sets the description/content of the dialog.
     */
    RequestDialog<T> description(Component description);

    /**
     * Convenience overload for plain string descriptions.
     */
    default RequestDialog<T> description(String description) {
        return description(Component.text(description));
    }

    /**
     * Sets the text of the submit button (default: "Submit").
     */
    RequestDialog<T> submitButton(Component text);

    /**
     * Convenience overload for plain string submit labels.
     */
    default RequestDialog<T> submitButton(String text) {
        return submitButton(Component.text(text));
    }

    /**
     * Shows the dialog to a player.
     * @param player The player to show the dialog to
     * @return A CompletableFuture with the player's input (null if cancelled)
     */
    CompletableFuture<T> show(Player player);
}
