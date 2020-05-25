package gilko.marcin.datamanager.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Entity
public class Sale {
	private Long id;
	@NotBlank(message="Item name is mandatory")
	private String item;
	@DecimalMin("1.00")
	private float quantity;
	@DecimalMin("0.01")
	@Digits(integer=6, fraction=2)
	private float amount;
	
	public Sale() {
		
	}
	public Sale(String item, float quantity, float amount) {
		this.item = item;
		this.quantity = quantity;
		this.amount = amount;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public float getQuantity() {
		return quantity;
	}
	public void setQuantity(float quantity) {
		this.quantity = quantity;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	
}
