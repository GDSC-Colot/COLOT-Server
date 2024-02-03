package com.gdsc.colot.domain.parkinglot;

import com.gdsc.colot.domain.BaseEntity;
import com.gdsc.colot.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "PARKING_LOT")
@NoArgsConstructor
public class ParkingLot extends BaseEntity {

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private Double latitude;

    @Column(nullable = false)
    private Double longitude;

    @Column(nullable = false)
    private String name;

    @Column(nullable = true, length = 500)
    private String description;

    @Column(nullable = false)
    private Boolean type;

    @Column(nullable = false)
    private Boolean operationMethod;

    @Column(nullable = false)
    private String image;

    @Column(nullable = false)
    private Long capacity;

    @ManyToOne
    @JoinColumn(name = "admin_id")
    private User user;

    @Builder
    public ParkingLot(String address, Double latitude, Double longitude, String name, String description, Boolean type, Boolean operationMethod, String image, Long capacity, User user) {
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.name = name;
        this.description = description;
        this.type = type;
        this.operationMethod = operationMethod;
        this.image = image;
        this.capacity = capacity;
        this.user = user;
    }
}
