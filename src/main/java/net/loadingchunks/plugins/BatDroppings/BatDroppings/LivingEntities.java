package net.loadingchunks.plugins.BatDroppings.BatDroppings;

public enum LivingEntities {
	PIG,COW,MUSHROOM_COW,CHICKEN,BAT,SHEEP,SNOWMAN,IRON_GOLEM,SQUID,VILLAGER,WOLF,
	ZOMBIE,SKELETON,CREEPER,SPIDER,CAVE_SPIDER,
	SLIME, SILVERFISH,
	BLAZE,WITHER_SKELETON,GHAST,PIG_ZOMBIE,MAGMA_CUBE,
	ENDERMAN,ENDERDRAGON,
	WITHER,
	GIANT;
	
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
