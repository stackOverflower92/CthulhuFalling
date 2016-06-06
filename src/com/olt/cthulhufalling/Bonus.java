package com.olt.cthulhufalling;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Transform;
import org.newdawn.slick.geom.Vector2f;

public class Bonus {
	private Rectangle mRect;
	private BonusEffect mEffect;
	private int mProbability;
	private int mLowProbabilityBound;
	private int mHighProbabilityBound;
	private BonusState mCurrentState;
	
	private Animation mFallAnimation;
	private Animation mTakeAnimation;
	private Animation mCurrentAnimation;
	
	private Image[] mFallAnimationFrames;
	private Image[] mTakeAnimationFrames;
	
	private int mAnimationDuration = 500;
	
	// -1 = down, 1 = up
	private int mDirection = -1;
	private float mFallRate = 0.5f;
	private int startRotation = 0;
	
	public Bonus(int probability, BonusEffect effect, int x) {
		init(probability, effect, x);
	}

	public int getProbability() {
		return mProbability;
	}
	
	public void setProbability(int mProbability) {
		this.mProbability = mProbability;
	}

	public int getLowProbabilityBound() {
		return mLowProbabilityBound;
	}

	public void setLowProbabilityBound(int mLowProbabilityBound) {
		this.mLowProbabilityBound = mLowProbabilityBound;
	}

	public int getHighProbabilityBound() {
		return mHighProbabilityBound;
	}

	public void setHighProbabilityBound(int mHighProbabilityBound) {
		this.mHighProbabilityBound = mHighProbabilityBound;
	}
	
	public Vector2f getPosition() {
		return new Vector2f(mRect.getX(), mRect.getY());
	}
	
	public BonusEffect getEffect() {
		return mEffect;
	}

	public void reverseGravity() {
		mDirection *= -1;
	}

	private void init(int probability, BonusEffect effect, int x) {
		// Default state
		mCurrentState = BonusState.FALLING;
		
		// Set default dimensions
		mRect = new Rectangle(x, 0, Constants.BONUS_WIDTH, Constants.BONUS_HEIGHT);
		
		// Set default probability
		mProbability = probability;
		
		try {
			// Set-up frames
			mFallAnimationFrames = new Image[] {
					new Image("assets/images/bonus/bonus.png"),
					new Image("assets/images/bonus/bonus.png"),
					new Image("assets/images/bonus/bonus.png"),
					new Image("assets/images/bonus/bonus.png")
			};
			
			mTakeAnimationFrames = new Image[] {
					new Image("assets/images/bonus/bonus.png"),
					new Image("assets/images/bonus/bonus.png"),
					new Image("assets/images/bonus/bonus.png"),
					new Image("assets/images/bonus/bonus.png")
			};
			
			// Set-up animations
			mFallAnimation = new Animation(mFallAnimationFrames, mAnimationDuration, true);
			mTakeAnimation = new Animation(mTakeAnimationFrames, mAnimationDuration, false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void render(GameContainer container, Graphics graphics) {
		// Choose current animation
		switch (mCurrentState) {
		case FALLING:
			mCurrentAnimation = mFallAnimation;
			break;
			
		case TAKEN:
			mCurrentAnimation = mTakeAnimation;
			break;
		}
		
		// Draw current animation
		mCurrentAnimation.draw(mRect.getX(), mRect.getY(), mRect.getWidth(), mRect.getHeight());
	}

	public void update(GameContainer container, int delta) {
		switch (mCurrentState) {
		case FALLING:
			mRect.setY(mRect.getY() - (mDirection * delta * mFallRate));
			break;
			
		case TAKEN:
			mRect.setY(mRect.getY() - 0);
			break;
		}
	}
}
