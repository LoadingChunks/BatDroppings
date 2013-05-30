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

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Slime;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

public class BatDroppingsEventListener implements Listener {

	private BatDroppings plugin;

	public BatDroppingsEventListener(BatDroppings plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onEntityDeath(EntityDeathEvent event)
	{	
		if(event.getEntity() == null)
			return;

		if(event.getEntity().getKiller() == null || !(event.getEntity().getKiller() instanceof Player) || !event.getEntity().getKiller().isOnline())
			return;
		
		if(!(event.getEntity() instanceof LivingEntity))
			return;
		
		if(event.getEntity() instanceof Player)
			return;
		
		if(this.plugin.getConfig().getStringList("blacklist").contains(event.getEntity().getWorld()))
			return;
				
		try {
			if(LivingEntities.valueOf(event.getEntityType().getName().toUpperCase()) != null)
			{
				if(!event.getEntity().getKiller().hasPermission("bd.loot." + event.getEntityType().getName()))
					return;
				
				if(this.plugin.eco.getBalance(event.getEntity().getKiller().getName()) > this.plugin.getConfig().getDouble("maxmoney"))
					return;
	
				double give = this.plugin.getConfig().getDouble("drops." + event.getEntityType().getName().toUpperCase());
				
				if(Double.compare(give, 0.0) == 0)
					return;
				
				if(event.getEntity() instanceof Slime)
				{
					give = give * (double)((Slime)event.getEntity()).getSize() * this.plugin.getConfig().getDouble("modifiers.slime");
				}
	
				this.plugin.eco.depositPlayer(event.getEntity().getKiller().getName(), give);
				
				//event.getEntity().getKiller().sendMessage("Received " + ChatColor.GOLD + "$" + give + " for killing " + event.getEntityType().getName());
			} else {
				this.plugin.getLogger().warning("Unkown monster type: " + event.getEntityType().getName());
			}
		} catch(IllegalArgumentException e)
		{
			this.plugin.getLogger().warning("Unknown monster type: " + event.getEntityType().getName());
		}
	}
}
