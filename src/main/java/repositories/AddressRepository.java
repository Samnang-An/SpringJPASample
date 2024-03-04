package repositories;

import domain.Address;
import domain.CD;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface AddressRepository extends JpaRepository<Address, Long> {
  @Query(value = "select * from address a where a.country=:country", nativeQuery = true)
  List<Address> findByCountry(@Param("country") String country);



}
