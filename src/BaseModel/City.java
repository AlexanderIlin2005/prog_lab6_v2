package BaseModel;

import server.managers.CollectionManager;
import server.utility.Element;
import server.utility.Validatable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.NoSuchElementException;
import java.util.Objects;

@XmlRootElement(name="City")
@XmlType(propOrder = {"id", "name", "coordinates", "creationDate", "area", "population", "metersAboveSeaLevel",
        "capital", "climate", "standard0fLiving", "governor"})
public class City extends Element implements Validatable, Serializable {
    private Integer id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным,
    // Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private String creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private long area; //Значение поля должно быть больше 0
    private long population; //Значение поля должно быть больше 0, Поле не может быть null
    private int metersAboveSeaLevel;
    private boolean capital;
    private Climate climate; //Поле может быть null
    private StandardOfLiving standard0fLiving; //Поле может быть null
    private Human governor; //Поле не может быть null

    private static int nextId = 1;
    LocalDateTime crdate;
    transient DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


    public City(String name, Coordinates coordinates, long area, Long population,
                Integer metersAboveSeaLevel, boolean capital, Climate climate,
                StandardOfLiving standard0fLiving, Human governor){
        //this.id = nextId;
        this.name = name;
        this.coordinates = coordinates;
        LocalDateTime crdate = LocalDateTime.now(ZoneId.of("Europe/Moscow"));
        this.creationDate = crdate.format(formatter);
        this.area = area;
        this.population = population;
        this.metersAboveSeaLevel = metersAboveSeaLevel;
        this.capital = capital;
        this.climate = climate;
        this.standard0fLiving = standard0fLiving;

        this.governor = governor;
    }

    public City(){}
    @Override
    public boolean validate() {
        //System.out.println("id = "+id + " nextId="+nextId);
        if (id == null || id <= 0) {
            id = nextId;
            //return false;
        }

        if (name == null || name.isEmpty()) {
            return false;
        }

        if (coordinates == null) {
            return false;
        }

        if (creationDate == null) {
            //creationDate = LocalDateTime.now(ZoneId.of("Europe/Moscow"));
            return false;
        }

        if (area <= 0) {
            return false;
        }

        if (population <= 0) {
            return false;
        }

        // standard0fLiving can be null

        if (governor == null) {
            return false;
        }

        return true;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        City city = (City) obj;
        return area == city.area &&
                capital == city.capital &&
                Objects.equals(id, city.id) &&
                Objects.equals(name, city.name) &&
                Objects.equals(coordinates, city.coordinates) &&
                Objects.equals(creationDate, city.creationDate) &&
                Objects.equals(population, city.population) &&
                Objects.equals(metersAboveSeaLevel, city.metersAboveSeaLevel) &&
                climate == city.climate &&
                standard0fLiving == city.standard0fLiving &&
                Objects.equals(governor, city.governor);
    }



    @Override
    public int hashCode() {
        return Objects.hash(id, name, coordinates, creationDate, area, population, metersAboveSeaLevel, capital, climate, standard0fLiving, governor);
    }

    @XmlElement
    public int getId() {
        return id;
    }

    public void setId(Integer newId){
        this.id = newId;
    }

    @XmlElement
    public String getName() {
        return name;
    }

    public void setName(String newName){
        this.name = newName;
    }

    @XmlElement
    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates newCoordinates){
        this.coordinates = newCoordinates;
    }





    @XmlElement
    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String newDate){
        crdate = LocalDateTime.parse(newDate, formatter);
        this.creationDate = newDate;
    }
    @XmlElement
    public Long getPopulation() {
        return population;
    }

    public void setPopulation(Long newPopulation) {
        this.population = newPopulation;
    }

    @XmlElement
    public Integer getMetersAboveSeaLevel() {
        return metersAboveSeaLevel;
    }

    public void setMetersAboveSeaLevel(Integer newMetersAboveSeaLevel){
        this.metersAboveSeaLevel = newMetersAboveSeaLevel;
    }


    @XmlElement
    public boolean getCapital() {
        return capital;
    }

    public void setCapital(boolean newStatus) {
        this.capital = newStatus;
    }
    @XmlElement
    public Climate getClimate() {
        return climate;
    }

    public void setClimate(Climate newClimate) {
        this.climate = newClimate;
    }


    public void setStandard0fLiving(StandardOfLiving standard0fLiving) {
        this.standard0fLiving = standard0fLiving;
    }
    @XmlElement
    public StandardOfLiving getStandard0fLiving() {
        return standard0fLiving;
    }

    public void setGovernor(Human governor) {
        this.governor = governor;
    }
    @XmlElement
    public Human getGovernor() {
        return governor;
    }

    public void setArea(long area) {
        this.area = area;
    }
    @XmlElement
    public long getArea() {
        return area;
    }


    public static void touchNextId() {
        nextId++;
    }

    public static int getMaxId(){
        return nextId;
    }

    public static void updateNextId(CollectionManager collectionManager) {
        int maxId = 0;
        try {
            maxId = collectionManager.getCollection().lastKey();
        } catch (NoSuchElementException exc) {
            maxId = 0;
        }
        nextId = maxId + 1;
    }

    @Override
    public int compareTo(Element element) {
        return (int)(this.id - element.getId());
    }

    @Override public String toString() {
        return "id: " + id + "\n" + "name: " + name + "\n" + "coordinates: " + coordinates + "\n"
                + "creationDate: " + creationDate + "\n" + "area: " + area + "\n" + "population: " + population
                + "\n" + "metersAboveSeaLevel: " + metersAboveSeaLevel + "\n" + "capital: " + capital + "\n"
                + "climate: " + climate + "\n" + "standardOfLiving: " + standard0fLiving + "\n" + "governor: " + governor;
    }

}
