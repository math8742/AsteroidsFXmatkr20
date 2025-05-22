package dk.sdu.mmmi.cbse.asteroid;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

public class AsteroidPlugin implements IGamePluginService {


    @Override
    public void start(GameData gameData, World world) {
        // Add entities to the world
        for (int i = 0; i <= 4; i++) {
            Entity asteroid = createAsteroid(gameData);
            world.addEntity(asteroid);
        }
    }

    private Entity createAsteroid(GameData gameData) {
        Entity asteroid = new Asteroid();
        int size = (int)((Math.random()*4)+10);
        int sides = 8;
        double[] asteroidCoordinates = new double[sides*2];
        for (int i = 0; i < sides; i++) {
            double angle = 2*Math.PI*i/sides;
            asteroidCoordinates[i*2] = Math.cos(angle)*size;
            asteroidCoordinates[i*2+1] = Math.sin(angle)*size;
        }
        asteroid.setPolygonCoordinates(asteroidCoordinates);
        asteroid.setX((int) (Math.random() * 800));
        asteroid.setY(0);
        asteroid.setRadius(size);
        asteroid.setRotation((int) (Math.random() * 90));
        return asteroid;
    }

    @Override
    public void stop(GameData gameData, World world) {
        // Remove entities
        for (Entity asteroid: world.getEntities(Asteroid.class)){
            world.removeEntity(asteroid);
        }
    }
}
