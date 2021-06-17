package me.waqe.plugin.events

import me.waqe.plugin.App
import net.md_5.bungee.api.ChatColor
import org.bukkit.Color
import org.bukkit.FireworkEffect
import org.bukkit.FireworkEffect.Type
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.entity.Firework
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.inventory.ItemStack

class JoinEvent(instance: App) : Listener {

    private val plugin = instance
    var prefix = ChatColor.GREEN.toString() + "TUTORIAL>> "

    @EventHandler
    fun onJoin(event: PlayerJoinEvent) {
        val player = event.player
        event.joinMessage = ""
        player.sendMessage(prefix + ChatColor.GOLD + "Welcome back, " + player.name)
        val item = ItemStack(Material.BOOK, 1)
        val meta = item.itemMeta
        meta!!.setDisplayName(ChatColor.LIGHT_PURPLE.toString() + "Welcome Book")
        val lore = ArrayList<String>()
        lore.add(ChatColor.WHITE.toString() + "Read this for instructions and rules")
        meta.lore = lore
        item.itemMeta = meta
        player.inventory.setItem(0, item)
        val spawn = Location(player.world, -10.5, 56.5, -2.5)
        player.teleport(spawn)

        // =======================
        if (!player.hasPlayedBefore()) {
            val fw = player.world.spawn(player.location, Firework::class.java)
            val fwm = fw.fireworkMeta
            val builder = FireworkEffect.builder()
            fwm.addEffect(builder.flicker(true).withColor(Color.BLUE).build())
            fwm.addEffect(builder.trail(true).build())
            fwm.addEffect(builder.withFade(Color.ORANGE).build())
            fwm.addEffect(builder.with(Type.CREEPER).build())
            fwm.power = 2
            fw.fireworkMeta = fwm
        }
    }}
