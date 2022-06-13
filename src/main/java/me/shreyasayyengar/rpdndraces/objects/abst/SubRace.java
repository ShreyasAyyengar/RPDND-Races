package me.shreyasayyengar.rpdndraces.objects.abst;

public enum SubRace {

    AASIMAR("aasimar", AbstractAasimar.class),
    ELF("elf", AbstractElf.class),
    GENASI("genasi", AbstractGenasi.class),
    TIEFLING("tiefling", AbstractTiefling.class);

    private final String key;
    private final Class<?> abstractClass;

    SubRace(String key, Class<?> abstractClass) {
        this.key = key;
        this.abstractClass = abstractClass;
    }

    public String getKey() {
        return key;
    }

    public Class<?> getAbstractClass() {
        return abstractClass;
    }
}