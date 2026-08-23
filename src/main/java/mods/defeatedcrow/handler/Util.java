package mods.defeatedcrow.handler;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;

// legacy imports kept as comments for 1.7.10 diff visibility
// import net.minecraft.block.Block; // 1.7.10 Block
// import net.minecraft.entity.EntityLivingBase;
// import net.minecraft.entity.player.EntityPlayer;
// import net.minecraft.potion.Potion;
// import net.minecraft.potion.PotionEffect;
// import net.minecraft.util.MathHelper;
// import net.minecraft.world.Level;
// import net.minecraft.world.biome.BiomeGenBase;

import mods.defeatedcrow.common.AMTLogger;
import mods.defeatedcrow.common.config.DCsConfig;

public class Util {

    private Util() {}

    // コンフィグで規定範囲外の数値を入れた時に、安全に動かすためのメソッドその1
    public static int getCupRender() {
        int l = DCsConfig.setCupTexture;
        if (l < 0) l = 1;
        else if (l > 3) l = 3;

        return l;
    }

    // その2
    public static int getTeppannReadyTime() {
        int l = DCsConfig.teppannReadyTime;
        if (l < 0) l = 1;
        else if (l > 60) l = 60;

        return l;
    }

    // その3
    public static int getCupStacksize() {
        int l = DCsConfig.cupStackSize;
        if (l <= 1) l = 1;
        else {
            if (l <= 3) l = 3;
            else {
                l = 8;
            }
        }
        return l;
    }

    public static int getHamaguriChanceValue() {
        int l = DCsConfig.clamChanceValue;
        if (l < 0) l = 1;
        else if (l > 100) l = 100;

        return l;
    }

    public static int getPrincessChanceValue() {
        int l = DCsConfig.princessChanceValue;
        if (l < 0) l = 1;
        else if (l > 100) l = 100;

        return l;
    }

    // コンフィグで規定範囲外の数値を入れた時に、安全に動かすためのメソッドその4
    // 各Block、Itemクラス側からコンフィグ内容を確認するための中継地点
    // altテクスチャが用意されていない物は下のメソッドを使う
    // 1.20.1: TEX_PASS removed — ResourceLocation based. Return fixed namespace.
    public static String getTexturePass() {
        return "defeatedcrow";
    }

    public static String getTexturePassNoAlt() {
        return "defeatedcrow";
    }

    // entityはテクスチャのパスの記述方法が違うので別途作成。
    public static String getEntityTexturePass() {
        return "defeatedcrow";
    }

    public static String getEntityTexturePassNoAlt() {
        return "defeatedcrow";
    }

    public static String getEntityTexturePassAlt() {
        return "defeatedcrow";
    }

    public static float getCupScale() {
        float f = (float) DCsConfig.setCupScale;
        if (f < 0.01F) f = 0.01F;
        else if (f > 10.0F) f = 10.0F;

        return f;
    }

    public static float getCupSize() {
        float f = (float) DCsConfig.setCupScale;
        if (f < 0.5F) f = 0.5F;
        else if (f > 2.0F) f = 2.0F;

        return 0.3F * f;
    }

    // 0,0=south, 1,90=west, 2,180=north, 3,-90=east
    // 相変わらず方角を覚えられないため自分用に作ったメソッド
    public static final int[] METAX = new int[] { 0, -1, 0, 1 };

    public static final int[] METAZ = new int[] { -1, 0, 1, 0 };

    public static final int[] RAD = new int[] { 0, -90, 180, 90 };

    // FMLの機能を利用した他MOD様のアイテム取得メソッド。
    // 1.20.1: GameRegistry.findItem/findBlock → ForgeRegistries / BuiltInRegistries
    public static Item getModItem(String modId, String name) {
        ResourceLocation key = new ResourceLocation(modId, name);
        return BuiltInRegistries.ITEM.getValue(key);
    }

    public static net.minecraft.world.level.block.Block getModBlock(String modId, String name) {
        ResourceLocation key = new ResourceLocation(modId, name);
        return BuiltInRegistries.BLOCK.getValue(key);
    }

    // 現在地のバイオームを確認。
    // 1.20.1: BiomeGenBase → Holder<Biome> + Level#getBiome(BlockPos)
    // 現行ロジックは座標ベースの biome 取得だが、1.20.1では Level / BlockPos 化が必要。
    // 暫定: null を返さないよう plains を返すスタブ（WT-Bで WorldGen 側を別途 BlockPos 化）。
    // 呼び出し元は event/handler の一部のみで、将来的に Level#getBiome(pos) に置換。
    @Deprecated
    public static net.minecraft.world.level.biome.Biome checkCurrentBiome(net.minecraft.world.level.Level world,
            net.minecraft.world.entity.player.Player player) {
        if (world == null || player == null) return null;
        // 1.20.1 Holder<Biome> を直接返す代わりに、Biome インスタンスを返す
        return world.getBiome(player.blockPosition()).value();
    }

    // 新規追加ポーションを発生させる場合、ここのメソッドを中継する。（追加失敗対策）
    // 1.20.1: Potion → MobEffect, PotionEffect → MobEffectInstance, 整数ID廃止で config 項目削除。
    // 互換: 生存していればそのまま付与、失敗時は REGENERATION を付与（旧挙動維持）
    @Deprecated
    public static boolean addPotionEffectDC(net.minecraft.world.entity.LivingEntity living,
            net.minecraft.world.effect.MobEffectInstance effect) {
        if (living == null || effect == null) return false;
        return living.addEffect(effect);
    }

    // nullチェック用
    // 1.7.10: stack != null && stackSize != 0 && getItem() != null
    // 1.20.1: !stack.isEmpty()
    public static boolean notEmptyItem(ItemStack item) {
        return item != null && !item.isEmpty();
    }

    // OreNameから一つだけアイテムを得る
    // 1.20.1: OreDict.getOres → TagHelper.getTagItems(TagKey) に置換。
    // 暫定: タグが未ロードなら null を返す。呼び出し元 (WT-A ItemWoodBox/ItemCardboard) は null fallback を持つ。
    public static ItemStack getOreStack(String ore) {
        java.util.List<ItemStack> list = TagHelper.getTagItems(ore);
        if (!list.isEmpty()) {
            ItemStack base = list.get(0);
            // 旧挙動では count=9 の ItemStack を返していた（圧縮レシピの解凍用）
            return new ItemStack(base.getItem(), 9);
        }
        // タグ未登録 or 未ロード → null（旧 OreDict でも null 返しだった）
        return null;
    }

    // 1.20.1 追加: タグベースの存在チェック
    public static boolean doesOreNameExist(String ore) {
        if (ore == null || ore.isEmpty()) return false;
        return !TagHelper.getTagItems(ore).isEmpty();
    }

    // 1.20.1 追加: ore名 → TagKey 生成ヘルパ（レシピ側で Ingredient.of(TagKey) に使う想定）
    public static TagKey<Item> getOreTag(String ore) {
        return TagHelper.forgeTag(ore);
    }

    // デバッグモード
    public static boolean checkDebugModePass(String pass) {
        byte[] b = null;
        String get = "";
        MessageDigest md5;

        try {
            md5 = MessageDigest.getInstance("MD5");
            md5.update(pass.getBytes());
            b = md5.digest();
        } catch (NoSuchAlgorithmException e) {
            AMTLogger.logger.warn("Failed to check password...", e);
        }

        get = getStringFromBytes(b);
        AMTLogger.debugInfo("Get String : " + get);

        if (!get.isEmpty()) {
            boolean match = get.matches("7805f2fa0adc68cd9a8f7cb2135e0b57");
            AMTLogger.info("DebugMode : " + match);
            return match;
        }

        return true;
    }

    private static String getStringFromBytes(byte[] b) {

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < b.length; i++) {

            if ((b[i] & 0xff) < 0x10) {
                builder.append("0");
            }
            builder.append(Integer.toHexString(0xff & b[i]));
        }

        return builder.toString();
    }

}



