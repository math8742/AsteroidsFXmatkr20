package dk.sdu.mmmi.cbse.asteroid;

import dk.sdu.mmmi.cbse.common.components.Component;
import dk.sdu.mmmi.cbse.common.components.Health;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.World;

import java.util.ServiceLoader;

public class AsteroidSplitter {

    ServiceLoader<Health> loader = ServiceLoader.load(Health.class);
    private Class<? extends Health> healthClass;

    public AsteroidSplitter() {
        for (Health health : loader) {
            this.healthClass = health.getClass();
        }
    }

    public void split(Asteroid asteroid, World world) {
        double radius = asteroid.getRadius();
        double x = asteroid.getX();
        double y = asteroid.getY();
        double rotation = asteroid.getRotation();
        double rotationL = rotation-90;
        double rotationR = rotation+90;

        world.removeEntity(asteroid);
        if (radius > 6){
            Entity asteroid1 = miniCopy(radius, x, y, rotationL);
            Entity asteroid2 = miniCopy(radius, x, y, rotationR);
            world.addEntity(asteroid1);
            world.addEntity(asteroid2);
        }
    }

    public Entity miniCopy(double radius, double x, double y, double rotation) {
        Entity asteroid = new Asteroid();
        int size = (int) (radius/2);
        int sides = 8;
        double[] asteroidCoordinates = new double[sides*2];
        for (int i = 0; i < sides; i++) {
            double angle = 2*Math.PI*i/sides;
            asteroidCoordinates[i*2] = Math.cos(angle)*size;
            asteroidCoordinates[i*2+1] = Math.sin(angle)*size;
        }
        asteroid.setPolygonCoordinates(asteroidCoordinates);
        asteroid.setX(x);
        asteroid.setY(y);
        asteroid.setRadius(size);
        asteroid.setRotation(rotation);

        // add health component
        if (healthClass != null){
            try {
                Health health = healthClass.getDeclaredConstructor().newInstance();
                health.setHealth(1);
                asteroid.addComponent((Component) health);
                System.out.println("class name = "+health.getClass().getSimpleName());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return asteroid;
    }
}
