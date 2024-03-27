package com.eazybytes.eazyschool.model;



import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;



@Data
@Table(name = "holidays")
@Entity
public class Holiday extends BaseEntity{

	@Id
    private String day;
    
    private String reason;
    
    @Enumerated(EnumType.STRING)
    private Type type;

    public enum Type {
        FESTIVAL, FEDERAL
    }
    
//	public String getDay() {
//		return day;
//	}
//
//	public String getReason() {
//		return reason;
//	}
//
//	public Type getType() {
//		return type;
//	}
     
    
//    public Holiday(String day, String reason, Type type, 
//            String createdBy, LocalDateTime createdAt, 
//            LocalDateTime updatedAt, String updatedBy) {
// 
//    	    super();
//            this.day = day;
//            this.reason = reason;
//            this.type = type;
//}

    
//    public Holiday(String day, String reason, Type type)  {
//    	this.day = day;
//    	this.reason = reason;
//    	this.type = type;
//    }
    
}
