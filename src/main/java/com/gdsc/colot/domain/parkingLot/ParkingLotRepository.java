package com.gdsc.colot.domain.parkingLot;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ParkingLotRepository extends JpaRepository<ParkingLot, Long> {
    @Query("SELECT p, " +
            "6371 * ACOS(SIN(RADIANS(:currentLatitude)) * SIN(RADIANS(p.latitude)) + COS(RADIANS(:currentLatitude)) * COS(RADIANS(p.latitude)) * COS(RADIANS(p.longitude) - RADIANS(:currentLongitude)))" +
            "FROM ParkingLot p " +
            "WHERE " +
            "6371 * ACOS(SIN(RADIANS(:currentLatitude)) * SIN(RADIANS(p.latitude)) + COS(RADIANS(:currentLatitude)) * COS(RADIANS(p.latitude)) * COS(RADIANS(p.longitude) - RADIANS(:currentLongitude))) <= :radius")
    List<Object[]> findNearbyParkingLots(@Param("currentLatitude") Double currentLatitude, @Param("currentLongitude") Double currentLongitude, @Param("radius") Double radius);

}
