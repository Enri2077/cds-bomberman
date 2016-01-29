package game;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

import entity.Player;
import input.Keyboard;
import serial.PlayerSerial;

public class ServerListener extends Listener{
	public ServerGame server;
	
	public ServerListener(ServerGame server){
		this.server = server;
	}
	
    public void received (Connection connection, Object object) {
        //System.out.println("Receiving");

        if (object instanceof ComObj) {
           ComObj com = (ComObj)object;
           switch(com.getMessage()){
        	   case NEW_PLAYER:
	       			Player player = new Player(1.5f,2.5f,new Keyboard(),connection.getID());
	       			server.level.players.add(player);
	       			System.out.println("Received create new player!");
	       			break;
        	   case KEYBOARD:
	       			//System.out.println("Received keyboard!");
	       			for(Player p : server.level.players){
	       				if(p.ID==connection.getID()){
	       					p.input = (Keyboard)com.getObject();
	       					PlayerSerial pSerial = new PlayerSerial();
	       					pSerial.x = p.gridX;
	       					pSerial.y = p.gridY;
	       					pSerial.id = connection.getID();
	       					pSerial.down = p.input.down;
	       					pSerial.left = p.input.left;
	       					pSerial.right = p.input.right;
	       					pSerial.up = p.input.up;
	       					pSerial.space = p.input.space;
	       					ComObj msg = new ComObj(Msg.PLAYER,pSerial);
	       					server.serverConn.sendToAllTCP(msg);
	       					break;
	       				}
	       			}
        		    break;
        	   default:
	       			System.out.println("[SERVER]: Received comm msg unknown!");
	       			break;     		
           }       			 
        }else{
            System.out.println("Received something");

        }
     }

}