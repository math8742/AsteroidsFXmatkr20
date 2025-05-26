
import dk.sdu.mmmi.cbse.common.components.Component;
import dk.sdu.mmmi.cbse.common.components.Health;

module Health {
    requires Common;
    provides Component with dk.sdu.mmmi.cbse.health.HealthComponent;
    provides Health with dk.sdu.mmmi.cbse.health.HealthComponent;
    exports dk.sdu.mmmi.cbse.health;
}
