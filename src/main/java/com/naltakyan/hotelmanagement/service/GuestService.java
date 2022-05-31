package com.naltakyan.hotelmanagement.service;

import com.naltakyan.hotelmanagement.model.Guest;
import com.naltakyan.hotelmanagement.repository.GuestRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.util.Assert.notNull;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@AllArgsConstructor
public class GuestService {

    private final GuestRepository guestRepository;

    private boolean existsById(Long id) {
        return guestRepository.existsById(id);
    }

    public Guest findById(Long id) {
        Guest guest = guestRepository.findById(id).orElse(null);
        if (guest == null) {
            throw new EntityNotFoundException("Cannot find Guest with id: " + id);
        } else {
            return guest;
        }
    }

    public List<Guest> findAll(final Pageable pageable) {return guestRepository.findAll(pageable).getContent();}


    @Transactional
    public Guest save(Guest guest){
        notNull(guest,"Guest should be null");
        notNull(guest.getFirstName(),"Guest Name should be null");
        notNull(guest.getPassport(),"Guest Passport should be null");
        notNull(guest.getPhone(),"Guest Phone should be null");
        notNull(guest.getEmail(),"Guest Email should be null");
        if(guest.getId()!= null && existsById(guest.getId())){
            throw new EntityExistsException("Guest with id: " + guest.getId() + " already exists");
        }
        return guestRepository.save(guest);
    }

    @Transactional
    public void update(Guest guest) {
        notNull(guest, "Guest should not be null");
        notNull(guest.getFirstName(), "Guest Name should not be null");
        notNull(guest.getPassport(),"Guest Passport should be null");
        notNull(guest.getPhone(),"Guest Phone should be null");
        notNull(guest.getEmail(),"Guest Email should be null");        if (!existsById(guest.getId())) {
            throw new EntityNotFoundException("Cannot find Contact with id: " + guest.getId());
        }
        guestRepository.save(guest);
    }


    public void deleteById(Long id) {
        if (!existsById(id)) {
            throw new EntityNotFoundException("Cannot find guest with id: " + id);
        }
        guestRepository.deleteById(id);
    }

    public Long count() {
        return guestRepository.count();
    }
}

