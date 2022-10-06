import render.RenderWindow;
import render.image_parser;

public class Main {
    public static Config config;
    public static void main(String[] args) {
        Config.createConfig("src/main/resources/ripoffminecraft.properties");
        config = new Config();
        new RenderWindow().run(config.gameresx, config.gameresy, config.fullscreen);

    }
}