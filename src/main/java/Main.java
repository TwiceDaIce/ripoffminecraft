import render.RenderWindow;
import render.image_parser;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL11.*;

import static org.lwjgl.opengl.GL11.*;

//import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;

//import static org.lwjgl.opengl.GL11.glTranslatef;

public class Main {
    public static void main(String[] args) {
        Config config = new Config();
        config.createConfig("src/main/resources/ripoffminecraft.properties");
        //config = new Config();
        new RenderWindow().run(config.gameresx, config.gameresy, config.fullscreen);
        System.out.println(config.gameresx + "rs");


    }
}