package game;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

import entity.Player;
import input.Keyboard;
import level.Level;
import serial.PlayerSerial;

public class ClientListener extends Listener{
	public Level level;
	
	public ClientListener(Level level){
		this.level = level;
	}
	
    public void received (Connection connection, Object object) {
        //System.out.println("Receiving");
    	
        if (object instanceof ComObj) {
           ComObj com = (ComObj)object;
           switch(com.getMessage()){
        	   case PLAYER:
        		   PlayerSerial p = (PlayerSerial) com.getObject();
        		   boolean found = false;
        		   for(Player play : level.players){
        			   if(play.ID == p.id){
        				   play.gridX = p.x;
        				   play.gridY = p.y;
        				   play.input = p.input;
        				   found = true;
        				   break;
        			   }
        		   }
        		   if(!found){
        			   Player newP = new Player(p.x,p.y,new Keyboard(),p.id);
        			   level.players.add(newP);
        		   }
        		   break;
        	   default:
	       			System.out.println("[CLIENT]Received comm msg unknown!");
	       			break;     		
           }       			 
        }else{
            System.out.println("Received something");

        }
     }

}