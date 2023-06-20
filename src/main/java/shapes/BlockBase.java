package shapes;

public abstract class BlockBase {
    //public lowestCompatibleTool()
    private double Hardness;
    private String localizedName;

    private String texturePath;
    public abstract void Break();
    public abstract void Place();

    public void setHardness(double Hardness) {
        this.Hardness = Hardness;
    }

    public double getHardness() {
        return this.Hardness;
    }

    public void setLocalizedName(String localizedName) {
        this.localizedName = localizedName;
    }

    public String getLocalizedName() {
        return this.localizedName;
    }

    public void setTexturePath(String texturePath) {
        this.texturePath = texturePath;
    }

    public String getTexturePath() {
        return this.texturePath;
    }
    public BlockBase(String localizedName, double hardness, String texturePath) {
        setLocalizedName(localizedName);
        setHardness(hardness);
        setTexturePath(texturePath);
    }

}
//TODO: add saftey meassures to the superclasses