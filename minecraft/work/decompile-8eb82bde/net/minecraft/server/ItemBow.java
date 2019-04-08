package net.minecraft.server;

public class ItemBow extends Item {

    public static final String[] a = new String[] { "pulling_0", "pulling_1", "pulling_2"};

    public ItemBow() {
        this.maxStackSize = 1;
        this.setMaxDurability(384);
        this.a(CreativeModeTab.j);
    }

    public void a(ItemStack itemstack, World world, EntityHuman entityhuman, int i) {
        boolean flag = entityhuman.abilities.canInstantlyBuild || EnchantmentManager.getEnchantmentLevel(Enchantment.ARROW_INFINITE.id, itemstack) > 0;

        if (flag || entityhuman.inventory.b(Items.ARROW)) {
            int j = this.d(itemstack) - i;
            float f = (float) j / 20.0F;

            f = (f * f + f * 2.0F) / 3.0F;
            if ((double) f < 0.1D) {
                return;
            }

            if (f > 1.0F) {
                f = 1.0F;
            }

            EntityArrow entityarrow = new EntityArrow(world, entityhuman, f * 2.0F);

            if (f == 1.0F) {
                entityarrow.setCritical(true);
            }

            int k = EnchantmentManager.getEnchantmentLevel(Enchantment.ARROW_DAMAGE.id, itemstack);

            if (k > 0) {
                entityarrow.b(entityarrow.j() + (double) k * 0.5D + 0.5D);
            }

            int l = EnchantmentManager.getEnchantmentLevel(Enchantment.ARROW_KNOCKBACK.id, itemstack);

            if (l > 0) {
                entityarrow.setKnockbackStrength(l);
            }

            if (EnchantmentManager.getEnchantmentLevel(Enchantment.ARROW_FIRE.id, itemstack) > 0) {
                entityarrow.setOnFire(100);
            }

            itemstack.damage(1, entityhuman);
            world.makeSound(entityhuman, "random.bow", 1.0F, 1.0F / (ItemBow.g.nextFloat() * 0.4F + 1.2F) + f * 0.5F);
            if (flag) {
                entityarrow.fromPlayer = 2;
            } else {
                entityhuman.inventory.a(Items.ARROW);
            }

            entityhuman.b(StatisticList.USE_ITEM_COUNT[Item.getId(this)]);
            if (!world.isStatic) {
                world.addEntity(entityarrow);
            }
        }

    }

    public ItemStack b(ItemStack itemstack, World world, EntityHuman entityhuman) {
        return itemstack;
    }

    public int d(ItemStack itemstack) {
        return 72000;
    }

    public EnumAnimation e(ItemStack itemstack) {
        return EnumAnimation.BOW;
    }

    public ItemStack a(ItemStack itemstack, World world, EntityHuman entityhuman) {
        if (entityhuman.abilities.canInstantlyBuild || entityhuman.inventory.b(Items.ARROW)) {
            entityhuman.a(itemstack, this.d(itemstack));
        }

        return itemstack;
    }

    public int b() {
        return 1;
    }
}
