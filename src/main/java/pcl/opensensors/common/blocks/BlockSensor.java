package pcl.opensensors.common.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import pcl.opensensors.OpenSensors;
import pcl.opensensors.common.tileentity.TileEntitySensor;

public class BlockSensor extends BlockContainer {

	public BlockSensor() {
		super(Material.iron);
		this.setHardness(5F);
		this.setResistance(30F);
		setBlockName(OpenSensors.MODID + ".sensor");
	}

	@SideOnly(Side.CLIENT)
	public static IIcon frontIcon;
	@SideOnly(Side.CLIENT)
	public static IIcon sideIcon;
	
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister ir)
	{
		frontIcon = ir.registerIcon(OpenSensors.MODID + ":sensor");
		sideIcon = ir.registerIcon(OpenSensors.MODID + ":block_top");
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int metadata, float clickX, float clickY, float clickZ) {
		TileEntity tileEntity = world.getTileEntity(x, y, z);
		if (tileEntity == null || player.isSneaking()) {
			return false;
		}
		player.openGui(OpenSensors.instance, 0, world, x, y, z);
		return true;
	}
	
	/**
	 * Called when the block is placed in the world.
	 */
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int dir)
	{
		
		if (dir == 0 && side == 4)
		{
			return frontIcon;
		}
		
		switch (side)
		{
		case 2: 
			if (dir == 0)
			{
				return frontIcon;
			}
			return sideIcon;
		case 3: 
			if (dir == 4)
				return frontIcon;
			if (dir == 2) {
				return frontIcon;
			}
			return sideIcon;
		case 4: 
			if (dir == 3)
			{
				return frontIcon;
			}
			return sideIcon;
		case 5: 
			if (dir == 1)
			{
				return frontIcon;
			}
			return sideIcon;
		}
		return sideIcon;
	}

	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase player, ItemStack stack) {
		super.onBlockPlacedBy(world, x, y, z, player, stack);
		int dir = MathHelper.floor_double((double) ((player.rotationYaw * 4F) / 360F) + 0.5D) & 3;
		world.setBlockMetadataWithNotify(x, y, z, dir, 3);
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int meta) {
		TileEntitySensor tileEntity = (TileEntitySensor) world.getTileEntity(x, y, z);
		dropContent(tileEntity, world, tileEntity.xCoord, tileEntity.yCoord, tileEntity.zCoord);
		super.breakBlock(world, x, y, z, block, meta);
	}

	public void dropContent(IInventory chest, World world, int xCoord, int yCoord, int zCoord) {
		if (chest == null)
			return;
		Random random = new Random();
		for (int i1 = 0; i1 < chest.getSizeInventory(); ++i1) {
			ItemStack itemstack = chest.getStackInSlot(i1);

			if (itemstack != null) {
				float offsetX = random.nextFloat() * 0.8F + 0.1F;
				float offsetY = random.nextFloat() * 0.8F + 0.1F;
				float offsetZ = random.nextFloat() * 0.8F + 0.1F;
				EntityItem entityitem;

				for (; itemstack.stackSize > 0; world.spawnEntityInWorld(entityitem)) {
					int stackSize = random.nextInt(21) + 10;
					if (stackSize > itemstack.stackSize)
						stackSize = itemstack.stackSize;

					itemstack.stackSize -= stackSize;
					entityitem = new EntityItem(world, xCoord + offsetX, yCoord + offsetY, zCoord + offsetZ, new ItemStack(itemstack.getItem(), stackSize, itemstack.getItemDamage()));

					float velocity = 0.05F;
					entityitem.motionX = (float) random.nextGaussian() * velocity;
					entityitem.motionY = (float) random.nextGaussian() * velocity + 0.2F;
					entityitem.motionZ = (float) random.nextGaussian() * velocity;

					if (itemstack.hasTagCompound())
						entityitem.getEntityItem().setTagCompound((NBTTagCompound) itemstack.getTagCompound().copy());
				}
			}
		}
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		// TODO Auto-generated method stub
		return new TileEntitySensor();
	}

}
