import java.util.ArrayList;
import java.util.List;
/**
 * Write a description of class ByteMap here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ByteMap
{
    // instance variables - replace the example below with your own
    private ArrayList<String> hexList;
    private ArrayList<CodeByteDbg> codeBytes;
    private String hexDump;

    /**
     * Constructor for objects of class ByteMap
     */
    public ByteMap(List<String> program, ArrayList<CodeByteDbg> bytes)
    {
        codeBytes = bytes;
        hexList = new ArrayList<String>();
        hexDump = "";
        for (int i = 0; i < program.size(); i++)
        {
            String current = program.get(i);
            String[] hexBytes = current.split(" ");
            for (int j = 0; j < hexBytes.length; j++)
            {
               hexList.add(hexBytes[j]);
            }
        }
        
        for (int i = 0; i < hexList.size(); i++)
            hexDump += hexList.get(i);
        
            
            
        //System.out.println(hexList);
        //System.out.println(hexDump);
    }
    
    public ByteMap(ByteMap old, int changedIndex, String newData)
    {
        this.hexList = old.getHexList();
        hexList.set(changedIndex, newData);
        this.hexDump = "";
        newHexDump();
    }
    
    private void newHexDump()
    {
        for (int i = 0; i < hexList.size(); i++)
        {
            hexDump += hexList.get(i);
        }
    }
    
    public ArrayList<CodeByteDbg> getCodeBytes()
    {
        return codeBytes;
    }
    
    public ArrayList<String> getHexList()
    {
        return hexList;
    }
    
    public String getHexDump()
    {
        return hexDump;
    }
}
