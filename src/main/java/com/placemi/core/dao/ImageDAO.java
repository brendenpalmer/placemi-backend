package com.placemi.core.dao;

import com.placemi.core.exceptions.ImageNotFoundException;

import java.awt.*;

/**
 * @author Created by brendenpalmer on 2016-02-06.
 */

public interface ImageDAO {
    Image getImage(int id) throws ImageNotFoundException;
}
