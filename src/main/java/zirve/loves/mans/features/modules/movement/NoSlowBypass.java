package zirve.loves.mans.features.modules.movement;

import net.minecraft.item.Item;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemPotion;
import net.minecraft.network.play.client.CPacketEntityAction;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import zirve.loves.mans.features.modules.Module;
import zirve.loves.mans.util.Wrapper;

public class NoSlowBypass extends Module {

    public NoSlowBypass() {
        super("NoSlow", "no slow for 2b", Category.MOVEMENT, false, false, false);
    }

    private boolean sneaking;

    @Override
    public void onUpdate() {
        if(Wrapper.mc.world != null) {
            Item item = Wrapper.getPlayer().getActiveItemStack().getItem();
            if (sneaking && ((!Wrapper.getPlayer().isHandActive() && item instanceof ItemFood || item instanceof ItemBow || item instanceof ItemPotion) || (!(item instanceof ItemFood) || !(item instanceof ItemBow) || !(item instanceof ItemPotion)))) {
                Wrapper.getPlayer().connection.sendPacket(new CPacketEntityAction(Wrapper.getPlayer(), CPacketEntityAction.Action.STOP_SNEAKING));
                sneaking = false;
            }
        }
    }

    @SubscribeEvent
    public void onUseItem(LivingEntityUseItemEvent event) {
        if (!sneaking) {
            Wrapper.getPlayer().connection.sendPacket(new CPacketEntityAction(Wrapper.getPlayer(), CPacketEntityAction.Action.START_SNEAKING));
            sneaking = true;
        }
    }
}