package com.DragonFish.aquaManage.ActuatorService.Controllers;

import com.DragonFish.aquaManage.ActuatorService.Services.PlantedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class responsible for handling HTTP requests related to the planted aquarium system actuators.
 * These actuators include controls for CO2 injection, nutrient dosing, heater temperature, light duration,
 * water pump flow rate, and auto water change volume.
 */
@RestController
@RequestMapping("/actuators/Planted")
public class PlantedServiceController {

    private final PlantedService plantedService;

    /**
     * Constructor-based dependency injection for the PlantedService.
     *
     * @param plantedService the service that handles the logic for controlling aquarium actuators.
     */
    @Autowired
    public PlantedServiceController(PlantedService plantedService) {
        this.plantedService = plantedService;
    }

    /**
     * Endpoint to set the CO2 injection rate for the aquarium.
     *
     * @param rate the desired CO2 injection rate in bubbles per minute.
     * @return a confirmation message with the set CO2 rate.
     */
    @PostMapping("/set-co2-rate")
    public String setCO2InjectionRate(@RequestParam double rate) {
        plantedService.setCO2InjectionRate(rate);
        return "CO2 Injection rate set to " + rate + " bubbles/minute";
    }

    /**
     * Endpoint to set the nutrient dosing rate for the aquarium.
     *
     * @param rate the desired nutrient dosing rate in milliliters per hour.
     * @return a confirmation message with the set nutrient dosing rate.
     */
    @PostMapping("/set-nutrient-rate")
    public String setNutrientDosingRate(@RequestParam double rate) {
        plantedService.setNutrientDosingRate(rate);
        return "Nutrient dosing rate set to " + rate + " mL/hour";
    }

    /**
     * Endpoint to set the heater temperature for the aquarium.
     *
     * @param temperature the desired heater temperature in degrees Celsius.
     * @return a confirmation message with the set heater temperature.
     */
    @PostMapping("/set-heater-temp")
    public String setHeaterTemperature(@RequestParam double temperature) {
        plantedService.setHeaterTemperature(temperature);
        return "Heater temperature set to " + temperature + "°C";
    }

    /**
     * Endpoint to set the light duration for the aquarium.
     *
     * @param duration the desired light duration in minutes.
     * @return a confirmation message with the set light duration.
     */
    @PostMapping("/set-light-duration")
    public String setLightTimeDuration(@RequestParam int duration) {
        plantedService.setLightTimeDuration(duration);
        return "Light duration set to " + duration + " minutes";
    }

    /**
     * Endpoint to set the water pump flow rate for the aquarium.
     *
     * @param flowRate the desired water pump flow rate in liters per hour.
     * @return a confirmation message with the set water pump flow rate.
     */
    @PostMapping("/set-water-pump-rate")
    public String setWaterPumpFlowRate(@RequestParam double flowRate) {
        plantedService.setWaterPumpFlowRate(flowRate);
        return "Water pump flow rate set to " + flowRate + " L/h";
    }

    /**
     * Endpoint to set the automatic water change volume for the aquarium.
     *
     * @param volume the desired volume of water to be changed automatically in liters.
     * @return a confirmation message with the set water change volume.
     */
    @PostMapping("/set-auto-water-change")
    public String setAutoWaterChange(@RequestParam double volume) {
        plantedService.setAutoWaterChange(volume);
        return "Auto water change volume set to " + volume + " liters";
    }
}
