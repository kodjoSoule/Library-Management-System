package com.lms.librarymanagementsystem.service;

import com.lms.librarymanagementsystem.model.Admin;
import com.lms.librarymanagementsystem.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    @Autowired
    AdminRepository adminRepository;

    public void saveAdmin(Admin admin) {
        adminRepository.save(admin);
    }
}
