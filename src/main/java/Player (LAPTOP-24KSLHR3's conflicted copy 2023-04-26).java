import org.jetbrains.annotations.NotNull;
import tileentity.BlockChest;

public class Player {
    // position
    private final double walkSpeed = 1.0; // make better later
    private double[] p = new double[2];

    private double cameraPitch = 0.0;
    private double cameraYaw = 0.0;
    private double[][] chunk1;
    private double[] block1;
    //double[][][] test = {chunk1 {block1 {p[0], p[1], p[2]} } };

    private int blockType;
    private int blockName;
    private int x;
    private int blockMeta;
    private int y;
    private int z;
    int[][][] chunk =
            {
                    {
                        {x,y,z}, {blockType, blockMeta},

                        {x,y,z}, {blockType, blockMeta}
                    }
    };
    int a = chunk[0][0][1];
    public void move(double CameraYaw, double Direction, double Magnitude, double @NotNull [] p) {
        double tempX = p[0] + (Magnitude*Math.cos(CameraYaw + Direction));
        double tempZ = p[2] + (Magnitude*Math.sin(CameraYaw + Direction));
        p[0] = tempX;
        p[2] = tempZ;
    }
}
