package com.timtro247.maven1311.service.impl;

import com.timtro247.maven1311.model.Images;
import com.timtro247.maven1311.repository.ImageRepository;
import com.timtro247.maven1311.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageServiceImpl implements ImageService {
    private  ImageRepository imageRepository;

    @Autowired
    public ImageServiceImpl(ImageRepository imageRepository){
        this.imageRepository = imageRepository;
    }

    @Override
    public Images save(Images image){
        imageRepository.save(image);
        return image;
    }
}
