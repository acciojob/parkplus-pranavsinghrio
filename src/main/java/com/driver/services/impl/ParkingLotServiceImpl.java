package com.driver.services.impl;

import com.driver.model.ParkingLot;
import com.driver.model.Spot;
import com.driver.model.SpotType;
import com.driver.repository.ParkingLotRepository;
import com.driver.repository.SpotRepository;
import com.driver.services.ParkingLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParkingLotServiceImpl implements ParkingLotService {
    @Autowired
    ParkingLotRepository parkingLotRepository1;
    @Autowired
    SpotRepository spotRepository1;
    @Override
    public ParkingLot addParkingLot(String name, String address) {
        ParkingLot p=new ParkingLot();
        p.setAddress(address);
        p.setName(name);
        parkingLotRepository1.save(p);
        return p;
    }

    @Override
    public Spot addSpot(int parkingLotId, Integer numberOfWheels, Integer pricePerHour) {
      Spot s=new Spot();
      ParkingLot p =parkingLotRepository1.findById(parkingLotId).get();
      if(numberOfWheels<4){
          s.setSpotType(SpotType.TWO_WHEELER);
      } else if (numberOfWheels<5) {
          s.setSpotType(SpotType.FOUR_WHEELER);
      }
      else{
          s.setSpotType(SpotType.OTHERS);
      }
      s.setParkingLot(p);
      s.setPricePerHour(pricePerHour);
      p.getSpotList().add(s);

      parkingLotRepository1.save(p);
      return s;
    }

    @Override
    public void deleteSpot(int spotId) {
     spotRepository1.deleteById(spotId);
    }

    @Override
    public Spot updateSpot(int parkingLotId, int spotId, int pricePerHour) {
       ParkingLot p=parkingLotRepository1.findById(parkingLotId).get();

      //  Spot spot=spotRepository1.findById(spotId).get();
               List<Spot> spots=p.getSpotList();
        Spot spot=null;
        for(Spot s:spots){
            if(s.getId()==spotId){
                spot=s;
                break;
            }
        }
        spot.setParkingLot(p);
        spot.setPricePerHour(pricePerHour);
       spotRepository1.save(spot);
       return spot;
    }
    @Override
    public void deleteParkingLot(int parkingLotId) {
        parkingLotRepository1.deleteById(parkingLotId);
    }
}
