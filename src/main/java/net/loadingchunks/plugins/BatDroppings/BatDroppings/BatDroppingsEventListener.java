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

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import org.bukkit.plugin.java.JavaPlugin;

import org.bukkit.entity.Animals;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.entity.Slime;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDeathEvent;

public class BatDroppingsEventListener implements Listener {

	private BatDroppings plugin;

	public BatDroppingsEventListener(BatDroppings plugin) {
		this.plugin = plugin;
	}

	// This is just one possible event you can hook.
	// See http://jd.bukkit.org/apidocs/ for a full event list.

	// All event handlers must be marked with the @EventHandler annotation 
	// The method name does not matter, only the type of the event parameter
	// is used to distinguish what is handled.

	@EventHandler
	public void onBlockPlace(BlockPlaceEvent event) {
		Bukkit.getServer().broadcastMessage("Player " + event.getPlayer().getName() + " placed " + event.getBlock().getType() + " at " + event.getBlock().getLocation());
	}
	
	@EventHandler
	public void onEntityDeath(EntityDeathEvent event)
	{
		if(event.getEntity().getKiller() == null || !(event.getEntity().getKiller() instanceof Player) || !event.getEntity().getKiller().isOnline())
			return;
		
		if(!(event.getEntity() instanceof Monster) && !(event.getEntity() instanceof Animals))
			return;
		
		if(LivingEntities.valueOf(event.getEntityType().getName().toUpperCase()) != null)
		{
			double give = this.plugin.getConfig().getDouble("drops." + event.getEntityType().getName().toUpperCase());
			
			if(Double.compare(give, 0.0) == 0)
				return;
			
			this.plugin.getLogger().info("[BatDroppings] Is not 0.");
			
			if(event.getEntity() instanceof Slime)
				give = give * (double)((Slime)event.getEntity()).getSize();
			
			this.plugin.getLogger().info("[BatDroppings] Depositing");
			this.plugin.eco.depositPlayer(event.getEntity().getKiller().getName(), give);
			
			this.plugin.getLogger().info("[BatDroppings] Sending message.");
			
			event.getEntity().getKiller().sendMessage("Received " + ChatColor.GOLD + "$" + give + " for killing " + event.getEntityType().getName());
		} else {
			this.plugin.getLogger().info("Unkown monster type: " + event.getEntityType().getName());
		}
	}
}
