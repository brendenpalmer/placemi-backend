package com.placemi.commons;

import com.placemi.core.exceptions.ImageNotFoundException;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.FilenameFilter;
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
     * Gets the database properties file
     *
     * @return The database properties file
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
     * Gets a random image path
     *
     * @param id The ID or unique identifier
     * @return The random image path
     */
    public static String getRandomImagePath(String id) throws ImageNotFoundException {
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
}
