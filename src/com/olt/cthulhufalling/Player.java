package com.olt.cthulhufalling;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.BasicGameState;

public class Player {
	// Members
	private int mMaxPoints;
	private int mMaxLevel;
	
	private Animation mEnemyIncomingAnimation;
	private Animation mDeathAnimation;
	private Animation mLevelWinAnimation;
	private Animation mRunLeftAnimation;
	private Animation mRunRightAnimation;
	private Animation mJumpAnimation;
	private Animation mCurrentAnimation;
	private Animation mStandAnimation;
	
	private Image[] mEnemyIncomingAnimationFrames;
	private Image[] mDeathAnimationFrames;
	private Image[] mLevelWinAnimationFrames;
	private Image[] mRunLeftAnimationFrames;
	private Image[] mRunRightAnimationFrames;
	private Image[] mJumpAnimationFrames;
	private Image[] mStandAnimationFrames;
	
	private int mEnemyIncomingAnimationDuration = 500;
	private int mDeathAnimationDuration = 500;
	private int mRunLeftAnimationDuration = 500;
	private int mRunRightAnimationDuration = 500;
	private int mLevelWinAnimationDuration = 500;
	private int mJumpAnimationDuration = 500;
	private int mStandAnimationDuration = 500;
	
	private Rectangle mRect;
	
	private Bonus mCurrentBonus;
	
	private int mHorizontalMoveFactor = 1;
	private int mVerticalMoveFactor = 3;
	private int mVerticalGravityDirection = 1;
	
	private boolean mIsJumping = false;
	private float mGravity = 0.3f;
	private float mVerticalAcceleration = 0;
	private float mJumpStrength = 4f;
	private boolean mIsGravityApplied = true;
	
	private BasicGameState mCurrentLevel;
	
	// Tiles for collisions
	private Tile mTopBoundingTile;
	private Tile mLeftBoundingTile;
	private Tile mRightBoundingTile;
	private Tile mBottomBoundingTile;
	
	// Constructors
	public Player() {
		init();
	}
	
	// Getters and Setters
	public int getMaxPoints() {
		return this.mMaxPoints;
	}
	
	public void setMaxPoints(int maxPoints) {
		this.mMaxPoints = maxPoints;
	}
	
	public int getMaxLevel() {
		return this.mMaxLevel;
	}
	
	public void setMaxLevel(int maxLevel) {
		this.mMaxLevel = maxLevel;
	}
	
	// Methods
	public void getInput(GameContainer container) {
		Input input = container.getInput();
		
		if (input.isKeyDown(Input.KEY_D) || input.isKeyDown(Input.KEY_RIGHT)) {
			runRight();
		} else if (input.isKeyDown(Input.KEY_A) || input.isKeyDown(Input.KEY_LEFT)) {
			runLeft();
		} else if (input.isKeyDown(Input.KEY_SPACE) || input.isKeyDown(Input.KEY_UP)) {
			jump();
		} else if (input.isKeyDown(Input.KEY_ENTER)) {
			useBonus();
		} else {
			stand();
		}
	}
	
	public Tile getTopBoundingTile() {
		return mTopBoundingTile;
	}

	public void setTopBoundingTile(Tile mTopBoundingTile) {
		this.mTopBoundingTile = mTopBoundingTile;
	}

	public Tile getLeftBoundingTile() {
		return mLeftBoundingTile;
	}

	public void setLeftBoundingTile(Tile mLeftBoundingTile) {
		this.mLeftBoundingTile = mLeftBoundingTile;
	}

	public Tile getRightBoundingTile() {
		return mRightBoundingTile;
	}

	public void setRightBoundingTile(Tile mRightBoundingTile) {
		this.mRightBoundingTile = mRightBoundingTile;
	}

	public Tile getBottomBoundingTile() {
		return mBottomBoundingTile;
	}

	public void setBottomBoundingTile(Tile mBottomBoundingTile) {
		this.mBottomBoundingTile = mBottomBoundingTile;
	}

	private void runRight() {
		// Set animation
		mCurrentAnimation = mRunRightAnimation;
		
		// Move character
		if ((mRect.getX() + mRect.getWidth()) < Constants.SCREEN_WIDTH) {
			mRect.setX(mRect.getX() + mHorizontalMoveFactor);
		}
	}
	
	private void runLeft() {
		// Set animation
		mCurrentAnimation = mRunLeftAnimation;
		
		// Move character
		if ((mRect.getX() > 0)) {
			mRect.setX(mRect.getX() - mHorizontalMoveFactor);
		}
	}
	
	private void jump() {
		if (!mIsJumping) {
			// If not jumping then jump
			mCurrentAnimation = mJumpAnimation;
			mVerticalAcceleration = mJumpStrength;
		}
	}
	
	private void stand() {
		mCurrentAnimation = mStandAnimation;
	}
	
	private void useBonus() {
		
	}
	
	private void applyGravity(boolean apply) {
		// Apply acceleration only if in the air
		if (apply) {
			if ((mRect.getY() + mRect.getHeight() < Constants.SCREEN_HEIGHT)) {
				mVerticalAcceleration += mGravity;
				mRect.setY(mRect.getY() + mVerticalAcceleration);
			}
		}
	}
	
	public Rectangle getRect() {
		return mRect;
	}
	
	public void isGravityApplied(boolean applied) {
		mIsGravityApplied = applied;
	}
	
	public void renderBoundingTiles(Graphics graphics) {
		graphics.setColor(Color.white);
		
		if (mTopBoundingTile != null)
			graphics.drawRect(
					mTopBoundingTile.getRect().getX(),
					mTopBoundingTile.getRect().getY(),
					mTopBoundingTile.getRect().getWidth(),
					mTopBoundingTile.getRect().getHeight()
			);
		
		if (mBottomBoundingTile != null)
			graphics.drawRect(
					mBottomBoundingTile.getRect().getX(),
					mBottomBoundingTile.getRect().getY(),
					mBottomBoundingTile.getRect().getWidth(),
					mBottomBoundingTile.getRect().getHeight()
			);
		
		if (mLeftBoundingTile != null)
			graphics.drawRect(
					mLeftBoundingTile.getRect().getX(),
					mLeftBoundingTile.getRect().getY(),
					mLeftBoundingTile.getRect().getWidth(),
					mLeftBoundingTile.getRect().getHeight()
			);
		
		if (mRightBoundingTile != null)
			graphics.drawRect(
					mRightBoundingTile.getRect().getX(),
					mRightBoundingTile.getRect().getY(),
					mRightBoundingTile.getRect().getWidth(),
					mRightBoundingTile.getRect().getHeight()
			);
	}
	
	public void init() {
		// Set current bonus to null (no bonus in the beginning)
		mCurrentBonus = null;
		
		// Set initial dimensions for player
		mRect = new Rectangle(Constants.SCREEN_WIDTH / 2, Constants.SCREEN_HEIGHT - 100, Constants.TILE_WIDTH, Constants.TILE_HEIGHT);
		
		// Init all animation frames
		try {
			mEnemyIncomingAnimationFrames = new Image[] {
				new Image("assets/images/lovecraft/howard.png"),
				new Image("assets/images/lovecraft/howard.png"),
				new Image("assets/images/lovecraft/howard.png"),
				new Image("assets/images/lovecraft/howard.png")
			};
			mDeathAnimationFrames = new Image[] {
				new Image("assets/images/lovecraft/howard.png"),
				new Image("assets/images/lovecraft/howard.png"),
				new Image("assets/images/lovecraft/howard.png"),
				new Image("assets/images/lovecraft/howard.png")
			};
			mLevelWinAnimationFrames = new Image[] {
				new Image("assets/images/lovecraft/howard.png"),
				new Image("assets/images/lovecraft/howard.png"),
				new Image("assets/images/lovecraft/howard.png"),
				new Image("assets/images/lovecraft/howard.png")
			};
			mRunLeftAnimationFrames = new Image[] {
				new Image("assets/images/lovecraft/howard.png"),
				new Image("assets/images/lovecraft/howard.png"),
				new Image("assets/images/lovecraft/howard.png"),
				new Image("assets/images/lovecraft/howard.png")
			};
			mRunRightAnimationFrames = new Image[] {
				new Image("assets/images/lovecraft/howard.png"),
				new Image("assets/images/lovecraft/howard.png"),
				new Image("assets/images/lovecraft/howard.png"),
				new Image("assets/images/lovecraft/howard.png")
			};
			mJumpAnimationFrames = new Image[] {
				new Image("assets/images/lovecraft/howard.png"),
				new Image("assets/images/lovecraft/howard.png"),
				new Image("assets/images/lovecraft/howard.png"),
				new Image("assets/images/lovecraft/howard.png")
			};
			mStandAnimationFrames = new Image[] {
				new Image("assets/images/lovecraft/howard.png"),
				new Image("assets/images/lovecraft/howard.png"),
				new Image("assets/images/lovecraft/howard.png"),
				new Image("assets/images/lovecraft/howard.png")
			};
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// Set-up animations
		mEnemyIncomingAnimation = new Animation(mEnemyIncomingAnimationFrames, mEnemyIncomingAnimationDuration, true);
		mDeathAnimation         = new Animation(mDeathAnimationFrames, mDeathAnimationDuration, false);
		mLevelWinAnimation      = new Animation(mLevelWinAnimationFrames, mLevelWinAnimationDuration, true);
		mRunLeftAnimation       = new Animation(mRunLeftAnimationFrames, mRunLeftAnimationDuration, true);
		mRunRightAnimation      = new Animation(mRunRightAnimationFrames, mRunRightAnimationDuration, true);
		mJumpAnimation          = new Animation(mJumpAnimationFrames, mJumpAnimationDuration, false);
		mStandAnimation         = new Animation(mStandAnimationFrames, mStandAnimationDuration, true);
		
		// Set default animation which is standing
		mCurrentAnimation = mStandAnimation;
	}
	
	public void render(Graphics graphics) {
		// Draw current animation
		mCurrentAnimation.draw(mRect.getX(), mRect.getY(), mRect.getWidth(), mRect.getHeight());
		mCurrentAnimation.start();
	}
	
	public void update(GameContainer container, int delta) {
		// Get user input
		getInput(container);
		
		// Apply gravity to player
		applyGravity(mIsGravityApplied);
	}
}
