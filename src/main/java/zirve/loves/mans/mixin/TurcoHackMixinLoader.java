package zirve.loves.mans.mixin;

import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import org.spongepowered.asm.launch.MixinBootstrap;
import org.spongepowered.asm.mixin.MixinEnvironment;
import org.spongepowered.asm.mixin.Mixins;
import zirve.loves.mans.TurcoHack;

import java.util.Map;

public class TurcoHackMixinLoader
        implements IFMLLoadingPlugin {
    private static boolean isObfuscatedEnvironment = false;

    public TurcoHackMixinLoader() {
        TurcoHack.LOGGER.info("\n\nLoading mixins by CumHax");
        MixinBootstrap.init();
        Mixins.addConfiguration("mixins.TurcoHack.json");
        MixinEnvironment.getDefaultEnvironment().setObfuscationContext("searge");
        TurcoHack.LOGGER.info(MixinEnvironment.getDefaultEnvironment().getObfuscationContext());
    }

    public String[] getASMTransformerClass() {
        return new String[0];
    }

    public String getModContainerClass() {
        return null;
    }

    public String getSetupClass() {
        return null;
    }

    public void injectData(Map<String, Object> data) {
        isObfuscatedEnvironment = (Boolean) data.get("runtimeDeobfuscationEnabled");
    }

    public String getAccessTransformerClass() {
        return null;
    }
}

