package ua.hudyma.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.hudyma.domain.Car;

public interface CarRepository extends JpaRepository<Car, Long> {
}
