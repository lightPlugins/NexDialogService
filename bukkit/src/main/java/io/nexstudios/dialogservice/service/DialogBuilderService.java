package io.nexstudios.dialogservice.service;

import io.nexstudios.serviceregistry.di.Service;
import io.nexstudios.dialogservice.api.DialogBuilder;

/**
 * Service for creating full dialog builders (DialogAPI wrapper).
 */
public interface DialogBuilderService extends Service {
    
    /**
     * Creates a new dialog builder.
     * @return A new DialogBuilder instance
     */
    DialogBuilder create();
}
