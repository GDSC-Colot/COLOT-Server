package com.gdsc.colot.service;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.gdsc.colot.exception.ErrorCode;
import com.gdsc.colot.exception.model.BadRequestException;
import com.gdsc.colot.exception.model.FileUploadFailedException;
import com.gdsc.colot.exception.model.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class S3Service {

    private final AmazonS3 amazonS3;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    public String uploadImage(MultipartFile multipartFile, String folder) {
        String fileName = createFileName(multipartFile.getOriginalFilename());
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(multipartFile.getSize());
        objectMetadata.setContentType(multipartFile.getContentType());

        try(InputStream inputStream = multipartFile.getInputStream()) {
            amazonS3.putObject(new PutObjectRequest(bucket+"/"+ folder + "/image", fileName, inputStream, objectMetadata)
                    .withCannedAcl(CannedAccessControlList.PublicRead));
            return amazonS3.getUrl(bucket+"/"+ folder + "/image", fileName).toString();
        } catch (IOException e) {
            log.error("IOException: {}", e.getMessage(), e);
            throw new FileUploadFailedException(ErrorCode.FILE_UPLOAD_FAILED, ErrorCode.FILE_UPLOAD_FAILED.getMessage());
        } catch (AmazonServiceException e) {
            log.error("AmazonServiceException: {}", e.getMessage(), e);
            log.error("HTTP Status Code: " + e.getStatusCode());
            log.error("AWS Error Code: " + e.getErrorCode());
            log.error("Error Type: " + e.getErrorType());
            log.error("Request ID: " + e.getRequestId());
            throw new FileUploadFailedException(ErrorCode.FILE_UPLOAD_FAILED, ErrorCode.FILE_UPLOAD_FAILED.getMessage());
        } catch (SdkClientException e) {
            log.error("SdkClientException: {}", e.getMessage(), e);
            throw new FileUploadFailedException(ErrorCode.FILE_UPLOAD_FAILED, ErrorCode.FILE_UPLOAD_FAILED.getMessage());
        }
    }

    private String createFileName(String fileName) {
        LocalDateTime localDateTime = LocalDateTime.now();
        return UUID.randomUUID().toString().concat(getFileExtension(fileName)) + localDateTime;
    }

    private String getFileExtension(String fileName) {
        if (fileName.length() == 0) {
            throw new NotFoundException(ErrorCode.NOT_FOUND_IMAGE_EXCEPTION, ErrorCode.NOT_FOUND_IMAGE_EXCEPTION.getMessage());
        }
        ArrayList<String> fileValidate = new ArrayList<>();
        fileValidate.add(".jpg");
        fileValidate.add(".JPG");
        fileValidate.add(".jpeg");
        fileValidate.add(".JPEG");
        fileValidate.add(".png");
        fileValidate.add(".PNG");
        fileValidate.add(".webp");
        fileValidate.add(".WebP");
        fileValidate.add(".heif");
        fileValidate.add(".HEIF");
        fileValidate.add(".heic");
        fileValidate.add(".HEIC");
        fileValidate.add(".svg");
        fileValidate.add(".SVG");
        String idxFileName = fileName.substring(fileName.lastIndexOf("."));
        if (!fileValidate.contains(idxFileName)) {
            throw new BadRequestException(ErrorCode.VALIDATION_IMAGE_REQUEST_FAILED, ErrorCode.VALIDATION_IMAGE_REQUEST_FAILED.getMessage());
        }
        return fileName.substring(fileName.lastIndexOf("."));
    }

    public void deleteFile(String imageUrl) {
        try {
            String imageKey = imageUrl.substring(49);
            amazonS3.deleteObject(bucket, imageKey);
        } catch (AmazonServiceException e) {
            log.error("AmazonServiceException: {}", e.getMessage(), e);
            throw new FileUploadFailedException(ErrorCode.FILE_UPLOAD_FAILED, ErrorCode.FILE_UPLOAD_FAILED.getMessage());
        } catch (SdkClientException e) {
            log.error("SdkClientException: {}", e.getMessage(), e);
            throw new FileUploadFailedException(ErrorCode.FILE_UPLOAD_FAILED, ErrorCode.FILE_UPLOAD_FAILED.getMessage());
        }
    }
}
