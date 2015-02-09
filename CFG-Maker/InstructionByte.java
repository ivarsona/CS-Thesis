import java.util.ArrayList;
/**
 * An instruction byte has:
 * 
 * a base address
 * a list of instructiosn starting at that base address
 * @author Drew Ivarson
 * @version 2/9/2015
 */
public class InstructionByte
{
    // instance variables - replace the example below with your own
    private int address;
    private ArrayList<ConcIns> instructions;

    /**
     * Constructor for objects of class InstructionByte
     * 
     * @param base the base address
     */
    public InstructionByte(int base, ConcIns ins)
    {
        address = base;
        instructions = new ArrayList<ConcIns>();
        instructions.add(ins);
    }
    
    public InstructionByte(int base)
    {
        address = base;
        instructions = new ArrayList<ConcIns>();
    }

    /**
     * Add an instruction to the set of instructions
     * 
     * @param  ins the new instruction
     */
    public void addInstruction(ConcIns ins)
    {
        instructions.add(ins);
    }
    
    /**
     * Gets the list of instructions
     * 
     * @return the list of instructions
     */
    public ArrayList<ConcIns> getInstructions()
    {
        return instructions;
    }
    
    /**
     * Gets the base address
     * 
     * @return the address
     */
    public int getAddress()
    {
        return address;
    }
    
    public String toString()
    {
        String toRet = "";
        for (int i = 0; i < instructions.size(); i++)
        {
            toRet += instructions.get(i).toString() + "\n";
        }
        return "Base Address: " + address + "\n" + toRet;
    }
    
}
