import java.util.ArrayList;
/**
 * Contains an address and list of possible values
 *
 * @author Drew Ivarson
 * @version 2/8/2015
 */
public class CodeByte
{
    private int address;
    private ArrayList<Integer> values;

    /**
     * Constructor for objects of class CodeBytes
     */
    public CodeByte(int add, int val)
    {
        address = add;
        values = new ArrayList<Integer>();
        values.add(val);
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
    
    public String toString()
    {
        String toRet = "Address: " + address + " Possible Values: ";
        for (int i = 0; i < values.size(); i++)
        {
            toRet += values.get(i) + ", ";
        }
        return toRet;
    }
}
