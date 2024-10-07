package com.DragonFish.aquaManage.ActuatorService.Services;

import com.DragonFish.aquaManage.ActuatorService.Models.MarineAquarium;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MarineService {

    private final MarineAquarium marine;

    public MarineService() {
        this.marine = new MarineAquarium();
    }

    public void setSaltwaterMixerRatio(double ratio) {
        marine.setSaltwaterMixerRatio(ratio);
        log.info("Set saltwater mixer ratio to: {}", ratio);
    }

    public void setCalciumDosingRate(double rate) {
        marine.setCalciumDosingRate(rate);
        log.info("Set calcium dosing rate to: {}", rate);
    }

    public void setAlkalinityDosingRate(double rate) {
        marine.setAlkalinityDosingRate(rate);
        log.info("Set alkalinity dosing rate to: {}", rate);
    }

    public void setSkimmerEfficiency(double efficiency) {
        marine.setSkimmerEfficiency(efficiency);
        log.info("Set skimmer efficiency to: {}", efficiency);
    }

    public void setHeaterTemperature(double rate){
        marine.setHeaterTemperature(rate);
        log.info("Set heater temperature to: {}", rate);
    }

    public void setLightTimeDuration(int time){
        marine.setLightTimerDuration(time);
        log.info("Set light time duration to: {}", time);
    }

    public void setWaterPumpFlowRate(double rate){
        marine.setWaterPumpFlowRate(rate);
        log.info("Set water pump flow rate to: {}", rate);
    }

    public void setAutoWaterChange(double volume){
        marine.setAutoWaterAddingVolume(volume);
        log.info("Set automatic water change volume to: {} liters", volume);
    }
}


