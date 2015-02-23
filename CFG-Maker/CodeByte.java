import java.util.ArrayList;
/**
 * Contains an address and list of possible values
 *
 * @author Drew Ivarson
 * @version 2/8/2015
 */
public class CodeByte
{
    private boolean visited;
    private int address;
    private ArrayList<Integer> values;
    private ArrayList<Integer> targets;

    /**
     * Constructor for objects of class CodeBytes
     */
    public CodeByte(int add, int val)
    {
        visited = false;
        address = add;
        values = new ArrayList<Integer>();
        values.add(val);
        targets = new ArrayList<Integer>();
    }

    public void addTarget(int addr)
    {
        targets.add(addr);
    }
    
    public ArrayList<Integer> getTargets()
    {
        return targets;
    }
    public void markVisited()
    {
        visited = true;
    }
    
    public void markUnvisited()
    {
        visited = false;
    }
    
    public boolean visited()
    {
        return visited;
    }
    public void addValue(int val)
    {
        if (!values.contains(val))
            values.add(val);
        else
            return;
        
    }
    /**
     * Getter method for a codebyte, gives the list of values
     * 
     * @return  values  ArrayList<Integer> of values 
     */
    public ArrayList<Integer> getValues()
    {
       return values;
    }
    
    public boolean notATarget(int addr)
    {
        return !targets.contains(addr);
    }
    
    public String toString()
    {
        String toRet = "Address: " + Integer.toHexString(address) + " Possible Values: ";
        for (int i = 0; i < values.size(); i++)
        {
            toRet += Integer.toHexString(values.get(i)) + ", ";
        }
        if (!targets.isEmpty())
        {
            toRet += "\nTargets: ";
            for (int i = 0; i < targets.size(); i++)
            {
                toRet += Integer.toHexString(targets.get(i)) + ", ";
            }
        }
        return toRet;
    }
}
