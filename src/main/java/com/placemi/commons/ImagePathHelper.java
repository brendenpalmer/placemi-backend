package com.placemi.commons;

import com.placemi.core.exceptions.ImageNotFoundException;
import com.placemi.core.model.Image;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author Created by brendenpalmer on 2016-02-06.
 */
public class ImagePathHelper {
    private static Properties properties = null;
    private static final String resourcePath = "/application.properties";
    private static String basePath = "";

    static {
        try {
            getProperties();
            basePath = properties.getProperty("base.image.path");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private ImagePathHelper() {
        // do nothing, private member, no need for external access
    }

    /**
     * Gets the application properties file
     *
     * @return The application properties
     * @throws IOException
     */
    private static synchronized Properties getProperties() throws IOException {
        if (properties != null) {
            return properties;
        } else {
            InputStream inputStream = new ClassPathResource(resourcePath).getInputStream();
            properties = new Properties();
            properties.load(inputStream);
            inputStream.close();
            return properties;
        }
    }

    /**
     * Gets the potential cached image path
     *
     * @param img The image
     * @return The potential cached image path
     */
    public static String getCachedImagePath(Image img) {
        String path = basePath;

        if (StringUtils.isEmpty(img.getId())) {
            path += "/random/images/cached/" +
                    (img.isGrayscale() ? "1" : "0") +
                    "quality" +
                    Integer.toString(img.getQuality()) +
                    "w" +
                    Integer.toString(img.getWidth()) +
                    "h" +
                    Integer.toString(img.getHeight()) +
                    img.getFileName();
        } else {
            path += "/img/uploads/cached/" +
                    img.getId() + "/" +
                    (img.isGrayscale() ? "1" : "0") +
                    "quality" +
                    Integer.toString(img.getQuality()) +
                    "w" +
                    Integer.toString(img.getWidth()) +
                    "h" +
                    Integer.toString(img.getHeight()) +
                    img.getFileName();
        }

        return path;
    }

    /**
     * Gets the correct image path for a random image
     *
     * @return The random image path
     * @throws ImageNotFoundException
     */
    public static String getBaseImagePath() throws ImageNotFoundException {
        File directory = new File(basePath + "/random");
        File[] images = directory.listFiles((File dir, String name) ->
                name.toLowerCase().endsWith(".png") ||
                        name.toLowerCase().endsWith(".jpg") ||
                        name.toLowerCase().endsWith(".jpeg")
        );

        long length = images.length;

        if (length == 0) {
            throw new ImageNotFoundException();
        }

        int index = (int) (Math.random() * ((length - 1) + 1));
        return images[index].getPath();
    }

    /**
     * Gets the correct image path. If no ID is
     * provided then a random image will be provided.
     * Otherwise, the image path will be returned
     * based on the ID.
     *
     * @param fileName The filename
     * @return The image path
     * @throws ImageNotFoundException
     */
    public static String getBaseImagePath(String fileName) throws ImageNotFoundException {
        File img = new File(basePath + "/img/uploads/" + fileName);

        if (img.length() == 0) {
            throw new ImageNotFoundException();
        }

        return img.getPath();
    }
}
