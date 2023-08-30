package mario.abraham.orderservice.repository;

import mario.abraham.orderservice.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

    void deleteByOrderNumber(String orderNumber);
}
