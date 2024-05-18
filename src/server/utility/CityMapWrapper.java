package server.utility;

import BaseModel.City;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.TreeMap;

@XmlRootElement
public class CityMapWrapper {

    private TreeMap<Integer, City> cityMap;

    public CityMapWrapper() {
    }

    public CityMapWrapper(TreeMap<Integer, City> cityMap) {
        this.cityMap = cityMap;
    }

    @XmlElement
    public TreeMap<Integer, City> getCityMap() {
        return cityMap;
    }

    public void setCityMap(TreeMap<Integer, City> cityMap) {
        this.cityMap = cityMap;
    }
}