package common.requests;

import java.io.Serializable;

public class Request implements Serializable {
    public final String name;

    protected Request(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }
}
