package com.placemi.core.dao;

import com.placemi.core.exceptions.ImageNotFoundException;

/**
 * @author Created by brendenpalmer on 2016-02-06.
 */
public interface ImageDAO {
    byte[] getImage() throws ImageNotFoundException;

    String getImage(String id) throws ImageNotFoundException;
}
