import java.nio.file.*;
import java.util.*;
/**
 * Given a file, gives back an InstructionSet object
 * 
 * @author Drew Ivarson 
 * @version 2/8/2015
 */


public class InstructionSetParser
{
    /**
     * Given a file, returns an instruction set
     * 
     * @param  filename   the name of the file
     * @return     the instrucion set described by the file 
     */
    public static InstructionSet parseInstructionSet(String filename)
    {
       InstructionSet ins = new InstructionSet();
       ArrayList<String> file = new ArrayList<String>();
       Path path = FileSystems.getDefault().getPath(filename);
       try
       {
             file = (ArrayList) Files.readAllLines(path);
       }
       catch (Exception e)
       {
             System.out.println("File reader error");
       }
       
       for (int i = 0; i < file.size(); i++)
       {
           System.out.println(file.get(i));
           int opcode;
           int numBytes;
           String name;
           AbstractInstruction abs;
           
           String line = file.get(i);
           opcode = Integer.parseInt(line.substring(0, line.indexOf(" ")), 16);
           line = line.substring(line.indexOf(" ") + 1, line.length());
           numBytes = Integer.parseInt(line.substring(0, line.indexOf(" ")));
           line = line.substring(line.indexOf(" ") + 1, line.length());
           name = line.substring(0, line.indexOf(" "));
           line = line.substring(line.indexOf(" ") + 1, line.length());
           String absStr = line.substring(0, line.length());
           
           if (absStr.equals("WRITECONST"))
               abs = AbstractInstruction.WRITECONST;
           else if (absStr.equals("WRITEREL"))
               abs = AbstractInstruction.WRITEREL;
           else if (absStr.equals("GOTOCONST"))
               abs = AbstractInstruction.GOTOCONST;
           else if (absStr.equals("GOTOREL"))
               abs = AbstractInstruction.GOTOREL;
           else if (absStr.equals("SKIPORGOTOCONST"))
               abs = AbstractInstruction.SKIPORGOTOCONST;
           else if (absStr.equals("SKIPORGOTOREL"))
               abs = AbstractInstruction.SKIPORGOTOREL;
           else
               abs = AbstractInstruction.SKIP;
               
           ins.add(new Instruction(opcode, numBytes, name, abs));
       }
       
       return ins;
    }
}
