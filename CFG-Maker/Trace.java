import java.util.ArrayList;
/**
 * Write a description of class ProgramRunThrough here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Trace
{
    private ArrayList<ConcIns> insList;

    /**
     * Constructor for objects of class ProgramRunThrough
     */
    public Trace()
    {
        insList = new ArrayList<ConcIns>();
    }

    /**
     * Add instruction
     * 
     * @param  ins the instruction 
     */
    public void addInstruction(ConcIns ins)
    {
        insList.add(ins);
    }
    
    public String toString()
    {
        return insList.toString();
    }

}
