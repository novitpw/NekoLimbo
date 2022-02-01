package ru.novit.nekocloud.limbo.listeners;

import com.destroystokyo.paper.event.player.PlayerInitialSpawnEvent;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.*;
import org.bukkit.event.entity.*;
import org.bukkit.event.hanging.HangingBreakByEntityEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.*;
import org.bukkit.event.world.StructureGrowEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import ru.novit.nekocloud.limbo.NekoLimbo;


public class LobbyGuardListener implements Listener {

    private final NekoLimbo nekoLimbo;

    public LobbyGuardListener(NekoLimbo nekoLimbo) {
        this.nekoLimbo = nekoLimbo;

        Bukkit.getPluginManager().registerEvents(this, nekoLimbo);

        Bukkit.getScheduler().runTaskTimer(nekoLimbo, () -> {
            for (World world : Bukkit.getWorlds()) {
                if (world == null)
                    continue;

                world.setTime(nekoLimbo.getConfig().getLong("fixedTime"));
                world.setWeatherDuration(0);
                world.setStorm(false);
            }
        }, 0L, 6000L);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onJoinPlayer(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        for (Player otherPlayer : Bukkit.getOnlinePlayers()) {
            otherPlayer.hidePlayer(player);
            player.hidePlayer(otherPlayer);
        }
        e.setJoinMessage(null);

        if (nekoLimbo.getConfig().getBoolean("giveSpeed")) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 999999, 1));
        }
        player.teleport(nekoLimbo.getSpawnLocation()); //костыль
    }

    @EventHandler
    public void onBreakPainting(HangingBreakByEntityEvent e) {
        e.setCancelled(true);
    }

//    @EventHandler //не устанавливает yaw игроку
//    public void onJoin(PlayerInitialSpawnEvent e) {
//        e.setSpawnLocation(nekoLimbo.getSpawnLocation());
//    }

    @EventHandler
    public void onQuitPlayer(PlayerQuitEvent event) {
        event.setQuitMessage(null);
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onDamage(EntityDamageEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onIteractPlayer(PlayerInteractEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onIteractEntity(EntityInteractEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onInteract(PlayerInteractEntityEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onSwap(PlayerSwapHandItemsEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onPickup(EntityPickupItemEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onIgnite(BlockIgniteEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onSpread(BlockSpreadEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onBurn(BlockBurnEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onBlockForm(BlockFromToEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onBlockFade(BlockFadeEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onPhysic(BlockPhysicsEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onFood(FoodLevelChangeEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onExplode(EntityExplodeEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onGrow(StructureGrowEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onForm(BlockFormEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onLeaves(LeavesDecayEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onEntityChangeBlock(EntityChangeBlockEvent e) {
        e.setCancelled(true);
    }
}
