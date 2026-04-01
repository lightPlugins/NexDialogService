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

    private String title = "Option selection required";
    private String description = "Please select an option";
    private String submitButtonText = "Submit";
    private String checkboxLabel = "Accept";
    private boolean checked = false;

    @Override
    public CheckboxRequestDialog title(String title) {
        this.title = title;
        return this;
    }

    @Override
    public CheckboxRequestDialog body(String body) {
        this.description = body;
        return this;
    }

    @Override
    public CheckboxRequestDialog submitButton(String text) {
        this.submitButtonText = text;
        return this;
    }

    @Override
    public CheckboxRequestDialog checkboxLabel(String label) {
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
                Component.text(title),
                Component.text(title),
                false,
                false,
                DialogBase.DialogAfterAction.CLOSE,
                List.of(DialogBody.plainMessage(Component.text(description))),
                List.of(
                    DialogInput.bool("checkbox", Component.text(checkboxLabel), checked, "true", "false")
                )
            ));

            entry.type(DialogType.multiAction(
                List.of(
                    ActionButton.create(
                        Component.text(submitButtonText),
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
