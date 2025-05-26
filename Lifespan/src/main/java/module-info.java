
import dk.sdu.mmmi.cbse.common.components.Component;
import dk.sdu.mmmi.cbse.common.components.Lifespan;

module Lifespan {
    requires Common;
    provides Component with dk.sdu.mmmi.cbse.lifespan.LifespanComponent;
    provides Lifespan with dk.sdu.mmmi.cbse.lifespan.LifespanComponent;
    exports dk.sdu.mmmi.cbse.lifespan;
}
