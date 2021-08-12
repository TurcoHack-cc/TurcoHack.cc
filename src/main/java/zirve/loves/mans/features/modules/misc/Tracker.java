package zirve.loves.mans.features.modules.misc;

import zirve.loves.mans.event.events.DeathEvent;
import zirve.loves.mans.features.command.Command;
import zirve.loves.mans.features.modules.Module;
import zirve.loves.mans.features.modules.client.HUD;
import zirve.loves.mans.util.EntityUtil;
import zirve.loves.mans.util.TextUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityExpBottle;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Objects;

public class Tracker
        extends Module {
    private static Tracker instance;
    private EntityPlayer trackedPlayer;
    private int usedExp = 0;
    private int usedStacks = 0;

    public Tracker() {
        super("Duel", "Tracks players in 1v1s.", Module.Category.MISC, true, true, false);
        instance = this;
    }

    public static Tracker getInstance() {
        if (instance == null) {
            instance = new Tracker();
        }
        return instance;
    }

    @Override
    public void onUpdate() {
        if (this.trackedPlayer == null) {
            this.trackedPlayer = EntityUtil.getClosestEnemy(1000.0);
        } else if (this.usedStacks != this.usedExp / 64) {
            this.usedStacks = this.usedExp / 64;
            Command.sendMessage(TextUtil.coloredString(this.trackedPlayer.getName() + " has used " + this.usedStacks + " stacks of XP!", HUD.getInstance().commandColor.getValue()));
        }
    }

    public void onSpawnEntity(Entity entity) {
        if (entity instanceof EntityExpBottle && Objects.equals(Tracker.mc.world.getClosestPlayerToEntity(entity, 3.0), this.trackedPlayer)) {
            ++this.usedExp;
        }
    }

    @Override
    public void onDisable() {
        this.trackedPlayer = null;
        this.usedExp = 0;
        this.usedStacks = 0;
    }

    @SubscribeEvent
    public void onDeath(DeathEvent event) {
        if (event.player.equals(this.trackedPlayer)) {
            this.usedExp = 0;
            this.usedStacks = 0;
        }
    }

    @Override
    public String getDisplayInfo() {
        if (this.trackedPlayer != null) {
            return this.trackedPlayer.getName();
        }
        return null;
    }
}

