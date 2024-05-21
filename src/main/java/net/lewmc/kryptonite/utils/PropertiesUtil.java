package net.lewmc.kryptonite.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesUtil {

    private final String file;

    public PropertiesUtil(String file) {
        this.file = file;
    }

    public void setProperty(String property, String value) {
        try {
            FileInputStream in = new FileInputStream(this.file);
            Properties props = new Properties();
            props.load(in);

            FileOutputStream out = new FileOutputStream(this.file);
            props.setProperty(property, value);
            props.store(out, null);

            in.close();
            out.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getProperty(String property) {
        try {
            FileInputStream in = new FileInputStream(this.file);
            Properties props = new Properties();

            props.load(in);
            String output = props.getProperty(property);

            in.close();

            return output;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}