package com.gdsc.colot.controller.parkinglot.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Getter
@NoArgsConstructor
public class ParkingLotRequestDto {

    private Boolean type;
    private Boolean operationMethod;
    private String address;
    private String name;
    private Double latitude;
    private Double longitude;
    private Long capacity;
    private String description;
    private MultipartFile image;

    @Builder
    public ParkingLotRequestDto(Boolean type, Boolean operationMethod, String address, String name, Double latitude, Double longitude, Long capacity, String description, MultipartFile image) {
        this.type = type;
        this.operationMethod = operationMethod;
        this.address = address;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.capacity = capacity;
        this.description = description;
        this.image = image;
    }
}
