package com.DragonFish.aquaManage.SensorService.Service;

import com.DragonFish.aquaManage.SensorService.Entity.SensorData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.Random;

@Service
@Slf4j
public class SensorModelService {

    private static final double WATER_DENSITY = 1000;
    private static final double SPECIFIC_HEAT = 4184;
    private static final double GRAVITY = 9.81;
    private final Random random = new Random();

    public double calculateTemperature(SensorData sensorData) {
        double heatEnergy = 100;
        double waterMass = 10;
        double temperatureChange = heatEnergy / (waterMass * SPECIFIC_HEAT);

        log.debug("Heat Energy: {}", heatEnergy);
        log.debug("Water Mass: {}", waterMass);
        log.debug("Specific Heat: {}", SPECIFIC_HEAT);
        log.debug("Calculated Temperature Change: {}", temperatureChange);

        double resultTemperature = sensorData.getRoomTemperature() + temperatureChange * Math.sin(sensorData.getTime());

        log.info("Room Temperature: {}", sensorData.getRoomTemperature());
        log.info("Calculated Result Temperature: {}", resultTemperature);

        return resultTemperature;
    }

    public double calculateFlowSpeed(SensorData sensorData) {
        double flowSpeed = Math.sqrt((2 * (sensorData.getPressure() + WATER_DENSITY * GRAVITY * sensorData.getHeight())) / WATER_DENSITY);
        log.info("Calculated Flow Speed: {}", flowSpeed);
        return flowSpeed;
    }

    public double calculatePressure(SensorData sensorData) {
        double pressure = WATER_DENSITY * (GRAVITY * sensorData.getHeight() + (0.5 * sensorData.getVelocity() * sensorData.getVelocity()));
        log.info("Calculated Pressure: {}", pressure);
        return pressure;
    }

    public double simulateLightIntensity() {
        double lightIntensity = random.nextInt(1000);
        log.info("Simulated Light Intensity: {}", lightIntensity);
        return lightIntensity;
    }

    public double simulateNitrate() {
        double nitrate = 10 + random.nextDouble() * 5;
        log.info("Simulated Nitrate: {}", nitrate);
        return nitrate;
    }

    public double simulateSalinity() {
        double salinity = 35 + random.nextDouble();
        log.info("Simulated Salinity: {}", salinity);
        return salinity;
    }

    public double simulatePh() {
        double ph = 6.5 + random.nextDouble() * 2;
        log.info("Simulated Ph: {}", ph);
        return ph;
    }

    public double simulateCalcium() {
        double calcium = 400 + random.nextDouble() * 50;
        log.info("Simulated Calcium: {}", calcium);
        return calcium;
    }

    public double simulateAlkalinity() {
        double alkalinity = 8 + random.nextDouble() * 3;
        log.info("Simulated Alkalinity: {}", alkalinity);
        return alkalinity;
    }

    public double simulateNitrite() {
        double nitrite = random.nextDouble();
        log.info("Simulated Nitrite: {}", nitrite);
        return nitrite;
    }
}
