package com.placemi.core.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by brendenpalmer on 20160206.
 */

@RestController
public class ImageController {
    @RequestMapping(value="/{width}x{height}", method= RequestMethod.GET)
    public String image() {
        return "Test";
    }

    @RequestMapping(value="/g/{width}x{height}", method= RequestMethod.GET)
    public String grayscaleImage() {
        return "Grayscale test";
    }
}
