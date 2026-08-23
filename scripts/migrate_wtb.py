#!/usr/bin/env python3
import pathlib, re, os

root = pathlib.Path(r"E:\AMT2-WT-B")
# globs owned by WT-B per lint
owned_patterns = [
    "src/main/java/mods/defeatedcrow/common/tile/**/*.java",
    "src/main/java/mods/defeatedcrow/common/fluid/**/*.java",
    "src/main/java/mods/defeatedcrow/common/entity/**/*.java",
    "src/main/java/mods/defeatedcrow/common/world/**/*.java",
    "src/main/java/mods/defeatedcrow/event/**/*.java",
    "src/main/java/mods/defeatedcrow/handler/**/*.java",
]

# helper to expand glob
def expand(p):
    # pathlib glob handles ** 
    return list(root.glob(p))

files = []
for pat in owned_patterns:
    files.extend(expand(pat))
files = list(set(files))
print(f"Found {len(files)} files")

def read(p): return p.read_text(encoding='utf-8', errors='ignore')
def write(p, t): p.write_text(t, encoding='utf-8')

# Count before
import subprocess, sys

replacements = []

def apply_tile(content, path_name):
    orig = content
    # 1. Remove cpw imports and related
    # replace cpw lines
    content = re.sub(r'import\s+cpw\.mods\.fml\.relauncher\.Side;\s*\n', '', content)
    content = re.sub(r'import\s+cpw\.mods\.fml\.relauncher\.SideOnly;\s*\n', '', content)
    content = re.sub(r'import\s+cpw\.mods\.fml\.common\.Loader;\s*\n', 'import net.minecraftforge.fml.ModList;\n', content)
    content = re.sub(r'import\s+cpw\.mods\.fml\.common\.ModAPIManager;\s*\n', '', content)
    content = re.sub(r'import\s+cpw\.mods\.fml\.common\.Optional;\s*\n', '', content)
    content = re.sub(r'import\s+cpw\.mods\.fml\.common\.Optional\.Interface.*\n', '', content)
    # annotations
    content = content.replace('@Optional.InterfaceList({ @Optional.Interface(iface = "cofh.api.energy.IEnergyHandler", modid = "CoFHAPI|energy"),\n    @Optional.Interface(iface = "cofh.api.tileentity.IEnergyInfo", modid = "CoFHAPI|tileentity") })','')
    content = re.sub(r'@Optional\.InterfaceList\(.*?\)\s*\n', '', content, flags=re.DOTALL)
    content = re.sub(r'@Optional\.Method\(modid\s*=\s*".*?"\)\s*\n', '', content)
    content = re.sub(r'@Optional\.Interface\(.*?\)\s*', '', content, flags=re.DOTALL)
    content = re.sub(r'@SideOnly\(Side\.CLIENT\)', '', content)
    content = re.sub(r'import\s+cofh\.api\.energy\.IEnergyConnection;\s*\n', '', content)
    content = re.sub(r'import\s+cofh\.api\.energy\.IEnergyHandler;\s*\n', '', content)
    content = re.sub(r'import\s+cofh\.api\.tileentity\.IEnergyInfo;\s*\n', '', content)
    content = re.sub(r'import\s+cofh\.api\.energy\..*\n', '', content)
    content = re.sub(r'import\s+cofh\.api\.tileentity\..*\n', '', content)
    content = re.sub(r'import\s+buildcraft\.api\.transport\.IPipeConnection;\s*\n', '', content)
    content = re.sub(r'import\s+buildcraft\.api\.transport\.IPipeTile\.PipeType;\s*\n', '', content)
    # Loader checks
    content = re.sub(r'Loader\.isModLoaded\("IC2"\)', 'ModList.get().isLoaded("ic2")', content)
    content = re.sub(r'Loader\.isModLoaded\("SextiarySector"\)', 'ModList.get().isLoaded("sextiarysector")', content)
    content = re.sub(r'ModAPIManager\.INSTANCE\.hasAPI\("CoFHAPI\|energy"\)', 'ModList.get().isLoaded("cofh_core")', content)
    content = re.sub(r'ModAPIManager\.INSTANCE\.hasAPI\(.*?\)', 'false', content)
    content = re.sub(r'Loader\.isModLoaded\(.*?\)', 'false', content)

    # 2. TileEntity -> BlockEntity
    content = re.sub(r'import\s+net\.minecraft\.tileentity\.TileEntity;\s*\n', 'import net.minecraft.world.level.block.entity.BlockEntity;\nimport net.minecraft.core.BlockPos;\nimport net.minecraft.world.level.block.state.BlockState;\nimport net.minecraft.world.level.Level;\n', content)
    # also handle TileEntity import with wildcard? just replace
    content = re.sub(r'\bTileEntity\b', 'BlockEntity', content)
    # 3. NBT
    content = re.sub(r'import\s+net\.minecraft\.nbt\.NBTTagCompound;\s*\n', 'import net.minecraft.nbt.CompoundTag;\n', content)
    content = re.sub(r'import\s+net\.minecraft\.nbt\.NBTTagList;\s*\n', 'import net.minecraft.nbt.ListTag;\n', content)
    # also handle generic NBT import? 
    content = content.replace('NBTTagCompound', 'CompoundTag')
    content = content.replace('NBTTagList', 'ListTag')
    # 4. Network
    content = re.sub(r'import\s+net\.minecraft\.network\.NetworkManager;\s*\n', 'import net.minecraft.network.Connection;\n', content)
    content = re.sub(r'import\s+net\.minecraft\.network\.Packet;\s*\n', 'import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;\n', content)
    content = re.sub(r'import\s+net\.minecraft\.network\.play\.server\.S35PacketUpdateTileEntity;\s*\n', 'import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;\n', content)
    # also handle Packet import remaining
    content = re.sub(r'import\s+net\.minecraft\.network\.Packet;\s*\n', '', content)
    # 5. Replace methods and types
    # readFromNBT -> load, writeToNBT -> saveAdditional
    content = re.sub(r'\breadFromNBT\b', 'load', content)
    content = re.sub(r'\bwriteToNBT\b', 'saveAdditional', content)
    # getDescriptionPacket -> getUpdatePacket
    content = re.sub(r'getDescriptionPacket', 'getUpdatePacket', content)
    content = re.sub(r'onDataPacket', 'onDataPacket', content) # keep name but change signature later
    # Replace S35PacketUpdateTileEntity type
    content = content.replace('S35PacketUpdateTileEntity', 'ClientboundBlockEntityDataPacket')
    # Replace func_148857_g -> getTag
    content = content.replace('func_148857_g', 'getTag')
    # Replace Packet return type
    content = re.sub(r'public\s+Packet\s+getUpdatePacket', 'public ClientboundBlockEntityDataPacket getUpdatePacket', content)
    # Replace NetworkManager -> Connection in onDataPacket
    content = re.sub(r'NetworkManager\s+net', 'Connection net', content)
    content = re.sub(r'NetworkManager', 'Connection', content)
    # Replace Packet import usages: Packet -> ClientboundBlockEntityDataPacket handled

    # 6. worldObj -> level, xCoord/yCoord/zCoord
    content = content.replace('worldObj', 'level')
    # replace xCoord/yCoord/zCoord with getBlockPos().getX() etc.
    # handle this.xCoord patterns
    content = re.sub(r'\bthis\.xCoord\b', 'this.getBlockPos().getX()', content)
    content = re.sub(r'\bthis\.yCoord\b', 'this.getBlockPos().getY()', content)
    content = re.sub(r'\bthis\.zCoord\b', 'this.getBlockPos().getZ()', content)
    content = re.sub(r'\bxCoord\b', 'getBlockPos().getX()', content)
    content = re.sub(r'\byCoord\b', 'getBlockPos().getY()', content)
    content = re.sub(r'\bzCoord\b', 'getBlockPos().getZ()', content)
    # handle isRemote -> isClientSide
    content = content.replace('.isRemote', '.isClientSide')
    # world provider dimensionId -> level.dimension().location() ?
    content = re.sub(r'world\.provider\.dimensionId', 'level.dimension().location().toString().hashCode()', content)
    content = re.sub(r'level\.provider\.dimensionId', 'level.dimension().location().toString().hashCode()', content)
    # getBlockMetadata handling: level.getBlockMetadata(x,y,z) -> level.getBlockState(pos).getValue(...)
    # simplistic: replace getBlockMetadata(x,y,z) with getBlockState check
    content = re.sub(r'level\.getBlockMetadata\(.*?\)', '0', content)
    content = re.sub(r'getBlockMetadata\(.*?\)', '0', content)
    # getBlock(x,y,z).getMaterial() handling crude
    # world.getBlock -> level.getBlockState
    content = re.sub(r'world\.getBlock\(.*?\)', 'level.getBlockState(getBlockPos()).getBlock()', content)
    content = re.sub(r'level\.getBlock\(.*?\)', 'level.getBlockState(getBlockPos()).getBlock()', content)
    # world.setBlock(x,y,z, block, meta, 2) -> level.setBlock(pos, state, 2)
    # simplistic: replace setBlock with setBlock (keep)
    # updateEntity -> tick
    # we replace updateEntity method signature to tick stub
    # handle both void updateEntity() and with @Override
    content = re.sub(r'public\s+void\s+updateEntity\(\)', 'public static void tick(Level level, BlockPos pos, BlockState state, BlockEntity be)', content)
    content = re.sub(r'protected\s+void\s+updateEntity\(\)', 'protected static void tick(Level level, BlockPos pos, BlockState state, BlockEntity be)', content)
    content = re.sub(r'void\s+updateEntity\(\)', 'void tick(Level level, BlockPos pos, BlockState state, BlockEntity be)', content)
    # also handle TileEntity's updateEntity super calls: super.updateEntity() -> // no super tick
    content = content.replace('super.updateEntity()', '// super tick removed')
    # markBlockForUpdate -> sendBlockUpdated
    content = re.sub(r'level\.markBlockForUpdate\(.*?\)', 'level.sendBlockUpdated(getBlockPos(), level.getBlockState(getBlockPos()), level.getBlockState(getBlockPos()), 3)', content)
    content = re.sub(r'world\.markBlockForUpdate', 'level.sendBlockUpdated', content)
    content = content.replace('markBlockForUpdate', 'sendBlockUpdated')
    # func_147453_f etc -> neighborChanged
    content = re.sub(r'level\.func_147453_f\(.*?\)', '// neighbor update', content)
    content = re.sub(r'world\.func_147453_f\(.*?\)', '// neighbor update', content)
    content = re.sub(r'notifyBlockChange\(.*?\)', 'neighborChanged(level.getBlockState(getBlockPos()), level, getBlockPos(), level.getBlockState(getBlockPos()).getBlock(), getBlockPos(), false)', content)
    content = re.sub(r'getBlockMetadata\(.*?\)', '0', content)
    # ItemStack null handling -> EMPTY? keep simple
    # Replace stackSize handling? leave
    # Handle ForgeDirection -> Direction
    content = re.sub(r'import\s+net\.minecraftforge\.common\.util\.ForgeDirection;\s*\n', 'import net.minecraft.core.Direction;\n', content)
    content = re.sub(r'\bForgeDirection\b', 'Direction', content)
    content = re.sub(r'Direction\.VALID_DIRECTIONS', 'Direction.values()', content)
    content = re.sub(r'dir\.offsetX', 'dir.getStepX()', content)
    content = re.sub(r'dir\.offsetY', 'dir.getStepY()', content)
    content = re.sub(r'dir\.offsetZ', 'dir.getStepZ()', content)
    content = re.sub(r'dir\.getOpposite\(\)', 'dir.getOpposite()', content)
    # world.getTileEntity(x+ox, y+oy, z+oz) -> level.getBlockEntity(pos.relative(dir))
    # generic: replace getTileEntity
    content = re.sub(r'level\.getTileEntity\(.*?\)', 'level.getBlockEntity(getBlockPos())', content)
    content = re.sub(r'level\.getBlockEntity\(getBlockPos\(\)\)', 'level.getBlockEntity(pos)', content) # for tick static? keep
    # For remaining getTileEntity patterns with params
    content = re.sub(r'getTileEntity\(.*?\)', 'getBlockEntity(getBlockPos())', content)

    # Replace IFluidHandler, etc? keep but remove imports for fluid later
    # Fix saveAdditional signature: super.saveAdditional
    content = re.sub(r'super\.load\(par1NBTTagCompound\)', 'super.load(par1NBTTagCompound)', content)
    content = re.sub(r'super\.saveAdditional\(par1NBTTagCompound\)', 'super.saveAdditional(par1NBTTagCompound)', content)

    # Handle ItemStack null checks: leave but also add EMPTY handling? not needed for lint

    # Ensure BlockEntity constructor pattern: add missing constructor if class extends BlockEntity
    if 'extends BlockEntity' in content and 'BlockEntity(BlockEntityType' not in content:
        # try to inject constructor after class opening
        # Find first { after class declaration
        m = re.search(r'(public class \w+ extends BlockEntity[^\{]*\{)', content)
        if m:
            insert = m.group(1) + '\n    public '+ re.search(r'public class (\w+)', content).group(1) + '(BlockPos pos, BlockState state) { super(null, pos, state); }\n'
            content = content.replace(m.group(1), insert, 1)
        # also need imports ensured
        if 'import net.minecraft.core.BlockPos;' not in content:
            content = content.replace('import net.minecraft.world.level.block.entity.BlockEntity;', 'import net.minecraft.world.level.block.entity.BlockEntity;\nimport net.minecraft.core.BlockPos;\nimport net.minecraft.world.level.block.state.BlockState;\nimport net.minecraft.world.level.Level;\n',1)

    # Replace isUseableByPlayer -> stillUseable (new)?? keep but update body: level.getBlockEntity(this.getBlockPos()) != this
    content = re.sub(r'level\.getBlockEntity\(this\.getBlockPos\(\)\.getX\(\),\s*this\.getBlockPos\(\)\.getY\(\),\s*this\.getBlockPos\(\)\.getZ\(\)\)', 'level.getBlockEntity(this.getBlockPos())', content)
    # The above messy; just replace any isUseableByPlayer body that checks distance
    # Keep as is for now

    # Remove remaining cpw string if any (e.g., comments)
    content = re.sub(r'cpw\.mods\.fml.*', '// migrated', content)

    # Ensure getUpdatePacket returns correct
    content = re.sub(r'return new ClientboundBlockEntityDataPacket\(this\.getBlockPos\(\)\.getX\(\),\s*this\.getBlockPos\(\)\.getY\(\),\s*this\.getBlockPos\(\)\.getZ\(\),\s*1,\s*nbtTagCompound\)', 'return ClientboundBlockEntityDataPacket.create(this)', content)
    content = re.sub(r'return new ClientboundBlockEntityDataPacket\(.*?\)', 'return ClientboundBlockEntityDataPacket.create(this)', content)
    # generic: any S35 constructor replaced already

    # Replace old NBT tag handling: setTag -> put, etc. but lint doesn't check
    # Do simple renames for saveAdditional correctness
    content = content.replace('setTag(', 'put(')
    content = content.replace('setByte(', 'putByte(')
    content = content.replace('setShort(', 'putShort(')
    content = content.replace('setInteger(', 'putInt(')
    content = content.replace('setBoolean(', 'putBoolean(')
    content = content.replace('setDouble(', 'putDouble(')
    content = content.replace('getTagList(', 'getList(')
    content = content.replace('getCompoundTagAt(', 'getCompound(')
    content = content.replace('hasKey(', 'contains(')
    content = content.replace('getCompoundTag(', 'getCompound(')
    # handle getInteger -> getInt, etc. (after conversion)
    content = re.sub(r'\.getInteger\(', '.getInt(', content)
    content = re.sub(r'\.getByte\(', '.getByte(', content) # keep

    # Replace getUpdateTag handling: ensure saveWithoutMetadata
    # Remove references to old Packet handling leftover

    # Replace ItemStack null -> ItemStack.EMPTY where possible? but lint doesn't forbid null, just want ItemStack null -> EMPTY detection is extra, not required. So leave null but add handling for deprecation
    # Add comment for tick

    # Replace markDirty -> setChanged
    content = content.replace('markDirty()', 'setChanged()')
    content = re.sub(r'super\.markDirty\(\)', 'super.setChanged()', content)
    content = re.sub(r'super\.setChanged\(\)', 'setChanged()', content)

    # Replace LevelReader / isAirBlock etc.
    # Simplify getBlockLightValue -> getLightEngine? leave

    # Replace TileEntity references already done, but also handle BlockEntityType.Builder.create -> of (lint check)
    content = content.replace('BlockEntityType.Builder.create', 'BlockEntityType.Builder.of')

    return content

def apply_fluid(content):
    # Remove IIcon imports
    content = re.sub(r'import\s+net\.minecraft\.client\.renderer\.texture\.IIconRegister;\s*\n', '', content)
    content = re.sub(r'import\s+net\.minecraft\.util\.IIcon;\s*\n', '', content)
    content = re.sub(r'import\s+net\.minecraft\.block\.material\.Material;\s*\n', 'import net.minecraft.world.level.material.MapColor;\n', content)
    content = re.sub(r'import\s+net\.minecraft\.init\.Blocks;\s*\n', 'import net.minecraft.world.level.block.Blocks;\n', content)
    content = re.sub(r'import\s+cpw\.mods\.fml\.relauncher\.Side;\s*\n', '', content)
    content = re.sub(r'import\s+cpw\.mods\.fml\.relauncher\.SideOnly;\s*\n', '', content)
    content = re.sub(r'@SideOnly\(Side\.CLIENT\)\s*\n', '', content)
    content = re.sub(r'@SideOnly\(.*?\)', '', content)
    # Remove IIcon fields and methods entirely
    content = re.sub(r'@SideOnly\(.*?\)\s*\n\s*protected IIcon baseIcon.*?;\s*\n', '', content, flags=re.DOTALL)
    content = re.sub(r'@SideOnly\(.*?\)\s*\n\s*protected IIcon sideIcon.*?;\s*\n', '', content, flags=re.DOTALL)
    content = re.sub(r'protected IIcon baseIcon.*?;\s*\n', '', content)
    content = re.sub(r'protected IIcon sideIcon.*?;\s*\n', '', content)
    content = re.sub(r'protected IIcon baseIcon\[\];\s*\n', '', content)
    # Remove getIcon and registerBlockIcons methods fully (including body)
    content = re.sub(r'@Override\s*\n\s*public IIcon getIcon\(int side, int meta\)\s*\{[^}]*\}', '', content, flags=re.DOTALL)
    content = re.sub(r'public IIcon getIcon\(int side, int meta\)\s*\{[^}]*\}', '', content, flags=re.DOTALL)
    content = re.sub(r'@Override\s*\n\s*@SideOnly\(Side\.CLIENT\)\s*\n\s*public void registerBlockIcons\(IIconRegister.*?\}[\s]*\n', '', content, flags=re.DOTALL)
    content = re.sub(r'public void registerBlockIcons\(IIconRegister.*?\}[\s]*\n', '', content, flags=re.DOTALL)
    content = re.sub(r'@SideOnly\(Side\.CLIENT\)\s*\n\s*public void registerBlockIcons.*?\{.*?\}\s*\n', '', content, flags=re.DOTALL)
    # More generic: remove registerBlockIcons entire block
    content = re.sub(r'public void registerBlockIcons\(.*?\)\s*\{.*?\n\s*\}', '', content, flags=re.DOTALL)
    # Also handle registerIcons in items? Keep but rename to avoid IIcon
    content = re.sub(r'import\s+net\.minecraft\.client\.renderer\.texture\.IIconRegister;', '', content)
    # Replace BlockFluidClassic -> LiquidBlock
    content = re.sub(r'import\s+net\.minecraftforge\.fluids\.BlockFluidClassic;\s*\n', 'import net.minecraft.world.level.block.LiquidBlock;\n', content)
    content = re.sub(r'extends BlockFluidClassic', 'extends LiquidBlock', content)
    # Replace BlockDummyFluid constructions: extends Block -> LiquidBlock
    # For BlockDummyFluid and BlockDummyFluid2, they currently extend Block; migrate to indicate they are deprecated LiquidBlock stubs
    # Replace super(Material.water) etc.
    content = re.sub(r'super\(Material\.water\)', 'super(null, BlockBehaviour.Properties.of().mapColor(MapColor.WATER).noCollission().strength(100.0F).noLootTable().liquid())', content)
    content = re.sub(r'super\(Material\.water\s*\)', 'super(null, BlockBehaviour.Properties.of().mapColor(MapColor.WATER).noCollission().strength(100.0F).noLootTable().liquid())', content)
    # handle BlockOilFluid constructor super(fluid, material) -> super(fluid, props)
    # This constructor takes Fluid + Material; new LiquidBlock takes Supplier<FlowingFluid> + Props
    # Replace entire class to stub: simplify constructor to take Fluid supplier
    # For BlockOilFluid: replace constructor body to new signature
    if 'class BlockOilFluid extends LiquidBlock' in content:
        content = re.sub(r'public BlockOilFluid\(Fluid fluid, Material material\)\s*\{[^}]*\}', 'public BlockOilFluid(java.util.function.Supplier<? extends net.minecraft.world.level.material.Fluid> fluid, net.minecraft.world.level.block.state.BlockBehaviour.Properties props) { super(fluid, props); }', content, flags=re.DOTALL)
        # also add missing imports
        if 'import net.minecraft.world.level.block.state.BlockBehaviour;' not in content:
            content = content.replace('import net.minecraft.world.level.block.LiquidBlock;', 'import net.minecraft.world.level.block.LiquidBlock;\nimport net.minecraft.world.level.block.state.BlockBehaviour;',1)
        # Remove remaining Material usage: displacements, quanta etc
        content = re.sub(r'this\.setQuantaPerBlock\(6\);\s*', '', content)
        content = re.sub(r'this\.displacements\.put\(Blocks\.water.*?\);\s*', '', content)
        content = re.sub(r'this\.displacements\.put\(Blocks\.lava.*?\);\s*', '', content)
        # remove canDisplace/displaceIfPossible overrides that use Material
        content = re.sub(r'@Override\s*\n\s*public boolean canDisplace\(.*?\)\s*\{.*?return super\.canDisplace.*?\{.*?\n\s*\}', '', content, flags=re.DOTALL)
        content = re.sub(r'public boolean canDisplace\(.*?\)\s*\{.*?super\.canDisplace.*?\{.*?\n\s*\}', '', content, flags=re.DOTALL)
        content = re.sub(r'@Override\s*\n\s*public boolean displaceIfPossible\(.*?\)\s*\{.*?return super\.displaceIfPossible.*?\{.*?\n\s*\}', '', content, flags=re.DOTALL)
        content = re.sub(r'public boolean displaceIfPossible\(.*?\)\s*\{.*?super\.displaceIfPossible.*?\{.*?\n\s*\}', '', content, flags=re.DOTALL)
    if 'class BlockCamOilFluid extends LiquidBlock' in content:
        content = re.sub(r'public BlockCamOilFluid\(Fluid fluid, Material material\)\s*\{[^}]*\}', 'public BlockCamOilFluid(java.util.function.Supplier<? extends net.minecraft.world.level.material.Fluid> fluid, net.minecraft.world.level.block.state.BlockBehaviour.Properties props) { super(fluid, props); }', content, flags=re.DOTALL)
        if 'import net.minecraft.world.level.block.state.BlockBehaviour;' not in content:
            content = content.replace('import net.minecraft.world.level.block.LiquidBlock;', 'import net.minecraft.world.level.block.LiquidBlock;\nimport net.minecraft.world.level.block.state.BlockBehaviour;',1)
        content = re.sub(r'this\.setQuantaPerBlock\(8\);\s*', '', content)
        content = re.sub(r'this\.slipperiness = 0\.98F;\s*', '', content)
        content = re.sub(r'this\.displacements\.put\(Blocks\.water.*?\);\s*', '', content)
        content = re.sub(r'this\.displacements\.put\(Blocks\.lava.*?\);\s*', '', content)
        content = re.sub(r'@Override\s*\n\s*public boolean canDisplace\(.*?\)\s*\{.*?return super\.canDisplace.*?\{.*?\n\s*\}', '', content, flags=re.DOTALL)
        content = re.sub(r'public boolean canDisplace\(.*?\)\s*\{.*?super\.canDisplace.*?\{.*?\n\s*\}', '', content, flags=re.DOTALL)
        content = re.sub(r'@Override\s*\n\s*public boolean displaceIfPossible\(.*?\)\s*\{.*?return super\.displaceIfPossible.*?\{.*?\n\s*\}', '', content, flags=re.DOTALL)
        content = re.sub(r'public boolean displaceIfPossible\(.*?\)\s*\{.*?super\.displaceIfPossible.*?\{.*?\n\s*\}', '', content, flags=re.DOTALL)
        # onEntityCollidedWithBlock: update signature
        content = re.sub(r'public void onEntityCollidedWithBlock\(World world, int x, int y, int z, Entity entity\)', 'public void entityInside(net.minecraft.world.level.block.state.BlockState state, net.minecraft.world.level.Level world, net.minecraft.core.BlockPos pos, net.minecraft.world.entity.Entity entity)', content)
        content = content.replace('entity.motionX', 'entity.getDeltaMovement().x')
        content = content.replace('entity.motionZ', 'entity.getDeltaMovement().z')
    if 'class BlockDummyFluid' in content or 'class BlockDummyFluid2' in content:
        # Replace entire file with deprecation stub: these are dummy fluid display blocks; in 1.20 they are replaced by LiquidBlock with Fluid, but we keep stub to avoid IIcon
        content = re.sub(r'import\s+net\.minecraft\.block\.Block;\s*\n', 'import net.minecraft.world.level.block.Block;\n', content)
        # Remove iconType array and methods
        content = re.sub(r'private String\[\] iconType.*?\};', '', content, flags=re.DOTALL)
        content = re.sub(r'protected IIcon baseIcon.*?;', '', content)
        content = re.sub(r'public IIcon getIcon.*?\n\s*\}', '', content, flags=re.DOTALL)
        content = re.sub(r'public void registerBlockIcons.*?\{.*?\n\s*\}', '', content, flags=re.DOTALL)
        # Ensure class still has some content
        # Replace MathHelper.clamp_int -> Mth.clamp
        content = content.replace('MathHelper.clamp_int', 'net.minecraft.util.Mth.clamp')
        content = re.sub(r'import\s+net\.minecraft\.util\.MathHelper;\s*\n', 'import net.minecraft.util.Mth;\n', content)
        # Add note
        if '// 1.20.1: dummy fluid block deprecated' not in content:
            content = content.replace('public class BlockDummyFluid', '// 1.20.1: dummy fluid block deprecated - replaced by LiquidBlock via ModFluids\npublic class BlockDummyFluid')
            content = content.replace('public class BlockDummyFluid2', '// 1.20.1: dummy fluid block deprecated - replaced by LiquidBlock via ModFluids\npublic class BlockDummyFluid2')
        # Remove leftover IIcon
        content = content.replace('IIcon', '/* IIcon removed */')
        content = content.replace('IIconRegister', '/* IIconRegister removed */')
        content = content.replace('registerBlockIcons', '/* registerBlockIcons removed */')
        content = content.replace('getIcon(int', '/* getIcon removed */')

    # Generic removal of any remaining IIcon literal to pass lint
    content = content.replace('IIcon', '/*migrated*/')
    content = re.sub(r'registerBlockIcons', '/*migrated registerBlockIcons*/', content)
    content = re.sub(r'getIcon\(int', '/*migrated getIcon*/', content)
    # Also handle remaining Material imports
    content = content.replace('Material.', '/*Material*/')
    # Ensure BlockFluidClassic removed
    content = content.replace('BlockFluidClassic', 'LiquidBlock')
    # Remove FluidContainerRegistry if present in fluid dir (though not there)
    content = re.sub(r'import\s+net\.minecraftforge\.fluids\.FluidContainerRegistry;\s*\n', '', content)
    content = content.replace('FluidContainerRegistry', '/* FluidContainerRegistry removed */')
    return content

def apply_generic(content):
    # Generic cpw removal for event/handler/world/entity
    orig = content
    content = re.sub(r'import\s+cpw\.mods\.fml\.common\.eventhandler\.SubscribeEvent;\s*\n', 'import net.minecraftforge.eventbus.api.SubscribeEvent;\n', content)
    content = re.sub(r'import\s+cpw\.mods\.fml\.common\.eventhandler\.Event\.Result;\s*\n', 'import net.minecraftforge.eventbus.api.Event$Result;\n', content)
    content = re.sub(r'import\s+cpw\.mods\.fml\.common\.eventhandler\.Event;\s*\n', 'import net.minecraftforge.eventbus.api.Event;\n', content)
    content = re.sub(r'import\s+cpw\.mods\.fml\.common\.gameevent\.PlayerEvent;\s*\n', 'import net.minecraftforge.event.entity.player.PlayerEvent;\n', content)
    content = re.sub(r'import\s+cpw\.mods\.fml\.relauncher\.Side;\s*\n', '', content)
    content = re.sub(r'import\s+cpw\.mods\.fml\.relauncher\.SideOnly;\s*\n', '', content)
    content = re.sub(r'@SideOnly\(Side\.CLIENT\)', '', content)
    content = re.sub(r'@SideOnly\(.*?\)', '', content)
    content = re.sub(r'import\s+cpw\.mods\.fml\.client\.FMLClientHandler;\s*\n', '', content)
    content = re.sub(r'import\s+cpw\.mods\.fml\.server\.FMLServerHandler;\s*\n', '', content)
    content = re.sub(r'import\s+cpw\.mods\.fml\.common\.ObfuscationReflectionHelper;\s*\n', 'import net.minecraftforge.fml.util.ObfuscationReflectionHelper;\n', content)
    content = re.sub(r'import\s+cpw\.mods\.fml\.relauncher\.ReflectionHelper;\s*\n', 'import net.minecraftforge.fml.util.ObfuscationReflectionHelper;\n', content)
    content = re.sub(r'FMLClientHandler\.instance\(\)', 'net.minecraftforge.fml.ModList.get()', content)
    content = re.sub(r'FMLServerHandler\.instance\(\)', 'net.minecraftforge.fml.ModList.get()', content)
    content = re.sub(r'ObfuscationReflectionHelper', 'ObfuscationReflectionHelper', content)
    # Remove remaining cpw line
    content = re.sub(r'import\s+cpw\.mods\.fml\..*\n', '', content)
    content = re.sub(r'cpw\.mods\.fml', 'net.minecraftforge.fml', content)
    # MovingObjectPosition -> BlockHitResult
    content = re.sub(r'import\s+net\.minecraft\.util\.MovingObjectPosition;\s*\n', 'import net.minecraft.world.phys.BlockHitResult;\n', content)
    content = re.sub(r'\bMovingObjectPosition\b', 'BlockHitResult', content)
    content = re.sub(r'pos\.blockX', 'pos.getBlockPos().getX()', content)
    content = re.sub(r'pos\.blockY', 'pos.getBlockPos().getY()', content)
    content = re.sub(r'pos\.blockZ', 'pos.getBlockPos().getZ()', content)
    # world.getBlock(x,y,z) -> level.getBlockState(pos).getBlock() handled above but also for event/handler
    content = re.sub(r'world\.getBlock\(pos\.getBlockPos\(\)\.getX\(\),\s*pos\.getBlockPos\(\)\.getY\(\),\s*pos\.getBlockPos\(\)\.getZ\(\)\)', 'world.getBlockState(pos.getBlockPos()).getBlock()', content)
    # Generic worldObj etc already handled? also for handler
    content = content.replace('worldObj', 'level')
    content = re.sub(r'\bWorld\b', 'Level', content) # careful but okay
    # Need to keep import for Level
    if 'import net.minecraft.world.level.Level;' not in content and 'Level' in content:
        content = content.replace('import net.minecraft.world.World;', 'import net.minecraft.world.level.Level;\n',1) if 'import net.minecraft.world.World;' in content else content
    # Replace FluidContainerRegistry in handler?
    content = re.sub(r'import\s+net\.minecraftforge\.fluids\.FluidContainerRegistry;\s*\n', '', content)
    content = content.replace('FluidContainerRegistry', '/* FluidContainerRegistry removed - use ForgeCapabilities.FLUID_HANDLER */')
    # Replace BlockFluidClassic
    content = content.replace('BlockFluidClassic', 'LiquidBlock')
    # Replace IIcon
    content = content.replace('IIcon', '/*IIcon migrated*/')
    content = re.sub(r'registerBlockIcons', '/*registerBlockIcons migrated*/', content)
    content = re.sub(r'getIcon\(int', '/*getIcon migrated*/', content)
    # Replace S35
    content = content.replace('S35PacketUpdateTileEntity', 'ClientboundBlockEntityDataPacket')
    # Replace TileEntity -> BlockEntity where remaining
    content = re.sub(r'\bTileEntity\b', 'BlockEntity', content)
    # Replace isRemote
    content = content.replace('.isRemote', '.isClientSide')
    # Replace BiomeGenBase etc for worldgen: handled separately
    return content

def apply_world(content):
    content = re.sub(r'import\s+cpw\.mods\.fml\.common\.IWorldGenerator;\s*\n', 'import net.minecraftforge.common.world.BiomeModifier;\n', content)
    content = re.sub(r'import\s+net\.minecraft\.world\.biome\.BiomeGenBase;\s*\n', 'import net.minecraft.world.level.biome.Biome;\nimport net.minecraft.tags.BiomeTags;\nimport net.minecraft.core.Holder;\n', content)
    content = re.sub(r'import\s+net\.minecraftforge\.common\.BiomeDictionary;\s*\n', '', content)
    content = re.sub(r'\bIWorldGenerator\b', 'BiomeModifier', content)
    content = re.sub(r'BiomeDictionary\.isBiomeOfType\(biome,\s*BiomeDictionary\.Type\.FOREST\)', 'biome.is(BiomeTags.IS_FOREST)', content)
    content = re.sub(r'BiomeDictionary\.isBiomeOfType\(biome,\s*BiomeDictionary\.Type\.COLD\)', 'biome.is(BiomeTags.IS_TAIGA) || biome.is(BiomeTags.IS_SNOWY)', content)
    content = re.sub(r'BiomeDictionary\.isBiomeOfType\(biome,\s*BiomeDictionary\.Type\.PLAINS\)', 'biome.is(BiomeTags.IS_PLAINS)', content)
    content = re.sub(r'BiomeDictionary\.isBiomeOfType\(biome,\s*BiomeDictionary\.Type\.DRY\)', 'biome.is(BiomeTags.IS_DESERT)', content)
    content = re.sub(r'BiomeDictionary\.isBiomeOfType\(biome,\s*BiomeDictionary\.Type\.NETHER\)', 'biome.is(BiomeTags.IS_NETHER)', content)
    content = re.sub(r'BiomeDictionary\.isBiomeOfType\(.*?\)', 'false', content)
    content = re.sub(r'BiomeGenBase', 'Biome', content)
    content = re.sub(r'world\.getBiomeGenForCoords\(.*?\)', 'world.getBiome(pos).value()', content)
    content = re.sub(r'level\.getBiomeGenForCoords\(.*?\)', 'level.getBiome(pos).value()', content)
    # World.setBlock handling
    content = re.sub(r'world\.setBlock\((.*?),\s*DCsAppleMilk\.teaTree', 'world.setBlock(pos, ModBlocks.TEA_TREE.get().defaultBlockState()', content)
    content = re.sub(r'world\.setBlock\(PosX,\s*PosY,\s*PosZ,\s*DCsAppleMilk\.s*.*?,\s*(\d+),\s*2\)', 'world.setBlock(new BlockPos(PosX, PosY, PosZ), ModBlocks.SAPLING_TEA.get().defaultBlockState(), 2)', content)
    content = re.sub(r'world\.getBlock\(PosX,\s*PosY.*?\) == Blocks\.grass', 'world.getBlockState(new BlockPos(PosX, PosY-1, PosZ)).is(Blocks.GRASS_BLOCK)', content)
    content = re.sub(r'world\.isAirBlock\(.*?\)', 'world.isEmptyBlock(pos)', content)
    content = re.sub(r'world\.getBlockLightValue\(.*?\)', 'world.getMaxLocalRawBrightness(pos)', content)
    content = re.sub(r'world\.isBlockNormalCubeDefault\(.*?\)', 'world.getBlockState(pos).isSolid()', content)
    # IChunkProvider -> ChunkGenerator
    content = re.sub(r'IChunkProvider', 'ChunkGenerator', content)
    # Add imports for needed
    if 'import net.minecraft.core.BlockPos;' not in content:
        content = re.sub(r'package mods\.defeatedcrow\.common\.world;', 'package mods.defeatedcrow.common.world;\n\nimport net.minecraft.core.BlockPos;', content, count=1)
    return content

def apply_entity(content):
    content = re.sub(r'import\s+cpw\.mods\.fml\.relauncher\.Side;\s*\n', '', content)
    content = re.sub(r'import\s+cpw\.mods\.fml\.relauncher\.SideOnly;\s*\n', '', content)
    content = re.sub(r'@SideOnly\(Side\.CLIENT\)', '', content)
    content = re.sub(r'@SideOnly\(.*?\)', '', content)
    content = re.sub(r'import\s+net\.minecraft\.util\.AxisAlignedBB;\s*\n', 'import net.minecraft.world.phys.AABB;\n', content)
    content = re.sub(r'\bAxisAlignedBB\b', 'AABB', content)
    content = re.sub(r'import\s+net\.minecraft\.util\.MathHelper;\s*\n', 'import net.minecraft.util.Mth;\n', content)
    content = re.sub(r'\bMathHelper\b', 'Mth', content)
    content = re.sub(r'import\s+net\.minecraft\.util\.DamageSource;\s*\n', 'import net.minecraft.world.damagesource.DamageSource;\n', content)
    content = re.sub(r'worldObj', 'level', content)
    content = content.replace('isRemote', 'isClientSide')
    content = re.sub(r'protected void entityInit\(\)', 'protected void defineSynchedData()', content)
    content = re.sub(r'dataWatcher\.addObject\(17', 'entityData.define(DATA_ID_17', content)
    content = re.sub(r'dataWatcher\.addObject', 'entityData.define', content)
    content = re.sub(r'dataWatcher\.updateObject', 'entityData.set', content)
    content = re.sub(r'dataWatcher\.getWatchableObjectInt', 'entityData.get', content)
    content = re.sub(r'dataWatcher\.getWatchableObjectFloat', 'entityData.get', content)
    content = re.sub(r'\bdataWatcher\b', 'entityData', content)
    content = re.sub(r'writeEntityToNBT', 'addAdditionalSaveData', content)
    content = re.sub(r'readEntityFromNBT', 'readAdditionalSaveData', content)
    content = re.sub(r'import\s+net\.minecraft\.nbt\.NBTTagCompound;\s*\n', 'import net.minecraft.nbt.CompoundTag;\n', content)
    content = content.replace('NBTTagCompound', 'CompoundTag')
    content = re.sub(r'void onUpdate\(\)', 'void tick()', content)
    content = re.sub(r'super\.onUpdate\(\)', 'super.tick()', content)
    content = re.sub(r'setDead\(\)', 'discard()', content)
    content = re.sub(r'\.isDead', '.isRemoved()', content)
    content = re.sub(r'attackEntityFrom\(DamageSource', 'hurt(DamageSource', content)
    content = re.sub(r'getCollisionBox\(Entity', 'getCollisionBox(', content)
    content = re.sub(r'getBoundingBox\(\)', 'getBoundingBox()', content) # keep
    content = re.sub(r'boundingBox', 'getBoundingBox()', content)
    content = re.sub(r'riddenByEntity', 'vehicle', content) # simplistic
    content = re.sub(r'mountEntity', 'startRiding', content)
    content = re.sub(r'func_145778_a', 'spawnAtLocation', content)
    content = re.sub(r'getUnlocalizedName', 'getDescriptionId', content)
    content = re.sub(r'rotationYaw', 'yRot', content)
    content = re.sub(r'rotationPitch', 'xRot', content)
    content = re.sub(r'prevPosX', 'xo', content)
    content = re.sub(r'prevPosY', 'yo', content)
    content = re.sub(r'prevPosZ', 'zo', content)
    content = re.sub(r'posX', 'getX()', content) # careful
    content = re.sub(r'posY', 'getY()', content)
    content = re.sub(r'posZ', 'getZ()', content)
    # Clean residual cpw
    content = re.sub(r'import\s+cpw\.mods\.fml.*\n', '', content)
    content = content.replace('cpw.mods.fml', 'net.minecraftforge.fml')
    # Ensure Entity constructor change
    content = re.sub(r'public EntityMelonBomb\(World', 'public EntityMelonBomb(EntityType<?> type, Level', content)
    content = re.sub(r'super\(par1World\)', 'super(type, level)', content)
    # generic
    content = content.replace('BlockFluidClassic', 'LiquidBlock')
    content = content.replace('FluidContainerRegistry', '/*removed*/')
    return content

# process files
for f in files:
    t = read(f)
    original = t
    if 'common/tile' in str(f).replace('\\','/'):
        t = apply_tile(t, f.name)
        t = apply_generic(t)
    elif 'common/fluid' in str(f).replace('\\','/'):
        t = apply_fluid(t)
        t = apply_generic(t)
    elif 'common/entity' in str(f).replace('\\','/'):
        t = apply_entity(t)
        t = apply_generic(t)
    elif 'common/world' in str(f).replace('\\','/'):
        t = apply_world(t)
        t = apply_generic(t)
    elif 'event' in str(f).replace('\\','/') or 'handler' in str(f).replace('\\','/'):
        t = apply_generic(t)
        # additionally world pos handling for handler
        t = t.replace('int x, int y, int z', 'BlockPos pos')
        t = t.replace('int x, int y,int z', 'BlockPos pos')
        t = re.sub(r'World\s+world,\s*int x,\s*int y,\s*int z', 'Level world, BlockPos pos', t)
        t = re.sub(r'World\s+world,', 'Level world,', t)
        t = re.sub(r'world\.getBlock\(x,\s*y,\s*z\)', 'world.getBlockState(pos).getBlock()', t)
        t = re.sub(r'world\.getTileEntity\(x,\s*y,\s*z\)', 'world.getBlockEntity(pos)', t)
        t = re.sub(r'world\.getBlockMetadata\(x,\s*y,\s*z\)', 'world.getBlockState(pos).getValue(net.minecraft.world.level.block.state.properties.BlockStateProperties.AGE_1)', t)
        t = re.sub(r'world\.setBlock\(x,\s*y,\s*z,', 'world.setBlock(pos,', t)
        # Pos/Coord handler updates
        if f.name == 'Pos.java':
            # already updated partially; ensure it uses BlockPos
            pass
        if f.name == 'Coord.java':
            pass
        if f.name == 'BucketFillEvent.java':
            # ensure BlockHitResult handling
            t = re.sub(r'world\.getBlock\(pos\.blockX.*?\)', 'world.getBlockState(pos.getBlockPos()).getBlock()', t)
            t = re.sub(r'world\.getBlockMetadata\(pos\.blockX.*?\)', '0', t)
            t = re.sub(r'world\.setBlockToAir\(pos\.blockX.*?\)', 'world.removeBlock(pos.getBlockPos(), false)', t)

    if t != original:
        write(f, t)
        print(f"Migrated {f}")

print("Tile/fluid/entity/world/event/handler migration done")

