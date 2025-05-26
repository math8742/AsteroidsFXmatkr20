package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import javafx.scene.text.Text;

public interface ITextService {
    Text getText();
    void update(GameData gameData, World world);
}
