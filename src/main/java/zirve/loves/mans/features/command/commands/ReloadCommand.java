package zirve.loves.mans.features.command.commands;

import zirve.loves.mans.TurcoHack;
import zirve.loves.mans.features.command.Command;

public class ReloadCommand
        extends Command {
    public ReloadCommand() {
        super("reload", new String[0]);
    }

    @Override
    public void execute(String[] commands) {
        TurcoHack.reload();
    }
}

