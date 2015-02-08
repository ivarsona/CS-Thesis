import java.util.ArrayList;
/**
 * Write a description of class Analyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Analyzer
{
        /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
    public static void visitAllCodeBytes(ByteMap map)
    {
        setAllUnvisited(map.getCodeBytes());
        checkForWrites(map.getCodeBytes(), map);
        //checkForControlFlow(map.CodeBytes(), map);
    }
    
    private static void setAllUnvisited(ArrayList<CodeByte> bytes)
    {
         for (int i = 0; i < bytes.size(); i++)
        {
            bytes.get(i).markAsUnvisited();
        }
    }
    
    private static void checkForWrites(ArrayList<CodeByte> bytes, ByteMap map)
    {
        for (int i = 0; i < bytes.size(); i++)
        {
            if (bytes.get(i).isMovOP())
            {
                String currentIns = bytes.get(i).getInstruction();
                //System.out.println("Code re-written: " + currentIns);
                String value = currentIns.substring(currentIns.indexOf(" ") + 1, 
                                                    currentIns.indexOf(" ") + 3);
                String loc = currentIns.substring(currentIns.lastIndexOf(" ") + 1,
                                                    currentIns.length());
                //System.out.println("To re-write:\nValue: " + value + "\nLocation: " + loc);
                int decLocInt = Integer.parseInt(loc, 16);
                //System.out.println("Hex: " + loc + " Decimal: " + decLocInt);
                
                ByteMap newMap = new ByteMap(map, decLocInt, value);
                       
                ArrayList<String> newDisAsm = (ArrayList<String>) 
                                             Disassembler.DisAsmFromHexDump(newMap.getHexDump());
                
                System.out.println("NEW DISASSEMBLY AFTER RE-WRITING:\n" + newDisAsm);
                ArrayList<CodeByte> newBytes = new ArrayList<CodeByte>();
                for (int j = 0; j < newDisAsm.size(); j++)
                {
                    String currentLine = newDisAsm.get(j);
                    String instruction = currentLine.substring(3, currentLine.length()); 
                    String address = currentLine.substring(0, currentLine.indexOf(":"));
                    String disassembly = currentLine;
                    String opcode = instruction.substring(0, instruction.indexOf(" "));
                    newBytes.add(new CodeByte(address, instruction, disassembly, opcode));
                }
               
                System.out.println("NEW CODEBYTES:");
                System.out.println(newBytes);
                
                                    
                                    
                
                
            }
        }
    }
    
    private static void checkForControlFlow(ArrayList<CodeByte> bytes, ByteMap map)
    {
        for (int i = 0; i < bytes.size(); i++)
        {
            if (bytes.get(i).isJMPOP())
                System.out.println("Control flow change found:\n" + bytes.get(i));
        }
    }
}
