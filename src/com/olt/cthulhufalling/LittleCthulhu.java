package com.olt.cthulhufalling;
import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

public class LittleCthulhu {
	// Members
	private int mId;
	private float mFallRate = 0.5f;
	// -1 = down, 1 = up
	private int mDirection = -1;
	private Animation mFallAnimation;
	private Animation mExplodeAnimation;
	private Animation mEatAnimation;
	private Animation mCurrentAnimation;
	
	private LittleCthulhuState mCurrentState;
	
	private Image[] mFallAnimationFrames;
	private Image[] mExplodeAnimationFrames;
	private Image[] mEatAnimationFrames;
	
	private Rectangle mRect;
	
	private int mAnimationDuration = 180;
	
	// Constructors
	public LittleCthulhu(GameContainer container, Rectangle size, float speed) {
		init(container, size, speed);
	}
	
	// Getters and Setters
	public int getmId() {
		return mId;
	}
	
	public void setId(int mId) {
		this.mId = mId;
	}
	
	public void setSize(Rectangle size) {
		mRect.setWidth(size.getWidth());
		mRect.setHeight(size.getHeight());
	}
	
	public void setCurrentState(LittleCthulhuState state) {
		mCurrentState = state;
	}

	public Vector2f getPosition() {
		return new Vector2f(mRect.getX(), mRect.getY());
	}

	public Rectangle getRect() {
		return mRect;
	}
	
	// Methods
	public void init(GameContainer container, Rectangle size, float speed) {
		// Set default state as falling
		mCurrentState = LittleCthulhuState.FALLING;
		
		// Set default rectangle for random size and position
		mRect = size;
		
		// Set default speed
		mFallRate = speed;
		
		try {
			// Set-up frames
			mFallAnimationFrames = new Image[] {
					new Image("assets/images/little_cthulhu/fall/cth1.png"),
					new Image("assets/images/little_cthulhu/fall/cth2.png"),
					new Image("assets/images/little_cthulhu/fall/cth3.png"),
					new Image("assets/images/little_cthulhu/fall/cth2.png")
			};
			
			mExplodeAnimationFrames = new Image[] {
					new Image("assets/images/little_cthulhu/fall/cth1.png"),
					new Image("assets/images/little_cthulhu/fall/cth2.png"),
					new Image("assets/images/little_cthulhu/fall/cth3.png"),
					new Image("assets/images/little_cthulhu/fall/cth2.png")
			};
			
			mEatAnimationFrames = new Image[] {
					new Image("assets/images/little_cthulhu/fall/cth1.png"),
					new Image("assets/images/little_cthulhu/fall/cth2.png"),
					new Image("assets/images/little_cthulhu/fall/cth3.png"),
					new Image("assets/images/little_cthulhu/fall/cth2.png")
			};
			
			// Set-up animations
			mFallAnimation = new Animation(mFallAnimationFrames, mAnimationDuration, true);
			mExplodeAnimation = new Animation(mExplodeAnimationFrames, mAnimationDuration, false);
			mEatAnimation = new Animation(mEatAnimationFrames, mAnimationDuration, false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void render(GameContainer container, Graphics graphics) {
		// Check current state
		switch (mCurrentState) {
		case FALLING:
			mCurrentAnimation = mFallAnimation;
			break;
			
		case EATING:
			mCurrentAnimation = mEatAnimation;
			break;
			
		case DEAD:
			mCurrentAnimation = mExplodeAnimation;
			break;
		}
		
		// Now render
		mCurrentAnimation.start();
		mCurrentAnimation.draw(mRect.getX(), mRect.getY(), mRect.getWidth(), mRect.getHeight());
	}

	public void update(GameContainer container, int delta) {
		switch(mCurrentState) {
		case FALLING:
			mRect.setY(mRect.getY() -(mDirection * delta * mFallRate));
			break;
			
		case EATING:
			mRect.setY(mRect.getY() - 0);
			break;
			
		case DEAD:
			mRect.setY(mRect.getY() - 0);
		}
	}
	
	public void eat() {
		mEatAnimation.start();
	}
	
	public void fall() {
		mFallAnimation.start();
	}
	
	public void explode() {
		mExplodeAnimation.start();
	}
	
	public void reverseGravity() {
		mDirection *= -1;
	}
}
