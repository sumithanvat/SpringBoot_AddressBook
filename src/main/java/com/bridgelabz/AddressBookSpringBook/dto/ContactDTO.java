package com.bridgelabz.AddressBookSpringBook.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.util.List;

public class ContactDTO {
    @Pattern(regexp = "^[A-Z]{1}[a-z]{2,}$",message ="Name not valid")
    public String name;
    @Pattern(regexp ="^[+]{1}[0-9]{2}[6-9]{1}[0-9]{9}$",message = "Invalid phone number")
    public String phoneNumber;
    @NotEmpty
    public String email;
    @NotEmpty
    public List<String> address;
    @NotEmpty
    public String state;
    @NotEmpty
    public String city;
    @NotNull
    public long pinCode;
}
