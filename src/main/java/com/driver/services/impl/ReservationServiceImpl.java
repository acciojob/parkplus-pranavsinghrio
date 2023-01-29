package com.driver.services.impl;

import com.driver.model.*;
import com.driver.repository.ParkingLotRepository;
import com.driver.repository.ReservationRepository;
import com.driver.repository.SpotRepository;
import com.driver.repository.UserRepository;
import com.driver.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {
    @Autowired
    UserRepository userRepository3;
    @Autowired
    SpotRepository spotRepository3;
    @Autowired
    ReservationRepository reservationRepository3;
    @Autowired
    ParkingLotRepository parkingLotRepository3;
    @Override
    public Reservation reserveSpot(Integer userId, Integer parkingLotId, Integer timeInHours, Integer numberOfWheels) throws Exception {
        try{
        User u = userRepository3.findById(userId).get();
        ParkingLot p = parkingLotRepository3.findById(parkingLotId).get();
        Reservation r = new Reservation(timeInHours);
        r.setUser(u);
        List<Spot> spotList = p.getSpotList();
        Spot spot = null;
        int price = Integer.MAX_VALUE;
        for (Spot s : spotList) {
            int wheels;
            if (s.getSpotType() == SpotType.TWO_WHEELER) {
                wheels = 2;
            } else if (s.getSpotType() == SpotType.FOUR_WHEELER) {
                wheels = 4;
            } else {
                wheels = 100;
            }
            if (!s.getOccupied() && wheels > numberOfWheels && s.getPricePerHour() < price) {
                spot = s;
                price = s.getPricePerHour();
            }
        }
        if (spot == null) {
            throw new Exception();
        }
        r.setSpot(spot);
        spot.setOccupied(true);
        u.getReservations().add(r);
        spot.getReservationList().add(r);
        spotRepository3.save(spot);
        userRepository3.save(u);
        return r;
    }
        catch (Exception e) {
        //throw new Exception();
        return null;
    }

}
}