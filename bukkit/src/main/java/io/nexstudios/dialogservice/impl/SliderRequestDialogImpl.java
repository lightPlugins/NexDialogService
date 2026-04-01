package io.nexstudios.dialogservice.impl;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import io.nexstudios.dialogservice.api.SliderRequestDialog;
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
 * Implementation of a slider request dialog for numeric input.
 */
public class SliderRequestDialogImpl implements SliderRequestDialog {

    private String title = "Input required";
    private String description = "Please select a value";
    private String submitButtonText = "Submit";
    private double minValue = 0.0;
    private double maxValue = 100.0;
    private double initialValue = 50.0;
    private double step = 1.0;

    @Override
    public SliderRequestDialog title(String title) {
        this.title = title;
        return this;
    }

    @Override
    public SliderRequestDialog body(String body) {
        this.description = body;
        return this;
    }

    @Override
    public SliderRequestDialog submitButton(String text) {
        this.submitButtonText = text;
        return this;
    }

    @Override
    public SliderRequestDialog minValue(double minValue) {
        this.minValue = minValue;
        return this;
    }

    @Override
    public SliderRequestDialog maxValue(double maxValue) {
        this.maxValue = maxValue;
        return this;
    }

    @Override
    public SliderRequestDialog initialValue(double initialValue) {
        this.initialValue = initialValue;
        return this;
    }

    @Override
    public SliderRequestDialog step(double step) {
        this.step = step;
        return this;
    }

    @Override
    public CompletableFuture<Double> show(Player player) {
        CompletableFuture<Double> future = new CompletableFuture<>();

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
                    DialogInput.numberRange(
                        "value",
                        200,
                        Component.text("Value"),
                        "%s",
                        (float) minValue,
                        (float) maxValue,
                        (float) initialValue,
                        (float) step
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
                            Float value = response.getFloat("value");
                            future.complete(value == null ? null : value.doubleValue());
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
