package com.DragonFish.aquaManage.ActuatorService.Services;

import com.DragonFish.aquaManage.ActuatorService.Models.PlantedAquarium;
import org.springframework.stereotype.Service;

@Service
public class PlantedService {

    private final PlantedAquarium planted;

    public PlantedService() {
        this.planted = new PlantedAquarium();
    }

    public void setCO2InjectionRate(double rate) {
        planted.setCO2InjectionRate(rate);
    }

    public void setNutrientDosingRate(double rate) {
        planted.setNutrientDosingRate(rate);
    }

    public void setHeaterTemperature(double rate){
        planted.setHeaterTemperature(rate);
    }

    public void setLightTimeDuration(int time){
        planted.setLightTimerDuration(time);
    }

    public void setWaterPumpFlowRate(double rate){
        planted.setWaterPumpFlowRate(rate);
    }

    public void setAutoWaterChange(double volume){
        planted.setAutoWaterAddingVolume(volume);
    }
}
