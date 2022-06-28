package dev.shreyasayyengar.rpdndraces.objects.abst;

public enum SubRace {

    AASIMAR(AbstractAasimar.class),
    ELF(AbstractElf.class),
    GENASI(AbstractGenasi.class),
    TIEFLING(AbstractTiefling.class);

    private final Class<?> abstractClass;

    SubRace(Class<?> abstractClass) {
        this.abstractClass = abstractClass;
    }


    public Class<?> getAbstractClass() {
        return abstractClass;
    }
}