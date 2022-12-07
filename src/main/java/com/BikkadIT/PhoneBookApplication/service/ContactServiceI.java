package com.BikkadIT.PhoneBookApplication.service;

import java.util.List;

import com.BikkadIT.PhoneBookApplication.entities.Contact;

public interface ContactServiceI {

	public abstract boolean saveContact(Contact contact);
	
	public abstract List<Contact> getAllContact();
	
	public abstract Contact getContactById(Integer id);
	
	public abstract boolean updateContact(Contact contact);
	
	public abstract boolean deleteContact(Integer id);
}
