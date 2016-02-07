package com.placemi.core.controller;

import com.placemi.core.exceptions.ImageNotFoundException;
import com.placemi.core.manager.impl.ImageManagerImpl;
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
    public byte[] image() throws IOException, ImageNotFoundException {
        return new ImageManagerImpl().getImage();
    }

    @RequestMapping(value = "/g/{width}x{height}", method = RequestMethod.GET)
    public String grayscaleImage() {
        return "Grayscale test";
    }

    @RequestMapping(value = "/{id}/{width}x{height}", method = RequestMethod.GET)
    public String userImage(@PathParam("id") String id) {
        try {
            ImageManagerImpl tmp = new ImageManagerImpl();
            return tmp.getImage(id);
        } catch (ImageNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
