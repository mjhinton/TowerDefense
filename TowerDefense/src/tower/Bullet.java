package tower;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import map.Map;
import model.Game;
import critter.Critter;

public class Bullet extends Tower{
	
	private double bullet_X;
	private double bullet_Y;
	private double velocity;
	private double velocity_X;
	private double velocity_Y;
	private double theta;
	protected Image image;
	protected int pointer;
	protected int tileSize = game.getBoard().getMap().CELL_PIXEL_SIZE;
	
	public Bullet(Point c, Game game){
		super(c, game);
		this.bullet_X = super.position.getX()*tileSize;
		this.bullet_Y = super.position.getY()*tileSize;
		velocity = 2.5; //TODO: decide on an appropriate velocity
		//TODO: implement making sprite display
	}
	//TODO: make move bullet refresh the position of the critter between every call
	public void moveBullet(ArrayList<Critter> enemies){ //defines potential movement directions of bullet
		implementTargetPattern(enemies);
		
		if(enemies.get(pointer)==null){
			bullet_X = super.position.getX()*tileSize;
			bullet_Y = super.position.getY()*tileSize;
		}
		
		else{
			double closest_X = enemies.get(pointer).getX()*tileSize;
			double closest_Y = enemies.get(pointer).getY()*tileSize;
			
			theta = Math.atan2(closest_Y-bullet_Y, closest_X-bullet_X);
			velocity_X = velocity * Math.cos(theta);
			velocity_Y = velocity * Math.sin(theta);
			
			if(bullet_X + velocity_X > closest_X && bullet_Y + velocity_Y > closest_Y) {
				//System.out.println("dealt damage: " + super.toString());
				damageEnemies(enemies);
				bullet_X = super.position.getX()*tileSize;
				bullet_Y = super.position.getY()*tileSize;
				activeBullet = false;
			}
			
			bullet_X += velocity_X;
			bullet_Y += velocity_Y;
		}
	}
	
	public void implementTargetPattern(ArrayList<Critter> enemies){
		if(lowestHealth) pointer = lowestHealth(enemies);
		else if(highestHealth) pointer = highestHealth(enemies);
		else if(closest) pointer = closest(enemies);
		else if(farthest) pointer = lowestHealth(enemies);
	}
	
	public void damageEnemies(ArrayList<Critter> enemies){
		for(int i = 0; i < enemies.size(); i++){
			Critter j = enemies.get(i);
			if(j == null) break;
			
			if (!super.isSpecial){
				j.getsHit((int) super.power);
			}
			
			else {
				j.setSpeed(j.getSpeed() * super.specialmod);
			}
		}
	}
	
	public void drawBullet(Graphics g) {
			g.drawImage(image, (int) bullet_X,
				(int) bullet_Y, null);
	}
	
	public int getX(){
		return (int) bullet_X;
	}
	
	public int getY(){
		return (int) bullet_Y;
	}
	
	public int lowestHealth(ArrayList<Critter> input){
		int pointer = 0;
		int lowestHealth = (int) Math.pow(10, 9);
		for(int i = 0; i < input.size(); i++){
			if(input.get(i).getHealth()<lowestHealth){
				lowestHealth = input.get(i).getHealth();
				pointer = i;
			}
		}
		return pointer;
	}
	
	public int highestHealth(ArrayList<Critter> input){
		int pointer = 0;
		int highestHealth = 0;
		for(int i = 0; i < input.size(); i++){
			if(input.get(i).getHealth()>highestHealth){
				highestHealth = input.get(i).getHealth();
				pointer = i;
			}
		}
		return pointer;
	}
	
	public int closest(ArrayList<Critter> input){
		int pointer = 0;
		double closestDistance = Math.pow(10, 9);
		for(int i = 0; i < input.size(); i++){
			if(distance(position.getX(), position.getY(), input.get(i).getX(), input.get(i).getY())<closestDistance){
				closestDistance = distance(position.getX(), position.getY(), input.get(i).getX(), input.get(i).getY());
				pointer = i;
			}
		}
		return pointer;
	}
	
	public int farthest(ArrayList<Critter> input){
		int pointer = 0;
		double farthestDistance = 0;
		for(int i = 0; i < input.size(); i++){
			if(distance(position.getX(), position.getY(), input.get(i).getX(), input.get(i).getY())>farthestDistance){
				farthestDistance = distance(position.getX(), position.getY(), input.get(i).getX(), input.get(i).getY());
				pointer = i;
			}
		}
		return pointer;
	}
}
