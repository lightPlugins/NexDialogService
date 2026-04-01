package io.nexstudios.dialogservice.service;

import io.nexstudios.dialogservice.api.CheckboxRequestDialog;
import io.nexstudios.serviceregistry.di.Service;

/**
 * Service for creating checkbox request dialogs.
 */
public interface CheckboxRequestDialogService extends Service {

    /**
     * Creates a new checkbox request dialog.
     * @return A new CheckboxRequestDialog instance
     */
    CheckboxRequestDialog create();
}
