package dk.sdu.mmmi.cbse.collision;

import dk.sdu.mmmi.cbse.common.components.Health;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.ScoreAsteroid;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;
import java.util.ArrayList;

public class Collision implements IPostEntityProcessingService {

    @Override
    public void process(GameData gameData, World world) {
        ArrayList<Entity> entities = new ArrayList<>(world.getEntities());
        for (int i = 0; i < entities.size(); i++) {
            Entity entity1 = entities.get(i);
            for (int j = i + 1; j < entities.size(); j++) {
                Entity entity2 = entities.get(j);
                checkCollision(entity1, entity2, world);
            }
        }
    }

    public void checkCollision(Entity entity1, Entity entity2, World world){
        double radius1 = entity1.getRadius();
        double radius2 = entity2.getRadius();
        double differenceX = entity1.getX() - entity2.getX();
        double differenceY = entity1.getY() - entity2.getY();
        double distanceSquared = differenceX * differenceX + differenceY * differenceY;
        double sumRadius = radius1 + radius2;

        if (distanceSquared < sumRadius * sumRadius) {
            doCollision(entity1, entity2, world);
        }
    }

    public void doCollision(Entity entity1, Entity entity2, World world){
        String type1 = entity1.getClassType();
        String type2 = entity2.getClassType();
        ScoreAsteroid scoreAsteroid = new ScoreAsteroid();

        if (type1.equals("Player") || type1.equals("Enemy") || type2.equals("Player") || type2.equals("Enemy")){
            if (type1.equals("Bullet")) {
                world.removeEntity(entity1);
                getHealthComponent(entity2).subtractHealth(1);
            } else if (type2.equals("Bullet")) {
                world.removeEntity(entity2);
                getHealthComponent(entity1).subtractHealth(1);
            } else if (type1.equals("Asteroid")) {
                world.removeEntity(entity2);
            } else if (type2.equals("Asteroid")) {
                world.removeEntity(entity1);
            }
        } else if (type1.equals("Asteroid") || type2.equals("Asteroid")) {
            if (type1.equals("Bullet")) {
                world.removeEntity(entity1);
                getHealthComponent(entity2).setHealth(0);
                world.setScore(scoreAsteroid.asteroidDestroyed(1));
                System.out.println("Sent score to microservice");
            } else if (type2.equals("Bullet")) {
                world.removeEntity(entity2);
                getHealthComponent(entity1).setHealth(0);
                world.setScore(scoreAsteroid.asteroidDestroyed(1));
                System.out.println("Sent score to microservice");
            }
        }
    }


    public Health getHealthComponent(Entity entity) {
        return (Health) entity.getComponent("HealthComponent");
    }
}
