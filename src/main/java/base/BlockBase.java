package base;

import shapes.Block;
import util.enums.CreativeTab;
import util.enums.Material;
import util.interfaces.IHasModel;

public class BlockBase extends Block implements IHasModel {

    public BlockBase(String name, Material material) {
        super(material);
        setLocalizedName(name);
        setRegistryName(name);
        setCreativeTab(CreativeTab.BUILDING_BLOCKS);
        setMaterial(Material.STONE);
    }

    @Override
    public void registerModels() {

    }
}
//TODO: add safety measures to the superclasses