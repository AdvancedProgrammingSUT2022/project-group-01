package utils;

import com.thoughtworks.xstream.XStream;

public class Converter {
    public static String toXML(Object object) {
        XStream xStream = new XStream();
        xStream.setMode(XStream.ID_REFERENCES);
        return xStream.toXML(object).replace("  ", "");
    }

    public static Object fromXML(String xml) {
        XStream xStream = new XStream();
        xStream.setMode(XStream.ID_REFERENCES);
        return xStream.fromXML(xml);
    }
}
