package io.nexstudios.dialogservice.service;

import io.nexstudios.serviceregistry.di.Service;
import io.nexstudios.dialogservice.api.ConfirmDialog;

/**
 * Service for creating confirmation dialogs.
 */
public interface ConfirmDialogService extends Service {
    
    /**
     * Creates a new confirmation dialog.
     * @return A new ConfirmDialog instance
     */
    ConfirmDialog create();
}

