package repositories;

import domain.Customer;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

  List<Customer> findAllBy();
  List<Customer> findByCounty(String country);

  @Query("select concat(c.firstName,' ' ,c.lastName) as fullname from Customer c where c.address.country= :country")
  List<String> findFullnameByCountry(@Param("country") String country);

}
