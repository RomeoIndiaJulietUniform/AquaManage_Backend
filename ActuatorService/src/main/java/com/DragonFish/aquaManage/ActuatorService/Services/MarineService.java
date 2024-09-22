package com.DragonFish.aquaManage.ActuatorService.Services;

import com.DragonFish.aquaManage.ActuatorService.Models.MarineAquarium;
import org.springframework.stereotype.Service;

@Service
public class MarineService {

    private final MarineAquarium marine;

    public MarineService() {
        this.marine = new MarineAquarium();
    }

    public void setSaltwaterMixerRatio(double ratio) {
        marine.setSaltwaterMixerRatio(ratio);
    }

    public void setCalciumDosingRate(double rate) {
        marine.setCalciumDosingRate(rate);
    }

    public void setAlkalinityDosingRate(double rate) {
        marine.setAlkalinityDosingRate(rate);
    }

    public void setSkimmerEfficiency(double efficiency) {
        marine.setSkimmerEfficiency(efficiency);
    }

    public void setHeaterTemperature(double rate){
        marine.setHeaterTemperature(rate);
    }

    public void setLightTimeDuration(int time){
        marine.setLightTimerDuration(time);
    }

    public void setWaterPumpFlowRate(double rate){
        marine.setWaterPumpFlowRate(rate);
    }

    public void setAutoWaterChange(double volume){
        marine.setAutoWaterAddingVolume(volume);
    }
}


