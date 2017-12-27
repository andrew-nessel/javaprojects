/*
 * SimpleMazeGame.java
 * Copyright (c) 2008, Drexel University.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *     * Neither the name of the Drexel University nor the
 *       names of its contributors may be used to endorse or promote products
 *       derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY DREXEL UNIVERSITY ``AS IS'' AND ANY
 * EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL DREXEL UNIVERSITY BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package maze;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;
import java.io.File;

import maze.ui.MazeViewer;

/**
 * 
 * @author Sunny
 * @version 1.0
 * @since 1.0
 */
public class SimpleMazeGame
{
    /**
     * Creates a small maze.
     */
    public static Maze createMaze()
    {
        
        Maze maze = new Maze();
        
        Room room = new Room(0);
        Room room2 = new Room(1);

        maze.addRoom(room);
        maze.addRoom(room2);
        
        Door door = new Door(room,room2);
        
        room.setSide(Direction.North, new Wall());
        room.setSide(Direction.East, new Wall());
        room.setSide(Direction.West, door);
        room.setSide(Direction.South, new Wall());
        
        room2.setSide(Direction.North, new Wall());
        room2.setSide(Direction.East, door);
        room2.setSide(Direction.West, new Wall());
        room2.setSide(Direction.South, new Wall());
        
        maze.setCurrentRoom(1);
        
        return maze;

    }

    public static Maze loadMaze(final String path)
    {
        Maze maze = new Maze();
        
        try{
            File file = new File(path);
            Scanner scan = new Scanner(file);
            ArrayList<String> fileLines = new ArrayList<String>();
            ArrayList<Room> rooms = new ArrayList<Room>();
            ArrayList<Door> doors = new ArrayList<Door>();
            int index = 0;
            
            while(scan.hasNextLine()){
                String nextLine = scan.nextLine();
                fileLines.add(index, nextLine);
                index++;
            }
            
            for(String line : fileLines){
            	
            	if(!line.equals("")) {
                    String[] para = line.split(" ");
                    
                    if(para[0].equals("room")){
                    	
                        int temp = Integer.parseInt(para[1]);
                        Room tempRoom = new Room(temp);
                        rooms.add(temp, tempRoom);
                        maze.addRoom(tempRoom);
                        
                    }else if (para[0].equals("door")){
                    	
                        int room1 = Integer.parseInt(para[2]);
                        int room2 = Integer.parseInt(para[3]);
                        Door newDoor = new Door(rooms.get(room1), rooms.get(room2));
                        
                        if(para[4].equals("open")){
                            newDoor.setOpen(true);
                        }else if(para[4].equals("close")){
                            newDoor.setOpen(false);
                        }
                        
                        doors.add(newDoor);
                        
                    }else{ 
                        System.out.println("Looks like we missed something"); //DEBUG //Might just be newline
                    }
            	}
                
            }
            
            for(String line : fileLines){
            	
            	if(!line.equals("")) {
                    String[] para = line.split(" ");
                    
                    if(para[0].equals("room")){
                        int roomNumber = Integer.parseInt(para[1]);
                        
                        for(int x=2; x<para.length; x++){
                            
                        	Direction dir = Direction.West;
                        	
                        	if(x == 3) {
                        		dir = Direction.East;
                        	}else if(x == 4) {
                        		dir = Direction.South;
                        	}else if(x == 5){
                        		dir = Direction.North;
                        	}
                        	
                            if(para[x].equals("wall")){
                            	                      	
                                rooms.get(roomNumber).setSide(dir, new Wall());
                                
                            }else if(para[x].matches("d\\d+")){
                            	
                            	int doorNumber = Integer.parseInt(para[x].substring(1));
                            	rooms.get(roomNumber).setSide(dir, doors.get(doorNumber));
                            	
                            }
                 
                        }
                        
                    }
            	}
                
            }
            
        
        }catch(Exception e){
            e.printStackTrace();
        }
        
        maze.setCurrentRoom(0);
        
        return maze;
    }

    public static void main(String[] args)
    {
    	if((args.length > 0) && (!args[0].equals(""))) {
            //Maze maze = createMaze();
    		
        	Maze maze = loadMaze(args[0]);
            MazeViewer viewer = new MazeViewer(maze);
            viewer.run();
    	}
    }
}

