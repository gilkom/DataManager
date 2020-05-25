package gilko.marcin.datamanager.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class Product {
	private Long id;
	@NotBlank(message="Name is mandatory")
	@Size(min=2, max=30)
	private String name;
	@NotBlank(message="Brand is mandatory")
	private String brand;
	@NotBlank(message="Made in is mandatory")
	private String madein;
	@DecimalMin("0.01")
	@Digits(integer=6, fraction=2)
	private float price;
	
	public Product() {
	}
	public Product(String name, String brand, String madein, float price) {
		this.name = name;
		this.brand = brand;
		this.madein = madein;
		this.price = price;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getMadein() {
		return madein;
	}
	public void setMadein(String madein) {
		this.madein = madein;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	
}
