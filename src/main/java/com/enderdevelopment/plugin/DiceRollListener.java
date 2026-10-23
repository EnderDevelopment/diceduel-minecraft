package com.enderdevelopment.plugin;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;

import java.util.Random;

public class DiceRollListener implements Listener {

    private final Random random = new Random();
    private final Plugin plugin = Main.getPlugin(Main.class);

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player) {
            Player player = (Player) event.getDamager();
            Entity target = event.getEntity();

            if (target instanceof LivingEntity) {
                LivingEntity mob = (LivingEntity) target;
                int dc = random.nextInt(20) + 1;
                mob.setMetadata("dc", new FixedMetadataValue(plugin, dc));
                mob.setAI(false);

                int attackRoll = rollDice(player.getInventory().getItemInMainHand());
                player.sendMessage("You rolled a " + attackRoll + " against DC " + dc);

                if (attackRoll >= dc) {
                    player.sendMessage("Attack successful!");
                } else {
                    player.sendMessage("Attack failed!");
                }

                mob.setAI(true);
                mob.removeMetadata("dc", plugin);
            }
        }
    }

    private int rollDice(ItemStack weapon) {
        if (weapon == null) {
            return random.nextInt(4) + 1; // d4 for fists
        }

        switch (weapon.getType()) {
            case WOODEN_SWORD:
                return random.nextInt(6) + 1; // d6
            case STONE_SWORD:
                return random.nextInt(8) + 1; // d8
            case COPPER_SWORD:
                return random.nextInt(10) + 1; // d10
            case IRON_SWORD:
                return random.nextInt(12) + 1; // d12
            case DIAMOND_SWORD:
                return random.nextInt(20) + 1; // d20
            case NETHERITE_SWORD:
                return Math.max(random.nextInt(20) + 1, random.nextInt(20) + 1); // 2d20 (highest)
            default:
                return random.nextInt(4) + 1; // d4 for fists
        }
    }
}