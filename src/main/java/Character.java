package main.java;

import java.util.ArrayList;

import processing.core.PApplet;

/**
* This class is used to store states of the characters in the program.
* You will need to declare other variables depending on your implementation.
*/
public class Character {
	
	private MainApplet parent;
	public float x, y, radius;
	private String name;
	private ArrayList<Character> targets;
	private String color;
	public Character(MainApplet parent, String name, String color, float x, float y){
		this.x = x;
		this.y = y;
		this.color = color;
		this.name = name;
		this.parent = parent;
		this.targets = new ArrayList<Character>();
		this.parent = parent;
		
	}

	public void display(){
		this.parent.fill(210, 2, 11);
		this.parent.ellipse(x, y, 20, 20);
		
		//this.parent.textSize(26);
		//this.parent.fill(255);
		//this.parent.text(name, x-name.length()*10+5, y+10);
	}

	
	public void addTarget(Character target){
		this.targets.add(target);
	}
	
	public ArrayList<Character> getTargets(){
		return this.targets;
	}
}
