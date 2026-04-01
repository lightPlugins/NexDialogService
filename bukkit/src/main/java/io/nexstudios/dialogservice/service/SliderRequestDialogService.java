package io.nexstudios.dialogservice.service;

import io.nexstudios.dialogservice.api.SliderRequestDialog;
import io.nexstudios.serviceregistry.di.Service;

/**
 * Service for creating slider request dialogs.
 */
public interface SliderRequestDialogService extends Service {

    /**
     * Creates a new slider request dialog.
     * @return A new SliderRequestDialog instance
     */
    SliderRequestDialog create();
}
