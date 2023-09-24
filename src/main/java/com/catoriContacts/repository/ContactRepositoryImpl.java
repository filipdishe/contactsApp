package com.catoriContacts.repository;

import com.catoriContacts.model.AddressBook;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ContactRepositoryImpl implements PanacheRepository<AddressBook> {

}
