package com.bridgelabz.AddressBookSpringBook.repository;

import com.bridgelabz.AddressBookSpringBook.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepo extends JpaRepository<Contact,Long> {
    boolean existsByEmail(String email);
}
