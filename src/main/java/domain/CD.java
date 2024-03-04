package domain;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import org.apache.logging.log4j.util.Strings;


@Entity
@Data
@DiscriminatorValue("cd")
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
