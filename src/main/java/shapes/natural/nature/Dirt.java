package shapes.natural.nature;

import base.BlockBase;
import item.Item;
import shapes.Block;
import shapes.meta.BlockMeta;
import util.enums.Material;

public class Dirt extends BlockBase {
    public BlockMeta BlockMeta;
    public Dirt() {
        super("Dirt", Material.DIRT);
        BlockMeta = new BlockMeta();
        this.BlockMeta.id = "rm:dirt";
        this.BlockMeta.solid = true;
        this.BlockMeta.transparent = false;
    }
}
