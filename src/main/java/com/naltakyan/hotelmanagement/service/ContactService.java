package com.naltakyan.hotelmanagement.service;

import java.util.List;

import com.naltakyan.hotelmanagement.model.Contact;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.naltakyan.hotelmanagement.repository.ContactRepository;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import static org.springframework.util.Assert.notNull;

@Service
@AllArgsConstructor
public class ContactService {

    private final ContactRepository contactRepository;

    private boolean existsById(Long id) {
        return contactRepository.existsById(id);
    }

    public Contact findById(Long id) {
        Contact contact = contactRepository.findById(id).orElse(null);
        if (contact==null) {
            throw new EntityNotFoundException("Cannot find Contact with id: " + id);
        }
        else return contact;
    }

    public List<Contact> findAll(final Pageable pageable) {
        return contactRepository.findAll(pageable).getContent();
    }

    public Contact save(Contact contact) {
        notNull(contact, "Contact should not be null");
        notNull(contact.getName(), "Contact Name should not be null");
        if (contact.getId() != null && existsById(contact.getId())) {
            throw new EntityExistsException("Contact with id: " + contact.getId() +
                    " already exists");
        }
        return contactRepository.save(contact);
    }

    public void update(Contact contact) {
        notNull(contact, "Contact should not be null");
        notNull(contact.getName(), "Contact Name should not be null");
        if (!existsById(contact.getId())) {
            throw new EntityNotFoundException("Cannot find Contact with id: " + contact.getId());
        }
        contactRepository.save(contact);
    }

    public void deleteById(Long id) {
        if (!existsById(id)) {
            throw new EntityNotFoundException("Cannot find contact with id: " + id);
        }
        contactRepository.deleteById(id);
    }

    public Long count() {
        return contactRepository.count();
    }
}
