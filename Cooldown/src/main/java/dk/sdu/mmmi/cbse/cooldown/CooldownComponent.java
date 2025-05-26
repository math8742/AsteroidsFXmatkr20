package dk.sdu.mmmi.cbse.cooldown;

import dk.sdu.mmmi.cbse.common.components.Component;
import dk.sdu.mmmi.cbse.common.components.Cooldown;

public class CooldownComponent implements Component, Cooldown {

    private int cooldown;

    public CooldownComponent() {
        this.cooldown = 0;
    }

    @Override
    public void setCooldown(int value) {
        this.cooldown = value;
    }

    @Override
    public void update() {
        int oldCooldown = cooldown;
        if (oldCooldown > 0) {
            this.cooldown = oldCooldown - 1;
        }
    }

    @Override
    public boolean noCooldown() {
        return cooldown == 0;
    }
}
