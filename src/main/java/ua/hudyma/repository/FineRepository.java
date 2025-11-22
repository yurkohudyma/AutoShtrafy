package ua.hudyma.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.hudyma.domain.Fine;

import java.util.Optional;

public interface FineRepository extends JpaRepository<Fine, Long> {
    Optional<Fine> findByFineCode(String fineCode);
}
