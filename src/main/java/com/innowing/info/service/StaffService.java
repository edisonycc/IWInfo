package com.innowing.info.service;

import com.innowing.info.repository.StaffRepository;
import com.innowing.info.entity.Staff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
public class StaffService {


    private final StaffRepository staffRepository;

    @Autowired
    public StaffService(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    public List<Staff> getAllUser() {
       System.out.println(staffRepository.findAll());
       return staffRepository.findAll();
    }

    public void addNewUser(Staff staff) {
//        Optional<Staff> userByEmail = staffRepository.findStaffByEmail(staff.getEmail());
//        if (userByEmail.isPresent()) {
//            throw new IllegalStateException("email taken");
//        }
        staffRepository.save(staff);
    }

    public void deleteUser(Long userId) {
        boolean exists = staffRepository.existsById(userId);
        if (!exists) {
         throw new IllegalStateException(
                 "user with id " + userId + " does not exists");
        }
        staffRepository.deleteById(userId);
    }

    @Transactional
    public void updateStaff(Long staffId, String name, String email) {
        Staff staff = staffRepository.findById(staffId).orElseThrow(() -> new IllegalStateException(
                "user with id " + staffId + " does not exist"
        ));

        if (name != null  && name.length() > 0 && !Objects.equals(staff.getName(), name))  {
            staff.setName(name);
        }

        if (email!= null  && email.length() > 0 && !Objects.equals(staff.getEmail(), email)) {
            Optional<Staff> userByEmail = staffRepository.findUserByEmail(email);
            if (userByEmail.isPresent()) {
                throw new IllegalStateException("email taken");
            }
            staff.setEmail(email);
        }

    }
}
