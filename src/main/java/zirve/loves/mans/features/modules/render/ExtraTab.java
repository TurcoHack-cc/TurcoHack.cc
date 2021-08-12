package zirve.loves.mans.features.modules.render;

import com.mojang.realmsclient.gui.ChatFormatting;
import zirve.loves.mans.TurcoHack;
import zirve.loves.mans.features.modules.Module;
import zirve.loves.mans.features.setting.Setting;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.scoreboard.ScorePlayerTeam;


public class ExtraTab
        extends Module {
    private static ExtraTab INSTANCE = new ExtraTab();
    public Setting<Integer> size = this.register(new Setting<Integer>("Size", 250, 1, 1000));

    public ExtraTab() {
        super("ExtraTab", "Extends Tab.", Module.Category.MISC, false, true, false);
        this.setInstance();
    }

    public static String getPlayerName(NetworkPlayerInfo networkPlayerInfoIn) {
        String name;
        String string = name = networkPlayerInfoIn.getDisplayName() != null ? networkPlayerInfoIn.getDisplayName().getFormattedText() : ScorePlayerTeam.formatPlayerName(networkPlayerInfoIn.getPlayerTeam(), networkPlayerInfoIn.getGameProfile().getName());
        if (TurcoHack.friendManager.isFriend(name)) {
            return ChatFormatting.AQUA + name;
        }
        return name;
    }

    public static ExtraTab getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new ExtraTab();
        }
        return INSTANCE;
    }

    private void setInstance() {
        INSTANCE = this;
    }
}

