package net.tefyer.eclipseallot.client.tag;

import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.tefyer.eclipseallot.api.APIUtils;

public class CustomTags {
    public static final TagKey<Block> MINEABLE_WITH_CONFIG_VALID_PICKAXE_WRENCH = APIUtils.Tag
            .createModBlockTag("mineable/pickaxe_or_wrench");
}
