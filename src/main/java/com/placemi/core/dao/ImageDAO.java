package com.placemi.core.dao;

import com.placemi.core.exceptions.ImageNotFoundException;
import com.placemi.core.model.Image;

/**
 * @author Created by brendenpalmer on 2016-02-06.
 */
public interface ImageDAO {
    Image getImage() throws ImageNotFoundException;

    String getImage(String id) throws ImageNotFoundException;
}
