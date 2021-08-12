package zirve.loves.mans.features.gui;

import zirve.loves.mans.TurcoHack;
import zirve.loves.mans.features.Feature;
import zirve.loves.mans.features.gui.components.Component;
import zirve.loves.mans.features.gui.components.items.Item;
import zirve.loves.mans.features.gui.components.items.buttons.ModuleButton;
import zirve.loves.mans.features.modules.Module;
import net.minecraft.client.gui.GuiScreen;
import org.lwjgl.input.Mouse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

public class TurcoHackGui
        extends GuiScreen {
    private static TurcoHackGui turcoGui;
    private static TurcoHackGui INSTANCE;

    static {
        INSTANCE = new TurcoHackGui();
    }

    private final ArrayList<Component> components = new ArrayList();

    public TurcoHackGui() {
        this.setInstance();
        this.load();
    }

    public static TurcoHackGui getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new TurcoHackGui();
        }
        return INSTANCE;
    }

    public static TurcoHackGui getClickGui() {
        return TurcoHackGui.getInstance();
    }

    private void setInstance() {
        INSTANCE = this;
    }

    private void load() {
        int x = -84;
        for (final Module.Category category : TurcoHack.moduleManager.getCategories()) {
            this.components.add(new Component(category.getName(), x += 90, 4, true) {

                @Override
                public void setupItems() {
                    counter1 = new int[]{1};
                    TurcoHack.moduleManager.getModulesByCategory(category).forEach(module -> {
                        if (!module.hidden) {
                            this.addButton(new ModuleButton(module));
                        }
                    });
                }
            });
        }
        this.components.forEach(components -> components.getItems().sort(Comparator.comparing(Feature::getName)));
    }

    public void updateModule(Module module) {
        for (Component component : this.components) {
            for (Item item : component.getItems()) {
                if (!(item instanceof ModuleButton)) continue;
                ModuleButton button = (ModuleButton) item;
                Module mod = button.getModule();
                if (module == null || !module.equals(mod)) continue;
                button.initSettings();
            }
        }
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.checkMouseWheel();
        this.drawDefaultBackground();
        this.components.forEach(components -> components.drawScreen(mouseX, mouseY, partialTicks));
    }

    public void mouseClicked(int mouseX, int mouseY, int clickedButton) {
        this.components.forEach(components -> components.mouseClicked(mouseX, mouseY, clickedButton));
    }

    public void mouseReleased(int mouseX, int mouseY, int releaseButton) {
        this.components.forEach(components -> components.mouseReleased(mouseX, mouseY, releaseButton));
    }

    public boolean doesGuiPauseGame() {
        return false;
    }

    public final ArrayList<Component> getComponents() {
        return this.components;
    }

    public void checkMouseWheel() {
        int dWheel = Mouse.getDWheel();
        if (dWheel < 0) {
            this.components.forEach(component -> component.setY(component.getY() - 10));
        } else if (dWheel > 0) {
            this.components.forEach(component -> component.setY(component.getY() + 10));
        }
    }

    public int getTextOffset() {
        return -6;
    }

    public Component getComponentByName(String name) {
        for (Component component : this.components) {
            if (!component.getName().equalsIgnoreCase(name)) continue;
            return component;
        }
        return null;
    }

    public void keyTyped(char typedChar, int keyCode) throws IOException {
        super.keyTyped(typedChar, keyCode);
        this.components.forEach(component -> component.onKeyTyped(typedChar, keyCode));
    }
}

