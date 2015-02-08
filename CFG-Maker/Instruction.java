
/**
 * An instruction is an assembly instruction. It has 4 fields:
 * 
 * 1. int opcode - binary representation of its name
 * 2. int number_of_bytes - the number of bytes long it is including the opcode
 * 3. String name - its disassembled name
 * 4. AbstractInstructrion absSyntax - its representation given the abstract syntax defined in the file
 * @author Drew Ivarson 
 * @version 2/8/2015
 */
public class Instruction
{
    // instance variables - replace the example below with your own
    private int opcode;
    private int number_of_bytes;
    private String name;
    private AbstractInstruction absSyntax;

    /**
     * @param op opcode
     * @param numBytes number of bytes
     * @param name the name
     * @param absIns the abstract instruction
     */
    public Instruction(int op, int numBytes, String name, AbstractInstruction absIns)
    {
        opcode = op;
        number_of_bytes = numBytes;
        this.name = name;
        absSyntax = absIns;
    }

    /**
     * Gets the opcode
     * 
     * @return  opcode the opcode
     */
    public int getOP()
    {
        return opcode;
    }
    
    /**
     * Gets the number of bytes
     * 
     * @return int number of bytes
     */
    public int getNumBytes()
    {
        return number_of_bytes;
    }
    
    /**
     * Gets the name
     * 
     * @return the name
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * Gets the abstract syntax
     * 
     * @return the AbstractInstruction
     */
    public AbstractInstruction getAbsSyntax()
    {
        return absSyntax;
    }
    
    /**
     * ToString in the form of:
     * name: NAME, opcode: xx, number of bytes: x, Abstract Syntax: absSyntax
     */
    public String toString()
    {
        return "Name: " + name + ", opcode: " + opcode + ", number of bytes: " + number_of_bytes
            + ", AbstractSyntax: " + absSyntax;
    }
}
