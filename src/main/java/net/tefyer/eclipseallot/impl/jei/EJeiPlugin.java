package net.tefyer.eclipseallot.impl.jei;

import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.MethodsReturnNonnullByDefault;

import javax.annotation.ParametersAreNonnullByDefault;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.forge.ForgeTypes;
import mezz.jei.api.helpers.IJeiHelpers;
import mezz.jei.api.helpers.IPlatformFluidHelper;
import net.minecraft.resources.ResourceLocation;
import net.tefyer.eclipseallot.Eclipseallot;
import org.jetbrains.annotations.NotNull;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
@JeiPlugin
public class EJeiPlugin implements IModPlugin {

    @Override
    public ResourceLocation getPluginUid() {
        return Eclipseallot.id("jei_plugin");
    }


    @Override
    public void registerRecipes(@NotNull IRecipeRegistration registration) {

    }
}
