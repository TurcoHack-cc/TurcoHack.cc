package zirve.loves.mans.features.modules.movement;

import zirve.loves.mans.features.modules.Module;

public class Speed
        extends Module {
    public Speed() {
        super("Speed", "Speed.", Module.Category.MOVEMENT, true, true, false);
    }

    @Override
    public String getDisplayInfo() {
        return "Strafe";
    }
}

