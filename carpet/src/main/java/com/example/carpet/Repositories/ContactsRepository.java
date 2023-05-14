package com.example.carpet.Repositories;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

import com.example.carpet.Models.Contact;
import com.example.carpet.Models.ContactDTO;

public interface ContactsRepository {
    public Optional<Contact> Get(int id) throws SQLException;

    public ArrayList<Contact> GetAll() throws SQLException;

    public boolean Create(ContactDTO contact) throws SQLException;

    public boolean Update(int id, ContactDTO payload) throws SQLException;

    public boolean Delete(int id) throws SQLException;
}
