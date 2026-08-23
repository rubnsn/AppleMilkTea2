import pathlib, re

for p in pathlib.Path('src/main/java/mods/defeatedcrow/common/tile').rglob('*.java'):
    t=p.read_text(encoding='utf-8',errors='ignore')
    original=t
    # Fix broken sendBlockUpdated with trailing .getX()
    t = re.sub(r'level\.sendBlockUpdated\(getBlockPos\(\), level\.getBlockState\(getBlockPos\(\)\), level\.getBlockState\(getBlockPos\(\)\), 3\)\.getX\(\), getBlockPos\(\)\.getY\(\), getBlockPos\(\)\.getZ\(\)\)', 'level.sendBlockUpdated(pos, level.getBlockState(pos), level.getBlockState(pos), 3)', t)
    t = re.sub(r'this\.level\.sendBlockUpdated\(getBlockPos\(\), level\.getBlockState\(getBlockPos\(\)\), level\.getBlockState\(getBlockPos\(\)\), 3\)\.getX\(\), getBlockPos\(\)\.getY\(\), getBlockPos\(\)\.getZ\(\)\)', 'level.sendBlockUpdated(pos, level.getBlockState(pos), level.getBlockState(pos), 3)', t)
    t = re.sub(r'sendBlockUpdated\(getBlockPos\(\), level\.getBlockState\(getBlockPos\(\)\), level\.getBlockState\(getBlockPos\(\)\), 3\)\.getX\(\)\, getBlockPos\(\)\.getY\(\)\, getBlockPos\(\)\.getZ\(\)\)', 'sendBlockUpdated(pos, level.getBlockState(pos), level.getBlockState(pos), 3)', t)
    # Fix isOnHeatSource broken line
    t = t.replace('Block block = this.level.getBlockState(getBlockPos()).getBlock().getX(), getBlockPos().getY() - 1, getBlockPos().getZ())', 'Block block = level.getBlockState(pos.below()).getBlock()')
    t = t.replace('int meta = this.0.getX(), getBlockPos().getY() - 1, getBlockPos().getZ())', 'int meta = 0')
    t = t.replace('this.level.isAirBlock(getBlockPos().getX(), getBlockPos().getY() - 1, getBlockPos().getZ())', 'level.isEmptyBlock(pos.below())')
    t = t.replace('this.level.canBlockSeeTheSky(getBlockPos().getX(), getBlockPos().getY(), getBlockPos().getZ())', 'level.canSeeSky(pos)')
    t = t.replace('level.isAirBlock(getBlockPos().getX(), getBlockPos().getY() + 1 + i, getBlockPos().getZ())', 'level.isEmptyBlock(pos.above(i+1))')
    t = t.replace('level.getBlockState(getBlockPos()).getBlock().getX(), getBlockPos().getY() + 1 + i, getBlockPos().getZ())', 'level.getBlockState(pos.above(i+1)).getBlock()')
    # Fix updatePlate broken
    t = t.replace('this.level.sendBlockUpdated(getBlockPos(), level.getBlockState(getBlockPos()), level.getBlockState(getBlockPos()), 3).getX(), getBlockPos().getY(), getBlockPos().getZ())', 'level.sendBlockUpdated(pos, level.getBlockState(pos), level.getBlockState(pos), 3)')
    t = t.replace('this.level.neighborChanged(level.getBlockState(getBlockPos()), level, getBlockPos(), level.getBlockState(getBlockPos()).getBlock(), getBlockPos(), false).getX(), getBlockPos().getY(), getBlockPos().getZ(), DCsAppleMilk.teppanII)', 'level.neighborChanged(level.getBlockState(pos), level, pos, level.getBlockState(pos).getBlock(), pos, false)')
    t = t.replace('this.// neighbor update.getX(), getBlockPos().getY(), getBlockPos().getZ(), DCsAppleMilk.teppanII)', 'level.neighborChanged(level.getBlockState(pos), level, pos, level.getBlockState(pos).getBlock(), pos, false)')
    t = t.replace('level.sendBlockUpdated(getBlockPos(), level.getBlockState(getBlockPos()), level.getBlockState(getBlockPos()), 3).getX(), getBlockPos().getY(), getBlockPos().getZ())', 'level.sendBlockUpdated(pos, level.getBlockState(pos), level.getBlockState(pos), 3)')
    t = t.replace('return this.level.getBlockEntity(pos).getX(), this.getBlockPos().getY(), this.getBlockPos().getZ()) != this ? false', 'return this.level.getBlockEntity(this.getBlockPos()) != this ? false')
    # Fix onServerUpdate that still references be.
    if 'private void onServerUpdate()' in t and 'be.getSizeInventory' in t:
        t = re.sub(r'private void onServerUpdate\(\) \{.*?level\.sendBlockUpdated\(.*?\).*?\n    \}', 'private void onServerUpdate() { level.sendBlockUpdated(getBlockPos(), level.getBlockState(getBlockPos()), level.getBlockState(getBlockPos()), 3); }', t, flags=re.DOTALL)
    if t!=original:
        p.write_text(t,encoding='utf-8')
        print('fixed',p.name)
