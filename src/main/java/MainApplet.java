package main.java;

import processing.core.PApplet;
import java.util.ArrayList;
import processing.core.PApplet;
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
	
	private String path = "main/resources/";
	private String file = "starwars-episode-1-interactions.json";
	
	private final static int width = 1200, height = 650;
	
	public void setup() {
		characters = new ArrayList<Character>();
		size(width, height);
		smooth();
		loadData();
		
	}
	public void keyPressed(){
		if(keyCode==32)
			setup();
	}
	public void draw() {
		stroke(60, 119, 119);
		strokeWeight(4);
		
		for(Character character: this.characters){
			for(Character target: character.getTargets())
				line(character.x, character.y, target.x, target.y);
		}
		
		for(Character character : characters)
			character.display();
	}

	private void loadData(){
		int x = 50;
		data = loadJSONObject(path+file);
		nodes = data.getJSONArray("nodes");
		links = data.getJSONArray("links");
		for(int i=0; i<nodes.size(); i++){
			JSONObject node = nodes.getJSONObject(i);
			Character a = new Character(this, node.getString("name"), node.getString("colour"), i/10 + 10, x ) ;
			x+=50;
			characters.add(a);
		}

		for(int i=0; i<links.size(); i++){
			JSONObject link = links.getJSONObject(i);
			characters.get(link.getInt("source")).addTarget(characters.get(link.getInt("target")));
		}
	}

}
