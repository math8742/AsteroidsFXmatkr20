package dk.sdu.mmmi.cbse.common.services;

import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;

public interface IGamePluginService {
    /**
     * Creates entities of a specific type and adds them to world via plugins for the types of entities
     *
     * @param gameData
     * @param world
     *
     * Preconditions:
     * gameData is not null
     * world is not null
     * Plugin for the given entity type has not already called the start method
     *
     * Postconditions:
     * Entities are created according to game logic defined in the plugin for the type of entity
     * Entities are added to the collection accessed via world.getEntities
     * Entities are in a valid state to be processed
     *
     * @throws NullPointerException if gameData or world is null
     */
    void start(GameData gameData, World world);

    /**
     * Removes one or more entities of the type in a plugin from world
     *
     * @param gameData
     * @param world
     *
     * Preconditions:
     * gameData is not null
     * world is not null
     * world.getEntities returns not null collection of game entities
     * Plugin for the given entity type has called the start method
     *
     * Postconditions:
     * Entities are removed from world based on game rules specified via the plugin for the type of entity
     *
     * @throws NullPointerException if gameData, world or world.getEntities is null or returns null
     */
    void stop(GameData gameData, World world);
}
