package com.lms.librarymanagementsystem.service;

import com.lms.librarymanagementsystem.repository.ExemplaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExamplaireService {
    @Autowired
    ExemplaireRepository exemplaireRepository;
    public void deleteById(Long id){
        exemplaireRepository.deleteById(id);
    }
    public void deleteAll(){
        exemplaireRepository.deleteAll();
    }



}
