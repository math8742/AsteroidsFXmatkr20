package dk.sdu.mmmi.cbse.playersystem;

import dk.sdu.mmmi.cbse.common.bullet.Bullet;
import dk.sdu.mmmi.cbse.common.bullet.BulletSPI;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.GameKeys;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

import java.util.Collection;
import java.util.ServiceLoader;

import static java.util.stream.Collectors.toList;
import dk.sdu.mmmi.cbse.common.components.Component;
import dk.sdu.mmmi.cbse.common.components.Health;
import dk.sdu.mmmi.cbse.common.components.Cooldown;

public class PlayerControlSystem implements IEntityProcessingService {

    ServiceLoader<Health> loader1 = ServiceLoader.load(Health.class);
    ServiceLoader<Cooldown> loader2 = ServiceLoader.load(Cooldown.class);

    @Override
    public void process(GameData gameData, World world) {
            
        for (Entity player : world.getEntities(Player.class)) {
            Health health = (Health) player.getComponent("HealthComponent");
            Cooldown cooldown = (Cooldown) player.getComponent("CooldownComponent");

            cooldown.update();
            if (!health.positiveHealth()) {
                world.removeEntity(player);
            } else {
                if (gameData.getKeys().isDown(GameKeys.LEFT)) {
                    player.setRotation(player.getRotation() - 5);
                }
                if (gameData.getKeys().isDown(GameKeys.RIGHT)) {
                    player.setRotation(player.getRotation() + 5);
                }
                if (gameData.getKeys().isDown(GameKeys.UP)) {
                    double changeX = Math.cos(Math.toRadians(player.getRotation()));
                    double changeY = Math.sin(Math.toRadians(player.getRotation()));
                    player.setX(player.getX() + changeX);
                    player.setY(player.getY() + changeY);
                }
                if (gameData.getKeys().isDown(GameKeys.SPACE)) {
                    if (cooldown.noCooldown()) {
                        cooldown.setCooldown(20);
                        getBulletSPIs().stream().findFirst().ifPresent(
                                spi -> {
                                    world.addEntity(spi.createBullet(player, gameData));
                                }
                        );
                    } else {
                        System.out.println("Cooldown still up for player");
                    }
                }

                if (player.getX() < 0) {
                    player.setX(1);
                }

                if (player.getX() > gameData.getDisplayWidth()) {
                    player.setX(gameData.getDisplayWidth() - 1);
                }

                if (player.getY() < 0) {
                    player.setY(1);
                }

                if (player.getY() > gameData.getDisplayHeight()) {
                    player.setY(gameData.getDisplayHeight() - 1);
                }
            }
        }
    }

    private Collection<? extends BulletSPI> getBulletSPIs() {
        return ServiceLoader.load(BulletSPI.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }
}
