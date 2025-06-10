package net.tefyer.eclipseallot.mixin;

import net.minecraft.client.resources.model.ModelManager;
import net.minecraft.server.packs.resources.PreparableReloadListener;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraftforge.fml.ModLoader;
import net.tefyer.eclipseallot.Eclipseallot;
import net.tefyer.eclipseallot.client.tag.TagPrefixItemRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

@Mixin(value = ModelManager.class)
public class ModelManagerMixin {

    @Inject(method = "reload", at = @At(value = "HEAD"))
    private void eclipseallot$loadDynamicModels(PreparableReloadListener.PreparationBarrier preparationBarrier,
                                         ResourceManager resourceManager, ProfilerFiller preparationsProfiler,
                                         ProfilerFiller reloadProfiler, Executor backgroundExecutor,
                                         Executor gameExecutor, CallbackInfoReturnable<CompletableFuture<Void>> cir) {
        if (!ModLoader.isLoadingStateValid()) return;

        long startTime = System.currentTimeMillis();

        TagPrefixItemRenderer.reinitModels();

        Eclipseallot.LOGGER.info(Eclipseallot.NAME+" Took {} ms to load Models.", System.currentTimeMillis() - startTime);

    }
}
