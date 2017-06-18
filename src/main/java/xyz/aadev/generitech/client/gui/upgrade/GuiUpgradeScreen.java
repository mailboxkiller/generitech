package xyz.aadev.generitech.client.gui.upgrade;/*
 * LIMITED USE SOFTWARE LICENSE AGREEMENT
 * This Limited Use Software License Agreement (the "Agreement") is a legal agreement between you, the end-user, and the AlgorithmicsAnonymous Team ("AlgorithmicsAnonymous"). By downloading or purchasing the software materials, which includes source code (the "Source Code"), artwork data, music and software tools (collectively, the "Software"), you are agreeing to be bound by the terms of this Agreement. If you do not agree to the terms of this Agreement, promptly destroy the Software you may have downloaded or copied.
 * AlgorithmicsAnonymous SOFTWARE LICENSE
 * 1. Grant of License. AlgorithmicsAnonymous grants to you the right to use the Software. You have no ownership or proprietary rights in or to the Software, or the Trademark. For purposes of this section, "use" means loading the Software into RAM, as well as installation on a hard disk or other storage device. The Software, together with any archive copy thereof, shall be destroyed when no longer used in accordance with this Agreement, or when the right to use the Software is terminated. You agree that the Software will not be shipped, transferred or exported into any country in violation of the U.S. Export Administration Act (or any other law governing such matters) and that you will not utilize, in any other manner, the Software in violation of any applicable law.
 * 2. Permitted Uses. For educational purposes only, you, the end-user, may use portions of the Source Code, such as particular routines, to develop your own software, but may not duplicate the Source Code, except as noted in paragraph 4. The limited right referenced in the preceding sentence is hereinafter referred to as "Educational Use." By so exercising the Educational Use right you shall not obtain any ownership, copyright, proprietary or other interest in or to the Source Code, or any portion of the Source Code. You may dispose of your own software in your sole discretion. With the exception of the Educational Use right, you may not otherwise use the Software, or an portion of the Software, which includes the Source Code, for commercial gain.
 * 3. Prohibited Uses: Under no circumstances shall you, the end-user, be permitted, allowed or authorized to commercially exploit the Software. Neither you nor anyone at your direction shall do any of the following acts with regard to the Software, or any portion thereof:
 * Rent;
 * Sell;
 * Lease;
 * Offer on a pay-per-play basis;
 * Distribute for money or any other consideration; or
 * In any other manner and through any medium whatsoever commercially exploit or use for any commercial purpose.
 * Notwithstanding the foregoing prohibitions, you may commercially exploit the software you develop by exercising the Educational Use right, referenced in paragraph 2. hereinabove.
 * 4. Copyright. The Software and all copyrights related thereto (including all characters and other images generated by the Software or depicted in the Software) are owned by AlgorithmicsAnonymous and is protected by United States copyright laws and international treaty provisions. AlgorithmicsAnonymous shall retain exclusive ownership and copyright in and to the Software and all portions of the Software and you shall have no ownership or other proprietary interest in such materials. You must treat the Software like any other copyrighted materials. You may not otherwise reproduce, copy or disclose to others, in whole or in any part, the Software. You may not copy the written materials accompanying the Software. You agree to use your best efforts to see that any user of the Software licensed hereunder complies with this Agreement.
 * 5. NO WARRANTIES. AlgorithmicsAnonymous DISCLAIMS ALL WARRANTIES, BOTH EXPRESS IMPLIED, INCLUDING BUT NOT LIMITED TO, IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE WITH RESPECT TO THE SOFTWARE. THIS LIMITED WARRANTY GIVES YOU SPECIFIC LEGAL RIGHTS. YOU MAY HAVE OTHER RIGHTS WHICH VARY FROM JURISDICTION TO JURISDICTION. AlgorithmicsAnonymous DOES NOT WARRANT THAT THE OPERATION OF THE SOFTWARE WILL BE UNINTERRUPTED, ERROR FREE OR MEET YOUR SPECIFIC REQUIREMENTS. THE WARRANTY SET FORTH ABOVE IS IN LIEU OF ALL OTHER EXPRESS WARRANTIES WHETHER ORAL OR WRITTEN. THE AGENTS, EMPLOYEES, DISTRIBUTORS, AND DEALERS OF AlgorithmicsAnonymous ARE NOT AUTHORIZED TO MAKE MODIFICATIONS TO THIS WARRANTY, OR ADDITIONAL WARRANTIES ON BEHALF OF AlgorithmicsAnonymous.
 * Exclusive Remedies. The Software is being offered to you free of any charge. You agree that you have no remedy against AlgorithmicsAnonymous, its affiliates, contractors, suppliers, and agents for loss or damage caused by any defect or failure in the Software regardless of the form of action, whether in contract, tort, includinegligence, strict liability or otherwise, with regard to the Software. Copyright and other proprietary matters will be governed by United States laws and international treaties. IN ANY CASE, AlgorithmicsAnonymous SHALL NOT BE LIABLE FOR LOSS OF DATA, LOSS OF PROFITS, LOST SAVINGS, SPECIAL, INCIDENTAL, CONSEQUENTIAL, INDIRECT OR OTHER SIMILAR DAMAGES ARISING FROM BREACH OF WARRANTY, BREACH OF CONTRACT, NEGLIGENCE, OR OTHER LEGAL THEORY EVEN IF AlgorithmicsAnonymous OR ITS AGENT HAS BEEN ADVISED OF THE POSSIBILITY OF SUCH DAMAGES, OR FOR ANY CLAIM BY ANY OTHER PARTY. Some jurisdictions do not allow the exclusion or limitation of incidental or consequential damages, so the above limitation or exclusion may not apply to you.
 */

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import xyz.aadev.aalib.client.gui.GuiBase;
import xyz.aadev.aalib.common.util.GuiHelper;
import xyz.aadev.generitech.Reference;
import xyz.aadev.generitech.api.util.MachineTier;
import xyz.aadev.generitech.client.gui.button.ButtonOverlay;
import xyz.aadev.generitech.client.gui.button.ButtonSides;
import xyz.aadev.generitech.common.blocks.Blocks;
import xyz.aadev.generitech.common.container.upgrade.ContanierUpgradeStorage;
import xyz.aadev.generitech.common.network.Network;
import xyz.aadev.generitech.common.network.messages.power.PacketSides;
import xyz.aadev.generitech.common.tileentities.TileEntityMachineBase;
import xyz.aadev.generitech.common.tileentities.power.TileEntityPower;
import xyz.aadev.generitech.common.tileentities.power.TileEntityPowerStorage;
import xyz.aadev.generitech.common.util.LanguageHelper;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class GuiUpgradeScreen extends GuiBase {

    TileEntity tileEntity;
    EntityPlayer player;
    Rectangle slot;
    Rectangle info;
    private int[] sides;
    private int machineTier;


    public GuiUpgradeScreen(InventoryPlayer inventoryPlayer, TileEntity tileEntity, int[] sides, int start, EntityPlayer player) {
        super(Reference.MOD_ID, new ContanierUpgradeStorage(inventoryPlayer, tileEntity, start));
        this.sides = sides;
        this.xSize = 176;
        this.ySize = 166;
        this.player = player;
        this.tileEntity = tileEntity;

        machineTier = MachineTier.byMeta(tileEntity.getBlockMetadata()).getTier();
        slot = new Rectangle(48, 31, 25, 24);
        info = new Rectangle(155,69,12,12);

    }

    @Override
    public void drawBG(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
        bindTexture("gui/upgrade/upgrade.png");
        drawTexturedModalRect(paramInt1, paramInt2, 0, 0, this.xSize, this.ySize);
        if (tileEntity instanceof TileEntityMachineBase) {
            sides = ((TileEntityMachineBase) tileEntity).getSides();
        }

        if (tileEntity instanceof TileEntityMachineBase) {
            //System.out.println(((TileEntityMachineBase) tileEntity).getPower());
            int temp = (int)(((float)((TileEntityMachineBase) tileEntity).getPower() / (float)((TileEntityMachineBase) tileEntity).getMaxPower()) * 26);
            if (temp > 25)temp=25;
            drawTexturedModalRect(paramInt1+11, paramInt2+56 - temp, 176, 55 - temp, 25, temp);

        }

    }

    @Override
    public void drawFG(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {



    }


    @Override
    public void initGui() {
        if (tileEntity instanceof TileEntityMachineBase) {
            this.mc.player.openContainer = this.inventorySlots;
            this.guiLeft = (this.width - this.xSize) / 2;
            this.guiTop = (this.height - this.ySize) / 2;
            int Forward = ((TileEntityMachineBase) tileEntity).getForward().getIndex();
            int Back = ((TileEntityMachineBase) tileEntity).getForward().getOpposite().getIndex();
            int Left = 4;
            int Right = 5;
            if (Forward == 3) {
                Left = 5;
                Right = 4;
            }
            if (Forward == 4) {
                Left = 3;
                Right = 2;
            }
            if (Forward == 5) {
                Left = 2;
                Right = 3;
            }
            //bottom
            this.addButton(new ButtonSides(0, guiLeft + 118, guiTop + 63, sides, tileEntity));
            //top
            this.addButton(new ButtonSides(1, guiLeft + 119, guiTop + 13, sides, tileEntity));
            this.addButton(new ButtonSides(Forward, guiLeft + 141, guiTop + 51, sides,  tileEntity));
            this.addButton(new ButtonSides(Back, guiLeft + 96, guiTop + 25, sides,  tileEntity));
            this.addButton(new ButtonSides(Right, guiLeft + 96, guiTop + 51, sides,  tileEntity));
            this.addButton(new ButtonSides(Left, guiLeft + 141, guiTop + 25, sides,  tileEntity));

            this.addButton(new ButtonOverlay(6,guiLeft + 155, guiTop + 69,tileEntity));


        }

    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        //adds side
        if (button.id==6){
            if (((TileEntityMachineBase) tileEntity).getOverlayState()) {
                ((TileEntityMachineBase) tileEntity).setOverlay_ticksLeft(0);
            } else {
                ((TileEntityMachineBase) tileEntity).setOverlay_ticksLeft(300);
            }
        } else {
            task(button.id);
        }

    }

    private void task(int i) throws IOException {
        Network.sendToServer(new PacketSides(sides, i, tileEntity.getPos()));
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);
        RenderItem renderItem = mc.getRenderItem();

        if (!slot.contains(mouseX - guiLeft, mouseY - guiTop)) {
            RenderHelper.enableGUIStandardItemLighting();
            renderItem.renderItemIntoGUI(new ItemStack(tileEntity.getBlockType(), 1, machineTier), guiLeft + 50, guiTop + 35);
        }

        if (slot.contains(mouseX - guiLeft, mouseY - guiTop)) {
            RenderHelper.enableGUIStandardItemLighting();
            renderItem.renderItemIntoGUI(new ItemStack(Blocks.BLOCK_MACHINEMATRICS.getBlock(), 1, machineTier), guiLeft + 50, guiTop + 35);
            ArrayList<String> powerMessage = new ArrayList<>();
            powerMessage.add("MachineTier (T" + machineTier + ")");
            renderToolTip(powerMessage, mouseX, mouseY);
        }
        if (info.contains(mouseX - guiLeft, mouseY - guiTop)){
            ArrayList<String> Message = new ArrayList<>();
            Message.add(LanguageHelper.TOOLTIP.translateMessage("overlay_time_remaining"+ ((TileEntityMachineBase) tileEntity).getOverlay_ticksLeft()/20));
            renderToolTip(Message, mouseX, mouseY);
        }



    }


}
