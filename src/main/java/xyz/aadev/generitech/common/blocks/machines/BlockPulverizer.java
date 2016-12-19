/*
 * LIMITED USE SOFTWARE LICENSE AGREEMENT
 *
 * This Limited Use Software License Agreement (the "Agreement") is a legal agreement between you, the end-user, and the AlgorithmicsAnonymous Team ("AlgorithmicsAnonymous"). By downloading or purchasing the software material, which includes source code (the "Source Code"), artwork data, music and software tools (collectively, the "Software"), you are agreeing to be bound by the terms of this Agreement. If you do not agree to the terms of this Agreement, promptly destroy the Software you may have downloaded or copied.
 *
 * AlgorithmicsAnonymous SOFTWARE LICENSE
 *
 * 1. Grant of License. AlgorithmicsAnonymous grants to you the right to use the Software. You have no ownership or proprietary rights in or to the Software, or the Trademark. For purposes of this section, "use" means loading the Software into RAM, as well as installation on a hard disk or other storage device. The Software, together with any archive copy thereof, shall be destroyed when no longer used in accordance with this Agreement, or when the right to use the Software is terminated. You agree that the Software will not be shipped, transferred or exported into any country in violation of the U.S. Export Administration Act (or any other law governing such matters) and that you will not utilize, in any other manner, the Software in violation of any applicable law.
 *
 * 2. Permitted Uses. For educational purposes only, you, the end-user, may use portions of the Source Code, such as particular routines, to develop your own software, but may not duplicate the Source Code, except as noted in paragraph 4. The limited right referenced in the preceding sentence is hereinafter referred to as "Educational Use." By so exercising the Educational Use right you shall not obtain any ownership, copyright, proprietary or other interest in or to the Source Code, or any portion of the Source Code. You may dispose of your own software in your sole discretion. With the exception of the Educational Use right, you may not otherwise use the Software, or an portion of the Software, which includes the Source Code, for commercial gain.
 *
 * 3. Prohibited Uses: Under no circumstances shall you, the end-user, be permitted, allowed or authorized to commercially exploit the Software. Neither you nor anyone at your direction shall do any of the following acts with regard to the Software, or any portion thereof:
 *
 * Rent;
 *
 * Sell;
 *
 * Lease;
 *
 * Offer on a pay-per-play basis;
 *
 * Distribute for money or any other consideration; or
 *
 * In any other manner and through any medium whatsoever commercially exploit or use for any commercial purpose.
 *
 * Notwithstanding the foregoing prohibitions, you may commercially exploit the software you develop by exercising the Educational Use right, referenced in paragraph 2. hereinabove.
 *
 * 4. Copyright. The Software and all copyrights related thereto (including all characters and other images generated by the Software or depicted in the Software) are owned by AlgorithmicsAnonymous and is protected by United States copyright laws and international treaty provisions. AlgorithmicsAnonymous shall retain exclusive ownership and copyright in and to the Software and all portions of the Software and you shall have no ownership or other proprietary interest in such materials. You must treat the Software like any other copyrighted material. You may not otherwise reproduce, copy or disclose to others, in whole or in any part, the Software. You may not copy the written materials accompanying the Software. You agree to use your best efforts to see that any user of the Software licensed hereunder complies with this Agreement.
 *
 * 5. NO WARRANTIES. AlgorithmicsAnonymous DISCLAIMS ALL WARRANTIES, BOTH EXPRESS IMPLIED, INCLUDING BUT NOT LIMITED TO, IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE WITH RESPECT TO THE SOFTWARE. THIS LIMITED WARRANTY GIVES YOU SPECIFIC LEGAL RIGHTS. YOU MAY HAVE OTHER RIGHTS WHICH VARY FROM JURISDICTION TO JURISDICTION. AlgorithmicsAnonymous DOES NOT WARRANT THAT THE OPERATION OF THE SOFTWARE WILL BE UNINTERRUPTED, ERROR FREE OR MEET YOUR SPECIFIC REQUIREMENTS. THE WARRANTY SET FORTH ABOVE IS IN LIEU OF ALL OTHER EXPRESS WARRANTIES WHETHER ORAL OR WRITTEN. THE AGENTS, EMPLOYEES, DISTRIBUTORS, AND DEALERS OF AlgorithmicsAnonymous ARE NOT AUTHORIZED TO MAKE MODIFICATIONS TO THIS WARRANTY, OR ADDITIONAL WARRANTIES ON BEHALF OF AlgorithmicsAnonymous.
 *
 * Exclusive Remedies. The Software is being offered to you free of any charge. You agree that you have no remedy against AlgorithmicsAnonymous, its affiliates, contractors, suppliers, and agents for loss or damage caused by any defect or failure in the Software regardless of the form of action, whether in contract, tort, includinegligence, strict liability or otherwise, with regard to the Software. Copyright and other proprietary matters will be governed by United States laws and international treaties. IN ANY CASE, AlgorithmicsAnonymous SHALL NOT BE LIABLE FOR LOSS OF DATA, LOSS OF PROFITS, LOST SAVINGS, SPECIAL, INCIDENTAL, CONSEQUENTIAL, INDIRECT OR OTHER SIMILAR DAMAGES ARISING FROM BREACH OF WARRANTY, BREACH OF CONTRACT, NEGLIGENCE, OR OTHER LEGAL THEORY EVEN IF AlgorithmicsAnonymous OR ITS AGENT HAS BEEN ADVISED OF THE POSSIBILITY OF SUCH DAMAGES, OR FOR ANY CLAIM BY ANY OTHER PARTY. Some jurisdictions do not allow the exclusion or limitation of incidental or consequential damages, so the above limitation or exclusion may not apply to you.
 */

package xyz.aadev.generitech.common.blocks.machines;

import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import xyz.aadev.aalib.common.util.TileHelper;
import xyz.aadev.generitech.GeneriTech;
import xyz.aadev.generitech.GeneriTechTabs;
import xyz.aadev.generitech.Reference;
import xyz.aadev.generitech.api.util.MachineTier;
import xyz.aadev.generitech.common.blocks.BlockMachineBase;
import xyz.aadev.generitech.common.items.Items;
import xyz.aadev.generitech.common.tileentities.machines.TileEntityPulverizer;

import java.util.Random;

public class BlockPulverizer extends BlockMachineBase {
    private static final PropertyBool ACTIVE = PropertyBool.create("active");

    public BlockPulverizer() {
        super(Material.ROCK, "machines/pulverizer/pulverizer", MachineTier.all());
        this.setDefaultState(blockState.getBaseState().withProperty(MACHINETIER, MachineTier.TIER_0).withProperty(FACING, EnumFacing.NORTH));
        this.setTileEntity(TileEntityPulverizer.class);
        this.setCreativeTab(GeneriTechTabs.GENERAL);
        this.setInternalName("pulverizer");
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ) {
        if (!world.isRemote) {
            if (heldItem== Items.ITEM_TOOL_WRENCH.getStack(1,0)){
                System.out.println("wawdawd");
            }else {
                player.openGui(GeneriTech.getInstance(), Reference.GUI_ID.PULVERIZER, world, pos.getX(), pos.getY(), pos.getZ());

            }


        }
        return true;
    }



    @Override
    public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
        TileEntityPulverizer tileEntity = TileHelper.getTileEntity(worldIn, pos, TileEntityPulverizer.class);
        if (tileEntity != null && tileEntity.canBeRotated()) {
            return state.withProperty(FACING, tileEntity.getForward()).withProperty(ACTIVE, tileEntity.isMachineActive());
        }
        return state.withProperty(FACING, EnumFacing.NORTH).withProperty(ACTIVE, false);
    }

    @Override
    public int getLightValue(IBlockState state, IBlockAccess world, BlockPos pos) {
        IBlockState blockState = getActualState(state, world, pos);
        return (blockState.getValue(ACTIVE) && blockState.getValue(MACHINETIER) == MachineTier.TIER_0) ? 15 : 0;
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, MACHINETIER, FACING, ACTIVE);
    }

    @Override
    public int damageDropped(IBlockState state) {
        return getMetaFromState(state);
    }

    @Override
    public void breakBlock(World world, BlockPos blockPos, IBlockState blockState) {
        TileEntityPulverizer tileEntity = TileHelper.getTileEntity(world, blockPos, TileEntityPulverizer.class);
        if (tileEntity != null && !tileEntity.isPulverizerPaused()) {
            super.breakBlock(world, blockPos, blockState);
            return;
        }

        TileHelper.DropItems(tileEntity, 0, 0);
        TileHelper.DropItems(tileEntity, 2, 3);
    }

    @Override
    public void randomDisplayTick(IBlockState state, World worldIn, BlockPos pos, Random rand) {
        TileEntityPulverizer tileEntity = TileHelper.getTileEntity(worldIn, pos, TileEntityPulverizer.class);
        if (tileEntity == null)
            return;

        if (!tileEntity.isMachineActive() || tileEntity.isPulverizerPaused())
            return;

        EnumFacing enumfacing = tileEntity.getForward();
        double d0 = (double) pos.getX() + 0.5D;
        double d1 = (double) pos.getY() + rand.nextDouble() * 6.0D / 16.0D;
        double d2 = (double) pos.getZ() + 0.5D;
        double d3 = 0.52D;
        double d4 = rand.nextDouble() * 0.6D - 0.3D;

        EnumParticleTypes particleTypes = null;
        switch (tileEntity.getBlockMetadata()) {
            case 0: // Stone
                particleTypes = EnumParticleTypes.SMOKE_NORMAL;
                break;
            default:
                break;
        }

        if (particleTypes != null) {
            switch (enumfacing) {
                case WEST:
                    worldIn.spawnParticle(particleTypes, d0 - d3, d1 + 0.7f, d2 + d4, 0.0D, 0.0D, 0.0D);
                    break;
                case EAST:
                    worldIn.spawnParticle(particleTypes, d0 + d3, d1 + 0.7f, d2 + d4, 0.0D, 0.0D, 0.0D);
                    break;
                case NORTH:
                    worldIn.spawnParticle(particleTypes, d0 + d4, d1 + 0.7f, d2 - d3, 0.0D, 0.0D, 0.0D);
                    break;
                case SOUTH:
                    worldIn.spawnParticle(particleTypes, d0 + d4, d1 + 0.7f, d2 + d3, 0.0D, 0.0D, 0.0D);
                    break;
                default:
                    break;
            }
        }
    }
}
