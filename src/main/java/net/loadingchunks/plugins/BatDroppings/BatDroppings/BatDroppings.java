package net.loadingchunks.plugins.BatDroppings.BatDroppings;

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
		
		if(economy == null)
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
		
		this.getConfig().addDefault("modifiers.slime", 0.5);
		
		this.getConfig().options().copyDefaults(true);
		this.saveConfig();
	}
}
