package BaseModel;

import server.utility.Validatable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

@XmlType(propOrder = {"x", "y"})
public class Coordinates implements Validatable, Serializable {
    private float x; //Значение поля должно быть больше -584
    private float y; // Значение поля должно быть больше -469
    public Coordinates(){}

    public Coordinates(float x, float y) {
        this.x = x;
        this.y = y;
    }


    @XmlElement
    public float getX() {
        return x;
    }

    public void setX(float newX){
        this.x = newX;
    }

    @XmlElement
    public float getY() {
        return y;
    }

    public void setY(float newY){
        this.y = newY;
    }

    @Override
    public boolean validate() {
        return (x > -584) && (y > -469);
    }

    @Override
    public String toString(){
        return "x="+x+", y="+y;
    }
}
