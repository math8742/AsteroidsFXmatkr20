package dk.sdu.mmmi.cbse.common.data;

import dk.sdu.mmmi.cbse.common.components.Component;
import java.io.Serializable;
import java.util.HashMap;
import java.util.UUID;

public class Entity implements Serializable {

    private final UUID ID = UUID.randomUUID();
    
    private double[] polygonCoordinates;
    private double x;
    private double y;
    private double rotation;
    private double radius;
    private HashMap<String, Component> components = new HashMap<>();
            

    public String getID() {
        return ID.toString();
    }


    public void setPolygonCoordinates(double... coordinates ) {
        this.polygonCoordinates = coordinates;
    }

    public double[] getPolygonCoordinates() {
        return polygonCoordinates;
    }
       

    public void setX(double x) {
        this.x =x;
    }

    public double getX() {
        return x;
    }

    
    public void setY(double y) {
        this.y = y;
    }

    public double getY() {
        return y;
    }

    public void setRotation(double rotation) {
        this.rotation = rotation;
    }

    public double getRotation() {
        return rotation;
    }

    public void setRadius(double radius){this.radius = radius;}

    public double getRadius(){return radius;}

    public void addComponent(Component component){components.put(component.getClass().getSimpleName(), component);}

    public Component getComponent(String type){return components.get(type);}
}
