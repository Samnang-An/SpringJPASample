package repositories;

import domain.Order;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OrderRepository extends JpaRepository<Order, Long> {

  List<Order> findAll();

  @Query("select o.orderNumber from Order o where o.status='closed'")
  List<String> findOrderNumberByClosedStatus();

  @Query("select o.orderNumber from Order o join o.customer where o.customer.address.city= :city")
  List<String> findOrderNumberByCustomerCountry(@Param("city") String city);


}
