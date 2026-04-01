package io.nexstudios.dialogservice.impl;

import io.nexstudios.dialogservice.api.ConfirmDialog;
import io.nexstudios.dialogservice.service.ConfirmDialogService;

/**
 * Implementation of ConfirmDialogService.
 */
public class ConfirmDialogServiceImpl implements ConfirmDialogService {
    
    @Override
    public ConfirmDialog create() {
        return new ConfirmDialogImpl();
    }
}
