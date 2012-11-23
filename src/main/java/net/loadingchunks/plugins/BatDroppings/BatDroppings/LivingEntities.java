package net.loadingchunks.plugins.BatDroppings.BatDroppings;

public enum LivingEntities {
	PIG,COW,MUSHROOMCOW,CHICKEN,BAT,SHEEP,SNOWMAN,IRON_GOLEM,SQUID,VILLAGER,WOLF,
	ZOMBIE,SKELETON,CREEPER,SPIDER,CAVESPIDER,
	SLIME, SILVERFISH,
	BLAZE,WITHER_SKELETON,GHAST,PIGZOMBIE,LAVASLIME,
	ENDERMAN,ENDERDRAGON,
	WITHER,
	GIANT,
	WITCH;
	
	public static final int length = LivingEntities.values().length;
	private final boolean defaultEnabled;
	private LivingEntities()
	{
		this(false);
	}
	
	private LivingEntities(boolean defaultEnabled) {
		this.defaultEnabled = defaultEnabled;
	}

	public boolean isDefaultEnabled() {
		return defaultEnabled;
	}
}
