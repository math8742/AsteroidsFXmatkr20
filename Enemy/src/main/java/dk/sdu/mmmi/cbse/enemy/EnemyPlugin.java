package dk.sdu.mmmi.cbse.enemy;

import dk.sdu.mmmi.cbse.common.components.Component;
import dk.sdu.mmmi.cbse.common.components.Health;
import dk.sdu.mmmi.cbse.common.components.Cooldown;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;

import java.util.ServiceLoader;

public class EnemyPlugin implements IGamePluginService {

    private Entity enemy;
    ServiceLoader<Health> loader1 = ServiceLoader.load(Health.class);
    ServiceLoader<Cooldown> loader2 = ServiceLoader.load(Cooldown.class);
    private Class<? extends Health> healthClass;
    private Class<? extends Cooldown> cooldownClass;

    public EnemyPlugin() {
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
        enemy = createEnemyShip(gameData);
        world.addEntity(enemy);
    }

    private Entity createEnemyShip(GameData gameData) {

        Entity enemyShip = new Enemy();
        enemyShip.setPolygonCoordinates(-5,-5,10,0,-5,5);
        enemyShip.setX(gameData.getDisplayHeight()/2+50);
        enemyShip.setY(gameData.getDisplayWidth()/2+50);
        enemyShip.setRadius(10);

        // add health and cooldown component
        if (healthClass != null){
            try {
                Health health = healthClass.getDeclaredConstructor().newInstance();
                health.setHealth(10);
                enemyShip.addComponent((Component) health);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (cooldownClass != null){
            try {
                Cooldown cooldown = cooldownClass.getDeclaredConstructor().newInstance();
                cooldown.setCooldown(0);
                enemyShip.addComponent((Component) cooldown);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return enemyShip;
    }

    @Override
    public void stop(GameData gameData, World world) {
        // Remove entities
        world.removeEntity(enemy);
    }

}