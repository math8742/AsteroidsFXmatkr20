package dk.sdu.mmmi.cbse.collision;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;

import java.util.Objects;

public class Collision implements IPostEntityProcessingService {
    @Override
    public void process(GameData gameData, World world) {
        for (Entity entity1 : world.getEntities()) {
            for (Entity entity2 : world.getEntities()) {
                if (!Objects.equals(entity1.getID(), entity2.getID())) {
                    checkCollision(entity1, entity2, world);
                }
            }
        }
    }

    public void checkCollision(Entity entity1, Entity entity2, World world){
        double radius1 = entity1.getRadius();
        double radius2 = entity2.getRadius();
        double differenceX = entity1.getX() - entity2.getX();
        double differenceY = entity1.getY() - entity2.getY();
        double distanceSqrd = differenceX * differenceX + differenceY * differenceY;
        double sumRadius = radius1 + radius2;

        if (distanceSqrd < sumRadius * sumRadius) {
            doCollision(entity1, entity2, world);
        }
    }

    public void doCollision(Entity entity1, Entity entity2, World world){
        String type1 = entity1.getClass().getSimpleName();
        String type2 = entity2.getClass().getSimpleName();

        if (type1.equals("Player") || type1.equals("Enemy") || type2.equals("Player") || type2.equals("Enemy")){
            if (type1.equals("Bullet")) {
                world.removeEntity(entity1);
                subtractHealth(entity2, 1, world);
            } else if (type2.equals("Bullet")) {
                world.removeEntity(entity2);
                subtractHealth(entity1, 1, world);
            } else if (type1.equals("Asteroid")) {
                world.removeEntity(entity2);
            } else if (type2.equals("Asteroid")) {
                world.removeEntity(entity1);
            } else {
                System.out.println("Inconsequential collision");
            }
        } else if (type1.equals("Asteroid") || type2.equals("Asteroid")) {
            if (type1.equals("Bullet")) {
                world.removeEntity(entity1);
                entity2.setHealth(0);
            } else if (type2.equals("Bullet")) {
                world.removeEntity(entity2);
                entity1.setHealth(0);
            }
        }
    }

    public void subtractHealth(Entity entity, int value, World world) {
        System.out.println("Subtracting "+value+" from health");
        int oldHealth = entity.getHealth();
        entity.setHealth(oldHealth-value);
        if (entity.getHealth() < 1){
            world.removeEntity(entity);
        }
    }
}
