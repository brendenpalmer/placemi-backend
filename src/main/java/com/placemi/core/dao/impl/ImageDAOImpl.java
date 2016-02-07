package com.placemi.core.dao.impl;

import com.placemi.commons.DatabaseHelper;
import com.placemi.commons.ImagePathHelper;
import com.placemi.core.dao.ImageDAO;
import com.placemi.core.exceptions.ImageNotFoundException;
import org.imgscalr.Scalr;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Created by brendenpalmer on 2016-02-06.
 */
public class ImageDAOImpl implements ImageDAO {
    @Override
    public byte[] getImage() throws ImageNotFoundException {
        try {
            BufferedImage img = Scalr.crop(ImageIO.read(new FileInputStream(ImagePathHelper.getRandomImagePath())), 1000, 1000);
            ByteArrayOutputStream bao = new ByteArrayOutputStream();

            // Write to output stream
            ImageIO.write(img, "jpg", bao);

            return bao.toByteArray();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
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
