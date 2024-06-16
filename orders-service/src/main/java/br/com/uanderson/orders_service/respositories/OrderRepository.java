package br.com.uanderson.orders_service.respositories;

import br.com.uanderson.orders_service.model.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
