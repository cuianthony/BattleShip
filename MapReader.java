
/**
 * Reads the map from a text file
 * 
 * @author (??????) 
 * @version (???????)
 */

import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;

public class MapReader
{
    // Instance Fields
    private String fileName;
    public int monster;
    
    public MapReader()
    {
        
    }
    
    // Sets the name of text file
    public void setFileName(String name)
    {
        fileName = name;
    }
    
    // This will reads in the characters from the text file only
    public ArrayList<Tile> getTiles()
    {
        ArrayList<Tile> list = new ArrayList<Tile>();
        Scanner fileScan = null;
        String text;
        monster = 0;
        
        try{
            fileScan = new Scanner( new File(fileName) );
            
            while(fileScan.hasNext())
            {
                text = fileScan.nextLine();
                for(int i = 0; i < text.length(); i++)
                {
                    if(text.charAt(i) == '1' || text.charAt(i) == '2' || text.charAt(i) == '3')
                    {
                        list.add(new Monster(text.charAt(i)));
                        monster++;
                    }
                    else
                    {
                        list.add(new Tile(text.charAt(i)));
                    }
                }
            }
        }catch(IOException ioe){ } // do nothing ...
        
        return list;
    }
}
