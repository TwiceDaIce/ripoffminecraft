package worldgeneration;

import world.World;

public class WorldGenerator {
    // make a 3x3x1 chunk of dirt
    public static void Generate() {
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                for (int z = 0; z < 1; z++) {
                    World.setBlock(x, y, z, new shapes.natural.nature.Dirt());
                }
            }
        }
    }
}
