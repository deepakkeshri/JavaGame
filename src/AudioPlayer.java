import java.util.HashMap;
import java.util.Map;

import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

public class AudioPlayer {
	
	private static Map<String,Music> musicMap = new HashMap<String,Music>();
	private static Map<String,Sound> soundMap = new HashMap<String,Sound>();
	
	public static void load() {
		try {
			musicMap.put("music", new Music( "/res/background_music.ogg"));
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			soundMap.put("sound", new Sound("/res/click_sound.ogg"));
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			soundMap.put("collision", new Sound("/res/collision.ogg"));
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			soundMap.put("boss", new Sound("/res/boss.ogg"));
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			soundMap.put("life", new Sound("/res/life.ogg"));
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Music getMusic(String key){
		return musicMap.get(key);
	}
	
	public static Sound getSound(String key){
		return soundMap.get(key);
	}
 
}
