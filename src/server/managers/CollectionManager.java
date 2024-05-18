package server.managers;

import BaseModel.*;
import common.Responce;
import server.utility.Console;

import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

@XmlRootElement(name="CityCollections")
public class CollectionManager {

    private LocalDateTime lastInitTime;
    private LocalDateTime lastSaveTime;
    private DumpManager dumpManager;

    private Console console;

    @XmlElement(name="CitiesCollection")
    private TreeMap<Integer, City> collection = new TreeMap<>();

    public CollectionManager(DumpManager dumpManager, Console console) throws JAXBException, IOException {
        this.lastInitTime = null;
        this.lastSaveTime = null;
        this.dumpManager = dumpManager;
        this.console = console;
        try {
            loadCollection();
        } catch (JAXBException e) {
            console.printError("Не удалось загрузить коллекцию из файла");
        }
    }

    public CollectionManager(){}

    public City minByPopulation() {
        City cityWithMinPopulation = collection.entrySet()
                .stream()
                .min(Comparator.comparingLong(entry -> entry.getValue().getPopulation()))
                .map(Map.Entry::getValue)
                .orElse(null);
        return cityWithMinPopulation;
    }

    public long countByMetersAboveSeaLevel(Console console, Integer m){
        long count = collection.values().stream()
                .filter(city -> city.getMetersAboveSeaLevel() == m)
                .count();
        return count;
    }


    public TreeMap<Integer, City> getCollection(){
        return collection;
    }




    public LocalDateTime getLastInitTime() {
        return lastInitTime;
    }


    public LocalDateTime getLastSaveTime() {
        return lastSaveTime;
    }



    public void clearCollection() {
        collection.clear();
    }

    public void validateAll(Console console) throws IOException {

        for(Map.Entry<Integer, City> entry : collection.entrySet()) {
            Integer key = entry.getKey();
            City city = entry.getValue();
            if (!city.validate()){
                console.printError("Элемент коллекции с id="+city.getId()+" имеет невалидные поля");
            }
        }

        console.println("Загруженные c объекты валидны.");
    }

    public boolean checkExist(int id) {
        for(Map.Entry<Integer, City> entry : collection.entrySet()) {
            Integer key = entry.getKey();
            City city = entry.getValue();
            if (city.getId() == id){return true;}
        }

        return false;
    }

    public String collectionType() {
        return collection.getClass().getName();
    }

    public int collectionSize() {
        return collection.size();
    }

    public City getFirst(){
        return (City) collection.firstEntry();
    }

    public City getLast(){
        return (City) collection.lastEntry();
    }

    public void addToCollection(City city){
        collection.put(City.getMaxId(), city);
        City.touchNextId();
    }

    public City getByKey(int key){
        for(Map.Entry<Integer, City> entry : collection.entrySet()) {
            Integer cityId = entry.getKey();
            City city = entry.getValue();
            if (cityId == key){return city;}
        }

        return null;
    }

    public void insert(Integer key, City city){
        collection.put(key, city);
        city.setId(key);
    }

    public void removeGreaterKey(Integer thresholdKey){
        collection.keySet().removeIf(key -> key > thresholdKey);

    }






    private void loadCollection() throws JAXBException {
        collection = dumpManager.readCollection().getCollection();
        for(Map.Entry<Integer, City> entry : collection.entrySet()) {
            City city = entry.getValue();
            int key = entry.getKey();
            city.setId(key);
        }
        this.lastInitTime = LocalDateTime.now(ZoneId.of("Europe/Moscow"));
    }

    public void saveCollection(String filename, Console console) {
        //dumpManager.setFileName(filename);
        //dumpManager.writeCollection(collection);
        dumpManager.writeCollection(this);
        this.lastSaveTime = LocalDateTime.now(ZoneId.of("Europe/Moscow"));
    }

    @Override
    public String toString() {
        if (collection.isEmpty()) return "Коллекция пуста!";
        //var last = getLast();

        StringBuilder info = new StringBuilder();
        for(Map.Entry<Integer, City> entry : collection.entrySet()) {
            info.append(entry);
            info.append("\n\n");
        }
        return info.toString();
    }


    public void removeFromCollectionByKey(Integer key) {
        collection.remove(key);
    }

    public Responce groupCountingByArea(Responce responce) {
        TreeMap<Long, Integer> areaCountMap = new TreeMap<>();
        for (City city : collection.values()) {
            long area = city.getArea();
            areaCountMap.put(area, areaCountMap.getOrDefault(area, 0) + 1);
        }

        for (Long area : areaCountMap.keySet()) {
            responce.addString("Площадь: " + area + ", количество элементов: " + areaCountMap.get(area));
        }

        return responce;
    }

    public void update(Integer key, City city) {
        collection.put(key, city);
    }

    public void replaceIfGreater(Integer key, City city){
        if (collection.get(key).getPopulation() > city.getPopulation()){
            collection.put(key, city);
        }
    }


    public void replaceIfLower(Integer key, City city){
        if (collection.get(key).getPopulation() < city.getPopulation()){
            collection.put(key, city);
        }
    }
}
