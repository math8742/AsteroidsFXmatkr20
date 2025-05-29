
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;

module Collision {
    requires Common;
    uses dk.sdu.mmmi.cbse.common.components.Health;
    provides IPostEntityProcessingService with dk.sdu.mmmi.cbse.collision.Collision;
}
