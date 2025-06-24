package net.tefyer.eclipseallot.datagen;

import com.tterrag.registrate.providers.ProviderType;
import net.tefyer.eclipseallot.datagen.lang.LangHandler;
import net.tefyer.eclipseallot.datagen.recipes.RecipeProvider;
import net.tefyer.eclipseallot.proxy.CommonProxy;

public class EDataGen {
    public static void init(){
        CommonProxy.REGISTRATE.addDataGenerator(ProviderType.RECIPE, RecipeProvider::init);
        CommonProxy.REGISTRATE.addDataGenerator(ProviderType.LANG, LangHandler::init);
    }
}
