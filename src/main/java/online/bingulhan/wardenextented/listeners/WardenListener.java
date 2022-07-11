package online.bingulhan.wardenextented.listeners;

import io.papermc.paper.event.entity.EntityMoveEvent;
import io.papermc.paper.event.entity.WardenAngerChangeEvent;
import online.bingulhan.wardenextented.WardenExtented;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Warden;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityPotionEffectEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffectType;

public class WardenListener implements Listener {

    /*
    @EventHandler
    public void event(EntityMoveEvent e) {
        if (e.getEntity().getType().equals(EntityType.WARDEN)) {
            Location n = e.getEntity().getLocation().clone().add(0,-1,0);
            if (!n.getBlock().getType().equals(Material.AIR)) {
                n.getBlock().setType(Material.COARSE_DIRT);
            }
        }
    } */

    @EventHandler
    public void event(PlayerInteractEvent e) {

        try {
            if (e.getPlayer().getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals(ChatColor.GREEN+"Köle çağırıcı")) {
                WardenExtented.CURRENT_WARDEN.teleport(e.getPlayer().getLocation().clone().add(5, 0, 0));
                e.getPlayer().sendMessage(ChatColor.RED+"Warden Çağrıldı!");
            }
        }catch (Exception exception) {

        }
    }

    @EventHandler
    public void event(EntityDamageByEntityEvent e) {
        if (e.getEntity() instanceof Player && e.getDamager() instanceof Warden) {
            e.setCancelled(true);
        }

        if (e.getEntity() instanceof Player && !(e.getDamager() instanceof Warden) && !(e.getDamager().getType().equals(EntityType.ARROW))) {
            WardenExtented.CURRENT_WARDEN.setTarget((LivingEntity) e.getDamager());
            WardenExtented.CURRENT_WARDEN.setJumping(false);
            WardenExtented.CURRENT_WARDEN.setAnger(e.getDamager(), 100);
        }

        if (e.getDamager() instanceof Player && !(e.getEntity() instanceof Player)) {
            WardenExtented.CURRENT_WARDEN.setTarget((LivingEntity) e.getEntity());
            WardenExtented.CURRENT_WARDEN.setJumping(false);
            WardenExtented.CURRENT_WARDEN.setAnger(e.getEntity(), 100);
        }
    }

    @EventHandler
    public void event(EntitySpawnEvent e) {
        if (e.getEntity() instanceof Warden) {
            if (WardenExtented.CURRENT_WARDEN == null) {
                WardenExtented.CURRENT_WARDEN = (Warden) e.getEntity();
                WardenExtented.CURRENT_WARDEN.setCustomName(ChatColor.RED+"Köle");
                WardenExtented.CURRENT_WARDEN.setCustomNameVisible(true);

            }else {
                WardenExtented.CURRENT_WARDEN.remove();
                WardenExtented.CURRENT_WARDEN = (Warden) e.getEntity();
                WardenExtented.CURRENT_WARDEN.setCustomName(ChatColor.RED+"Köle");
                WardenExtented.CURRENT_WARDEN.setCustomNameVisible(true);


            }
        }
    }

    @EventHandler
    public void event(WardenAngerChangeEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void event(EntityPotionEffectEvent e) {
        if (e.getModifiedType().equals(PotionEffectType.BLINDNESS) || e.getModifiedType().equals(PotionEffectType.DARKNESS)) {
            e.setCancelled(true);
        }
    }


}
