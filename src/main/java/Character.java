package main.java;

import java.util.ArrayList;

/**
* This class is used to store states of the characters in the program.
* You will need to declare other variables depending on your implementation.
*/
public class Character {
	
	private MainApplet parent;
	public float ini_x, ini_y, x, y;
	public String name;
	private ArrayList<Character> targets;
	public String color;
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
	}

	
	public void addTarget(Character target){
		this.targets.add(target);
	}
	
	public ArrayList<Character> getTargets(){
		return this.targets;
	}
}
