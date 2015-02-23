import java.util.ArrayList;
/**
 * A program is a list of codebytes
 * 
 * @author Drew Ivarson
 * @version 2/8/2015
 */
public class Program
{
    private ArrayList<CodeByte> bytes;

    /**
     * Constructor for objects of class Program
     */
    public Program(ArrayList<CodeByte> cb)
    {
        // initialise instance variables
        bytes = cb;
    }

    public void markAllUnvisited()
    {
        for (int i = 0; i < bytes.size(); i++)
        {
            bytes.get(i).markVisited();
        }
    }
    /**
     * Gets the list of codebytes
     * 
     * @return     the list of codebytes 
     */
    public ArrayList<CodeByte> getBytes()
    {
        return bytes;
    }
    
    public String toString()
    {
        String toRet = "";
        for (int i = 0; i < bytes.size(); i++)
        {
            toRet += bytes.get(i).toString() + "\n";
        }
        
        
        
        return toRet;
    }
}
