package com.DragonFish.aquaManage.ActuatorService.Controllers;

import com.DragonFish.aquaManage.ActuatorService.Services.MarineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/actuators/Marine")
public class MarineServiceController {

    private final MarineService marineService;

    @Autowired
    public MarineServiceController(MarineService marineService) {
        this.marineService = marineService;
    }

    @GetMapping("/greet")
    public String getGreet() {
        try {
            return "Hello from Actuator";
        } catch (Exception e) {
            throw new RuntimeException("Error in getGreet: " + e.getMessage());
        }
    }

    @PostMapping("/set-saltwater-ratio")
    public String setSaltwaterMixerRatio(@RequestParam double ratio) {
        try {
            marineService.setSaltwaterMixerRatio(ratio);
            return "Saltwater mixer ratio set to " + ratio;
        } catch (Exception e) {
            throw new RuntimeException("Error setting saltwater mixer ratio: " + e.getMessage());
        }
    }

    @PostMapping("/set-calcium-rate")
    public String setCalciumDosingRate(@RequestParam double rate) {
        try {
            marineService.setCalciumDosingRate(rate);
            return "Calcium dosing rate set to " + rate + " mL/hour";
        } catch (Exception e) {
            throw new RuntimeException("Error setting calcium dosing rate: " + e.getMessage());
        }
    }

    @PostMapping("/set-alkalinity-rate")
    public String setAlkalinityDosingRate(@RequestParam double rate) {
        try {
            marineService.setAlkalinityDosingRate(rate);
            return "Alkalinity dosing rate set to " + rate + " mL/hour";
        } catch (Exception e) {
            throw new RuntimeException("Error setting alkalinity dosing rate: " + e.getMessage());
        }
    }

    @PostMapping("/set-skimmer-efficiency")
    public String setSkimmerEfficiency(@RequestParam double efficiency) {
        try {
            marineService.setSkimmerEfficiency(efficiency);
            return "Skimmer efficiency set to " + efficiency + "%";
        } catch (Exception e) {
            throw new RuntimeException("Error setting skimmer efficiency: " + e.getMessage());
        }
    }

    @PostMapping("/set-heater-temp")
    public String setHeaterTemperature(@RequestParam double temperature) {
        try {
            marineService.setHeaterTemperature(temperature);
            return "Heater temperature set to " + temperature + "°C";
        } catch (Exception e) {
            throw new RuntimeException("Error setting heater temperature: " + e.getMessage());
        }
    }

    @PostMapping("/set-light-duration")
    public String setLightTimeDuration(@RequestParam int duration) {
        try {
            marineService.setLightTimeDuration(duration);
            return "Light duration set to " + duration + " minutes";
        } catch (Exception e) {
            throw new RuntimeException("Error setting light duration: " + e.getMessage());
        }
    }

    @PostMapping("/set-water-pump-rate")
    public String setWaterPumpFlowRate(@RequestParam double flowRate) {
        try {
            marineService.setWaterPumpFlowRate(flowRate);
            return "Water pump flow rate set to " + flowRate + " L/h";
        } catch (Exception e) {
            throw new RuntimeException("Error setting water pump flow rate: " + e.getMessage());
        }
    }

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