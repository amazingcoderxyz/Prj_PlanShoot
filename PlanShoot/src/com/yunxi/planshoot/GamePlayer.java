package com.yunxi.planshoot;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.KeyEvent;
import android.view.MotionEvent;

//游戏主角类
public class GamePlayer {
	private Bitmap bmpPlayer;
	private Bitmap bmpHp;
	private int posX,posY;
	private int centerX,centerY;
	private int touchX, touchY;
	private int speed = 5;
	//移动标识
	private boolean isUp, isDown, isLeft, isRight;
	private int Hp;

	
	public GamePlayer(Bitmap bmpPlayer, Bitmap bmpHp){
		this.bmpPlayer = bmpPlayer;
		this.bmpHp = bmpHp;
		posX = MainSurfaceView.screenW /2 - bmpPlayer.getWidth()/2;
		posY = MainSurfaceView.screenH - bmpPlayer.getHeight();
		centerX = posX + bmpPlayer.getWidth()/2;
		centerY = posY + bmpPlayer.getHeight()/2;
		
	}
	
	public void draw(Canvas canvas, Paint paint){
		canvas.drawBitmap(bmpPlayer, posX, posY, paint);
		for(int i=0; i<Hp; i++){
			canvas.drawBitmap(bmpHp, i*bmpHp.getWidth(), MainSurfaceView.screenH-bmpHp.getHeight(), paint);
		}
	}
	
	public void onTouchEvent(MotionEvent event){
		if(event.getAction() == MotionEvent.ACTION_DOWN){

			touchX = (int) event.getX();
			touchY = (int) event.getY();
			if(touchX < centerX){
				isLeft = true;
				isRight = false;
			}
			if(touchX > centerX){
				isRight = true;
				isLeft = false;
			}
			if(touchY < centerY){
				isUp = true;
				isDown = false;
			}
			if(touchY > centerY){
				isDown = true;
				isUp = false;
			}
		}else if(event.getAction() == MotionEvent.ACTION_UP){
			isLeft = false;
			isRight = false;
			isUp = false;
			isDown = false;
		}
	}
	
	public void onKeyDown(int keyCode, KeyEvent event){
		if(keyCode == KeyEvent.KEYCODE_DPAD_UP){
			isUp = true;
		}
		if(keyCode == KeyEvent.KEYCODE_DPAD_DOWN){
			isDown = true;
		}
		if(keyCode == KeyEvent.KEYCODE_DPAD_LEFT){
			isLeft = true;
		}
		if(keyCode == KeyEvent.KEYCODE_DPAD_RIGHT){
			isRight = true;
		}
	}
	
	public void onKeyUp(int keyCode, KeyEvent event){
		if(keyCode == KeyEvent.KEYCODE_DPAD_UP){
			isUp = false;
		}
		if(keyCode == KeyEvent.KEYCODE_DPAD_DOWN){
			isDown = false;
		}
		if(keyCode == KeyEvent.KEYCODE_DPAD_LEFT){
			isLeft = false;
		}
		if(keyCode == KeyEvent.KEYCODE_DPAD_RIGHT){
			isRight = false;
		}
	}
	
	public void logic(){
		if(isLeft){
			posX -= speed;
		}
		if(isRight){
			posX += speed;
		}
		if(isUp){
			posY -= speed;
		}
		if(isDown){
			posY += speed;
		}
		
		//左右出界
		if(posX + bmpPlayer.getWidth() > MainSurfaceView.screenW){
			posX = MainSurfaceView.screenW - bmpPlayer.getWidth();
		}else if(posX < 0){
			posX = 0;
		}
		
		//上下出界
		if(posY+ bmpPlayer.getHeight() > MainSurfaceView.screenH){
			posY = MainSurfaceView.screenH - bmpPlayer.getHeight();
		}else if(posY <0){
			posY =0;
		}
		
		centerX = posX + bmpPlayer.getWidth()/2;
		centerY = posY + bmpPlayer.getHeight()/2;
		//若已经到达touch点，停上移动
		if(touchX >= centerX){
			isLeft = false;
		}
		if(touchX <= centerX){
			isRight = false;
		}
		if(touchY >= centerY){
			isUp = false;
		}
		if(touchY <= centerY){
			isDown = false;
		}
	}
	
	public int getHp(){
		return this.Hp;
	}
	
	public void setHp(int hp){
		this.Hp = hp;
	}
	
	public boolean isDead(){
		if(this.Hp <=0){
			return true;
		}
		return false;
	}
}
