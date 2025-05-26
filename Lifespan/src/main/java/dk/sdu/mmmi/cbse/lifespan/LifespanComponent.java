package dk.sdu.mmmi.cbse.lifespan;

import dk.sdu.mmmi.cbse.common.components.Component;
import dk.sdu.mmmi.cbse.common.components.Lifespan;

public class LifespanComponent implements Component, Lifespan {

    private int lifespan;

    public LifespanComponent() {
        this.lifespan = 1;
    }

    @Override
    public void setLifespan(int value) {
        this.lifespan = value;
    }

    @Override
    public void update() {
        int oldLifespan = lifespan;
        if (oldLifespan > 0) {
            this.lifespan = oldLifespan-1;
        }
    }

    @Override
    public boolean hasLifespan() {
        return lifespan > 0;
    }
}
