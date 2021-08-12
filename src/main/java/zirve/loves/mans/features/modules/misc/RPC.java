package zirve.loves.mans.features.modules.misc;

import zirve.loves.mans.DiscordPresence;
import zirve.loves.mans.features.modules.Module;
import zirve.loves.mans.features.setting.Setting;

public class RPC
        extends Module {
    public static RPC INSTANCE;
    public Setting<Boolean> uwuMode = this.register(new Setting<Boolean>("UwUMode", false));

    public RPC() {
        super("RPC", "Discord rich presence", Category.MISC, false, false, false);
        INSTANCE = this;
    }

    @Override
    public void onEnable() {
        DiscordPresence.start();
    }

    @Override
    public void onDisable() {
        DiscordPresence.stop();
    }
}

