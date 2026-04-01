# NexDialogService

> A lightweight dialog service for Paper plugins, built on top of Paper's experimental Dialog API.

## Highlights

- **Paper Dialog API support** starting with **Paper 1.21.6+**
- **Fluent builders** for fast and readable dialog creation
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
    .title("Accept invitation?")
    .body("Would you like to accept this invitation?")
    .confirmButton("Accept")
    .cancelButton("Decline")
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
    .title("Change player name")
    .body("Enter your new name")
    .placeholder("Name...")
    .minCharacters(3)
    .maxCharacters(16)
    .submitButton("Change")
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
    .title("Send money")
    .body("How much do you want to send?")
    .minValue(1.0)
    .maxValue(1000.0)
    .initialValue(100.0)
    .step(10.0)
    .submitButton("Send")
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
    .title("Accept terms")
    .body("Please accept the terms and conditions")
    .checkboxLabel("I accept")
    .checked(false)
    .submitButton("Accept")
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
    .title("Settings")
    .body("Configure your settings")
    .toggle("pvp", "Allow PvP", false)
    .slider("damage", 1.0f, 10.0f, 5.0f)
    .textInput("title", "Title", "", 1, 32)
    .button("Save", true, result -> {
        player.sendMessage("Saved!");
    })
    .button("Cancel", false, result -> {
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
