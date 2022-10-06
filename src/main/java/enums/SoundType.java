package enums;

public enum SoundType {
    NONE("test"),
    STONE(""),
    GRASS(""),
    DIRT("");

    private final String path;
    SoundType(String materialPath) {
        this.path = materialPath;
    }
    public String getPath() {
        return path;
    }

}
/*class testclass {
    public static void main(String[] args) {
        String test = SoundType.STONE.getPath();
    }
}*/
