package com.bridgelabz.AddressBookSpringBook.controller;

import com.bridgelabz.AddressBookSpringBook.dto.ContactDTO;
import com.bridgelabz.AddressBookSpringBook.dto.ResponseDTO;

import com.bridgelabz.AddressBookSpringBook.service.ContactService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class ContactController {
    @Autowired // This annotation automatically injects an instance of ContactService into this field.
    private ContactService contactService; // Field to hold the reference to the ContactService bean.

//----------------------------------------------------------------------------------------------------------------------
    // API endpoint to add a new contact.
    @PostMapping("/addcontact")
    public ResponseEntity<ResponseDTO> addContact(@Valid @RequestBody ContactDTO contactDTO) {
        // Log that the data is added successfully.
        log.info("Data added Successfully");

        // Create a ResponseDTO with the success message and the data returned by the contactService.
        ResponseDTO responseDTO = new ResponseDTO("Data Added Successfully", contactService.addContact(contactDTO));

        // Return the ResponseDTO with HttpStatus.CREATED (201 - Created) status code.
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    //------------------------------------------------------------------------------------------------------------------
    // API endpoint to retrieve a contact by its unique identifier.
    @GetMapping("/getcontactbyid/{id}")
    public ResponseEntity<ResponseDTO> getContactById(@PathVariable long id) {
        // Log a warning that data is being retrieved based on the provided ID.
        log.warn("Data is retrieving from id");

        // Create a ResponseDTO with the success message and the data returned by the contactService.
        ResponseDTO responseDTO = new ResponseDTO("Data Fetched Successfully", contactService.getContactById(id));

        // Return the ResponseDTO with HttpStatus.OK (200 - OK) status code.
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    //------------------------------------------------------------------------------------------------------------------
    // API endpoint to retrieve all contact data.
    @GetMapping("/getallcontact")
    public ResponseEntity<ResponseDTO> getAllContact() {
        // Log a warning that all data is being retrieved.
        log.warn("Retrieving all data");

        // Create a ResponseDTO with the success message and the data returned by the contactService.
        ResponseDTO responseDTO = new ResponseDTO("All Data Retrieve Successfully", contactService.findAll());

        // Return the ResponseDTO with HttpStatus.OK (200 - OK) status code.
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    //------------------------------------------------------------------------------------------------------------------
    // API endpoint to update a contact by its unique identifier.
    @PutMapping("/updatecontact/{id}")
    public ResponseEntity updateContact(@PathVariable long id, @RequestBody ContactDTO contactDTO) {
        // Create a ResponseDTO with the success message and the data returned by the contactService.updateContact method.
        ResponseDTO responseDTO = new ResponseDTO("Contact Updated", contactService.updateContact(id, contactDTO));

        // Return the ResponseDTO with HttpStatus.OK (200 - OK) status code.
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    //------------------------------------------------------------------------------------------------------------------
    // API endpoint to delete an employee by its unique identifier.
    @DeleteMapping("/deletebyid/{id}")
    public ResponseEntity<ResponseDTO> deleteEmployee(@PathVariable long id) {
        // Attempt to delete the employee with the provided ID.
        boolean deleted = contactService.deleteContact(id);

        // Check if the employee was deleted successfully.
        if (deleted) {
            // Create a ResponseDTO with the success message and the deleted employee's ID.
            ResponseDTO responseDTO = new ResponseDTO("Data Deleted Successfully", id);

            // Return the ResponseDTO with HttpStatus.OK (200 - OK) status code.
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        } else {
            // Create a ResponseDTO with the "Data Not Found" message and the provided ID.
            ResponseDTO responseDTO = new ResponseDTO("Data Not Found", id);

            // Return the ResponseDTO with HttpStatus.NOT_FOUND (404 - Not Found) status code.
            return new ResponseEntity<>(responseDTO, HttpStatus.NOT_FOUND);
        }
    }
}
