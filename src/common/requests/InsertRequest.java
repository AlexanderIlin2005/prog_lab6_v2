package common.requests;

import BaseModel.City;

public class InsertRequest extends Request {

    private int key;
    private City city;

    public InsertRequest(int key, City city) {
        super("insert");
        this.key = key;
        this.city = city;
    }

    public int getKey(){
        return key;
    }

    public City getCity(){
        return city;
    }
}
