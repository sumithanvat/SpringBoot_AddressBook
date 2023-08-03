package com.bridgelabz.AddressBookSpringBook.service;

import com.bridgelabz.AddressBookSpringBook.dto.ContactDTO;
import com.bridgelabz.AddressBookSpringBook.dto.ResponseDTO;
import com.bridgelabz.AddressBookSpringBook.exception.CustomException;
import com.bridgelabz.AddressBookSpringBook.exception.DuplicateEmailException;
import com.bridgelabz.AddressBookSpringBook.model.Contact;
import com.bridgelabz.AddressBookSpringBook.repository.ContactRepo;
import com.bridgelabz.AddressBookSpringBook.util.EmailService;
import com.bridgelabz.AddressBookSpringBook.util.JWTToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@Service
public class ContactService {
    @Autowired
    private ContactRepo contactRepo;
    @Autowired
    private JWTToken jwtToken;
    @Autowired
    private EmailService emailService;

    public Contact addContact(ContactDTO contactDTO) {
        // Check if the email already exists in the database
        String email = contactDTO.email;
        if (contactRepo.existsByEmail(email)) {
            throw new DuplicateEmailException("Email '" + email + "' is already taken. Please use a different email.");
        }

        // Email is not present in the database, so create and save the new employee
        Contact contactData = new Contact(contactDTO);
        return contactRepo.save(contactData);
    }
    //*************************************************************************************************************
    // Created method and logic for get contact from id
    public Contact getContactById(long id) {
        return contactRepo.findById(id).orElseThrow(() -> new CustomException("Message With Id:" + id + " Not Present"));
    }
    //************************************************************************************************************
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
  //***********************************************************************************************************
    public boolean deleteContact(long id) {
        // Check if Contact is present
        if (contactRepo.existsById(id)) {
            contactRepo.deleteById(id); // Delete the contact from the repository.
            return true; // Indicate that the contact was successfully deleted.
        }
        return false; // The contact with the given ID was not found, no deletion occurred.
    }
  //*************************************************************************************************************
    public ResponseDTO addContactToken(ContactDTO contactDTO ){
        Contact contact= new Contact(contactDTO);
        contactRepo.save(contact);
        //ContactService contactService;
        String token = jwtToken.createToken(contact.getId());
        emailService.sendEmail(contactDTO.getEmail(),"Data added successfully ",
                "Hy..."+contact.getName()+" \n\n we added data successfully \n\n"+contact);
        return new ResponseDTO(token,contact);

    }
    //**********************************************************************************************************
    public Contact getContactById(String token){
        long id=jwtToken.decodeToken(token);
        return contactRepo.findById(id)
                .orElseThrow(()->new CustomException("Employee not found with id: "+id));
    }
    //***************************************************************************************************************
    public void deleteContactByToken(String token) {
        long id = jwtToken.decodeToken(token);
        Contact contact = contactRepo.findById(id)
                .orElseThrow(() -> new CustomException("Contact not found with id: " + id));
        contactRepo.delete(contact);
    }
    //*****************************************************************************************************************
    public Contact updateContactByToken(String token, ContactDTO contactDTO) {
        long id = jwtToken.decodeToken(token);
        Contact contact = contactRepo.findById(id)
                .orElseThrow(() -> new CustomException("Contact not found with c id: " + id));

        // Update the contact properties based on the provided contactDTO
        // For example, you can use setters to update the properties as needed.
        contact.setName(contactDTO.getName());
        contact.setEmail(contactDTO.getEmail());
        // Add other propartiee you want to update
        contactRepo.save(contact); // Save the updated contact
        return contact;
    }

}
