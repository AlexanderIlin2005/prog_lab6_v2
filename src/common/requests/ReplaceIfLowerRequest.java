package common.requests;

import BaseModel.City;

public class ReplaceIfLowerRequest extends Request {

    private int key;
    private City city;

    public ReplaceIfLowerRequest(int key, City city) {
        super("replace_if_lower");
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
