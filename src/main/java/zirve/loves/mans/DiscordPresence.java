package zirve.loves.mans;

import club.minnced.discord.rpc.DiscordEventHandlers;
import club.minnced.discord.rpc.DiscordRPC;
import club.minnced.discord.rpc.DiscordRichPresence;
import zirve.loves.mans.features.modules.misc.RPC;
import net.minecraft.client.Minecraft;

public class DiscordPresence {
    private static final DiscordRPC rpc;
    public static DiscordRichPresence presence;
    private static Thread thread;
    private static int index;

    static {
        index = 1;
        rpc = DiscordRPC.INSTANCE;
        presence = new DiscordRichPresence();
    }

    public static void start() {
        DiscordEventHandlers handlers = new DiscordEventHandlers();
        rpc.Discord_Initialize("874279968866177074", handlers, true, "");
        DiscordPresence.presence.startTimestamp = System.currentTimeMillis() / 1000L;
        DiscordPresence.presence.details = "Kurdish Edition";
        DiscordPresence.presence.state = "Playing" + " : " + Minecraft.getMinecraft().currentServerData.serverIP;
        DiscordPresence.presence.largeImageKey = "turco";
        DiscordPresence.presence.largeImageText = "TurcoHack.cc - b1";
        rpc.Discord_UpdatePresence(presence);
        thread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                rpc.Discord_RunCallbacks();
                DiscordPresence.presence.details = "UwU Edition";
                DiscordPresence.presence.state = "Playing" + " : " + Minecraft.getMinecraft().currentServerData.serverIP;
                if (RPC.INSTANCE.uwuMode.getValue().booleanValue()) {
                    if (index == 25) {
                        index = 1;
                    }
                    DiscordPresence.presence.largeImageKey = "zt" + index;
                    ++index;
                }
                rpc.Discord_UpdatePresence(presence);
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException interruptedException) {
                }
            }
        }, "RPC-Callback-Handler");
        thread.start();
    }

    public static void stop() {
        if (thread != null && !thread.isInterrupted()) {
            thread.interrupt();
        }
        rpc.Discord_Shutdown();
    }
}

