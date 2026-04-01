package io.nexstudios.dialogservice.impl;

import io.nexstudios.dialogservice.service.CheckboxRequestDialogService;

/**
 * Implementation of CheckboxRequestDialogService.
 */
public class CheckboxRequestDialogServiceImpl implements CheckboxRequestDialogService {
    
    @Override
    public CheckboxRequestDialogImpl create() {
        return new CheckboxRequestDialogImpl();
    }
}

