package com.example.workshopnextleveltechnologiesmvc.util;

import javax.xml.bind.JAXBException;
import java.io.FileNotFoundException;

public interface XmlParser {

    <T> T xmlParse(String filePath, Class<T> tClass) throws JAXBException, FileNotFoundException;


}
