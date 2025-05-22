package dk.sdu.mmmi.cbse.asteroid;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.GameKeys;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

import java.util.Collection;
import java.util.ServiceLoader;

import static java.util.stream.Collectors.toList;


public class AsteroidControlSystem implements IEntityProcessingService {

    @Override
    public void process(GameData gameData, World world) {
        for (Entity asteroid: world.getEntities(Asteroid.class)) {
            double rotation = asteroid.getRotation();
            double radians = Math.toRadians(rotation);
            double changeX = Math.cos(radians)*0.5;
            double changeY = Math.sin(radians)*0.5;
            double newX = asteroid.getX()+changeX;
            double newY = asteroid.getY()+changeY;
            boolean bounced = false;

            if (newX < 0 || newX > gameData.getDisplayWidth()) {
                rotation = 180 -rotation;
                bounced = true;
                newX = Math.max(0, Math.min(newX, gameData.getDisplayWidth()));
            }

            if (newY < 0 || newY > gameData.getDisplayHeight()) {
                rotation = 360 -rotation;
                bounced = true;
                newY = Math.max(0, Math.min(newY, gameData.getDisplayHeight()));
            }

            if (bounced) {
                asteroid.setRotation((rotation+360)%360);
            }

            asteroid.setX(newX);
            asteroid.setY(newY);
        }
    }
}
