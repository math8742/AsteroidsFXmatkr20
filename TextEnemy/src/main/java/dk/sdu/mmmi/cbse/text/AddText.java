package dk.sdu.mmmi.cbse.text;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.ITextService;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.components.Health;
import javafx.scene.text.Text;

public class AddText implements ITextService {

    private final Text enemyText = new Text(10, 50, "Enemy health = 10");

    @Override
    public Text getText() {
        return enemyText;
    }

    @Override
    public void update(GameData gameData, World world) {
        for (Entity entity : world.getEntities()) {
            if (entity.getClass().getSimpleName().equals("Enemy")){
                Health health = (Health) entity.getComponent("HealthComponent");
                int healthValue = health.getHealth();
                enemyText.setText("Enemy health = "+healthValue);
            }
        }
    }
}
