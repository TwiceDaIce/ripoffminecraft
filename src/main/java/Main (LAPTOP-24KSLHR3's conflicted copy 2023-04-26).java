import render.RenderWindow;
import render.image_parser;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL11.*;
import util.RegistryHandler;
import util.event.EventBus;

import static org.lwjgl.opengl.GL11.*;

//import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;

//import static org.lwjgl.opengl.GL11.glTranslatef;

public class Main {
    public static void main(String[] args) {
        EventBus eventBus = new EventBus();
        //eventBus.register(new RegistryHandler());
        Config config = new Config();
        config.createConfig("src/main/resources/ripoffminecraft.properties");
        //config = new Config();
        new RenderWindow().run(800, 600, config.fullscreen);
        System.out.println(config.gameresx + "rs");


    }
}