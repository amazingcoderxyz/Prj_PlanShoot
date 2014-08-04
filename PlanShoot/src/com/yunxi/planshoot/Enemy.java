package com.yunxi.planshoot;

import android.graphics.Bitmap;

public class Enemy {
	public int type;
	public static final int TYPE_FLY = 1;
	public static final int TYPE_DUCKL = 2;
	public static final int TYPE_DUCKR = 3;
	private Bitmap bmpEnemy;
	private int posx,posy;
	private int FrameW, FrameH;
	private int FrameIndex;
	private int speed;
	private boolean isDead;//ÊÇ·ñ³öÆÁ
	
	public Enemy(Bitmap bmpEnemy, int type, int x, int y){
		this.bmpEnemy = bmpEnemy;
		this.type = type;
		this.posx = x;
		this.posy = y;
		switch(type){
			case TYPE_FLY:
				speed = 25;
				break;
			case TYPE_DUCKL:
			case TYPE_DUCKR:
				speed = 3;
				break;
		}
	}
	
}
