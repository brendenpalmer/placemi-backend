package com.placemi.core.dao;

import com.placemi.core.exceptions.ImageNotFoundException;

import java.io.InputStream;

/**
 * @author Created by brendenpalmer on 2016-02-06.
 */
public interface ImageDAO {
    InputStream getImageLink() throws ImageNotFoundException;

    String getImageLink(String id) throws ImageNotFoundException;
}
