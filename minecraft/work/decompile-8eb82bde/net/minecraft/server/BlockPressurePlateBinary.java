package net.minecraft.server;

import java.util.Iterator;
import java.util.List;

public class BlockPressurePlateBinary extends BlockPressurePlateAbstract {

    public static final BlockStateBoolean POWERED = BlockStateBoolean.of("powered");
    private final EnumMobType b;

    protected BlockPressurePlateBinary(Material material, EnumMobType enummobtype) {
        super(material);
        this.j(this.blockStateList.getBlockData().set(BlockPressurePlateBinary.POWERED, Boolean.valueOf(false)));
        this.b = enummobtype;
    }

    protected int e(IBlockData iblockdata) {
        return ((Boolean) iblockdata.get(BlockPressurePlateBinary.POWERED)).booleanValue() ? 15 : 0;
    }

    protected IBlockData a(IBlockData iblockdata, int i) {
        return iblockdata.set(BlockPressurePlateBinary.POWERED, Boolean.valueOf(i > 0));
    }

    protected int e(World world, BlockPosition blockposition) {
        AxisAlignedBB axisalignedbb = this.a(blockposition);
        List list;

        switch (SwitchHelperMobType.a[this.b.ordinal()]) {
        case 1:
            list = world.getEntities((Entity) null, axisalignedbb);
            break;

        case 2:
            list = world.a(EntityLiving.class, axisalignedbb);
            break;

        default:
            return 0;
        }

        if (!list.isEmpty()) {
            Iterator iterator = list.iterator();

            while (iterator.hasNext()) {
                Entity entity = (Entity) iterator.next();

                if (!entity.aH()) {
                    return 15;
                }
            }
        }

        return 0;
    }

    public IBlockData fromLegacyData(int i) {
        return this.getBlockData().set(BlockPressurePlateBinary.POWERED, Boolean.valueOf(i == 1));
    }

    public int toLegacyData(IBlockData iblockdata) {
        return ((Boolean) iblockdata.get(BlockPressurePlateBinary.POWERED)).booleanValue() ? 1 : 0;
    }

    protected BlockStateList getStateList() {
        return new BlockStateList(this, new IBlockState[] { BlockPressurePlateBinary.POWERED});
    }
}
