package io.nexstudios.dialogservice.impl;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import io.nexstudios.dialogservice.api.ConfirmDialog;
import io.papermc.paper.dialog.Dialog;
import io.papermc.paper.registry.data.dialog.ActionButton;
import io.papermc.paper.registry.data.dialog.DialogBase;
import io.papermc.paper.registry.data.dialog.action.DialogAction;
import io.papermc.paper.registry.data.dialog.body.DialogBody;
import io.papermc.paper.registry.data.dialog.type.DialogType;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickCallback;
import org.bukkit.entity.Player;

/**
 * Implementation of a simple confirmation dialog for yes/no confirmations.
 */
public class ConfirmDialogImpl implements ConfirmDialog {

    private Component title = Component.text("Confirmation required");
    private Component description = Component.text("Please confirm this action");
    private Component confirmButtonText = Component.text("Yes");
    private Component cancelButtonText = Component.text("No");

    @Override
    public ConfirmDialog title(Component title) {
        this.title = title;
        return this;
    }

    @Override
    public ConfirmDialog body(Component body) {
        this.description = body;
        return this;
    }

    @Override
    public ConfirmDialog confirmButton(Component text) {
        this.confirmButtonText = text;
        return this;
    }

    @Override
    public ConfirmDialog cancelButton(Component text) {
        this.cancelButtonText = text;
        return this;
    }

    @Override
    public CompletableFuture<Boolean> show(Player player) {
        CompletableFuture<Boolean> future = new CompletableFuture<>();

        Dialog dialog = Dialog.create(factory -> {
            var entry = factory.empty();

            entry.base(DialogBase.create(
                title,
                title,
                false,
                false,
                DialogBase.DialogAfterAction.CLOSE,
                List.of(DialogBody.plainMessage(description)),
                List.of()
            ));

            entry.type(DialogType.confirmation(
                ActionButton.create(
                    confirmButtonText,
                    null,
                    200,
                    DialogAction.customClick((response, audience) -> future.complete(true), ClickCallback.Options.builder().build())
                ),
                ActionButton.create(
                    cancelButtonText,
                    null,
                    200,
                    DialogAction.customClick((response, audience) -> future.complete(false), ClickCallback.Options.builder().build())
                )
            ));
        });

        player.showDialog(dialog);
        return future;
    }
}
