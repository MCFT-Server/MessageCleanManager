package messagecleanmanager;

import cn.nukkit.event.EventHandler;
import cn.nukkit.event.EventPriority;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerChatEvent;
import cn.nukkit.event.player.PlayerCommandPreprocessEvent;
import cn.nukkit.event.player.PlayerJoinEvent;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.TextFormat;

public class Main extends PluginBase implements Listener {
	@Override
	public void onEnable() {
		getServer().getPluginManager().registerEvents(this, this);
		saveDefaultConfig();
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		event.getPlayer().setRemoveFormat(false);
	}
	
	@EventHandler(priority = EventPriority.LOWEST)
	public void onCommand(PlayerCommandPreprocessEvent event) {
		if (getConfig().getBoolean("clean-command")) {
			event.setMessage(TextFormat.clean(event.getMessage()));
		}
	}
	
	@EventHandler(priority = EventPriority.LOWEST)
	public void onChat(PlayerChatEvent event) {
		if (getConfig().getBoolean("clean-chat")) {
			event.setMessage(TextFormat.clean(event.getMessage()));
		}
	}
}
