package softuni.exam.util;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;

public interface XmlParser {

    <T> T parseXml(String filePath, Class<T> tClass) throws JAXBException, FileNotFoundException;


}
