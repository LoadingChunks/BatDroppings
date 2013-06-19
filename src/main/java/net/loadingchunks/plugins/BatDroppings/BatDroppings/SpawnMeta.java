package net.loadingchunks.plugins.BatDroppings.BatDroppings;

import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;

public class SpawnMeta implements MetadataValue {
	
	SpawnReason reason;
	BatDroppings plugin;
	
	public SpawnMeta(BatDroppings lplugin, SpawnReason lreason) {
		reason = lreason;
		this.plugin = lplugin;
	}

	public boolean asBoolean() {
		return false;
	}

	public byte asByte() {
		return 0;
	}

	public double asDouble() {
		return 0;
	}

	public float asFloat() {
		return 0;
	}

	public int asInt() {
		return 0;
	}

	public long asLong() {
		return 0;
	}

	public short asShort() {
		return 0;
	}

	public String asString() {
		return null;
	}

	public Plugin getOwningPlugin() {
		return plugin;
	}

	public void invalidate() {

	}

	public Object value() {
		return null;
	}
	
	public SpawnReason asSpawnReason() {
		return reason;
	}

}
