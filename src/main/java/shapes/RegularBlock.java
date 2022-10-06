package shapes;

import enums.SoundType;
import java.lang.*;

public class RegularBlock {

    // default will be stone unless overridden
    public SoundType sound = SoundType.STONE;
    public String path = String.valueOf(SoundType.STONE);

    // unused but might be useful later on
    public int rotationIndex;

    // self-explanatory
    public boolean transparent = false;

    // light emission, in a scale of 1-16
    public int lightEmission = 0;

    // public Vector3d vertices = [-0.5,-0.5,-0.5];
}
