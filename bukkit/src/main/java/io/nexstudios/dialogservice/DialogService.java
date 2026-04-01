package io.nexstudios.dialogservice;

import io.nexstudios.serviceregistry.di.ServiceAccessor;
import io.nexstudios.serviceregistry.di.ServiceModule;
import io.nexstudios.dialogservice.impl.ConfirmDialogServiceImpl;
import io.nexstudios.dialogservice.impl.TextRequestDialogServiceImpl;
import io.nexstudios.dialogservice.impl.SliderRequestDialogServiceImpl;
import io.nexstudios.dialogservice.impl.CheckboxRequestDialogServiceImpl;
import io.nexstudios.dialogservice.impl.DialogBuilderServiceImpl;
import io.nexstudios.dialogservice.service.ConfirmDialogService;
import io.nexstudios.dialogservice.service.TextRequestDialogService;
import io.nexstudios.dialogservice.service.SliderRequestDialogService;
import io.nexstudios.dialogservice.service.CheckboxRequestDialogService;
import io.nexstudios.dialogservice.service.DialogBuilderService;

/**
 * Dialog Service Module - registers all dialog services for dependency injection.
 * 
 * This service provides:
 * - ConfirmDialogService: Simple yes/no dialogs
 * - TextRequestDialogService: Text input dialogs
 * - SliderRequestDialogService: Numeric input dialogs
 * - CheckboxRequestDialogService: Checkbox dialogs
 * - DialogBuilderService: Full dialog builder for the Paper DialogAPI
 */
public class DialogService implements ServiceModule {
    
    @Override
    public void install(ServiceAccessor serviceAccessor) {
        // Register all dialog services
        serviceAccessor.register(ConfirmDialogService.class, new ConfirmDialogServiceImpl());
        serviceAccessor.register(TextRequestDialogService.class, new TextRequestDialogServiceImpl());
        serviceAccessor.register(SliderRequestDialogService.class, new SliderRequestDialogServiceImpl());
        serviceAccessor.register(CheckboxRequestDialogService.class, new CheckboxRequestDialogServiceImpl());
        serviceAccessor.register(DialogBuilderService.class, new DialogBuilderServiceImpl());
    }
}
