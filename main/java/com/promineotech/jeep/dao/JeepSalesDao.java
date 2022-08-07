package com.promineotech.jeep.dao;

import com.promineotech.jeep.entity.Image;
import com.promineotech.jeep.entity.Jeep;
import com.promineotech.jeep.entity.JeepModel;

import java.util.List;
import java.util.Optional;

public interface JeepSalesDao {
//    static void saveImage(Image image) {
//    }

//    @param model
//    @param trim
//    @return



    List<Jeep> fetchJeeps(JeepModel model, String trim);
    void saveImage(Image image);

    Optional<Image> retrieveImage(String imageId);
}
