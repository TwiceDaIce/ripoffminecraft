package world;

import shapes.Block;

public class Chunk {
    public static final int CHUNK_SIZE = 16;
    public static final int CHUNK_HEIGHT = 128;
    public static final int CHUNK_VOLUME = CHUNK_SIZE * CHUNK_SIZE * CHUNK_HEIGHT;

    public int x;
    public int y;

    public Block[][][] Blocks = new Block[CHUNK_SIZE][CHUNK_HEIGHT][CHUNK_SIZE];
}
