package com.DragonFish.aquaManage.ActuatorService.Controllers;

import com.DragonFish.aquaManage.ActuatorService.Services.MarineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Controller class responsible for handling HTTP requests related to marine aquarium system actuators.
 * These actuators include controls for saltwater mixer, calcium and alkalinity dosing, skimmer efficiency,
 * heater temperature, light duration, water pump flow rate, and auto water change volume.
 */
@RestController
@RequestMapping("/actuators/Marine")
public class MarineServiceController {

    private final MarineService marineService;

    /**
     * Constructor-based dependency injection for MarineService.
     *
     * @param marineService the service that handles the logic for controlling marine aquarium actuators.
     */
    @Autowired
    public MarineServiceController(MarineService marineService) {
        this.marineService = marineService;
    }

    /**
     * A test endpoint to check if the actuator service is responsive.
     *
     * @return a greeting message.
     */
    @GetMapping("/greet")
    public String getGreet() {
        try {
            return "Hello from Actuator";
        } catch (Exception e) {
            throw new RuntimeException("Error in getGreet: " + e.getMessage());
        }
    }

    /**
     * Endpoint to set the saltwater mixer ratio for the marine aquarium.
     *
     * @param ratio the desired saltwater mixing ratio.
     * @return a confirmation message with the set saltwater mixer ratio.
     */
    @PostMapping("/set-saltwater-ratio")
    public String setSaltwaterMixerRatio(@RequestParam double ratio) {
        try {
            marineService.setSaltwaterMixerRatio(ratio);
            return "Saltwater mixer ratio set to " + ratio;
        } catch (Exception e) {
            throw new RuntimeException("Error setting saltwater mixer ratio: " + e.getMessage());
        }
    }

    /**
     * Endpoint to set the calcium dosing rate for the marine aquarium.
     *
     * @param rate the desired calcium dosing rate in milliliters per hour.
     * @return a confirmation message with the set calcium dosing rate.
     */
    @PostMapping("/set-calcium-rate")
    public String setCalciumDosingRate(@RequestParam double rate) {
        try {
            marineService.setCalciumDosingRate(rate);
            return "Calcium dosing rate set to " + rate + " mL/hour";
        } catch (Exception e) {
            throw new RuntimeException("Error setting calcium dosing rate: " + e.getMessage());
        }
    }

    /**
     * Endpoint to set the alkalinity dosing rate for the marine aquarium.
     *
     * @param rate the desired alkalinity dosing rate in milliliters per hour.
     * @return a confirmation message with the set alkalinity dosing rate.
     */
    @PostMapping("/set-alkalinity-rate")
    public String setAlkalinityDosingRate(@RequestParam double rate) {
        try {
            marineService.setAlkalinityDosingRate(rate);
            return "Alkalinity dosing rate set to " + rate + " mL/hour";
        } catch (Exception e) {
            throw new RuntimeException("Error setting alkalinity dosing rate: " + e.getMessage());
        }
    }

    /**
     * Endpoint to set the skimmer efficiency for the marine aquarium.
     *
     * @param efficiency the desired skimmer efficiency as a percentage.
     * @return a confirmation message with the set skimmer efficiency.
     */
    @PostMapping("/set-skimmer-efficiency")
    public String setSkimmerEfficiency(@RequestParam double efficiency) {
        try {
            marineService.setSkimmerEfficiency(efficiency);
            return "Skimmer efficiency set to " + efficiency + "%";
        } catch (Exception e) {
            throw new RuntimeException("Error setting skimmer efficiency: " + e.getMessage());
        }
    }

    /**
     * Endpoint to set the heater temperature for the marine aquarium.
     *
     * @param temperature the desired heater temperature in degrees Celsius.
     * @return a confirmation message with the set heater temperature.
     */
    @PostMapping("/set-heater-temp")
    public String setHeaterTemperature(@RequestParam double temperature) {
        try {
            marineService.setHeaterTemperature(temperature);
            return "Heater temperature set to " + temperature + "°C";
        } catch (Exception e) {
            throw new RuntimeException("Error setting heater temperature: " + e.getMessage());
        }
    }

    /**
     * Endpoint to set the light duration for the marine aquarium.
     *
     * @param duration the desired light duration in minutes.
     * @return a confirmation message with the set light duration.
     */
    @PostMapping("/set-light-duration")
    public String setLightTimeDuration(@RequestParam int duration) {
        try {
            marineService.setLightTimeDuration(duration);
            return "Light duration set to " + duration + " minutes";
        } catch (Exception e) {
            throw new RuntimeException("Error setting light duration: " + e.getMessage());
        }
    }

    /**
     * Endpoint to set the water pump flow rate for the marine aquarium.
     *
     * @param flowRate the desired water pump flow rate in liters per hour.
     * @return a confirmation message with the set water pump flow rate.
     */
    @PostMapping("/set-water-pump-rate")
    public String setWaterPumpFlowRate(@RequestParam double flowRate) {
        try {
            marineService.setWaterPumpFlowRate(flowRate);
            return "Water pump flow rate set to " + flowRate + " L/h";
        } catch (Exception e) {
            throw new RuntimeException("Error setting water pump flow rate: " + e.getMessage());
        }
    }

    /**
     * Endpoint to set the automatic water change volume for the marine aquarium.
     *
     * @param volume the desired volume of water to be changed automatically in liters.
     * @return a confirmation message with the set water change volume.
     */
    @PostMapping("/set-auto-water-change")
    public String setAutoWaterChange(@RequestParam double volume) {
        try {
            marineService.setAutoWaterChange(volume);
            return "Auto water change volume set to " + volume + " liters";
        } catch (Exception e) {
            throw new RuntimeException("Error setting auto water change volume: " + e.getMessage());
        }
    }
}
