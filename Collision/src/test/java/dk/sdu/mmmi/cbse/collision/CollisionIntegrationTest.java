package dk.sdu.mmmi.cbse.collision;

import dk.sdu.mmmi.cbse.common.bullet.Bullet;
import dk.sdu.mmmi.cbse.common.components.Component;
import dk.sdu.mmmi.cbse.common.components.Health;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;

public class CollisionIntegrationTest {

    private Collision collision;
    private GameData gameData;
    private World world;
    private Entity testPlayer;
    private Bullet bullet;
    private Health playerHealth;

    @BeforeEach
    public void testSetUp() {
        collision = new Collision() {
            @Override
            public Health getHealthComponent(Entity entity) {
                return (Health) entity.getComponent("HealthTest");
            }
        };
        gameData = new GameData();
        world = new World();

        testPlayer = new Entity() {
            @Override
            public String getClassType() {
                return "Player";
            }
        };
        testPlayer.setX(20);
        testPlayer.setY(20);
        testPlayer.setRadius(10);

        playerHealth = new CollisionUnitTest.HealthTest();
        playerHealth.setHealth(10);
        testPlayer.addComponent((Component) playerHealth);

        bullet = new Bullet();
        bullet.setX(25);
        bullet.setY(20);
        bullet.setRadius(1.5);

        world.addEntity(testPlayer);
        world.addEntity(bullet);
    }

    @Test
    public void testBulletAndPlayerCollision() {
        collision.process(gameData, world);

        Collection<Entity> entities = world.getEntities();
        assert entities.size() == 1 : "Should only be 1 entity left but got "+entities.size()+" instead";
        assert entities.contains(testPlayer) : "Player should still be in world but player not in world";
        assert playerHealth.getHealth() == 9 : "Player health should be 9 but got "+playerHealth.getHealth()+" instead";
    }
}
