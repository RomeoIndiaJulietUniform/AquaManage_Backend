package com.DragonFish.aquaManage.ActuatorService.Services;

import com.DragonFish.aquaManage.ActuatorService.Models.PlantedAquarium;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PlantedService {

    private final PlantedAquarium planted;

    public PlantedService() {
        this.planted = new PlantedAquarium();
    }

    public void setCO2InjectionRate(double rate) {
        planted.setCO2InjectionRate(rate);
        log.info("Set CO2 injection rate to: {}", rate);
    }

    public void setNutrientDosingRate(double rate) {
        planted.setNutrientDosingRate(rate);
        log.info("Set nutrient dosing rate to: {}", rate);
    }

    public void setHeaterTemperature(double rate){
        planted.setHeaterTemperature(rate);
        log.info("Set heater temperature to: {}", rate);
    }

    public void setLightTimeDuration(int time){
        planted.setLightTimerDuration(time);
        log.info("Set light timer duration to: {} minutes", time);
    }

    public void setWaterPumpFlowRate(double rate){
        planted.setWaterPumpFlowRate(rate);
        log.info("Set water pump flow rate to: {}", rate);
    }

    public void setAutoWaterChange(double volume){
        planted.setAutoWaterAddingVolume(volume);
        log.info("Set automatic water change volume to: {} liters", volume);
    }
}
