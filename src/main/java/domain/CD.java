package domain;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.NamedQuery;
import lombok.Data;
import lombok.ToString;
import org.apache.logging.log4j.util.Strings;


@Entity
@Data
@ToString(callSuper = true)
@DiscriminatorValue("cd")
@NamedQuery(
		name="CD.findByArtist",
		query = "select c from CD c where c.artist= :artist"
)
public class CD extends Product {
	String artist;

	@Override
	public String getGenre() {
		return Strings.EMPTY;
	}

	@Override
	public double getApy() {
		return 0;
	}
}
