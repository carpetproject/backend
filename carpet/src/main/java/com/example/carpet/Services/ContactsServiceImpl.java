package com.example.carpet.Services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.carpet.Models.Contact;
import com.example.carpet.Models.ContactDTO;
import com.example.carpet.Repositories.ContactsRepository;

@Service
public class ContactsServiceImpl implements ContactsService {

    private ContactsRepository contactsRepo;

    public ContactsServiceImpl(ContactsRepository contactsRepo) {
        this.contactsRepo = contactsRepo;
    }

    @Override
    public Optional<Contact> Get(int id) {
        try {

            return contactsRepo.Get(id);

        } catch (SQLException e) {
            e.printStackTrace();

            return Optional.ofNullable(null);
        }
    }

    @Override
    public ArrayList<Contact> GetAll() {
        try {

            return contactsRepo.GetAll();

        } catch (SQLException e) {
            e.printStackTrace();

            return new ArrayList<Contact>();
        }
    }

    @Override
    public boolean Create(ContactDTO contact) {
        try {

            return contactsRepo.Create(contact);

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean Update(int id, ContactDTO payload) {
        try {

            return contactsRepo.Update(id, payload);

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean Delete(int id) {
        try {

            return contactsRepo.Delete(id);

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
