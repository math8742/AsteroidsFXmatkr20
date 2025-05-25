package dk.sdu.mmmi.cbse.main;

import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.ITextService;
import java.util.*;
import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main extends Application {

    private Collection<IGamePluginService> gamePluginServices;
    private Collection<IEntityProcessingService> entityProcessingServices;
    private Collection<IPostEntityProcessingService> postEntityProcessingServices;
    private Collection<ITextService> textServices;

    public static void main(String[] args) {
        launch(Main.class);
    }

    @Override
    public void start(Stage window) throws Exception {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ModuleConfig.class);

        this.gamePluginServices = context.getBean("getPluginServices", Collection.class);
        this.entityProcessingServices = context.getBean("getEntityProcessingServices", Collection.class);
        this.postEntityProcessingServices = context.getBean("getPostEntityProcessingServices", Collection.class);
        this.textServices = context.getBean("getTextServices", Collection.class);

        Game game = new Game(gamePluginServices, entityProcessingServices, postEntityProcessingServices, textServices);
        game.start(window);
    }
}
