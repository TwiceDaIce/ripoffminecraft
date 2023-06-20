package shapes;

import shapes.meta.BlockLocation;
import util.enums.CreativeTab;
import util.enums.Material;

public class Block {
    private String localizedName;
    private String registryName;
    private CreativeTab creativeTab;
    public Material material;

    private BlockLocation location;

    public int x;
    public int y;
    public int z;
    public Block(Material material) {
        this.material = material;
    }

    public void setLocalizedName(String name) {
        localizedName = name;
    }
    public void setRegistryName(String name) {
        registryName = name;
    }
    public void setCreativeTab(CreativeTab tab) {
        creativeTab = tab;
    }
    public void setMaterial(Material material) {
        this.material = material;
    }
}
