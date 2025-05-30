package dk.sdu.mmmi.cbse.enemy;

import dk.sdu.mmmi.cbse.common.bullet.BulletSPI;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.GameKeys;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.components.Component;
import dk.sdu.mmmi.cbse.common.components.Health;
import dk.sdu.mmmi.cbse.common.components.Cooldown;

import java.util.Collection;
import java.util.ServiceLoader;

import static java.util.stream.Collectors.toList;


public class EnemyControlSystem implements IEntityProcessingService {

    ServiceLoader<Health> loader1 = ServiceLoader.load(Health.class);
    ServiceLoader<Cooldown> loader2 = ServiceLoader.load(Cooldown.class);

    @Override
    public void process(GameData gameData, World world) {

        for (Entity enemy : world.getEntities(Enemy.class)) {
            Health health = (Health) enemy.getComponent("HealthComponent");
            Cooldown cooldown = (Cooldown) enemy.getComponent("CooldownComponent");

            cooldown.update();
            if (!health.positiveHealth()) {
                world.removeEntity(enemy);
            } else {
                int input = (int) (Math.random() * 4);
                if (input == 0) {
                    enemy.setRotation(enemy.getRotation() - 5);
                }
                if (input == 1) {
                    enemy.setRotation(enemy.getRotation() + 5);
                }
                if (input == 2) {
                    double changeX = Math.cos(Math.toRadians(enemy.getRotation()));
                    double changeY = Math.sin(Math.toRadians(enemy.getRotation()));
                    enemy.setX(enemy.getX() + changeX);
                    enemy.setY(enemy.getY() + changeY);
                }
                if (input == 3) {
                    if (cooldown.noCooldown()) {
                        cooldown.setCooldown(20);
                        getBulletSPIs().stream().findFirst().ifPresent(
                                spi -> {
                                    world.addEntity(spi.createBullet(enemy, gameData));
                                }
                        );
                    }
                }

                if (enemy.getX() < 0) {
                    enemy.setX(1);
                }

                if (enemy.getX() > gameData.getDisplayWidth()) {
                    enemy.setX(gameData.getDisplayWidth() - 1);
                }

                if (enemy.getY() < 0) {
                    enemy.setY(1);
                }

                if (enemy.getY() > gameData.getDisplayHeight()) {
                    enemy.setY(gameData.getDisplayHeight() - 1);
                }
            }
        }
    }

    private Collection<? extends BulletSPI> getBulletSPIs() {
        return ServiceLoader.load(BulletSPI.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }
}