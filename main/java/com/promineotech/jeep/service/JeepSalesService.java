package com.promineotech.jeep.service;

import com.promineotech.jeep.entity.Image;
import com.promineotech.jeep.entity.Jeep;
import com.promineotech.jeep.entity.JeepModel;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface JeepSalesService {
    List<Jeep> fetchJeeps(JeepModel model, String trim);

    String uploadImage(MultipartFile image, Long jeepPK);

    Image retrieveImage(String imageId);
}
