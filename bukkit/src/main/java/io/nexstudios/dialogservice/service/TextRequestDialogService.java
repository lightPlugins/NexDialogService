package io.nexstudios.dialogservice.service;

import io.nexstudios.dialogservice.api.TextRequestDialog;
import io.nexstudios.serviceregistry.di.Service;

/**
 * Service for creating text request dialogs.
 */
public interface TextRequestDialogService extends Service {

    /**
     * Creates a new text request dialog.
     * @return A new TextRequestDialog instance
     */
    TextRequestDialog create();
}
