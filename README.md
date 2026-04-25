# NexDialogService

> A lightweight, Component-first dialog service for Paper plugins, built on top of Paper's experimental Dialog API.

## Highlights

- **Paper Dialog API support** starting with **Paper 1.21.6+**
- **Component-first fluent builders** with String convenience overloads
- **Dependency injection** ready via `NexServiceRegistry`
- **Specialized dialog builders** for common use cases
- **Async callback handling** with `CompletableFuture`
- **Clean plugin-facing API** with simple service access

## Supported Paper Versions

The Dialog API is available starting with **Paper 1.21.6**.

This service targets:

- **Paper 1.21.6 and newer**
- Including later Paper release trains with the newer version naming scheme

## What this library provides

### API layer

- `DialogBuilder` - full dialog builder for complex dialogs
- `ConfirmDialog` - yes/no confirmation dialogs
- `SliderRequestDialog` - numeric input with a slider
- `TextRequestDialog` - text input dialog
- `CheckboxRequestDialog` - checkbox-based input dialog

All public dialog methods accept `net.kyori.adventure.text.Component` values first. For convenience, each of them also provides `String` overloads that convert to plain text Components.

### Service layer

- `ConfirmDialogService`
- `SliderRequestDialogService`
- `TextRequestDialogService`
- `CheckboxRequestDialogService`
- `DialogBuilderService`

### Implementation layer

- Direct use of Paper's Dialog API
- No reflection-based dialog building
- Builder-oriented and easy to consume from other plugins

## Installation / Registration

The dialog services are registered in `DialogService.install(...)`:

```java
public void install(ServiceAccessor serviceAccessor) {
    serviceAccessor.register(ConfirmDialogService.class, new ConfirmDialogServiceImpl());
    serviceAccessor.register(TextRequestDialogService.class, new TextRequestDialogServiceImpl());
    serviceAccessor.register(SliderRequestDialogService.class, new SliderRequestDialogServiceImpl());
    serviceAccessor.register(CheckboxRequestDialogService.class, new CheckboxRequestDialogServiceImpl());
    serviceAccessor.register(DialogBuilderService.class, new DialogBuilderServiceImpl());
}
```

## Usage

### Retrieve services

```java
ConfirmDialogService confirmService = serviceAccessor.getService(ConfirmDialogService.class);
TextRequestDialogService textService = serviceAccessor.getService(TextRequestDialogService.class);
SliderRequestDialogService sliderService = serviceAccessor.getService(SliderRequestDialogService.class);
CheckboxRequestDialogService checkboxService = serviceAccessor.getService(CheckboxRequestDialogService.class);
DialogBuilderService builderService = serviceAccessor.getService(DialogBuilderService.class);
```

### Confirm dialog

```java
confirmService.create()
    .title(Component.text("Accept invitation?"))
    .body(Component.text("Would you like to accept this invitation?"))
    .confirmButton(Component.text("Accept"))
    .cancelButton(Component.text("Decline"))
    .show(player)
    .thenAccept(result -> {
        if (Boolean.TRUE.equals(result)) {
            player.sendMessage("Accepted!");
        }
    });
```

### Text dialog

```java
textService.create()
    .title(Component.text("Change player name"))
    .body(Component.text("Enter your new name"))
    .placeholder(Component.text("Name..."))
    .minCharacters(3)
    .maxCharacters(16)
    .submitButton(Component.text("Change"))
    .show(player)
    .thenAccept(name -> {
        if (name != null) {
            player.sendMessage("New name: " + name);
        }
    });
```

### Slider dialog

```java
sliderService.create()
    .title(Component.text("Send money"))
    .body(Component.text("How much do you want to send?"))
    .label(Component.text("Amount"))
    .minValue(1.0)
    .maxValue(1000.0)
    .initialValue(100.0)
    .step(10.0)
    .submitButton(Component.text("Send"))
    .show(player)
    .thenAccept(amount -> {
        if (amount != null) {
            player.sendMessage("Sending: $" + amount);
        }
    });
```

### Checkbox dialog

```java
checkboxService.create()
    .title(Component.text("Accept terms"))
    .body(Component.text("Please accept the terms and conditions"))
    .checkboxLabel(Component.text("I accept"))
    .checked(false)
    .submitButton(Component.text("Accept"))
    .show(player)
    .thenAccept(accepted -> {
        if (Boolean.TRUE.equals(accepted)) {
            player.sendMessage("Thank you!");
        }
    });
```

### Full custom dialog

```java
builderService.create()
    .title(Component.text("Settings"))
    .body(Component.text("Configure your settings"))
    .toggle("pvp", Component.text("Allow PvP"), false)
    .slider("damage", Component.text("Damage"), 1.0f, 10.0f, 5.0f)
    .textInput("title", Component.text("Title"), "", 1, 32)
    .button(Component.text("Save"), true, result -> {
        player.sendMessage("Saved!");
    })
    .button(Component.text("Cancel"), false, result -> {
        player.sendMessage("Discarded");
    })
    .show(player);
```

## Return values

- `ConfirmDialog` → `CompletableFuture<Boolean>`
- `TextRequestDialog` → `CompletableFuture<String>`
- `SliderRequestDialog` → `CompletableFuture<Double>`
- `CheckboxRequestDialog` → `CompletableFuture<Boolean>`
- `DialogBuilder` → `CompletableFuture<Object>`

`null` is returned when the user cancels the dialog.

## Error handling

```java
dialog.show(player)
    .thenAccept(result -> {
        // success
    })
    .exceptionally(ex -> {
        player.sendMessage("Error: " + ex.getMessage());
        return null;
    });
```

## Notes

- This library is designed for Paper's experimental Dialog API.
- The public API is intentionally small and fluent.
- `description(...)` is kept as an alias for `body(...)` on the specialized dialogs.

---

**NexDialogService** is part of the NexStudios ecosystem.
