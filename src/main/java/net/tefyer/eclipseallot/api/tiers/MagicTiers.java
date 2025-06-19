package net.tefyer.eclipseallot.api.tiers;

import lombok.Getter;

public enum MagicTiers {
    NOVICE("Novice"),
    ARCHMAGE("Archmage"),
    MAGE("Mage"),
    METAL("Metal"),
    FULL_METAL("Full Metal")
    ;

    @Getter
    public String name;

    MagicTiers(String name) {
        this.name = name;
    }
}
