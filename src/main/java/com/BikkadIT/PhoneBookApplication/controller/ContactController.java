package com.BikkadIT.PhoneBookApplication.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.BikkadIT.PhoneBookApplication.entities.Contact;
import com.BikkadIT.PhoneBookApplication.props.ApiProps;
import com.BikkadIT.PhoneBookApplication.props.AppConstants;
import com.BikkadIT.PhoneBookApplication.service.ContactServiceI;

@RestController
public class ContactController {

	@Autowired
	private ContactServiceI contactServiceI;
	
	@Autowired
	private ApiProps apiProps;

	@PostMapping(value = "/saveContact", consumes = "application/json")
	public ResponseEntity<String> saveContact(@RequestBody Contact contact) {

		boolean saveContact = contactServiceI.saveContact(contact);

		Map<String,String> messages = apiProps.getMessages();
		System.out.println(messages);
		if (saveContact) {
			String msg = messages.get(AppConstants.SUCCESS);
			return new ResponseEntity<String>(msg, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<String>(messages.get(AppConstants.FAIL), HttpStatus.CREATED);

		}
	}

	@GetMapping(value = "getAllContact", produces = "application/json")
	public ResponseEntity<List<Contact>> getAllContact() {

		List<Contact> allContact = contactServiceI.getAllContact();
		Stream<Contact> stream = allContact.stream();
		Stream<Contact> filter = stream.filter((contact) -> contact.getActiveSwitch()==AppConstants.Y);
		List<Contact> list = filter.collect(Collectors.toList());
		
		return new ResponseEntity<List<Contact>>(list, HttpStatus.OK);

	}

	@GetMapping(value = "/getContactById/{contactId}", produces = "application/json")
	public ResponseEntity<Contact> getContactById(@PathVariable Integer contactId) {
		Contact contact = contactServiceI.getContactById(contactId);
		return new ResponseEntity<Contact>(contact, HttpStatus.OK);

	}

	@PutMapping(value = "/updateContact", consumes = "application/json")
	public ResponseEntity<String> updateContact(@RequestBody Contact contact) {

		boolean saveContact = contactServiceI.updateContact(contact);

		 Map<String, String> messages = apiProps.getMessages();
		if (saveContact) {
			String msg =messages.get(AppConstants.UPADTE_SUCCESS);
			return new ResponseEntity<String>(msg, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<String>(messages.get(AppConstants.UPDATE_FAIL), HttpStatus.CREATED);

		}

	}
	
	@DeleteMapping(value="/deleteContact/{contactId}")
	public ResponseEntity<String> deleteContact(@PathVariable Integer contactId){
		boolean deleteContact = contactServiceI.deleteContact(contactId);
		
		Map<String, String> messages = apiProps.getMessages();
		if(deleteContact) {
			String msg = messages.get(AppConstants.DELETE_SUCCESS);
			return new ResponseEntity<String>(msg, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<String>(messages.get(AppConstants.DELETE_FAIL), HttpStatus.CREATED);

		}
		
		
		
		
	}
	
	
	@DeleteMapping(value="/deleteContactsoft/{contactId}")
	public ResponseEntity<String> deleteContactSoft(@PathVariable Integer contactId){
		boolean deleteContact = contactServiceI.deleteConatctSoft(contactId);
		
		if(deleteContact) {
			String msg = "Contact Deleted  Successfully";
			return new ResponseEntity<String>(msg, HttpStatus.CREATED);
		} else {
			return new ResponseEntity<String>("Contact not Deleted Successfully", HttpStatus.CREATED);

		}
		
	}
	

}
