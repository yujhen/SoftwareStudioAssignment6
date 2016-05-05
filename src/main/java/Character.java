package main.java;

import java.util.ArrayList;

/**
* This class is used to store states of the characters in the program.
* You will need to declare other variables depending on your implementation.
*/
public class Character {
	
	private MainApplet parent;
	public float ini_x, ini_y, x, y;
	private String name;
	private ArrayList<Character> targets;
	private String color;
	public Character(MainApplet parent, String name, String color, float x, float y){
		this.ini_x = x;
		this.ini_y = y;
		this.color = color.substring(1) ;
		this.name = name;
		this.parent = parent;
		this.targets = new ArrayList<Character>();
		this.parent = parent;
		this.x = this.ini_x;
		this.y = this.ini_y;
	}

	public void display(){
		this.parent.noStroke();
		this.parent.fill(this.parent.unhex(this.color));
		this.parent.ellipse(x, y, 30, 30);
		
		
		//this.parent.textSize(26);
		//this.parent.fill(255);
		//this.parent.text(name, x-name.length()*10+5, y+10);
	}

	
	public void addTarget(Character target){
		this.targets.add(target);
	}
	
	public void removeTarget(Character target){
		this.targets.remove(target);
	}
	
	public ArrayList<Character> getTargets(){
		return this.targets;
	}
}
