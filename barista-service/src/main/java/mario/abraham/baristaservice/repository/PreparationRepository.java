package mario.abraham.baristaservice.repository;

import mario.abraham.baristaservice.model.Preparation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PreparationRepository extends JpaRepository<Preparation, Long> {

    Preparation findByOrderNumber(String orderNumber);

    void deleteByOrderNumber(String orderNumber);
}
