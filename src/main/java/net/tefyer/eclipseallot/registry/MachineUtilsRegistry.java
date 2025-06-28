package net.tefyer.eclipseallot.registry;

import it.unimi.dsi.fastutil.ints.Int2IntFunction;
import net.minecraftforge.fluids.FluidType;
import net.tefyer.eclipseallot.ConfigHolder;
import net.tefyer.eclipseallot.api.APIUtils;
import net.tefyer.eclipseallot.api.data.RotationState;
import net.tefyer.eclipseallot.api.recipe.ERecipeType;
import net.tefyer.eclipseallot.api.tiers.TechTiers;
import java.util.Locale;
import java.util.function.BiFunction;

import static net.tefyer.eclipseallot.api.APIUtils.Formatting.toEnglishName;
import static net.tefyer.eclipseallot.proxy.CommonProxy.REGISTRATE;

public class MachineUtilsRegistry {
    public static final TechTiers[] ELECTRIC_TIERS = APIUtils.Tiers.tiersBetween(TechTiers.LOW, TechTiers.HYPER);

    public static final Int2IntFunction defaultTankSizeFunction = tier -> (tier <= TechTiers.LOW.usage ? 8 :
            tier == TechTiers.BASIC.usage ? 12 : tier == TechTiers.ADVANCED.usage ? 16 : tier == TechTiers.NUCLEAR.usage ? 32 : 64) *
            FluidType.BUCKET_VOLUME;

    public static MachineDefinition[] registerSimpleMachines(String name, ERecipeType recipeType,
                                                             Int2IntFunction tankScalingFunction,
                                                             boolean hasPollutionDebuff) {
        return registerSimpleMachines(name, recipeType, tankScalingFunction, hasPollutionDebuff, ELECTRIC_TIERS);
    }
    public static MachineDefinition[] registerSimpleMachines(String name, ERecipeType recipeType,
                                                             Int2IntFunction tankScalingFunction) {
        return registerSimpleMachines(name, recipeType, tankScalingFunction, false);
    }
    public static MachineDefinition[] registerSimpleMachines(String name, ERecipeType recipeType) {
        return registerSimpleMachines(name, recipeType, defaultTankSizeFunction);
    }

    public static MachineDefinition[] registerSimpleMachines(String name,
                                                             ERecipeType recipeType,
                                                             Int2IntFunction tankScalingFunction,
                                                             boolean hasPollutionDebuff,
                                                             int... tiers) {
        return registerTieredMachines(name,
                (holder, tier) -> new SimpleTieredMachine(holder, tier, tankScalingFunction), (tier, builder) -> {
                    if (hasPollutionDebuff) {
                        builder.recipeModifiers(GTRecipeModifiers.ENVIRONMENT_REQUIREMENT
                                                .apply(GTMedicalConditions.CARBON_MONOXIDE_POISONING, 100 * tier),
                                        GTRecipeModifiers.OC_NON_PERFECT)
                                .conditionalTooltip(defaultEnvironmentRequirement(),
                                        ConfigHolder.INSTANCE.gameplay.environmentalHazards);
                    } else {
                        builder.recipeModifier(GTRecipeModifiers.OC_NON_PERFECT);
                    }
                    return builder
                            .langValue("%s %s %s".formatted(VLVH[tier], toEnglishName(name), VLVT[tier]))
                            .editableUI(SimpleTieredMachine.EDITABLE_UI_CREATOR.apply(GTCEu.id(name), recipeType))
                            .rotationState(RotationState.NON_Y_AXIS)
                            .recipeType(recipeType)
                            .workableTieredHullRenderer(GTCEu.id("block/machines/" + name))
                            .tooltips(workableTiered(tier, GTValues.V[tier], GTValues.V[tier] * 64, recipeType,
                                    tankScalingFunction.applyAsInt(tier), true))
                            .register();
                },
                tiers);
    }
    public static MachineDefinition[] registerTieredMachines(String name,
                                                             BiFunction<IMachineBlockEntity, TechTiers, MetaMachine> factory,
                                                             BiFunction<Integer, MachineBuilder<MachineDefinition>,
                                                                     MachineDefinition> builder,
                                                             TechTiers... tiers) {
        MachineDefinition[] definitions = new MachineDefinition[TechTiers.values().length];
        for (TechTiers tier : tiers) {
            var register = REGISTRATE
                    .machine(tier.name.toLowerCase(Locale.ROOT) + "_" + name,
                            holder -> factory.apply(holder, tier))
                    .tier(tier);
            definitions[tier] = builder.apply(tier, register);
        }
        return definitions;
    }

    public static void init(){}
}
