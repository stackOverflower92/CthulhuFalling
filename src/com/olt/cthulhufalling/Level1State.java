package com.olt.cthulhufalling;


import java.util.ArrayList;
import java.util.Random;

import org.lwjgl.util.vector.Vector2f;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.ShapeFill;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.fills.GradientFill;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;


public class Level1State extends BasicGameState {
	public static int id = 2;
	
	// Level background image
	private Image mBackground;
	
	// Time between one spawn and another (milliseconds)
	private int mEnemiesSpawnRate = 1000;
	private int mEnemiesCountRate = 0;
	
	// Same thing for bonuses
	private int mBonusSpawnRate = 1500;
	private int mBonusCountRate = 0;
	
	// Min and max sizes for enemies
	private int mEnemiesMinSize = 80;
	private int mEnemiesMaxSize = 150;
	
	// Fall speed rate
	private float mFallSpeedRate = 0.3f;
	
	// Array used to store enemies falling down
	private ArrayList<LittleCthulhu> mEnemies = new ArrayList<LittleCthulhu>();
	private ArrayList<Bonus> mGameBonuses = new ArrayList<Bonus>();
	
	// Random
	private Random mRand = new Random();
	
	// Static declaration for level bonuses
	private Bonus[] mBonuses = new Bonus[] {
		new Bonus(70, BonusEffect.SLOW_TIME_5, mRand.nextInt(Constants.SCREEN_WIDTH)),
		new Bonus(27, BonusEffect.SLOW_TIME_10, mRand.nextInt(Constants.SCREEN_WIDTH)),
		new Bonus(3, BonusEffect.IMMUNITY_5, mRand.nextInt(Constants.SCREEN_WIDTH))
	};
	
	// Level stats
	// Target points to pass to next level
	private int mTargetPoints = 70;
	
	// Current points
	private int mCurrentPoints = 0;
	
	// Points increase factor
	private int mPointsIncreaseFactor = 5;
	
	// Points decrease factor
	private int mPointsDecreaseFactor = 5;
	
	// Current state of the level (playing or finished)
	private LevelState mCurrentLevelState = LevelState.PLAYING;
	
	// Random string extracted when player wins
	private String mLevelWinString;
	
	// Player
	private Player mPlayer;

	private void addBonus(Bonus bonus) {
		mGameBonuses.add(bonus);
	}

	private void removeBonus(Bonus bonus) {
		mGameBonuses.remove(bonus);
	}

	private void addEnemy(GameContainer container, Rectangle size, float speed) {
		mEnemies.add(new LittleCthulhu(container, size, speed));
	}

	private void removeEnemy(LittleCthulhu enemy) {
		mEnemies.remove(enemy);
	}

	private Bonus extractBonus() {
		Random _rand = new Random();
		int _currentSum = 0;
		int _pick;
		
		// Calculate bounds for every bonus
		for (int i = 0; i < mBonuses.length; i++) {
			if (i == 0) {
				mBonuses[i].setLowProbabilityBound(0);
				mBonuses[i].setHighProbabilityBound(mBonuses[i].getProbability());
				
				_currentSum = mBonuses[i].getProbability();
			} else {
				// Calculate sum
				_currentSum += mBonuses[i].getProbability();
				
				// Set upper bound
				mBonuses[i].setHighProbabilityBound(_currentSum);
				mBonuses[i].setLowProbabilityBound(mBonuses[i - 1].getHighProbabilityBound() + 1);
			}
		}
		
		// Now pick a number between 0 and 100
		_pick = _rand.nextInt(100);
		
		for (int i = 0; i < mBonuses.length; i++) {
			if (mBonuses[i].getLowProbabilityBound() <= _pick && mBonuses[i].getHighProbabilityBound() >= _pick) {
				// This is our guy
				return mBonuses[i];
			}
		}
		// We should never rech this
		return null;
	}
	
	private void increasePoints() {
		mCurrentPoints += mPointsIncreaseFactor;
		
		// Check if level points target is reached
		if (mTargetPoints <= mCurrentPoints) {
			// Good, player won this level! let's show that!
			mCurrentLevelState = LevelState.FINISHED;
		}
	}
	
	private void decreasePoints() {
		if ((mCurrentPoints - mPointsDecreaseFactor) > 0)
			mCurrentPoints -= mPointsDecreaseFactor;
		else
			mCurrentPoints = 0;
	}

	// HUD stuff
	private void drawIngameHUD(GameContainer container, Graphics graphics) {
		Rectangle _topRect = new Rectangle(0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT / 11);
		
	}
	
	private void drawLevelFinishHUD(Graphics graphics) {
		int _midRectHeight = Constants.SCREEN_HEIGHT / 10;
		Rectangle _midRect = new Rectangle(0, Constants.SCREEN_HEIGHT / 2 - _midRectHeight, Constants.SCREEN_WIDTH, _midRectHeight);
		
		// Render
		graphics.drawString(mLevelWinString, Constants.SCREEN_WIDTH / 2, Constants.SCREEN_HEIGHT / 2);
	}
	
	private void drawLevelLostHUD(Graphics graphics) {
		graphics.drawString("You Died!", Constants.SCREEN_WIDTH / 2, Constants.SCREEN_HEIGHT / 2);
	}
	
	private void extractWinSentence() {
		mLevelWinString = Constants.WIN_SENTENCES[new Random().nextInt(Constants.WIN_SENTENCES.length)];
	}
	
	private boolean playerCollidesWithEnemy() {
		boolean result = false;
		
		for (int i = 0; i < mEnemies.size(); i++) {
			if (mPlayer.getRect().intersects(mEnemies.get(i).getRect())) {
				result = true;
			}
		}
		
		return result;
	}
	
	@Override
	public void enter(GameContainer container, StateBasedGame game) throws SlickException {
		// TODO Auto-generated method stub
		super.enter(container, game);
		
		// Reset stuff
		mEnemies = new ArrayList<LittleCthulhu>();
		mGameBonuses = new ArrayList<Bonus>();
		
		mPlayer = new Player();
	}

	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		// Init background
		mBackground = new Image("assets/images/wallpapers/wallpaper-level0.jpg");
		
		// Extract win string for level
		extractWinSentence();
		
		// Initialize player
		mPlayer = new Player();
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics graphics) throws SlickException {
		// Draw background
		mBackground.draw(0f, 0f, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
		
		if (mCurrentLevelState == LevelState.PLAYING) {
			// Draw enemies
			for (int i = 0; i < mEnemies.size(); i++) {			
				// Always render enemies existing in list
				mEnemies.get(i).render(container, graphics);
			}
			
			// Draw bonuses
			for (int i = 0; i < mGameBonuses.size(); i++) {
				// Always render bonuses existing in list
				mGameBonuses.get(i).render(container, graphics);
			}
			
			// Draw player
			mPlayer.render(graphics);
		}
		if (mCurrentLevelState == LevelState.FINISHED) {
			// Draw finish level stuff
			drawLevelFinishHUD(graphics);
		}
		if (mCurrentLevelState == LevelState.LOST) {
			// Player died
			drawLevelLostHUD(graphics);
		}
		if (mCurrentLevelState == LevelState.PAUSED) {
			// Player paused the game
		}
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {
		if (mCurrentLevelState == LevelState.PLAYING) {
			mEnemiesCountRate++;
			mBonusCountRate++;
			
			// Update already added enemies and check for end of the screen
			for (int i = 0; i < mEnemies.size(); i++) {
				// Update every enemy
				mEnemies.get(i).update(container, delta);
				
				// Check end of the screen and remove unused enemies
				if (mEnemies.get(i).getPosition().getY() > Constants.SCREEN_HEIGHT) {
					// Remove enemy from array
					removeEnemy(mEnemies.get(i));
					
					// Increase points
					increasePoints();
				}
			}
			
			// Add new enemies every mEnemiesSpawnRate seconds
			if (mEnemiesCountRate % mEnemiesSpawnRate == 0) {
				Random _rand = new Random();
				
				// Extract random number between min and max size and set them
				int _finalSize = _rand.nextInt((mEnemiesMaxSize - mEnemiesMinSize) + 1) + mEnemiesMinSize;
				Rectangle _sizeAndPosition = new Rectangle(
						_rand.nextInt((Constants.SCREEN_WIDTH) + 1),
						0,
						_finalSize,
						_finalSize					
				);
				
				// Add new enemy to the collection
				addEnemy(container, _sizeAndPosition, mFallSpeedRate);
			}
			
			// Update already added bonuses and check for end of the screen
			for (int i = 0; i < mGameBonuses.size(); i++) {
				// Update every bonus
				mGameBonuses.get(i).update(container, delta);
				
				
				// Check the end of the screen and remove unused bonuses
				if (mGameBonuses.get(i).getPosition().getY() > Constants.SCREEN_HEIGHT) {
					removeBonus(mGameBonuses.get(i));
				}
			}
			
			if (mBonusCountRate % mBonusSpawnRate == 0) {
				Bonus _extBonus = extractBonus();
				
				if (_extBonus != null)
					addBonus(_extBonus);
				else
					System.out.println("NULL BONUS");
			}
			
			// Update player
			mPlayer.update(container, delta);
			
			// Check if player collides with enemies
			if (playerCollidesWithEnemy()) {
				System.out.println("Dead!");
				mCurrentLevelState = LevelState.LOST;
			}
		} 
		if (mCurrentLevelState == LevelState.FINISHED) {
			// Level is finished, show screen before going to next level
		}
		if (mCurrentLevelState == LevelState.LOST) {
			// Player died, go back to main menu
			mCurrentLevelState = LevelState.PLAYING;
			game.enterState(MainMenuState.id, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
		}
		if (mCurrentLevelState == LevelState.PAUSED) {
			// Player paused the game
		}
	}

	@Override
	public int getID() {
		return id;
	}
}
