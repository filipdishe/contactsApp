package com.catoriContacts.service;

import com.catoriContacts.dto.AddressBookDTO;

import java.util.List;

public interface ContactService {

    List<AddressBookDTO> getContacts();

    AddressBookDTO getContactById(Long contactId);

    AddressBookDTO createNewContact(AddressBookDTO contact);
}
