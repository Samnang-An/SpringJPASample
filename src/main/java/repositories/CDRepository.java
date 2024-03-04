package repositories;

import domain.CD;
import domain.Customer;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CDRepository extends JpaRepository<CD, Long> {

  List<CD> findByArtistAndPriceLessThan(String artist, double price);

  List<CD> findByArtist(String artist);

  @Query("select c from CD c where c.artist= :artist and c.price> :price")
  List<CD> findByArtistAndPriceGreaterThan(@Param("artist") String artist, @Param("price") double price);

  @Query(value = "select * from product p where p.artist=:artist", nativeQuery = true)
  List<CD> findByArtistNative(@Param("artist") String artist);

}
