package com.BikkadIT.PhoneBookApplication.service;

import java.util.List;

import com.BikkadIT.PhoneBookApplication.entities.Contact;

public interface ContactServiceI {

	public abstract boolean saveContact(Contact contact);
	
	public abstract List<Contact> getAllContact();
}
