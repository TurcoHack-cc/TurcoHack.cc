package zirve.loves.mans.features.modules.misc;

import zirve.loves.mans.features.modules.Module;
import net.minecraft.util.EnumHand;

public class OffhandSwing extends Module {
	
    public OffhandSwing() {
        super("OffhandSwing", "aeaeae", Module.Category.MISC, true, false, false);
    }
	
	    public void update() {

        mc.player.swingingHand = EnumHand.OFF_HAND;

       }

}
