package dk.sdu.mmmi.cbse.collision;

import dk.sdu.mmmi.cbse.common.components.Component;
import dk.sdu.mmmi.cbse.common.components.Health;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.World;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class CollisionUnitTest {

    private Collision collision;
    private World world;

    @BeforeEach
    public void testSetUp() {
        collision = new Collision();
        world = mock(World.class);
    }

    public static class HealthTest implements Health, Component {

        private int health;

        public HealthTest() {
            this.health = 1;
        }

        @Override
        public void setHealth(int value) {
            this.health = value;
        }

        @Override
        public void subtractHealth(int value) {
            int oldHealth = health;
            if (oldHealth >= value) {
                this.health = oldHealth - value;
            }
        }

        @Override
        public boolean positiveHealth() {
            return health > 0;
        }

        @Override
        public int getHealth() {
            return health;
        }
    }

    @Test
    public void testCollisionPlayerAndBullet() {
        Entity player = mock(Entity.class);
        Health playerHealth = spy(new HealthTest());
        Entity bullet = mock(Entity.class);

        playerHealth.setHealth(10);

        when(player.getClassType()).thenReturn("Player");
        when(bullet.getClassType()).thenReturn("Bullet");

        when(player.getComponent("HealthComponent")).thenReturn((Component) playerHealth);

        collision.doCollision(player, bullet, world);

        verify(playerHealth).subtractHealth(1);
        verify(world).removeEntity(bullet);
    }

    @Test
    public void testCollisionBulletAndAsteroid() {
        Entity asteroid = mock(Entity.class);
        Health asteroidHealth = spy(new HealthTest());
        Entity bullet = mock(Entity.class);

        when(asteroid.getClassType()).thenReturn("Asteroid");
        when(bullet.getClassType()).thenReturn("Bullet");

        when(asteroid.getComponent("HealthComponent")).thenReturn((Component) asteroidHealth);

        collision.doCollision(asteroid, bullet, world);

        verify(asteroidHealth).setHealth(0);
        verify(world).removeEntity(bullet);
    }

    @Test
    public void testCollisionAsteroidAndPlayer() {
        Entity player = mock(Entity.class);
        Health playerHealth = spy(new HealthTest());
        Entity asteroid = mock(Entity.class);
        Health asteroidHealth = spy(new HealthTest());

        playerHealth.setHealth(10);

        when(player.getClassType()).thenReturn("Player");
        when(asteroid.getClassType()).thenReturn("Asteroid");

        when(player.getComponent("HealthComponent")).thenReturn((Component) playerHealth);
        when(asteroid.getComponent("HealthComponent")).thenReturn((Component) asteroidHealth);

        collision.doCollision(player, asteroid, world);

        verify(world).removeEntity(player);
    }
}
