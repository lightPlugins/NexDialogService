package io.nexstudios.dialogservice.impl;

import io.nexstudios.dialogservice.api.DialogBuilder;
import io.nexstudios.dialogservice.service.DialogBuilderService;

/**
 * Implementation of DialogBuilderService.
 */
public class DialogBuilderServiceImpl implements DialogBuilderService {
    
    @Override
    public DialogBuilder create() {
        return new DialogBuilderImpl();
    }
}
