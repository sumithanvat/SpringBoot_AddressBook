package com.bridgelabz.AddressBookSpringBook.service;

import com.bridgelabz.AddressBookSpringBook.dto.ContactDTO;
import com.bridgelabz.AddressBookSpringBook.exception.CustomException;
import com.bridgelabz.AddressBookSpringBook.model.Contact;
import com.bridgelabz.AddressBookSpringBook.repository.ContactRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService {
    @Autowired
    private ContactRepo contactRepo;

    // Created Method and logic for Add contact
    public Contact addContact(ContactDTO contactDTO) {
        Contact contactData = new Contact(contactDTO);
        return contactRepo.save(contactData);
    }
    // Created method and logic for get contact from id
    public Contact getContactById(long id) {
        return contactRepo.findById(id).orElseThrow(() -> new CustomException("Message With Id:" + id + " Not Present"));
    }
    // Used method find all from ContactRepo
    public List<Contact> findAll() {
        return contactRepo.findAll();
    }
    // API method to update an Contact record by its unique identifier.
    public Contact updateContact(long id, ContactDTO contactDTO) {
        // Retrieve the existing Contact data by the provided ID.
        Contact contactData = getContactById(id);

        // Check if the contact data exists.
        if (contactData != null) {
            // Create a new Contact object from the provided ContactDTO.
            Contact saveContact = new Contact(contactDTO);

            // Set the ID of the new Contact to the provided ID, to update the existing record.
            saveContact.setId(id);

            // Save the updated contact data and return the updated Contact object.
            return contactRepo.save(saveContact);
        }

        // If the Contact data does not exist, return null.
        return null;
    }
    public boolean deleteContact(long id) {
        // Check if Contact is present
        if (contactRepo.existsById(id)) {
            contactRepo.deleteById(id);
            return true;
        }
        return false;
    }

}
