package maps;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

import characters.Player;
import characters.Warrior;
import wave.Wave;

public class MainCity extends InGameScreen{
	private Wave game;
	private Music music;
	private Warrior player;
	
	public MainCity(Wave game) {
		this.game = game;
		tiledMap = new TmxMapLoader().load("map/PortalArea1/portal_area_1.tmx");
		mainLayer = (TiledMapTileLayer) tiledMap.getLayers().get("Ground");
		tileSize = (int) mainLayer.getTileWidth();
		mapWH = mainLayer.getWidth() * tileSize;  
		mapRight = mapTop = mapWH * RATIO;
		
		renderer = new OrthogonalTiledMapRenderer(tiledMap, RATIO);
		
		player = new Warrior("SC", "Warrior", 1);
		
//		music = Gdx.audio.newMusic(Gdx.files.internal("Music/cancer.ogg"));
//		music.setVolume(0.1f);
//		music.play();
	}

	public void update(float delta) {
		// Move based on mouse movement
		if (Gdx.input.isTouched()) {
			cam.translate(Gdx.input.getDeltaX(), Gdx.input.getDeltaY());
			cam.update();
		}
		
		moveCam(delta);
	}
	
	@Override
	public void render(float delta) {
		// Exit if pressed
		if (Gdx.input.isKeyPressed(Keys.ESCAPE)) {
			Gdx.app.exit();
		}
		

		
		// Set up camera and view
		game.batch.setProjectionMatrix(cam.combined);
		renderer.setView(cam);
		renderer.render();
		// Update based on update func
		update(delta);
		game.batch.begin();
		game.batch.draw(player.getTexture(), 300, 50, 30, 30);
		game.batch.end();
	}
	
	@Override
	public void dispose() {
		// Destroy music
		music.dispose();
	}

}
