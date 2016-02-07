package com.placemi.core.controller;

import com.placemi.core.exceptions.ImageNotFoundException;
import com.placemi.core.manager.impl.ImageManagerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.io.IOException;

/**
 * @author Created by brendenpalmer on 2016-02-06.
 */
@RestController
public class ImageController {
    @RequestMapping(value = "/{width}x{height}", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    @Autowired
    public byte[] image(@PathParam("width") int width,
                        @PathParam("height") int height,
                        ImageManagerImpl imageManager) throws IOException, ImageNotFoundException {
        return imageManager.getImage(width, height);
    }

    @RequestMapping(value = "/g/{width}x{height}", method = RequestMethod.GET)
    public String grayscaleImage() {
        return "Grayscale test";
    }

    @RequestMapping(value = "/{id}/{width}x{height}", method = RequestMethod.GET)
    @Autowired
    public String userImage(@PathParam("id") String id,
                            ImageManagerImpl imageManager) {
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
