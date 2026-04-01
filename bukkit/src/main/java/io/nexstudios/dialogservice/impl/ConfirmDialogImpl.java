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

    private String title = "Confirmation required";
    private String description = "Please confirm this action";
    private String confirmButtonText = "Yes";
    private String cancelButtonText = "No";

    @Override
    public ConfirmDialog title(String title) {
        this.title = title;
        return this;
    }

    @Override
    public ConfirmDialog body(String body) {
        this.description = body;
        return this;
    }

    @Override
    public ConfirmDialog confirmButton(String text) {
        this.confirmButtonText = text;
        return this;
    }

    @Override
    public ConfirmDialog cancelButton(String text) {
        this.cancelButtonText = text;
        return this;
    }

    @Override
    public CompletableFuture<Boolean> show(Player player) {
        CompletableFuture<Boolean> future = new CompletableFuture<>();

        Dialog dialog = Dialog.create(factory -> {
            var entry = factory.empty();

            entry.base(DialogBase.create(
                Component.text(title),
                Component.text(title),
                false,
                false,
                DialogBase.DialogAfterAction.CLOSE,
                List.of(DialogBody.plainMessage(Component.text(description))),
                List.of()
            ));

            entry.type(DialogType.confirmation(
                ActionButton.create(
                    Component.text(confirmButtonText),
                    null,
                    200,
                    DialogAction.customClick((response, audience) -> future.complete(true), ClickCallback.Options.builder().build())
                ),
                ActionButton.create(
                    Component.text(cancelButtonText),
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
