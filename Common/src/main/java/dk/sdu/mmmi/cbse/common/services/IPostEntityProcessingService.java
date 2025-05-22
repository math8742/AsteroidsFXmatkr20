package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

public interface IPostEntityProcessingService {
    /**
     * Processes entities after main processing logic has run in a given frame
     *
     * @param gameData
     * @param world
     *
     * Preconditions:
     * gameData is not null
     * world is not null
     * world.getEntities returns not null collection of game entities
     * Entities in world.getEntities are in a valid state to be processed
     * Main processing via IEntityProcessingService has already been done for current frame
     *
     * Postconditions:
     * Entities are modified based on game rules specified via IPostEntityProcessingService
     * Entities remain in valid state to be processed
     *
     * @throws NullPointerException if gameData, world or world.getEntities is null or returns null
     */
    void process(GameData gameData, World world);
}
