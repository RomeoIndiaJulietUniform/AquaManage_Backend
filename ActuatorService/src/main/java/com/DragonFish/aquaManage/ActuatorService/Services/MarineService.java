package com.DragonFish.aquaManage.ActuatorService.Services;

import com.DragonFish.aquaManage.ActuatorService.Models.MarineAquarium;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * MarineService provides various methods to configure and control settings
 * related to a marine aquarium system. It interacts with the MarineAquarium
 * class to adjust parameters such as saltwater ratio, calcium dosing rate,
 * heater temperature, and more.
 */
@Service
@Slf4j
public class MarineService {

    private final MarineAquarium marine;

    /**
     * Default constructor initializing a MarineAquarium instance.
     */
    public MarineService() {
        this.marine = new MarineAquarium();
    }

    /**
     * Sets the ratio for mixing saltwater in the aquarium.
     *
     * @param ratio the desired saltwater mixer ratio (double).
     */
    public void setSaltwaterMixerRatio(double ratio) {
        marine.setSaltwaterMixerRatio(ratio);
        log.info("Set saltwater mixer ratio to: {}", ratio);
    }

    /**
     * Sets the rate of calcium dosing for the marine aquarium.
     *
     * @param rate the desired calcium dosing rate (double).
     */
    public void setCalciumDosingRate(double rate) {
        marine.setCalciumDosingRate(rate);
        log.info("Set calcium dosing rate to: {}", rate);
    }

    /**
     * Sets the rate of alkalinity dosing for the marine aquarium.
     *
     * @param rate the desired alkalinity dosing rate (double).
     */
    public void setAlkalinityDosingRate(double rate) {
        marine.setAlkalinityDosingRate(rate);
        log.info("Set alkalinity dosing rate to: {}", rate);
    }

    /**
     * Sets the efficiency of the protein skimmer used in the marine aquarium.
     *
     * @param efficiency the desired skimmer efficiency (double).
     */
    public void setSkimmerEfficiency(double efficiency) {
        marine.setSkimmerEfficiency(efficiency);
        log.info("Set skimmer efficiency to: {}", efficiency);
    }

    /**
     * Sets the desired temperature for the aquarium heater.
     *
     * @param rate the heater temperature (double).
     */
    public void setHeaterTemperature(double rate) {
        marine.setHeaterTemperature(rate);
        log.info("Set heater temperature to: {}", rate);
    }

    /**
     * Configures the duration for which the lights in the aquarium should remain on.
     *
     * @param time the light timer duration in hours (int).
     */
    public void setLightTimeDuration(int time) {
        marine.setLightTimerDuration(time);
        log.info("Set light time duration to: {}", time);
    }

    /**
     * Sets the flow rate for the water pump in the aquarium.
     *
     * @param rate the desired water pump flow rate (double).
     */
    public void setWaterPumpFlowRate(double rate) {
        marine.setWaterPumpFlowRate(rate);
        log.info("Set water pump flow rate to: {}", rate);
    }

    /**
     * Configures the automatic water change volume for the aquarium.
     *
     * @param volume the volume of water to be automatically changed (double).
     */
    public void setAutoWaterChange(double volume) {
        marine.setAutoWaterAddingVolume(volume);
        log.info("Set automatic water change volume to: {} liters", volume);
    }
}


