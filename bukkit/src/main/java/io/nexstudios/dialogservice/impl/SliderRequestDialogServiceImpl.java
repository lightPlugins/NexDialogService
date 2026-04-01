package io.nexstudios.dialogservice.impl;

import io.nexstudios.dialogservice.service.SliderRequestDialogService;

/**
 * Implementation of SliderRequestDialogService.
 */
public class SliderRequestDialogServiceImpl implements SliderRequestDialogService {
    
    @Override
    public SliderRequestDialogImpl create() {
        return new SliderRequestDialogImpl();
    }
}

