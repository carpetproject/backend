package com.example.carpet.Services;

import java.util.ArrayList;
import java.util.Optional;

import com.example.carpet.Models.Contact;
import com.example.carpet.Models.ContactDTO;

public interface ContactsService {

    public Optional<Contact> Get(int id);

    public ArrayList<Contact> GetAll();

    public boolean Create(ContactDTO contact);

    public boolean Update(int id, ContactDTO payload);

    public boolean Delete(int id);
}
