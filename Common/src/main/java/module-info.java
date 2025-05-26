module Common {
    requires javafx.graphics;
    requires spring.web;
    requires java.net.http;
    exports dk.sdu.mmmi.cbse.common.services;
    exports dk.sdu.mmmi.cbse.common.data;
    exports dk.sdu.mmmi.cbse.common.components;
}