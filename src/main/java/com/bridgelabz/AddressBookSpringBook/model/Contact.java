package com.bridgelabz.AddressBookSpringBook.model;

import com.bridgelabz.AddressBookSpringBook.dto.ContactDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Entity
@NoArgsConstructor
@Data
@Table(name = "Contact_Table")
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String phoneNumber;
    private String email;
    @ElementCollection
    @CollectionTable(name = "Address_Table",joinColumns = @JoinColumn (name = "id"))
    @Column(name = "address")
    private List<String> address;
    private String state;
    private String city;
    private long pinCode;
    public Contact(ContactDTO contactDTO){

        this.name = contactDTO.name;
        this.phoneNumber= contactDTO.phoneNumber;
        this.email = contactDTO.email;
        this.address=contactDTO.address;
        this.state=contactDTO.state;
        this.city=contactDTO.city;
        this.pinCode=contactDTO.pinCode;

    }

}
