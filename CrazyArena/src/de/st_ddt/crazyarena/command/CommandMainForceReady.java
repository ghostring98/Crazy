package de.st_ddt.crazyarena.command;

import java.util.List;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.st_ddt.crazyarena.CrazyArena;
import de.st_ddt.crazyarena.arenas.Arena;
import de.st_ddt.crazyarena.participants.ParticipantType;
import de.st_ddt.crazyplugin.exceptions.CrazyCommandNoSuchException;
import de.st_ddt.crazyplugin.exceptions.CrazyCommandUsageException;
import de.st_ddt.crazyplugin.exceptions.CrazyException;
import de.st_ddt.crazyutil.modules.permissions.PermissionModule;
import de.st_ddt.crazyutil.paramitrisable.MapParamitrisable;
import de.st_ddt.crazyutil.source.Localized;

public class CommandMainForceReady extends CommandExecutor
{

	public CommandMainForceReady(final CrazyArena plugin)
	{
		super(plugin);
	}

	@Override
	@Localized("CRAZYARENA.COMMAND.FORCEREADY $Name$")
	public void command(final CommandSender sender, final String[] args) throws CrazyException
	{
		if (args.length != 1)
			throw new CrazyCommandUsageException("<Arena>");
		final Arena<?> arena = plugin.getArenaByName(args[0]);
		if (arena == null)
			throw new CrazyCommandNoSuchException("Arena", args[0]);
		for (final Player player : arena.getParticipatingPlayers(ParticipantType.SELECTING))
			if (!arena.ready(player))
				if (arena.leave(player, true))
					plugin.getArenaByPlayer().remove(player);
		plugin.sendLocaleMessage("COMMAND.FORCEREADY", sender, arena.getName());
	}

	@Override
	public List<String> tab(final CommandSender sender, final String[] args)
	{
		if (args.length != 1)
			return null;
		return MapParamitrisable.tabHelp(plugin.getArenasByName(), args[0].toLowerCase());
	}

	@Override
	public boolean hasAccessPermission(final CommandSender sender)
	{
		return PermissionModule.hasPermission(sender, "crazyarena.forceready");
	}
}
