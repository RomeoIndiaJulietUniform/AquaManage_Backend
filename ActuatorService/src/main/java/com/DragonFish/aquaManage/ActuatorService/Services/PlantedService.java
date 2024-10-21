package com.DragonFish.aquaManage.ActuatorService.Services;

import com.DragonFish.aquaManage.ActuatorService.Models.PlantedAquarium;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * The PlantedService class provides methods to manage and control various settings
 * in a planted aquarium. It interacts with the PlantedAquarium class to adjust
 * parameters such as CO2 injection rate, nutrient dosing, heater temperature,
 * and more, essential for maintaining an aquatic environment suitable for plants.
 */
@Service
@Slf4j
public class PlantedService {

    private final PlantedAquarium planted;

    /**
     * Constructor initializing a new instance of PlantedAquarium.
     */
    public PlantedService() {
        this.planted = new PlantedAquarium();
    }

    /**
     * Sets the CO2 injection rate for the planted aquarium.
     *
     * @param rate the desired CO2 injection rate (double).
     */
    public void setCO2InjectionRate(double rate) {
        planted.setCO2InjectionRate(rate);
        log.info("Set CO2 injection rate to: {}", rate);
    }

    /**
     * Sets the nutrient dosing rate for the aquarium, which controls how much
     * fertilizer is added to the water to support plant growth.
     *
     * @param rate the desired nutrient dosing rate (double).
     */
    public void setNutrientDosingRate(double rate) {
        planted.setNutrientDosingRate(rate);
        log.info("Set nutrient dosing rate to: {}", rate);
    }

    /**
     * Configures the heater temperature in the planted aquarium to maintain
     * optimal water temperature for the aquatic plants.
     *
     * @param rate the desired heater temperature (double).
     */
    public void setHeaterTemperature(double rate) {
        planted.setHeaterTemperature(rate);
        log.info("Set heater temperature to: {}", rate);
    }

    /**
     * Sets the duration for how long the aquarium lights will remain on to
     * provide adequate lighting for the plants' photosynthesis process.
     *
     * @param time the desired light timer duration in minutes (int).
     */
    public void setLightTimeDuration(int time) {
        planted.setLightTimerDuration(time);
        log.info("Set light timer duration to: {} minutes", time);
    }

    /**
     * Adjusts the flow rate of the water pump, ensuring proper water circulation
     * for plant health.
     *
     * @param rate the desired water pump flow rate (double).
     */
    public void setWaterPumpFlowRate(double rate) {
        planted.setWaterPumpFlowRate(rate);
        log.info("Set water pump flow rate to: {}", rate);
    }

    /**
     * Sets the volume of water for automatic water changes in the aquarium.
     * This helps maintain water quality by periodically replacing water.
     *
     * @param volume the desired volume of water to change automatically (double).
     */
    public void setAutoWaterChange(double volume) {
        planted.setAutoWaterAddingVolume(volume);
        log.info("Set automatic water change volume to: {} liters", volume);
    }
}
