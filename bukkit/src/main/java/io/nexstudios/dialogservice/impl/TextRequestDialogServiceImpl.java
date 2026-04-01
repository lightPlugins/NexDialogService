package io.nexstudios.dialogservice.impl;

import io.nexstudios.dialogservice.service.TextRequestDialogService;

/**
 * Implementation of TextRequestDialogService.
 */
public class TextRequestDialogServiceImpl implements TextRequestDialogService {
    
    @Override
    public TextRequestDialogImpl create() {
        return new TextRequestDialogImpl();
    }
}

