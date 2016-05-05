package main.java;

import processing.core.PApplet;
import java.util.ArrayList;

import de.looksgood.ani.Ani;
import processing.data.JSONArray;
import processing.data.JSONObject;
/**
* This class is for sketching outcome using Processing
* You can do major UI control and some visualization in this class.  
*/
@SuppressWarnings("serial")
public class MainApplet extends PApplet{
	JSONObject data;
	JSONArray nodes, links;
	private ArrayList<Character> characters;
	private ArrayList<Character> network;
	//¬ö¿ý·Æ¹«¥Ø«e©ì¦²ªºcharacter
	private Character characters_now;

	private String path = "main/resources/";
	private String file = "starwars-episode-1-interactions.json";
	
	private final static int width = 1200, height = 650;
	
	public void setup() {
		
		characters = new ArrayList<Character>();
		network = new ArrayList<Character>();

		Ani.init(this);
		size(width, height);
		smooth();
		loadData();
	}
	
	public void draw() {
		background(255);
		
		//draw circle
		stroke(0);
		strokeWeight(2);
		fill(255);
		ellipse(600, 350, 500, 500);
		
		//draw network (if the character and its target are both in network)
		noFill();
		for(Character character: this.network){
			for(Character target: character.getTargets()){
				if(network.contains(target)==true){
					//for curve line
					float a = (character.x+target.x)/2;
					float b = (character.y+target.y)/2;
					a=(a+600)/2;
					b=(b+350)/2;
					bezier(character.x, character.y,a,b,a,b, target.x, target.y);
					
					//for straight line (for test)
					//line(character.x, character.y, target.x, target.y);
				}
			}
		}
		
		//draw left-side character points 
		for(Character character : characters)
			character.display();
		
		//draw right-side character points
		for(Character character : network)
			character.display();
		
		//draw character's name
		noStroke();
		float a ,b; 		
		for(Character character : characters){
			a = (mouseX - character.x);
			b = (mouseY - character.y);
			if((a*a)+(b*b)<225){
				fill(unhex(character.color));
				rect(character.x+character.name.length()+15, character.y-35, character.name.length()*14+5, 30, 12, 12, 12, 12);
				textSize(20);
				fill(0);
				text(character.name, character.x+character.name.length()+20,  character.y-13);
			}
		}
		
		for(Character character : network){
			a = (mouseX - character.x);
			b = (mouseY - character.y);
			if((a*a)+(b*b)<225){
				fill(unhex(character.color));
				rect(character.x+character.name.length()+15, character.y-35, character.name.length()*14+5, 30, 12, 12, 12, 12);
				textSize(20);
				fill(0);
				text(character.name, character.x+character.name.length()+20,  character.y-13);
			}
		}
		
	}
	
	public void mouseDragged( ) {
		//when mouse dragged point moved
			if(characters_now!=null){
				characters_now.x = mouseX;
				characters_now.y = mouseY;
			}
	}

	public void mousePressed(){
		//find which point was dragged, and record
		boolean find = false;
		for(Character character : characters){
			if(((character.x-mouseX)*(character.x-mouseX))+((character.y-mouseY)*(character.y-mouseY))<225){
				characters_now = character;
				find = true;
				break;
			}
		}
		if(find==false){ 
			for(Character character : network){
				if(((character.x-mouseX)*(character.x-mouseX))+((character.y-mouseY)*(character.y-mouseY))<225){
					characters_now = character;
					break;
				}
			}
		}
	}

	public void mouseReleased(){
		//check whether this point have been add to network or not
		if(characters_now!=null){
			if((((characters_now.x-600)*(characters_now.x-600))+((characters_now.y-350)*(characters_now.y-350)))>62500){
				characters_now.x = characters_now.ini_x;
				characters_now.y = characters_now.ini_y;
				if(network.contains(characters_now)) {
					network.remove(characters_now);
					characters.add(characters_now);
				}
				
			}else{
				if(!network.contains(characters_now)) {
					network.add(characters_now);
					characters.remove(characters_now);
				}
			}
			drawnetwork();
		}
		characters_now = null;	
	}
	
	//draw right-side network
	public void drawnetwork(){
		if(network.size()!=0){
			float network_num = 2f*(float)Math.PI/((float)network.size());
			float i = 1;
			for(Character character: this.network){
				character.x = 600 + 250*cos(i*network_num);
				character.y = 350 + 250*sin(i*network_num);
				character.display();
				i++;
			}
		}
	}
	
	
	private void loadData(){
		int x = 50; //just for calculating location
		data = loadJSONObject(path+file);
		nodes = data.getJSONArray("nodes");
		links = data.getJSONArray("links");
		for(int i=0; i<nodes.size(); i++){
			JSONObject node = nodes.getJSONObject(i);
			Character a = new Character(this, node.getString("name"), node.getString("colour"), (i/10)*40 + 80, x ) ;
			x+=40;
			if( x > 440) x=50;
			characters.add(a);
		}

		for(int i=0; i<links.size(); i++){
			JSONObject link = links.getJSONObject(i);
			characters.get(link.getInt("source")).addTarget(characters.get(link.getInt("target")));
		}
		
	}

}
