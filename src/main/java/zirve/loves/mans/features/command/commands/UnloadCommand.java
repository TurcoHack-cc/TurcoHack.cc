package zirve.loves.mans.features.command.commands;

import zirve.loves.mans.TurcoHack;
import zirve.loves.mans.features.command.Command;

public class UnloadCommand
        extends Command {
    public UnloadCommand() {
        super("unload", new String[0]);
    }

    @Override
    public void execute(String[] commands) {
        TurcoHack.unload(true);
    }
}

