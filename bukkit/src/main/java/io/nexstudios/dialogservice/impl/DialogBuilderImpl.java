package io.nexstudios.dialogservice.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import io.nexstudios.dialogservice.api.DialogBuilder;
import io.papermc.paper.dialog.Dialog;
import io.papermc.paper.dialog.DialogResponseView;
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
 * Implementation of a full dialog builder that provides all features of the Paper DialogAPI.
 */
public class DialogBuilderImpl implements DialogBuilder {

    private enum InputKind {
        TEXT,
        BOOLEAN,
        NUMBER
    }

    private record InputSpec(String key, InputKind kind) {
    }

    private record ButtonSpec(String text, boolean mainButton, Consumer<Map<String, Object>> callback) {
    }

    private String title = "Dialog";
    private final List<DialogBody> bodies = new ArrayList<>();
    private final List<DialogInput> inputs = new ArrayList<>();
    private final List<ButtonSpec> buttons = new ArrayList<>();
    private final List<InputSpec> inputSpecs = new ArrayList<>();

    @Override
    public DialogBuilder title(String title) {
        this.title = title;
        return this;
    }

    @Override
    public DialogBuilder body(String body) {
        this.bodies.add(DialogBody.plainMessage(Component.text(body)));
        return this;
    }

    @Override
    public DialogBuilder slider(String key, float min, float max, float initial) {
        this.inputs.add(DialogInput.numberRange(
            key,
            200,
            Component.text(key),
            "%s",
            min,
            max,
            initial,
            1.0F
        ));
        this.inputSpecs.add(new InputSpec(key, InputKind.NUMBER));
        return this;
    }

    @Override
    public DialogBuilder textInput(String key, String placeholder, String initialValue, int minChars, int maxChars) {
        this.inputs.add(DialogInput.text(
            key,
            200,
            Component.text(placeholder),
            true,
            initialValue,
            maxChars,
            null
        ));
        this.inputSpecs.add(new InputSpec(key, InputKind.TEXT));
        return this;
    }

    @Override
    public DialogBuilder toggle(String key, String label, boolean initialValue) {
        this.inputs.add(DialogInput.bool(key, Component.text(label), initialValue, "true", "false"));
        this.inputSpecs.add(new InputSpec(key, InputKind.BOOLEAN));
        return this;
    }

    @Override
    public DialogBuilder button(String text, boolean isSubmitButton, Consumer<Map<String, Object>> callback) {
        this.buttons.add(new ButtonSpec(text, isSubmitButton, callback));
        return this;
    }

    @Override
    public CompletableFuture<Object> show(Player player) {
        CompletableFuture<Object> future = new CompletableFuture<>();

        Dialog dialog = Dialog.create(factory -> {
            var entry = factory.empty();

            entry.base(DialogBase.create(
                Component.text(title),
                Component.text(title),
                false,
                false,
                DialogBase.DialogAfterAction.CLOSE,
                bodies,
                inputs
            ));

            List<ActionButton> actionButtons = new ArrayList<>();
            ActionButton exitAction = null;

            for (ButtonSpec spec : buttons) {
                ActionButton actionButton = ActionButton.create(
                    Component.text(spec.text()),
                    null,
                    200,
                    DialogAction.customClick((response, audience) -> spec.callback().accept(buildResponseMap(response)), ClickCallback.Options.builder().build())
                );

                if (spec.mainButton()) {
                    actionButtons.add(actionButton);
                } else if (exitAction == null) {
                    exitAction = actionButton;
                } else {
                    actionButtons.add(actionButton);
                }
            }

            if (exitAction == null && actionButtons.isEmpty()) {
                exitAction = ActionButton.create(
                    Component.text("Close"),
                    null,
                    200,
                    DialogAction.customClick((response, audience) -> { }, ClickCallback.Options.builder().build())
                );
            }

            entry.type(DialogType.multiAction(actionButtons, exitAction, Math.max(1, buttons.size())));
        });

        player.showDialog(dialog);
        future.complete(dialog);
        return future;
    }

    private Map<String, Object> buildResponseMap(DialogResponseView response) {
        Map<String, Object> result = new LinkedHashMap<>();
        for (InputSpec spec : inputSpecs) {
            Object value = switch (spec.kind()) {
                case TEXT -> response.getText(spec.key());
                case BOOLEAN -> response.getBoolean(spec.key());
                case NUMBER -> response.getFloat(spec.key());
            };
            result.put(spec.key(), value);
        }
        return result;
    }
}
