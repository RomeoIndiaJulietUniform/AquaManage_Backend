package com.DragonFish.aquaManage.SensorService.Service;

import com.DragonFish.aquaManage.SensorService.Entity.SensorData;
import org.springframework.stereotype.Service;
import java.util.Random;

@Service
public class SensorModelService {

    private static final double WATER_DENSITY = 1000;
    private static final double SPECIFIC_HEAT = 4184;
    private static final double GRAVITY = 9.81;
    private final Random random = new Random();

    public double calculateTemperature(SensorData sensorData) {
        double heatEnergy = 100;
        double waterMass = 10;
        double temperatureChange = heatEnergy / (waterMass * SPECIFIC_HEAT);
        return sensorData.getRoomTemperature() + temperatureChange * Math.sin(sensorData.getTime());
    }

    public double calculateFlowSpeed(SensorData sensorData) {
        return Math.sqrt((2 * (sensorData.getPressure() + WATER_DENSITY * GRAVITY * sensorData.getHeight())) / WATER_DENSITY);
    }

    public double calculatePressure(SensorData sensorData) {
        return WATER_DENSITY * (GRAVITY * sensorData.getHeight() + (0.5 * sensorData.getVelocity() * sensorData.getVelocity()));
    }

    public double simulateLightIntensity() {
        return random.nextInt(1000);
    }

    public double simulateNitrate() {
        return 10 + random.nextDouble() * 5;
    }

    public double simulateSalinity() {
        return 35 + random.nextDouble();
    }

    public double simulatePh() {
        return 6.5 + random.nextDouble() * 2;
    }

    public double simulateCalcium() {
        return 400 + random.nextDouble() * 50;
    }

    public double simulateAlkalinity() {
        return 8 + random.nextDouble() * 3;
    }

    public double simulateNitrite() {
        return random.nextDouble();
    }
}
