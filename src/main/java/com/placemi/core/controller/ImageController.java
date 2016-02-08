package com.placemi.core.controller;

import com.placemi.core.exceptions.ImageNotFoundException;
import com.placemi.core.manager.impl.ImageManagerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @author Created by brendenpalmer on 2016-02-06.
 */
@RestController
public class ImageController {
    @Autowired
    private ImageManagerImpl imageManager;

    @RequestMapping(value = "/{width}x{height}", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] image(@PathVariable String width,
                        @PathVariable String height) throws IOException, ImageNotFoundException {
        return imageManager.getImage(Integer.parseInt(width), Integer.parseInt(height));
    }

    @RequestMapping(value = "/g/{width}x{height}", method = RequestMethod.GET)
    public String grayscaleImage() {
        return "Grayscale test";
    }

    @RequestMapping(value = "/{id}/{width}x{height}", method = RequestMethod.GET)
    public String userImage(@PathVariable String id) {
        try {
            return imageManager.getImage(id);
        } catch (ImageNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
