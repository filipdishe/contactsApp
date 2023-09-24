package com.catoriContacts.controller;

import com.catoriContacts.dto.AddressBookDTO;
import com.catoriContacts.service.ContactServiceImpl;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/contacts")
public class ContactController {

    private final ContactServiceImpl contactService;

    @Inject
    public ContactController(ContactServiceImpl contactService) {
        this.contactService = contactService;
    }

    @GET
    @RolesAllowed("individual")
    @Produces(MediaType.APPLICATION_JSON)
    public List<AddressBookDTO> getAllContacts() {
        return contactService.getContacts();
    }

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getContact(@PathParam("id") Long contactId) {
        AddressBookDTO contact = contactService.getContactById(contactId);

        if(contact != null) {
            return Response.ok(contact).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Contact with id " + contactId + " not found!")
                    .build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createNewContact(AddressBookDTO contact) {
        AddressBookDTO responseContact = contactService.createNewContact(contact);

        if (responseContact != null) {
            return Response.ok(responseContact).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST)
                    .build();
        }
    }

}
