
/**
 * An instruction set is an object that defines the instructions that any program can have.
 * 
 * @author Drew Ivarson
 * @version 2/8/2015
 */

import java.util.ArrayList;

public class InstructionSet
{
    // instance variables - replace the example below with your own
    private ArrayList<Instruction> instructions;

    /**
     * Constructor for objects of class InstructionSet
     */
    public InstructionSet()
    {
        instructions = new ArrayList<Instruction>();
    }

    /**
     * Add an instruction
     * 
     * @param  Instruction ins the instruction
     */
    public void add(Instruction ins)
    {
        instructions.add(ins);
    }
    
    /**
     * Given the opcode, gets the number of bytes
     * 
     * @param opcode
     * @return numBytes
     */
    public int getNumBytesFromOpcode(int opcode)
    {
        if (instructions.isEmpty())
            return 0;
        else
        {
            for (int i = 0; i < instructions.size(); i++)
            {
                if (instructions.get(i).getOP() == opcode)
                    return instructions.get(i).getNumBytes();
            }
            
            return 0;
        }
    }
    
    /**
     * Given an opcode, gets the name
     * 
     * @param opcode the opcode
     * @return name the name
     */
    public String getNameFromOpcode(int opcode)
    {
        if (instructions.isEmpty())
            return "OPCODE NOT FOUND";
        else
        {
            for (int i = 0; i < instructions.size(); i++)
            {
                if (instructions.get(i).getOP() == opcode)
                {
                    return instructions.get(i).getName();
                }
            }
        }
        
        return "OPCODE NOT FOUND";
    }
    
    /**
     * Given the opcode, gets the abstract form
     * 
     * @param the opcode
     * @return AbstractInstruction
     */
    public AbstractInstruction getAbstractSyntaxFromOP(int opcode)
    {
        if (instructions.isEmpty())
            return AbstractInstruction.SKIP;
        else
        {
            for (int i = 0; i < instructions.size(); i++)
            {
                if (instructions.get(i).getOP() == opcode)
                {
                    return instructions.get(i).getAbsSyntax();
                }
            }
        }
        
        return AbstractInstruction.SKIP;
    }
    
    public String toString()
    {
        String toRet = "";
        for (int i = 0; i < instructions.size(); i++)
        {
            toRet += instructions.get(i).toString();
            toRet += "\n";
        }
        return toRet;
    }
}
