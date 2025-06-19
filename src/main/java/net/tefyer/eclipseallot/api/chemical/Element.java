package net.tefyer.eclipseallot.api.chemical;

import lombok.Getter;

public enum Element {
    CHAOSIUM("Chaosium","Ch",130,153,130),
    FURRISIUM("Furrisium","Fr",131,143,131),
    NORMALIUM("Normalium","No",132,151,132);

    @Getter
    public String name;
    @Getter
    public String symbol;
    @Getter
    public int protons;
    @Getter
    public int electrons;
    @Getter
    public int neutrons;

    Element(String name, String symbol,int protons, int electrons, int neutrons) {
        this.name = name;
        this.symbol = symbol;
        this.protons = protons;
        this.electrons = electrons;
        this.neutrons = neutrons;
    }

    public void init(){}
}
