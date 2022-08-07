package com.promineotech.jeep.controller;

import com.promineotech.jeep.entity.Image;
import com.promineotech.jeep.entity.Jeep;
import com.promineotech.jeep.entity.JeepModel;
import com.promineotech.jeep.service.JeepSalesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
public class DefaultJeepSalesController implements JeepSalesController {
    @Autowired
    private JeepSalesService jeepSalesService;

    @Override
    public ResponseEntity<byte[]> retrieveImage(String imageId) {
        log.debug("Retrieving image with ID = {}", imageId);
        Image image = jeepSalesService.retrieveImage(imageId);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", image.getMimeType().getMimeType());
        headers.add("Content-Length", Integer.toString(image.getData().length));

        return ResponseEntity.ok().headers(headers).body(image.getData());
    }

    @Override
    public List<Jeep> fetchJeeps(JeepModel model, String trim) {
        log.debug("model={}, trim={}", model, trim);
        return jeepSalesService.fetchJeeps(model, trim);
        //    log.info("model={}, trim={}", model, trim);
    }

//    @Override
//    public String uploadImage(MultipartFile image, Long jeepPK) {
//        return null;
//    }

    @Override
    public String uploadImage(MultipartFile image, Long jeepPK) {
        log.debug("image={}, jeepPK={}", image, jeepPK);
        String imageId = jeepSalesService.uploadImage(image, jeepPK);
        return "{\"imageID\":\"" + imageId + "\"}";
    }


}
