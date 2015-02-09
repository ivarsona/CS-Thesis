import java.util.ArrayList;
/**
 * A concrete instruction has an opcode, data bytes, and a disassembly
 * 
 * @author Drew Ivarson
 * @version 2/8/2015
 */
public class ConcIns
{
    // instance variables - replace the example below with your own
    private int opcode;
    private ArrayList<Integer> data;
    private String disassembly;

    /**
     * Constructor for objects of class ConcIns
     */
    public ConcIns(int op, ArrayList<Integer> dataStuff, String dis)
    {
        opcode = op;
        data = dataStuff;
        disassembly = dis;
    }
    
    public String toString()
    {
        return disassembly;
    }
}
