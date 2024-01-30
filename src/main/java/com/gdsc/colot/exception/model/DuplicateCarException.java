package com.gdsc.colot.exception.model;

import com.gdsc.colot.exception.ErrorCode;

public class DuplicateCarException extends CustomException {

    public DuplicateCarException(ErrorCode error, String message) {
        super(error, message);
    }

}
