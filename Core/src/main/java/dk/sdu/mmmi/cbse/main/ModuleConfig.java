package dk.sdu.mmmi.cbse.main;


import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.ITextService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.lang.module.ModuleFinder;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.ServiceLoader;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Configuration
public class ModuleConfig {

    @Bean
    public Collection<? extends IGamePluginService> getPluginServices() {
        return ServiceLoader.load(IGamePluginService.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }

    @Bean
    public Collection<? extends IEntityProcessingService> getEntityProcessingServices() {
        return ServiceLoader.load(IEntityProcessingService.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }

    @Bean
    public Collection<? extends IPostEntityProcessingService> getPostEntityProcessingServices() {
        return ServiceLoader.load(IPostEntityProcessingService.class).stream().map(ServiceLoader.Provider::get).collect(toList());
    }

    @Bean
    public Collection<? extends ITextService> getTextServices() {
        Collection<ITextService> textServices = ServiceLoader.load(ITextService.class).stream().map(ServiceLoader.Provider::get).collect(toList());
        textServices.addAll(loadTextServices());
        return textServices;
    }

    // Module layer for one module of split packages
    private Collection<ITextService> loadTextServices() {
        var finder = ModuleFinder.of(Paths.get("plugins"));
        var parent = ModuleLayer.boot();
        java.lang.module.Configuration cf = parent.configuration().resolve(finder, ModuleFinder.of(), Set.of("TextPlayer"));
        ModuleLayer myL = parent.defineModulesWithOneLoader(cf, ClassLoader.getSystemClassLoader());
        var services = ServiceLoader.load(myL, ITextService.class);

        return services.stream().map(ServiceLoader.Provider::get).collect(Collectors.toList());
    }


}
