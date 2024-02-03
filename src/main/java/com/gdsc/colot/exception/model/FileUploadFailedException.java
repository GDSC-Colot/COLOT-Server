package com.gdsc.colot.exception.model;

import com.gdsc.colot.exception.ErrorCode;

public class FileUploadFailedException extends CustomException{
    public FileUploadFailedException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}
