package com.placemi.core.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Created by brendenpalmer on 2016-02-06.
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
