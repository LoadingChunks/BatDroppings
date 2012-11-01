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

import net.milkbowl.vault.economy.Economy;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class BatDroppings extends JavaPlugin {

	//ClassListeners
	private BatDroppingsCommandExecutor commandExecutor;
	private BatDroppingsEventListener eventListener;
	//ClassListeners
	
	public Economy eco = null;

	public void onDisable() {
		// add any code you want to be executed when your plugin is disabled
	}

	public void onEnable() {
		PluginManager pm = this.getServer().getPluginManager();
		
		RegisteredServiceProvider<Economy> economy = getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
		
		if(eco == null)
		{
			this.getLogger().warning("[BatDroppings] Vault not found, disabling.");
			pm.disablePlugin(this);
			return;
		}
		
		this.eco = economy.getProvider();
		
		this.commandExecutor = new BatDroppingsCommandExecutor(this);
		this.eventListener = new BatDroppingsEventListener(this);

		getCommand("bd").setExecutor(commandExecutor);

		// you can register multiple classes to handle events if you want
		// just call pm.registerEvents() on an instance of each class
		pm.registerEvents(eventListener, this);

		for(LivingEntities l : LivingEntities.values())
		{
			if(!this.getConfig().contains("drops." + l.toString()))
				this.getConfig().addDefault("drops." + l.toString(), 0.0);				
		}
		
		this.getConfig().options().copyDefaults(true);
		this.saveConfig();
	}
}
