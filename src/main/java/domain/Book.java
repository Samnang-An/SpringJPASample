package domain;


import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;
import org.apache.logging.log4j.util.Strings;


@Entity
@Data
@DiscriminatorValue("book")
public class Book extends Product {
	double apy;

	@Override
	public String getArtist() {
		return Strings.EMPTY;
	}

	@Override
	public String getGenre() {
		return Strings.EMPTY;
	}
}
