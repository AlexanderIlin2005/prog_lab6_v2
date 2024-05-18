package common.requests;

import BaseModel.City;

public class ReplaceIfGreaterRequest extends Request {

    private int key;
    private City city;

    public ReplaceIfGreaterRequest(int key, City city) {
        super("replace_if_greater");
        this.key = key;
        this.city = city;
    }

    public City getCity() {
        return city;
    }

    public int getKey() {
        return key;
    }
}
