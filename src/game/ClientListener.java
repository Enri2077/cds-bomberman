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
        				   play.gridX = (float) p.x;
        				   play.gridY = (float) p.y;
        				   play.input.up = p.up;
        				   play.input.down = p.down;
        				   play.input.space = p.space;
        				   play.input.right = p.right;
        				   play.input.left = p.left;
        				   found = true;
        				   break;
        			   }
        		   }
        		   if(!found){
        			   Player newP = new Player((float)p.x,(float)p.y,new Keyboard(),p.id);
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