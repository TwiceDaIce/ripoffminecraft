package item;

import shapes.natural.nature.Dirt;
import item.Item;

public class ItemStack<T> {
    int amount;
    String flags;
    String nameTag;
    Item item;
    final String id = ""; // rm: is assumed
    /*public ItemStack(Item<T> type, T item, int amount, String flags, String nameTag) {
        this.item = item;
        this.amount = amount;
        this.flags = flags;
        this.nameTag = nameTag;
    }

    }
    ItemStack stack = new ItemStack<Dirt>(Dirt)*/
}
