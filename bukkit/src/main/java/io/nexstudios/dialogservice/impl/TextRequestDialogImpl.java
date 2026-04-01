package io.nexstudios.dialogservice.impl;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import io.nexstudios.dialogservice.api.TextRequestDialog;
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
 * Implementation of a text request dialog for text input.
 */
public class TextRequestDialogImpl implements TextRequestDialog {

    private String title = "Text input required";
    private String description = "Please enter text";
    private String submitButtonText = "Submit";
    private String placeholder = "";
    private String initialValue = "";
    private int maxCharacters = 256;
    private int minCharacters = 1;

    @Override
    public TextRequestDialog title(String title) {
        this.title = title;
        return this;
    }

    @Override
    public TextRequestDialog body(String body) {
        this.description = body;
        return this;
    }

    @Override
    public TextRequestDialog submitButton(String text) {
        this.submitButtonText = text;
        return this;
    }

    @Override
    public TextRequestDialog placeholder(String placeholder) {
        this.placeholder = placeholder;
        return this;
    }

    @Override
    public TextRequestDialog initialValue(String initialValue) {
        this.initialValue = initialValue;
        return this;
    }

    @Override
    public TextRequestDialog maxCharacters(int maxCharacters) {
        this.maxCharacters = maxCharacters;
        return this;
    }

    @Override
    public TextRequestDialog minCharacters(int minCharacters) {
        this.minCharacters = minCharacters;
        return this;
    }

    @Override
    public CompletableFuture<String> show(Player player) {
        CompletableFuture<String> future = new CompletableFuture<>();

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
                    DialogInput.text(
                        "text",
                        200,
                        Component.text(placeholder.isBlank() ? "Text" : placeholder),
                        true,
                        initialValue,
                        maxCharacters,
                        null
                    )
                )
            ));

            entry.type(DialogType.multiAction(
                List.of(
                    ActionButton.create(
                        Component.text(submitButtonText),
                        null,
                        200,
                        DialogAction.customClick((response, audience) -> {
                            String value = response.getText("text");
                            if (value != null && value.length() >= minCharacters && value.length() <= maxCharacters) {
                                future.complete(value);
                            } else {
                                future.complete(null);
                            }
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
