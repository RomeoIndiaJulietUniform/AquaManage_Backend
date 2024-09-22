package com.DragonFish.aquaManage.SensorService.Repository;
import com.DragonFish.aquaManage.SensorService.Entity.SensorData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SensorDataRepository extends JpaRepository<SensorData, Long> {
}

