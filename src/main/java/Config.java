import java.io.*;
import java.util.*;
public class Config {
    Properties p = new Properties();
    // File file; // "src/main/resources/ripoffminecraft.properties"
    String ORS;
    boolean firstStart;
    int[] volume; // 0 master 1 music 2 ambient 3 block 4 mob
    int gameresx, gameresy;
    Boolean fullscreen = false;
    public void loadConfig(String pathname) {
        String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        System.out.println(rootPath);
        String configPath = rootPath + "/app.properties";
        try { p.load(new FileInputStream(configPath)); }
        catch(IOException e) {
            System.out.println(e + " attempting to load configuration.");
        }
        /*ORS =
        firstStart =
        volume[0] =
        volume[1] =
        volume[2] =
        volume[3] =
        volume[4] =
        gameresx =
        gameresy =
        fullscreen =*/
    }

    public static void createConfig(String pathname) {
        new Config().loadConfig(pathname);
    }
}
