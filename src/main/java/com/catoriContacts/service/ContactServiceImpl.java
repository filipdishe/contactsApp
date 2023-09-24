package com.catoriContacts.service;

import com.catoriContacts.dto.AddressBookDTO;
import com.catoriContacts.mapper.AddressBookMapper;
import com.catoriContacts.model.AddressBook;
import com.catoriContacts.repository.ContactRepositoryImpl;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class ContactServiceImpl implements ContactService {

    private final ContactRepositoryImpl contactRepository;

    private AddressBookMapper addressBookMapper;

    @Inject
    public ContactServiceImpl(ContactRepositoryImpl contactRepository, AddressBookMapper addressBookMapper) {
        this.contactRepository = contactRepository;
        this.addressBookMapper = addressBookMapper;
    }

    @Override
    public List<AddressBookDTO> getContacts() {
        // 1. Map DAO to DTO
        List<AddressBookDTO> contacts = contactRepository.listAll()
                .stream()
                .map(contact -> addressBookMapper.toDTO(contact))
                .collect(Collectors.toList());

        return contacts;
    }

    @Override
    public AddressBookDTO getContactById(Long contactId) {
        AddressBook contact = contactRepository.findById(contactId);
        return addressBookMapper.toDTO(contact);
    }

    @Override
    @Transactional
    public AddressBookDTO createNewContact(AddressBookDTO contact) {
        // 1. Map DTO to DAO
        AddressBook newContact = addressBookMapper.toDAO(contact);
        contactRepository.persist(newContact);

        boolean isPersisted = contactRepository.isPersistent(newContact);

        if (isPersisted) {
            return addressBookMapper.toDTO(newContact);
        } else {
            return null;
        }
    }

}
