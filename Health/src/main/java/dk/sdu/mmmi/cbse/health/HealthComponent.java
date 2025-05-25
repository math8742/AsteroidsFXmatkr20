package dk.sdu.mmmi.cbse.health;

import dk.sdu.mmmi.cbse.common.components.Component;
import dk.sdu.mmmi.cbse.common.components.Health;

public class HealthComponent implements Component, Health {

    private int health;

    public HealthComponent(){
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
