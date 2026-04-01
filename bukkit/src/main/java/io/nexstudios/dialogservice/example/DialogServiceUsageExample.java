package io.nexstudios.dialogservice.example;

import io.nexstudios.dialogservice.service.CheckboxRequestDialogService;
import io.nexstudios.dialogservice.service.ConfirmDialogService;
import io.nexstudios.dialogservice.service.DialogBuilderService;
import io.nexstudios.dialogservice.service.SliderRequestDialogService;
import io.nexstudios.dialogservice.service.TextRequestDialogService;
import io.nexstudios.serviceregistry.di.ServiceAccessor;
import org.bukkit.entity.Player;

/**
 * Examples for using the Dialog Service System.
 *
 * This class demonstrates how external plugins can use the dialog services.
 */
public class DialogServiceUsageExample {

    private final ConfirmDialogService confirmDialogService;
    private final TextRequestDialogService textRequestDialogService;
    private final SliderRequestDialogService sliderRequestDialogService;
    private final CheckboxRequestDialogService checkboxRequestDialogService;
    private final DialogBuilderService dialogBuilderService;

    public DialogServiceUsageExample(ServiceAccessor serviceAccessor) {
        this.confirmDialogService = serviceAccessor.getService(ConfirmDialogService.class);
        this.textRequestDialogService = serviceAccessor.getService(TextRequestDialogService.class);
        this.sliderRequestDialogService = serviceAccessor.getService(SliderRequestDialogService.class);
        this.checkboxRequestDialogService = serviceAccessor.getService(CheckboxRequestDialogService.class);
        this.dialogBuilderService = serviceAccessor.getService(DialogBuilderService.class);
    }

    /**
     * Example 1: Simple confirmation dialog (yes/no)
     */
    public void showConfirmDialog(Player player) {
        confirmDialogService.create()
            .title("Accept invitation?")
            .body("Would you like to accept this invitation?")
            .confirmButton("Accept")
            .cancelButton("Decline")
            .show(player)
            .thenAccept(result -> {
                if (Boolean.TRUE.equals(result)) {
                    player.sendMessage("You have accepted the invitation!");
                } else {
                    player.sendMessage("You have declined the invitation.");
                }
            });
    }

    /**
     * Example 2: Slider dialog for money/numeric input
     */
    public void showSliderDialog(Player player) {
        sliderRequestDialogService.create()
            .title("Send money")
            .body("How much money do you want to send?")
            .minValue(1.0)
            .maxValue(1000.0)
            .initialValue(100.0)
            .step(10.0)
            .submitButton("Send")
            .show(player)
            .thenAccept(amount -> {
                if (amount != null) {
                    player.sendMessage("You are sending: $" + String.format("%.2f", amount));
                } else {
                    player.sendMessage("Money transfer cancelled.");
                }
            });
    }

    /**
     * Example 3: Text dialog for message input
     */
    public void showTextDialog(Player player) {
        textRequestDialogService.create()
            .title("Write message")
            .body("Write a message to your friend")
            .placeholder("Enter your message...")
            .initialValue("")
            .minCharacters(1)
            .maxCharacters(100)
            .submitButton("Send")
            .show(player)
            .thenAccept(message -> {
                if (message != null) {
                    player.sendMessage("Message sent: " + message);
                } else {
                    player.sendMessage("Message sending cancelled.");
                }
            });
    }

    /**
     * Example 4: Checkbox dialog for accepting conditions
     */
    public void showCheckboxDialog(Player player) {
        checkboxRequestDialogService.create()
            .title("Accept terms")
            .body("Please accept the terms and conditions")
            .checkboxLabel("I accept the terms and conditions")
            .checked(false)
            .submitButton("Accept")
            .show(player)
            .thenAccept(accepted -> {
                if (Boolean.TRUE.equals(accepted)) {
                    player.sendMessage("You have accepted the terms!");
                } else {
                    player.sendMessage("You must accept the terms to continue.");
                }
            });
    }

    /**
     * Example 5: Full custom dialog with multiple input types
     */
    public void showCustomDialog(Player player) {
        dialogBuilderService.create()
            .title("Player Settings")
            .body("Configure your player settings")
            .toggle("pvp", "Allow PvP", false)
            .slider("damage", 1.0f, 10.0f, 5.0f)
            .textInput("title", "Title", "", 1, 32)
            .button("Save", true, result -> {
                Boolean pvpEnabled = (Boolean) result.get("pvp");
                Float damage = (Float) result.get("damage");
                String title = String.valueOf(result.get("title"));

                player.sendMessage("Settings saved: PvP=" + pvpEnabled + ", Damage=" + damage + ", Title=" + title);
            })
            .button("Cancel", false, result -> player.sendMessage("Settings discarded."))
            .show(player);
    }
}
