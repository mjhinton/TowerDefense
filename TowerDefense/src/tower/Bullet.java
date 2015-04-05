package tower;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import map.Map;
import model.Game;
import critter.Critter;

public class Bullet{
	
	public final double STANDARD_BULLET_SPEED=0.3;
	public final double COLLISION_DISTANCE=0.4;
	public final int BULLET_PIXEL_OFFSET=8;
	
	private double bullet_X;
	private double bullet_Y;
	private double velocity;
	private double velocity_X;
	private double velocity_Y;
	private double target_X;
	private double target_Y;
	private Game game;
	private Tower tower;
	private double max_X;
	private double max_Y;
	private double min_X;
	private double min_Y;
	
	protected Image image;
	protected int pointer;
	
	public Bullet(Game game, Tower tower, double targetX, double targetY, double velocityMultiplier){
		this.bullet_X = Map.getCenterX(tower.position.x);
		this.bullet_Y = Map.getCenterY(tower.position.y);
		this.target_X=targetX;
		this.target_Y=targetY;
		this.velocity = velocityMultiplier*STANDARD_BULLET_SPEED;
		this.tower=tower;
		this.game=game;
		game.addBullet(this);
		this.max_X=game.getBoard().getMap().getWidth();
		this.max_Y=game.getBoard().getMap().getHeight();
		this.min_X=0;
		this.min_Y=0;
		this.image=tower.getBulletImage();
		
		double dx=target_X-bullet_X;
		double dy=target_Y-bullet_Y;
		this.velocity_X = velocity * dx/Math.sqrt(dx*dx+dy*dy);
		this.velocity_Y = velocity * dy/Math.sqrt(dx*dx+dy*dy);
		
		//TODO: implement making sprite display
	}
	
	public void updateBullet(){
		bullet_X += velocity_X;
		bullet_Y += velocity_Y;
		ArrayList <Critter> critters=game.getWave().getCritterBank();
		for (int i=0; i<critters.size();i++){
			Critter c=critters.get(i);
			if (detectCollision(c)){
				c.getsHit((int)tower.getPower());
				game.removeBullet(this);
			}else if (bullet_X>max_X || bullet_X<min_X || bullet_Y>max_Y || bullet_Y<min_Y){
				game.removeBullet(this);
			}
		}
		//System.out.println(bullet_X+","+ bullet_Y);
	}
	public boolean detectCollision(Critter critter){
		double cX=Map.getCenterX(critter.getX());
		double cY=Map.getCenterY(critter.getY());
		if(Tower.distanceSquared(cX,cY,bullet_X,bullet_Y)<COLLISION_DISTANCE){
			return true;
		}else{
			return false;
		}
		
		
	}
	
//	//TODO: make move bullet refresh the position of the critter between every call
//	public void moveBullet(ArrayList<Critter> enemies){ //defines potential movement directions of bullet
//		implementTargetPattern(enemies);
//		activeBullet = true;
//		
//		if(enemies.get(pointer)==null){
//			bullet_X = super.position.getX();
//			bullet_Y = super.position.getY();
//			bulletReached = true;
//			activeBullet = false;
//		}// TODO: attempt to reset if creep dies on the way; incomplete
//		
//			target_X = enemies.get(pointer).getX();
//			target_Y = enemies.get(pointer).getY();
//			double dx=target_X-bullet_X;
//			double dy=target_Y-bullet_Y;
//			velocity_X = velocity * dx/Math.sqrt(dx*dx+dy*dy);
//			velocity_Y = velocity * dy/Math.sqrt(dx*dx+dy*dy);
//		
//		if(!bulletReached&&activeBullet){
//			bullet_X += velocity_X;
//			bullet_Y += velocity_Y;
//			if(bullet_X + velocity_X > target_X || bullet_Y + velocity_Y > target_Y) bulletReached = true;
//		}
//		
//		if(bulletReached){
//				System.out.println("dealt damage: " + super.toString());
//				damageEnemies(enemies);
//				
//				inRange.clear();
//				
//				bullet_X = super.position.getX();
//				bullet_Y = super.position.getY();
//				
//				activeBullet = bulletReached = false;
//		}
//	}
	
	
//	public void damageEnemies(ArrayList<Critter> enemies){
//		for(int i = 0; i < enemies.size(); i++){
//			Critter j = enemies.get(i);
//			if(j == null) break;
//			
//			if(i==0){
//				if(super.isSpecial) j.setSpeed(j.getSpeed() * super.specialmod);
//				else j.getsHit((int) super.power);
//			}
//			
//			if(i>0){
//				if(distance(enemies.get(0).getX(), enemies.get(0).getY(), j.getX(), j.getY())<range){
//					if(super.isSpecial) j.setSpeed(j.getSpeed() * super.specialmod);
//					else j.getsHit((int) super.power);
//				}
//			}
//		}
//	}
	
	public void drawBullet(Graphics g) {
			g.drawImage(image, (int) (bullet_X*Map.CELL_PIXEL_SIZE)-BULLET_PIXEL_OFFSET,
				(int) (bullet_Y*Map.CELL_PIXEL_SIZE)-BULLET_PIXEL_OFFSET, null);
	}
	
	public double getX(){
		return bullet_X;
	}
	
	public double getY(){
		return  bullet_Y;
	}
	
	
}