package com.gdsc.colot.controller.parkinglot;

import com.gdsc.colot.common.dto.BaseResponse;
import com.gdsc.colot.controller.parkinglot.dto.request.ParkingLotRequestDto;
import com.gdsc.colot.exception.SuccessCode;
import com.gdsc.colot.security.UserDetailsImpl;
import com.gdsc.colot.service.parkinglot.ParkingLotService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/parking-lot")
@RestController
public class ParkingLotController {

    private final ParkingLotService parkingLotService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public BaseResponse createParkingLot(
            @AuthenticationPrincipal UserDetailsImpl loginUser,
            @RequestParam("type") Boolean type,
            @RequestParam("operationMethod") Boolean operationMethod,
            @RequestParam("address") String address,
            @RequestParam("name") String name,
            @RequestParam("latitude") Double latitude,
            @RequestParam("longitude") Double longitude,
            @RequestParam("capacity") Long capacity,
            @RequestParam(name = "description", required = false, defaultValue = "") String description,
            @RequestPart("image") MultipartFile image
    ) {
        parkingLotService.createParkingLot(
                ParkingLotRequestDto.builder()
                        .type(type)
                        .operationMethod(operationMethod)
                        .address(address)
                        .name(name)
                        .latitude(latitude)
                        .longitude(longitude)
                        .capacity(capacity)
                        .description(description)
                        .image(image)
                        .build(),
                loginUser.getEmail()
        );
        return BaseResponse.success(SuccessCode.PARKING_LOT_CREATED_SUCCESS);
    }

}
