package world;

public enum TimeOfDay {
    DAY(1000),
    NOON(6000),
    SUNSET(12000),
    NIGHT(13000),
    MIDNIGHT(18000),
    SUNRISE(23000);
    public final int time;

    private TimeOfDay(int time) {
        this.time = time;
    }
}
