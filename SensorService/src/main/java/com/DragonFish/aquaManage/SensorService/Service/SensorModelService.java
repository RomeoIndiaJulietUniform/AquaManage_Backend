package com.DragonFish.aquaManage.SensorService.Service;

import com.DragonFish.aquaManage.SensorService.Entity.SensorData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.Random;

/**
 * SensorModelService provides methods for calculating various aquarium-related
 * parameters such as temperature, flow speed, and pressure, as well as simulating
 * key environmental factors like light intensity, nitrate, salinity, pH, calcium,
 * alkalinity, and nitrite levels. These calculations help monitor and manage
 * the aquatic environment.
 */
@Service
@Slf4j
public class SensorModelService {

    // Constants related to water properties
    private static final double WATER_DENSITY = 1000; // kg/m^3
    private static final double SPECIFIC_HEAT = 4184; // J/(kg * °C)
    private static final double GRAVITY = 9.81;      // m/s^2

    private final Random random = new Random(); // For simulating random sensor values

    /**
     * Calculates the temperature change in the water based on the heat energy
     * absorbed and the water's properties.
     *
     * @param sensorData Data from the sensor including time and room temperature.
     * @return The calculated water temperature.
     */
    public double calculateTemperature(SensorData sensorData) {
        double heatEnergy = 100; // Example heat energy in joules
        double waterMass = 10;   // Mass of water in kg
        double temperatureChange = heatEnergy / (waterMass * SPECIFIC_HEAT);

        log.debug("Heat Energy: {}", heatEnergy);
        log.debug("Water Mass: {}", waterMass);
        log.debug("Specific Heat: {}", SPECIFIC_HEAT);
        log.debug("Calculated Temperature Change: {}", temperatureChange);

        // Calculate final water temperature using the room temperature and time
        double resultTemperature = sensorData.getRoomTemperature() + temperatureChange * Math.sin(sensorData.getTime());

        log.info("Room Temperature: {}", sensorData.getRoomTemperature());
        log.info("Calculated Result Temperature: {}", resultTemperature);

        return resultTemperature;
    }

    /**
     * Calculates the flow speed of water based on pressure and height.
     *
     * @param sensorData Data from the sensor including pressure and height.
     * @return The calculated flow speed of water.
     */
    public double calculateFlowSpeed(SensorData sensorData) {
        double flowSpeed = Math.sqrt((2 * (sensorData.getPressure() + WATER_DENSITY * GRAVITY * sensorData.getHeight())) / WATER_DENSITY);
        log.info("Calculated Flow Speed: {}", flowSpeed);
        return flowSpeed;
    }

    /**
     * Calculates water pressure based on height and velocity of the water.
     *
     * @param sensorData Data from the sensor including height and velocity.
     * @return The calculated pressure in pascals.
     */
    public double calculatePressure(SensorData sensorData) {
        double pressure = WATER_DENSITY * (GRAVITY * sensorData.getHeight() + (0.5 * sensorData.getVelocity() * sensorData.getVelocity()));
        log.info("Calculated Pressure: {}", pressure);
        return pressure;
    }

    /**
     * Simulates the intensity of light in the aquarium. This simulates a random
     * light intensity value between 0 and 1000.
     *
     * @return The simulated light intensity.
     */
    public double simulateLightIntensity() {
        double lightIntensity = random.nextInt(1000);
        log.info("Simulated Light Intensity: {}", lightIntensity);
        return lightIntensity;
    }

    /**
     * Simulates nitrate levels in the aquarium water, generating random values
     * between 10 and 15.
     *
     * @return The simulated nitrate level.
     */
    public double simulateNitrate() {
        double nitrate = 10 + random.nextDouble() * 5;
        log.info("Simulated Nitrate: {}", nitrate);
        return nitrate;
    }

    /**
     * Simulates the salinity of the aquarium water, generating a random value
     * around 35 PSU (practical salinity units).
     *
     * @return The simulated salinity value.
     */
    public double simulateSalinity() {
        double salinity = 35 + random.nextDouble();
        log.info("Simulated Salinity: {}", salinity);
        return salinity;
    }

    /**
     * Simulates the pH level of the aquarium water, generating a random value
     * between 6.5 and 8.5.
     *
     * @return The simulated pH level.
     */
    public double simulatePh() {
        double ph = 6.5 + random.nextDouble() * 2;
        log.info("Simulated pH: {}", ph);
        return ph;
    }

    /**
     * Simulates the calcium level in the aquarium water, generating a random value
     * between 400 and 450 mg/L.
     *
     * @return The simulated calcium level.
     */
    public double simulateCalcium() {
        double calcium = 400 + random.nextDouble() * 50;
        log.info("Simulated Calcium: {}", calcium);
        return calcium;
    }

    /**
     * Simulates the alkalinity of the water, generating a random value between 8 and 11 dKH.
     *
     * @return The simulated alkalinity level.
     */
    public double simulateAlkalinity() {
        double alkalinity = 8 + random.nextDouble() * 3;
        log.info("Simulated Alkalinity: {}", alkalinity);
        return alkalinity;
    }

    /**
     * Simulates the nitrite level in the aquarium, generating a small random value.
     *
     * @return The simulated nitrite level.
     */
    public double simulateNitrite() {
        double nitrite = random.nextDouble();
        log.info("Simulated Nitrite: {}", nitrite);
        return nitrite;
    }
}
