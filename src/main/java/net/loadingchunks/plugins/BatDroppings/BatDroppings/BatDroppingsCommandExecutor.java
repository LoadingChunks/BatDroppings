package net.loadingchunks.plugins.BatDroppings.BatDroppings;

/*
    This file is part of BatDroppings

    Foobar is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    Foobar is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with Foobar.  If not, see <http://www.gnu.org/licenses/>.
 */

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BatDroppingsCommandExecutor implements CommandExecutor {

    private BatDroppings plugin;

    public BatDroppingsCommandExecutor(BatDroppings plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("bd")) {
        	if(args.length == 0)
        		return false;
        	else if(args[0].equalsIgnoreCase("reload"))
        	{
        		if(sender.isOp())
        		{
        			this.plugin.reloadConfig();
        			sender.sendMessage("BatDroppings Config Reloaded!");
        		} else {
        			sender.sendMessage("You must be an op to use this command.");
        		}
        		return true;
        	} else if(args[0].equalsIgnoreCase("set"))
        	{
        		if(sender.isOp())
        		{
        			if(args.length == 3 && LivingEntities.valueOf(args[1].toUpperCase()) != null)
        			{
        				double value = Double.valueOf(args[2]);
        				this.plugin.getConfig().set("drops." + args[1].toUpperCase(), value);
        				this.plugin.saveConfig();
        				sender.sendMessage(ChatColor.GOLD + args[1].toUpperCase() + " now drops " + ChatColor.GOLD + this.plugin.eco.currencyNameSingular() + value);
        			} else {
        				sender.sendMessage("Please enter a valid mob type and value.");
        				return false;
        			}
        		} else {
        			sender.sendMessage("You must be an op to use this command.");
        		}
        		return true;
        	}
        }
        return false;
    }
}
