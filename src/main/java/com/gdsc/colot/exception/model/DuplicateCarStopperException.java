package com.gdsc.colot.exception.model;

import com.gdsc.colot.exception.ErrorCode;

public class DuplicateCarStopperException extends CustomException{
    public DuplicateCarStopperException(ErrorCode error, String message) {
        super(error, message);
    }
}
