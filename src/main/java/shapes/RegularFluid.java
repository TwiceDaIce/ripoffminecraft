package shapes;

public class RegularFluid {
    // 0.0 means you always float and must force yourself to sink
    // 0.5 means neutral buoyancy, you stay in place
    // 1.0 means you sink even if you try to swim up
    public float floatIndex = 0.7f;

    // the default height of fluids compared to blocks
    public float standardHeight = 0.9f;
}
