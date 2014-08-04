package com.yunxi.planshoot;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.view.MotionEvent;

public class GameMenu {
	private Bitmap bmpMenu;
	private Bitmap bmpBtnStart;
	private Bitmap bmpBtnStartPress;
	
	private int btnX, btnY;
	private boolean bPress;
	
	public GameMenu(Bitmap bmpMenu, Bitmap bmpBtn, Bitmap bmpBtnPress){
		this.bmpMenu = bmpMenu;
		this.bmpBtnStart = bmpBtn;
		this.bmpBtnStartPress = bmpBtnPress;
		btnX = MainSurfaceView.screenW/2 - bmpBtnStart.getWidth()/2;
		btnY = MainSurfaceView.screenH - bmpBtnStart.getHeight();
		bPress = false;
	}
	
	public void draw(Canvas canvas, Paint paint){
		float sw =MainSurfaceView.screenW;
		float bw = bmpMenu.getWidth();
		float wp = sw/bw;
		float sh = MainSurfaceView.screenH;
		float bh = bmpMenu.getHeight();
		float hp = sh/bh;
		Matrix matrix = new Matrix();
		matrix.postScale(wp, hp);
		canvas.drawBitmap(bmpMenu, matrix, paint);
		if(bPress == false){
			canvas.drawBitmap(bmpBtnStart, btnX, btnY, paint);
		}else{
			canvas.drawBitmap(bmpBtnStartPress, btnX, btnY, paint);
		}
	}
	
	public void onTouchEvent(MotionEvent event){
		int pointx = (int)event.getX();
		int pointy = (int)event.getY();
		if(event.getAction() == MotionEvent.ACTION_DOWN || event.getAction()==MotionEvent.ACTION_MOVE){
			if(pointx > btnX && pointx < btnX+bmpBtnStart.getWidth()
					&& pointy >btnY && pointy < btnY+bmpBtnStart.getHeight()){
				bPress = true;
			}
			else
				bPress = false;
		}else if(event.getAction() == MotionEvent.ACTION_UP ){
			if(pointx > btnX && pointx < btnX+bmpBtnStart.getWidth()
					&& pointy >btnY && pointy < btnY+bmpBtnStart.getHeight()){
				bPress = false;
				MainSurfaceView.gameState=MainSurfaceView.GAME_RUN;
			}
		}				
	}
}
