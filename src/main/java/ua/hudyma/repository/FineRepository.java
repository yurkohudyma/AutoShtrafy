package ua.hudyma.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.hudyma.domain.Fine;

public interface FineRepository extends JpaRepository<Fine, Long> {
}
