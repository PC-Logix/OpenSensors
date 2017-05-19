package pcl.opensensors.common.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import pcl.opensensors.OpenSensors;
import pcl.opensensors.common.tileentity.TileEntitySensor;

public class BlockSensor extends BlockContainer {

	public BlockSensor() {
		super(Material.iron);
		this.setHardness(5F);
		this.setResistance(30F);
		setBlockName("sensor");
	}

	/**
	 * Called when the block is placed in the world.
	 */
	@Override
	public void onBlockPlacedBy(World par1World, int x, int y, int z, EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack) {
		super.onBlockPlacedBy(par1World, x, y, z, par5EntityLivingBase, par6ItemStack);
		int whichDirectionFacing = 0;
		if (par5EntityLivingBase.rotationPitch >= 70) {
			whichDirectionFacing = 0;// down
		} else if (par5EntityLivingBase.rotationPitch <= -70) {
			whichDirectionFacing = 1;// up
		} else {
			whichDirectionFacing = MathHelper.floor_double(par5EntityLivingBase.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;
			switch (whichDirectionFacing) {
			case 0:
				whichDirectionFacing = ForgeDirection.SOUTH.ordinal();
				break;
			case 1:
				whichDirectionFacing = ForgeDirection.WEST.ordinal();
				break;
			case 2:
				whichDirectionFacing = ForgeDirection.NORTH.ordinal();
				break;
			case 3:
				whichDirectionFacing = ForgeDirection.EAST.ordinal();
				break;
			default:
				break;
			}
		}
		par1World.setBlockMetadataWithNotify(x, y, z, par5EntityLivingBase.isSneaking() ? whichDirectionFacing : ForgeDirection.OPPOSITES[whichDirectionFacing], 2);
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
		return null;
	}

}
