
import dk.sdu.mmmi.cbse.common.components.Component;
import dk.sdu.mmmi.cbse.common.components.Cooldown;

module Cooldown {
    requires Common;
    provides Component with dk.sdu.mmmi.cbse.cooldown.CooldownComponent;
    provides Cooldown with dk.sdu.mmmi.cbse.cooldown.CooldownComponent;
    exports dk.sdu.mmmi.cbse.cooldown;
}
