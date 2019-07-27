package fr.kaeios.explosiondrop.listeners;

import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;

import java.util.HashMap;
import java.util.Map;

public final class TntExplodeListener implements Listener {

    // Plugin configuration
    private final FileConfiguration config;
    // Contain config path of each entity that can explode
    private final Map<EntityType, String> entities = new HashMap<>();

    public TntExplodeListener(final FileConfiguration config) {
        this.config = config;
        setupEntities();
    }

    @EventHandler(ignoreCancelled = true)
    public void onTntExplode(final EntityExplodeEvent event){
        final EntityType entityType = event.getEntityType();
        // Check if drop is active for this entity
        if(!entities.containsKey(entityType)) return;
        if(!config.getBoolean("drop-all." + entities.get(entityType), false)) return;
        // Break each exploded block so they drop
        event.blockList().iterator().forEachRemaining(Block::breakNaturally);
        // Clear blocklist so tnt don't to anything more to blocks but still damage entities
        event.blockList().clear();
    }

    private void setupEntities() {
        entities.put(EntityType.PRIMED_TNT, "tnt");
        entities.put(EntityType.MINECART_TNT, "minecart-tnt");
        entities.put(EntityType.CREEPER, "creeper");
        entities.put(EntityType.ENDER_CRYSTAL, "end_crystal");
        entities.put(EntityType.WITHER, "wither");
        entities.put(EntityType.WITHER_SKULL, "wither-skull");
    }

}
