package io.nexstudios.dialogservice.api;

import java.util.concurrent.CompletableFuture;
import net.kyori.adventure.text.Component;
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
    ConfirmDialog title(Component title);

    /**
     * Convenience overload for plain string titles.
     */
    default ConfirmDialog title(String title) {
        return title(Component.text(title));
    }

    /**
     * Sets the body text of the dialog.
     */
    ConfirmDialog body(Component body);

    /**
     * Convenience overload for plain string bodies.
     */
    default ConfirmDialog body(String body) {
        return body(Component.text(body));
    }

    /**
     * Sets the description text of the dialog.
     * This is an alias for {@link #body(Component)}.
     */
    default ConfirmDialog description(Component description) {
        return body(description);
    }

    /**
     * Convenience overload for plain string descriptions.
     */
    default ConfirmDialog description(String description) {
        return description(Component.text(description));
    }

    /**
     * Sets the text of the confirmation button (default: "Yes").
     */
    ConfirmDialog confirmButton(Component text);

    /**
     * Convenience overload for plain string confirmation labels.
     */
    default ConfirmDialog confirmButton(String text) {
        return confirmButton(Component.text(text));
    }

    /**
     * Sets the text of the cancel button (default: "No").
     */
    ConfirmDialog cancelButton(Component text);

    /**
     * Convenience overload for plain string cancel labels.
     */
    default ConfirmDialog cancelButton(String text) {
        return cancelButton(Component.text(text));
    }

    /**
     * Shows the dialog to a player.
     * @param player The player to show the dialog to
     * @return A CompletableFuture with the result (true = confirmed, false = cancelled)
     */
    CompletableFuture<Boolean> show(Player player);
}
