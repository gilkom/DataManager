package gilko.marcin.datamanager.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class Customer {
	private Long id;
	@NotBlank(message="Name is mandatory")
	@Size(min=2, max=30)
	private String name;
	@NotBlank(message="Email is mandatory")
	@Email
	private String email;
	@NotBlank(message="Address is mandatory")
	@Size(min=2, max=30)
	private String address;
	@Column(nullable = true, length = 64)
	private String photo;
	
	public Customer() {
		
	}
	public Customer(String name, String email, String address, String photo) {
		this.name = name;
		this.email = email;
		this.address = address;
		this.photo = photo;
	}
	
	@Transient
	public String getPhotosImagePath() {
		if( photo == null || photo.isEmpty()) {
			return "/user-photos/okladka.png";
		}
		return "/user-photos/" + id + "/" + photo;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
	
}
