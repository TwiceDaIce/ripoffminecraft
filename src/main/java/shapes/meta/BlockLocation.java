package shapes.meta;

import shapes.Block;

import java.util.ArrayList;

public class BlockLocation {
    private static int x;
    private static int y;
    private static int z;
    public BlockLocation(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    public static BlockLocation above(BlockLocation location) {
        return new BlockLocation(location.x, location.y + 1, location.z);
    }
    public static BlockLocation[] blocksBetween(BlockLocation location1, BlockLocation location2) {
        ArrayList<BlockLocation> locations = new ArrayList<BlockLocation>();

        for (int x = location1.x; x < location2.x; x++) {
            for (int y = location1.y; y < location2.y; y++) {
                for (int z = location1.z; z < location2.z; z++) {
                    locations.add(new BlockLocation(x, y, z));
                }
            }
        }
        BlockLocation[] returnObject = new BlockLocation[locations.size()];
        returnObject = locations.toArray(returnObject);
        // convert ArrayList to array
        // for every location in locations, create a new BlockLocation and add it to a list to retur

        return returnObject;
    }

    public static boolean equals(BlockLocation location1, BlockLocation location2) {
        return location1.x == location2.x && location1.y == location2.y && location1.z == location2.z;
    }
    public static BlockLocation offset(int xc, int yc, int zc) {
        return new BlockLocation(x + xc, y + yc, z + zc);
    }
}
