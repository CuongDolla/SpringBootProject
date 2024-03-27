package com.eazybytes.eazyschool.service;

import com.eazybytes.eazyschool.constants.EazySchoolConstants;
import com.eazybytes.eazyschool.model.Contact;
import com.eazybytes.eazyschool.repository.ContactRepository;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
@Slf4j, is a Lombok-provided annotation that will automatically generate an SLF4J
Logger static property in the class at compilation time.
* */
@Slf4j
@Service
// This class will do its function where The Hibernate Validation confirm that the data the end-user send is correct
// So in this class, we will code the business logic where the company will process the data to its own business logic thanks to @Service

public class ContactService {

	
	@Autowired
	private ContactRepository contactRepository;
	
	public ContactService() {
		System.out.println("Contact Service Bean initialized");
	}
	
	
	// This function will let us know if the data that the end-user have submitted it is saved in the database true or not
	// If the function return us FALSE, mean that The procedure of storing the data inside the database is not functional
	// If it is true then the data is confirmly storing inside the database 
    public boolean saveMessageDetails(Contact contact){
        boolean isSaved = false;
        contact.setStatus(EazySchoolConstants.OPEN);
        //contact.setCreatedBy(EazySchoolConstants.ANONYMOUS);
        //contact.setCreatedAt(LocalDateTime.now());
        // This variable result is the crucial one to confirm if the data is storing inside the database true or not
        Contact savedContact = contactRepository.save(contact);
        if (null!= savedContact && savedContact.getContactId() > 0 ) {
        	isSaved = true;
        }
        return isSaved;
    }

    // This function is setting the filter of all the message. It only take the message with the status of OPEN 
    public List<Contact> findMsgsWithOpenStatus(){
    	List<Contact> contactMsgs = contactRepository.findByStatus(EazySchoolConstants.OPEN);
    	return contactMsgs;
    	
    }

	public boolean updateMsgStatus(int contactId) {
		boolean isUpdated = false;
		Optional<Contact> contact = contactRepository.findById(contactId);
		contact.ifPresent(contact1 -> {
			contact1.setStatus(EazySchoolConstants.CLOSE);
			//contact1.setUpdateBy(updatedBy);
			//contact1.setUpdateAt(LocalDateTime.now());
		});
		Contact updatedContact = contactRepository.save(contact.get());
		if(null != updatedContact && updatedContact.getUpdateBy()!= null) {
			isUpdated = true;
		}
//		int result = contactRepository.updateMsgStatus(contactId,EazySchoolConstants.CLOSE,updatedBy);
//		if (result > 0) {
//			isUpdated = true;
//		}
		return isUpdated;
		
	}
    
}
