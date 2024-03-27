package com.eazybytes.eazyschool.model;




import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;


/*
@Data annotation is provided by Lombok library which generates getter, setter,
equals(), hashCode(), toString() methods & Constructor at compile time.
This makes our code short and clean. 
@Data only work some time so It's best to write setter(), getter() function
* */
@Entity
@Table(name = "contact_msg")
@Data
public class Contact extends BaseEntity{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator="native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "contact_id")
	private int contactId;
	
	// Inside the Model, we can Implement Validation for each attribute inside Contact class
	// with either @NotBlank, @Size, @Pattern,....
	@NotBlank(message = "Name must not be blank")
	@Size(min = 3, message = "Name must be at least 3 characters long")
    private String name;
	
	@NotBlank(message = "Mobile Number must not be blank")
	@Pattern(regexp="(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
    private String mobileNum;
	
	@NotBlank(message = "Email must not be blank")
	@Email(message = "Please provide a valid email address")
    private String email;
	
	@NotBlank(message = "Subject must not be blank")
	@Size(min = 5, message = "Subject must be at least 5 characters long")
    private String subject;
	
	@NotBlank(message = "Message must not be blank")
	@Size(min = 10, message = "Message must be at least 10 characters long")
    private String message;
	
	private String status;
	
	public Contact() {
		
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getMobileNum() {
		return mobileNum;
	}
	
	public void setMobileNum(String mobileNum) {
		this.mobileNum = mobileNum;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getSubject() {
		return subject;
	}
	
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	@Override
	public String toString() {
		return "Contact: {" +
					"name='" + name + '\'' +
					"mobileNum='" + mobileNum + '\'' +
					"email='" + email + '\'' +
					"subject='" + subject + '\'' +
					"message='" + message + '\'' +
					'}';
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getContactId() {
		return contactId;
	}

	public void setContactId(int contactId) {
		this.contactId = contactId;
	}
}
