package com.placemi.core.dao.impl;

import com.placemi.commons.DatabaseHelper;
import com.placemi.core.dao.ImageDAO;
import com.placemi.core.exceptions.ImageNotFoundException;
import com.placemi.core.model.Image;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Created by brendenpalmer on 2016-02-06.
 */
public class ImageDAOImpl implements ImageDAO {
    @Override
    public Image getImage() throws ImageNotFoundException {
        return new Image();
    }

    @Override
    public String getImage(String id) throws ImageNotFoundException {
        String link = null;
        try {
            Connection conn = DatabaseHelper.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT link FROM pic WHERE slug = ?");
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                link = rs.getString("link");
            }

            conn.close();
            ps.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ImageNotFoundException();
        }

        if (link == null) {
            throw new ImageNotFoundException();
        }

        return link;
    }
}
