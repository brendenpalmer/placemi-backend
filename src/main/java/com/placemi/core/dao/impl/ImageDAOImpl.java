package com.placemi.core.dao.impl;

import com.placemi.commons.DatabaseHelper;
import com.placemi.core.dao.ImageDAO;
import com.placemi.core.exceptions.ImageNotFoundException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Created by brendenpalmer on 2016-02-06.
 */
public class ImageDAOImpl implements ImageDAO {
    @Override
    public InputStream getImageLink() throws ImageNotFoundException {
        InputStream is = null;

        try {
            is = new FileInputStream("/img/random/cached/1920x1080.jpeg");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        if (is == null) {
            throw new ImageNotFoundException();
        }

        return is;
    }

    @Override
    public String getImageLink(String id) throws ImageNotFoundException {
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
