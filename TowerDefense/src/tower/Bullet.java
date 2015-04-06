package tower;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import map.Map;
import model.Game;
import critter.Critter;

public class Bullet {

	public final double STANDARD_BULLET_SPEED = 0.3;
	public final double COLLISION_DISTANCE = 0.4;
	public final int BULLET_PIXEL_OFFSET = 8;

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

	public Bullet(Game game, Tower tower, double targetX, double targetY,
			double velocityMultiplier) {
		this.bullet_X = Map.getCenterX(tower.position.x);
		this.bullet_Y = Map.getCenterY(tower.position.y);
		this.target_X = targetX;
		this.target_Y = targetY;
		this.velocity = velocityMultiplier * STANDARD_BULLET_SPEED;
		this.tower = tower;
		this.game = game;
		
		this.max_X = game.getBoard().getMap().getWidth();
		this.max_Y = game.getBoard().getMap().getHeight();
		this.min_X = 0;
		this.min_Y = 0;
		this.image = tower.getBulletImage();

		double dx = target_X - bullet_X;
		double dy = target_Y - bullet_Y;
		
		if(dx==0 && dy==0){
			//don't add the bullet to the game
		}else{
			game.addBullet(this);
			this.velocity_X = velocity * dx / Math.sqrt(dx * dx + dy * dy);
			this.velocity_Y = velocity * dy / Math.sqrt(dx * dx + dy * dy);
		}
		

		// TODO: implement making sprite display
	}

	public void updateBullet() {
		bullet_X += velocity_X;
		bullet_Y += velocity_Y;
		try {
			ArrayList<Critter> critters = game.getWave().getCritterBank();
			for (int i = 0; i < critters.size(); i++) {
				Critter c = critters.get(i);
				if (detectCollision(c)) {
					if (tower.getBlastRadius()>0){
						blast(critters);
					}else{
						hit(c);
					}
					
					game.removeBullet(this);
				} 
			}
		} catch (NullPointerException e) {
			//System.out.println("No critters left for bullet to hit.");
		}
		if (bullet_X > max_X || bullet_X < min_X
				|| bullet_Y > max_Y || bullet_Y < min_Y) {
			game.removeBullet(this);
		}

	}
	
	//Method for testing purposes
	public void setBulletPosition(double x, double y){
		this.bullet_X = x;
		this.bullet_Y = y;
	}
	
	public void hit(Critter critter){
		if (tower.getIsSpecial()){
			if (critter.getSpeed() > critter.STANDARD_SPEED*tower.specialmod){
				critter.reduceSpeed(tower.specialmod);
			}
		}else{
			critter.getsHit(tower.getPower());
		}
	}

	public boolean detectCollision(Critter critter) {
		double cX = Map.getCenterX(critter.getX());
		double cY = Map.getCenterY(critter.getY());
		if (Tower.distanceSquared(cX, cY, bullet_X, bullet_Y) < COLLISION_DISTANCE
				&& critter.onPath()) {
			return true;
		} else {
			return false;
		}

	}


	public void blast(ArrayList<Critter> critters){
		double blastRadius=tower.getBlastRadius();
		//System.out.println("blast radius is: "+blastRadius);
		for(int i = 0; i < critters.size(); i++){
			Critter c=critters.get(i);
			boolean flag=Tower.distanceSquared(bullet_X, bullet_Y, Map.getCenterX(c.getX()), Map.getCenterY(c.getY()))<blastRadius*blastRadius;
			//System.out.println("distance squared is: "+Tower.distanceSquared(bullet_X, bullet_Y, Map.getCenterX(c.getX()), Map.getCenterY(c.getY()))); 
			//System.out.println(flag);
			//System.out.println("critters on path" + critters.get(i).onPath());
			if(flag && critters.get(i).onPath()){
				//System.out.println("within blast radius!");
				hit(c);
			}
		}
	}

	public void drawBullet(Graphics g) {
		g.drawImage(image, (int) (bullet_X * Map.CELL_PIXEL_SIZE)
				- BULLET_PIXEL_OFFSET, (int) (bullet_Y * Map.CELL_PIXEL_SIZE)
				- BULLET_PIXEL_OFFSET, null);
	}

	public double getX() {
		return bullet_X;
	}

	public double getY() {
		return bullet_Y;
	}

}
