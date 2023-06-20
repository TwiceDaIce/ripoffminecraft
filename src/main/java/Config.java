import java.io.*;
import java.util.*;
public class Config {
    Properties p = new Properties();
    // File file; // "src/main/resources/ripoffminecraft.properties"
    String ORS;
    Boolean firstStart;
    Integer[] volume = new Integer[5]; // 0 master 1 music 2 ambient 3 block 4 mob
    int gameresx, gameresy;
    boolean fullscreen = false;
    public void loadConfig(String pathname) {
        volume[0] = 0;
        System.out.println("how");
        String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        System.out.println(rootPath);
        String configPath = rootPath + "/ripoffminecraft.properties";
        try { p.load(new FileInputStream(configPath)); }
        catch(IOException e) {
            System.out.println(e + " attempting to load configuration.");
        }
        ORS = p.getProperty("ORS");
        /*if (p.getProperty("firstStart").equals("true")) {
            firstStart = true;
        } else { firstStart = false; }*/
        try {
            firstStart = Boolean.parseBoolean(p.getProperty("firstStart"));
            fullscreen = Boolean.parseBoolean(p.getProperty("fullscreen"));
            System.out.println(firstStart + " " + fullscreen);
        } catch (Exception e) { System.out.println("Exception Thrown while trying to load a boolean: " + e); }
        try {
            volume[0] = Integer.parseInt(p.getProperty("master", "100"));
            volume[1] = Integer.parseInt(p.getProperty("music", "98"));
            volume[2] = Integer.parseInt(p.getProperty("ambient", "100"));
            volume[3] = Integer.parseInt(p.getProperty("block", "100"));
            volume[4] = Integer.parseInt(p.getProperty("mob", "100"));
        } catch (Exception e) { System.out.println("Exception Thrown while trying to get volume levels: " + e); }
        try {
            gameresx = Integer.parseInt(p.getProperty("game_res_x", "600"));
            gameresy = Integer.parseInt(p.getProperty("game_res_y", "400"));
        } catch (Exception e) { System.out.println("Exception Thrown while trying to load a resolution: " + e); }
    }
    public static void createConfig(String pathname) {
        new Config().loadConfig(pathname);
    }
}
