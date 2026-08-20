package mods.defeatedcrow.common;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.src.*;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class CreativeTabAMT extends CreativeTabs {

    // クリエイティブタブのアイコン画像や名称の登録クラス
    public CreativeTabAMT(String type) {
        super(type);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public String getTranslatedTabLabel() {
        return "Apple&Milk&Tea:Core";
    }

    @Override
    public Item getTabIconItem() {
        return Item.getItemFromBlock(DCsAppleMilk.teaMakerNext);
    }

}
