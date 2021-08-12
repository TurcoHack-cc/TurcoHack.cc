package zirve.loves.mans.features.command.commands;

import com.mojang.realmsclient.gui.ChatFormatting;
import zirve.loves.mans.TurcoHack;
import zirve.loves.mans.features.command.Command;

public class HelpCommand
        extends Command {
    public HelpCommand() {
        super("help");
    }

    @Override
    public void execute(String[] commands) {
        HelpCommand.sendMessage("Commands: ");
        for (Command command : TurcoHack.commandManager.getCommands()) {
            HelpCommand.sendMessage(ChatFormatting.GRAY + TurcoHack.commandManager.getPrefix() + command.getName());
        }
    }
}

