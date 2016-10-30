package messagecleanmanager;

import cn.nukkit.event.EventHandler;
import cn.nukkit.event.EventPriority;
import cn.nukkit.event.Listener;
import cn.nukkit.event.block.SignChangeEvent;
import cn.nukkit.event.player.PlayerChatEvent;
import cn.nukkit.event.player.PlayerCommandPreprocessEvent;
import cn.nukkit.event.player.PlayerJoinEvent;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.TextFormat;

public class Main extends PluginBase implements Listener {
	@Override
	public void onEnable() {
		getServer().getPluginManager().registerEvents(this, this);
		initConfig();
	}
	
	private void initConfig() {
		saveDefaultConfig();
		if (getConfig().get("clean-sign") == null) {
			saveResource("config.yml", true);
		}
	}

	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		event.getPlayer().setRemoveFormat(false);
	}

	@EventHandler(priority = EventPriority.LOWEST)
	public void onCommand(PlayerCommandPreprocessEvent event) {
		if (getConfig().getBoolean("clean-command") && !event.getPlayer().isOp()) {
			event.setMessage(TextFormat.clean(event.getMessage()));
		}
	}

	@EventHandler(priority = EventPriority.LOWEST)
	public void onChat(PlayerChatEvent event) {
		if (getConfig().getBoolean("clean-chat") && !event.getPlayer().isOp()) {
			event.setMessage(TextFormat.clean(event.getMessage()));
		}
	}

	@EventHandler(priority = EventPriority.LOWEST)
	public void onSignChange(SignChangeEvent event) {
		if (getConfig().getBoolean("clean-sign") && !event.getPlayer().isOp()) {
			for (int i = 0; i < 4; i++) {
				event.setLine(i, TextFormat.clean(event.getLine(i)));
			}
		}
	}
}
