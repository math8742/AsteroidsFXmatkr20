package dk.sdu.mmmi.cbse.text;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.services.ITextService;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.components.Health;
import javafx.scene.text.Text;

public class AddText implements ITextService {

    private final Text playerText = new Text(10, 80, "Player health = 10");

    @Override
    public Text getText() {
        return playerText;
    }

    @Override
    public void update(GameData gameData, World world) {
        for (Entity entity : world.getEntities()) {
            if (entity.getClassType().equals("Player")){
                Health health = (Health) entity.getComponent("HealthComponent");
                int healthValue = health.getHealth();
                playerText.setText("Player health = "+healthValue);
            }
        }
    }
}
