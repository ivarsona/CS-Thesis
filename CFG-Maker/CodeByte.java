/**
 * Write a description of class CodeByte here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CodeByte
{
    private String address;
    private String instruction;
    private String disassembly;
    private String opcode;
    private boolean visited;

    /**
     * Constructor for objects of class CodeByte
     */
    public CodeByte(String addr, String ins, String disasm, String op)
    {
        // initialise instance variables
        address = addr;
        instruction = ins;
        disassembly = disasm;
        opcode = op;
        try
        {
            opcode = instruction.substring(0, instruction.indexOf(" "));
        }
        catch (Exception e)
        {
            System.out.println("Invalid instruction: " + ins);
        }
        visited = false;
    }
    
    public void markAsVisited()
    {
        visited = true;
    }
    
    public void markAsUnvisited()
    {
        visited = false;
    }
    
    public boolean isVisited()
    {
        return visited;
    }

    public String getAddress()
    {
        return address;
    }
    
    public String getInstruction()
    {
        return instruction;
    }
    
    public String getDisasm()
    {
        return disassembly;
    }
    
    public boolean isMovOP()
    {
        if (opcode.equals(Disassembler.MOVOP))
            return true;
        else
            return false;
    }
    
    public boolean isJMPOP()
    {
        if (opcode.equals(Disassembler.JUMPOP))
            return true;
        else
            return false;
    }
    
    public String toString()
    {
        return "Address: " + address 
            + "\nInstruction: " + instruction 
            + "\nDisassembly: " + disassembly
            + "\nVisited: " + visited + "\n";
    }
}
