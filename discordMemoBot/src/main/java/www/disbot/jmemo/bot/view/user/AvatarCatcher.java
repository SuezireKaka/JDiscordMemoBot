package www.disbot.jmemo.bot.view.user;

import java.awt.image.BufferedImage;
import java.net.URL;
import java.net.URLConnection;
import java.util.Objects;

import javax.imageio.ImageIO;

public class AvatarCatcher {
	public BufferedImage catchAvatar(String url) throws Exception {
		URLConnection connection = new URL(url).openConnection();
		connection.setRequestProperty("User-Agent", "bot emily-bot");
		BufferedImage profileImg;
		try {
			profileImg = ImageIO.read(connection.getInputStream());
		}
		catch (Exception ignored) {
			profileImg = ImageIO
					.read(Objects.requireNonNull(AvatarCatcher.class.getClassLoader().getResource("default_profile.jpg")));
		}
		return profileImg;
	}
}
