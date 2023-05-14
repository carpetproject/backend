package com.example.carpet.Controllers;

import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.carpet.Models.Contact;
import com.example.carpet.Models.ContactDTO;
import com.example.carpet.Services.ContactsService;

@RestController
@RequestMapping("/contacts")
public class ContactsController {

    private ContactsService contactsService;

    public ContactsController(ContactsService contactsService) {
        this.contactsService = contactsService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contact> GetById(@PathVariable int id) {

        var contact = contactsService.Get(id);

        if (!contact.isPresent()) {

            return new ResponseEntity<Contact>(null, null, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Contact>(contact.get(), null, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<ArrayList<Contact>> GetAll() {
        return new ResponseEntity<ArrayList<Contact>>(contactsService.GetAll(), null, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Boolean> Create(@RequestBody ContactDTO contact) {
        if (contactsService.Create(contact)) {
            return new ResponseEntity<Boolean>(true, null, HttpStatus.CREATED);
        }

        return new ResponseEntity<Boolean>(false, null, HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Boolean> Update(@PathVariable("id") int id, @RequestBody ContactDTO payload) {
        if (contactsService.Update(id, payload)) {
            return new ResponseEntity<Boolean>(true, null, HttpStatus.OK);
        }

        return new ResponseEntity<Boolean>(false, null, HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> Delete(@PathVariable("id") int id) {
        if (contactsService.Delete(id)) {
            return new ResponseEntity<Boolean>(true, null, HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<Boolean>(false, null, HttpStatus.NOT_FOUND);
    }
}
