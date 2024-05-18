package server.managers;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import server.utility.Console;
import java.io.*;

public class DumpManager {

    private Console console;
    private String fileName;

    public DumpManager(Console console, String fileName) {
        this.console = console;
        this.fileName = fileName;
    }

    public void setFileName(String newName){
        this.fileName = fileName;
    }

    public void writeCollection(CollectionManager collectionManager) {
        //TreeMap<Integer, City> cityMap
        //JAXBContext context = JAXBContext.newInstance(CityMapWrapper.class);
        //Marshaller marshaller = context.createMarshaller();
        //marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        //marshaller.marshal(new CityMapWrapper(cityMap), new File(fileName));
        try {
            JAXBContext context = JAXBContext.newInstance(CollectionManager.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(fileName));
            marshaller.marshal(collectionManager, bufferedOutputStream);
            bufferedOutputStream.close();
            System.out.println("saved");
        } catch (JAXBException | IOException e) {
            //e.printStackTrace();
            console.printError("Ошибка записи коллекции в файл");
        }
    }

    public CollectionManager readCollection() throws JAXBException {
        //JAXBContext context = JAXBContext.newInstance(CityMapWrapper.class);
        //Unmarshaller unmarshaller = context.createUnmarshaller();
        //CityMapWrapper cityMapWrapper = (CityMapWrapper) unmarshaller.unmarshal(new File(fileName));
        //TreeMap<Integer, City> readCityMap = cityMapWrapper.getCityMap();
        //return readCityMap;
        JAXBContext context = JAXBContext.newInstance(CollectionManager.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        return (CollectionManager) unmarshaller.unmarshal(new File(fileName));
    }
}
