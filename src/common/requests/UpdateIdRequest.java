package common.requests;

import BaseModel.City;

public class UpdateIdRequest extends Request {

    private final City city;
    private int id;
    public UpdateIdRequest(int id, City city) {
        super("update_id");
        this.id = id;
        this.city = city;
    }

    public int getId(){
        return id;
    }

    public City getCity() {
        return city;
    }
}
