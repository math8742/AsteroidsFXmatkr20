package dk.sdu.mmmi.cbse.bulletsystem;

import dk.sdu.mmmi.cbse.common.bullet.Bullet;
import dk.sdu.mmmi.cbse.common.bullet.BulletSPI;
import dk.sdu.mmmi.cbse.common.components.Component;
import dk.sdu.mmmi.cbse.common.components.Lifespan;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import java.util.ServiceLoader;

public class BulletControlSystem implements IEntityProcessingService, BulletSPI {

    ServiceLoader<Lifespan> loader = ServiceLoader.load(Lifespan.class);
    private Class<? extends Lifespan> lifespanClass;

    public BulletControlSystem() {
        for (Lifespan lifespan: loader) {
            this.lifespanClass = lifespan.getClass();
        }
    }

    @Override
    public void process(GameData gameData, World world) {

        for (Entity bullet : world.getEntities(Bullet.class)) {
            Lifespan lifespan = (Lifespan) bullet.getComponent("LifespanComponent");
            if (lifespan.hasLifespan()){

                lifespan.update();

                double rotation = bullet.getRotation();
                double changeX = Math.cos(Math.toRadians(bullet.getRotation()));
                double changeY = Math.sin(Math.toRadians(bullet.getRotation()));
                double newX = bullet.getX() + changeX * 3;
                double newY = bullet.getY() + changeY * 3;
                boolean bounced = false;

                if (newX < 0 || newX > gameData.getDisplayWidth()) {
                    rotation = 180 - rotation;
                    bounced = true;
                    newX = Math.max(0, Math.min(newX, gameData.getDisplayWidth()));
                }

                if (newY < 0 || newY > gameData.getDisplayHeight()) {
                    rotation = 360 - rotation;
                    bounced = true;
                    newY = Math.max(0, Math.min(newY, gameData.getDisplayHeight()));
                }

                if (bounced) {
                    bullet.setRotation((rotation + 360) % 360);
                }

                bullet.setX(newX);
                bullet.setY(newY);
            } else {
                world.removeEntity(bullet);
            }
        }
    }

    @Override
    public Entity createBullet(Entity shooter, GameData gameData) {
        Entity bullet = new Bullet();
        bullet.setPolygonCoordinates(1, -1, 1, 1, -1, 1, -1, -1);
        double radians = Math.toRadians(shooter.getRotation());
        double additionX = Math.cos(radians) * 0.5;
        double additionY = Math.sin(radians) * 0.5;
        bullet.setX(shooter.getX() + (additionX * 20));
        bullet.setY(shooter.getY() + (additionY * 20));
        bullet.setRotation(shooter.getRotation());
        bullet.setRadius(1.5);

        // add lifespan component
        if (lifespanClass != null){
            try {
                Lifespan lifespan = lifespanClass.getDeclaredConstructor().newInstance();
                lifespan.setLifespan(200);
                bullet.addComponent((Component) lifespan);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return bullet;
    }
}
