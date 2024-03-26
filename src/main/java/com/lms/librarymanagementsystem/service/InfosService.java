package com.lms.librarymanagementsystem.service;

import com.lms.librarymanagementsystem.model.Infos;
import com.lms.librarymanagementsystem.repository.InfosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InfosService {
    @Autowired
    private InfosRepository infosRepository;
    public Infos getAllInfos() {
        return infosRepository.findAll().get(0);
    }
    //findFirstByOrderByIdAsc
    public Infos getFirstInfos() {
        return infosRepository.findFirstByOrderByIdAsc();
    }

    public void updateInfos(Infos infos) {
        infosRepository.save(infos);
    }
}
