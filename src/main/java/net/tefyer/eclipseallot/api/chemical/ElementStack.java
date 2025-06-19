package net.tefyer.eclipseallot.api.chemical;

import net.tefyer.eclipseallot.api.APIUtils;

public record ElementStack(Element element, int size) {

    public double mass() {
        return size*element.protons + size*element.neutrons + size*APIUtils.Number.electronsWeight(element.electrons);
    }

}
