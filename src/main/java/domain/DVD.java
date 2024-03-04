package domain;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import org.apache.logging.log4j.util.Strings;


@Entity
@Data
@DiscriminatorValue("dvd")
public class DVD extends Product {

	private String genre;

	@Override
	public String getArtist() {
		return Strings.EMPTY;
	}

	@Override
	public double getApy() {
		return 0;
	}
}