
/*
 *************************************
 ***  © Maxence Vérité 2018 **********
 *************************************
 */

package tool;

import javafx.beans.property.DoubleProperty;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class SoundBox {

	public static final double SON_CONSEILLE = 0.25; // SON MAXIMUM CONSEILLE
	public static boolean isPlayingMusic;// Si la musique est déjà en marche
	public static MediaPlayer musicPlayer, soundPlayer;

	public static void playSound(String URI) {
		double precedentVolume;

		Media m = new Media(URI);
		soundPlayer = new MediaPlayer(m);

		if (musicPlayer.getVolume() > 0.8) {
			precedentVolume = musicPlayer.getVolume();

			musicPlayer.setVolume(musicPlayer.getVolume() - 0.25);

			soundPlayer.setOnEndOfMedia(new Runnable() {

				public void run() {
					musicPlayer.setVolume(precedentVolume);
				}
			});
		}

		soundPlayer.play();

	}

	public static void playBackgroundMusic(String URI) {
		isPlayingMusic = true;

		Media m = new Media(URI);

		musicPlayer = new MediaPlayer(m);

		musicPlayer.setOnEndOfMedia(new Runnable() { // si la musique s'arrête, on recommence.
			public void run() {
				musicPlayer.seek(Duration.ZERO);
			}
		});
		musicPlayer.play();
		musicPlayer.setVolume(SON_CONSEILLE);
	}

	public static void stopMusic() {
		musicPlayer.setOnEndOfMedia(null); // coupe le listener qui rejoue la musique en boucle
		musicPlayer.stop(); // arrete la musique
		isPlayingMusic = false;
	}

	public static DoubleProperty getVolumeProperty() {
		return musicPlayer.volumeProperty(); // volume property pour lié au contrôleur qui agit sur le volume.
	}

}
