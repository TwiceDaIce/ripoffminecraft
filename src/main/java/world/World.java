package world;

import player.Player;

public class World {
    // events
    // scoreboard
    // make an expandable array of chunks on the x and z axis

    private static long seed;
    private static int dimension;
    private static Player[] Players;
    // internal
    public static void broadcastClientMessage(String message, String id) {
        // broadcast from client to server
    }
    public static long getAbsoluteTime() {
        // get time in ticks
        return 0;
    }
    public static Player[] getPlayers() {
        // get all players
        return Players;
    }
    public static int getDimension() {
        // get dimension
        return dimension;
    }

    public static long getSeed() {
        // get seed
        return seed;
    }
    public static int getTime() {
        // get time of day
        return 0;
    }
    public static void setTime(int time) {
        // set time of day

    }
    public static void say(String message) {
        // broadcast from server to client chat
    }

    public static void setBlock(int x, int y, int z, shapes.Block block) {
        // access chunk and set block

    }
}
