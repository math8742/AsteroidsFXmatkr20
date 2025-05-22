package dk.sdu.mmmi.cbse.playersystem;

import dk.sdu.mmmi.cbse.common.components.Component;
import dk.sdu.mmmi.cbse.common.components.Cooldown;
import dk.sdu.mmmi.cbse.common.components.Health;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

import java.util.ServiceLoader;

public class PlayerPlugin implements IGamePluginService {

    private Entity player;
    ServiceLoader<Health> loader1 = ServiceLoader.load(Health.class);
    ServiceLoader<Cooldown> loader2 = ServiceLoader.load(Cooldown.class);
    private Class<? extends Health> healthClass;
    private Class<? extends Cooldown> cooldownClass;

    public PlayerPlugin() {
        for (Health health : loader1) {
            this.healthClass = health.getClass();
        }
        for (Cooldown cooldown : loader2) {
            this.cooldownClass = cooldown.getClass();
        }
    }

    @Override
    public void start(GameData gameData, World world) {

        // Add entities to the world
        player = createPlayerShip(gameData);
        world.addEntity(player);
    }

    private Entity createPlayerShip(GameData gameData) {

        Entity playerShip = new Player();
        playerShip.setPolygonCoordinates(-5,-5,10,0,-5,5);
        playerShip.setX(gameData.getDisplayHeight()/2);
        playerShip.setY(gameData.getDisplayWidth()/2);
        playerShip.setRadius(10);

        // add health and cooldown component
        if (healthClass != null){
            try {
                Health health = healthClass.getDeclaredConstructor().newInstance();
                health.setHealth(10);
                playerShip.addComponent((Component) health);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (cooldownClass != null){
            try {
                Cooldown cooldown = cooldownClass.getDeclaredConstructor().newInstance();
                cooldown.setCooldown(0);
                playerShip.addComponent((Component) cooldown);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return playerShip;
    }

    @Override
    public void stop(GameData gameData, World world) {
        // Remove entities
        world.removeEntity(player);
    }

}
