import dk.sdu.mmmi.cbse.common.services.ITextService;

module TextPlayer {
    requires Common;
    requires javafx.graphics;
    uses dk.sdu.mmmi.cbse.common.components.Health;
    provides ITextService with dk.sdu.mmmi.cbse.text.AddText;
}