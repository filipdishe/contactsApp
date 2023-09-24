package com.catoriContacts.mapper;

import com.catoriContacts.dto.AddressBookDTO;
import com.catoriContacts.model.AddressBook;
import org.mapstruct.Mapper;

@Mapper(componentModel = "cdi")
public interface AddressBookMapper {

    AddressBookDTO toDTO(AddressBook addressBook);

    AddressBook toDAO(AddressBookDTO addressBookDTO);
}
