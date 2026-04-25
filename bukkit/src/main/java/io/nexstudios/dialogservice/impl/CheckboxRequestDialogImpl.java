package io.nexstudios.dialogservice.impl;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import io.nexstudios.dialogservice.api.CheckboxRequestDialog;
import io.papermc.paper.dialog.Dialog;
import io.papermc.paper.registry.data.dialog.ActionButton;
import io.papermc.paper.registry.data.dialog.DialogBase;
import io.papermc.paper.registry.data.dialog.action.DialogAction;
import io.papermc.paper.registry.data.dialog.body.DialogBody;
import io.papermc.paper.registry.data.dialog.input.DialogInput;
import io.papermc.paper.registry.data.dialog.type.DialogType;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickCallback;
import org.bukkit.entity.Player;

/**
 * Implementation of a checkbox request dialog for binary options.
 */
public class CheckboxRequestDialogImpl implements CheckboxRequestDialog {

    private Component title = Component.text("Option selection required");
    private Component description = Component.text("Please select an option");
    private Component submitButtonText = Component.text("Submit");
    private Component checkboxLabel = Component.text("Accept");
    private boolean checked = false;

    @Override
    public CheckboxRequestDialog title(Component title) {
        this.title = title;
        return this;
    }

    @Override
    public CheckboxRequestDialog body(Component body) {
        this.description = body;
        return this;
    }

    @Override
    public CheckboxRequestDialog submitButton(Component text) {
        this.submitButtonText = text;
        return this;
    }

    @Override
    public CheckboxRequestDialog checkboxLabel(Component label) {
        this.checkboxLabel = label;
        return this;
    }

    @Override
    public CheckboxRequestDialog checked(boolean checked) {
        this.checked = checked;
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
                List.of(
                    DialogInput.bool("checkbox", checkboxLabel, checked, "true", "false")
                )
            ));

            entry.type(DialogType.multiAction(
                List.of(
                    ActionButton.create(
                        submitButtonText,
                        null,
                        200,
                        DialogAction.customClick((response, audience) -> {
                            Boolean value = response.getBoolean("checkbox");
                            future.complete(value);
                        }, ClickCallback.Options.builder().build())
                    )
                ),
                ActionButton.create(
                    Component.text("Cancel"),
                    null,
                    200,
                    DialogAction.customClick((response, audience) -> future.complete(null), ClickCallback.Options.builder().build())
                ),
                1
            ));
        });

        player.showDialog(dialog);
        return future;
    }
}
