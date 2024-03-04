package domain;


import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.Data;
import lombok.ToString;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
		name="product_type",
		discriminatorType = DiscriminatorType.STRING
)
public abstract class Product {

	@Id
	@GeneratedValue
	private long id;

	private String name;

	private String description;

	private double price;

	public Product() {
	}

	public Product(String name, String description, double price) {
		this.name = name;
		this.description = description;
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString(){
		return this.getClass().getName()+
				" : "+
				getName() + " ; " +
			  getDescription()+ " ; " +
				getPrice() + " ; " +
				getArtist() + " ; " +
				getApy() + " ; " +
				getGenre();
	}



	public abstract String getArtist();
	public abstract String getGenre();
	public abstract double getApy();



}
