import java.util.List;
import java.util.ArrayList;
/**
 * Write a description of class Disassembler here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Disassembler
{
    
    public static final String JUMPOP = "0c";
    public static final String JUMPDIS = "JMP";
    public static final String MOVOP = "c6";
    public static final String MOVDIS = "MOVB";
    public static final String INCOP = "40";
    public static final String INCDIS = "INC";
    public static final String REG1OP = "01";
    public static final String REG1NAME = "%eax";
    public static final String REG2OP = "02";
    public static final String REG2NAME = "%ebx";
    
    public static List<String> DisAsmFromHexDump(String hexDump)
    {
        System.out.println("Hex Dump being parsed: " + hexDump);
        String currentByte = "";
        String restOfDump = hexDump;
        ArrayList<String> binFile = new ArrayList<String>();
        while (restOfDump.length() > 0)
        {     
            currentByte = restOfDump.substring(0,2);
            restOfDump = restOfDump.substring(2, restOfDump.length());
            //System.out.println("Current byte: " + currentByte);
            //System.out.println("Current hex dump: " + restOfDump);
            if (currentByte.equals(MOVOP))
            {
                try
                {
                    String valByte = restOfDump.substring(0,2);
                    String locByte = restOfDump.substring(2,4);
                    //System.out.println(currentByte + " " + valByte + " " + locByte);
                    binFile.add(currentByte + " " + valByte + " " + locByte);
                    restOfDump = restOfDump.substring(4,restOfDump.length());
                    //System.out.println("Rest of dump: " + restOfDump);
                }
                catch (Exception e)
                {
                    System.out.println("Tried to do a MOV instruction without 2 bytes remaining!"
                                        + "\nHex Dump: " + hexDump);
                    return null;
                }
            }
            else
            {
                String valByte = restOfDump.substring(0,2);
                //System.out.println(currentByte + " " + valByte);
                binFile.add(currentByte + " " + valByte);
                restOfDump = restOfDump.substring(2, restOfDump.length());
                //System.out.println("Rest of dump: " + restOfDump);
            }
          
            
        }
        
        return DisAsmFromStrList(binFile);
        
    }
    
    
    
    public static List<String> DisAsmFromStrList(List<String> fileDump)
    {
        ArrayList<String> dump = new ArrayList<String>();
        dump.addAll(fileDump);
        ArrayList<String> opCodes = new ArrayList<String>();
        
        //build list of opcodes
        for (int i = 0; i < dump.size(); i++)
        {
            opCodes.add(getOPCode(dump.get(i)));
        }
        
        //add in the disassembled opcodes
        for (int j = 0; j < dump.size(); j++)
        {
            //System.out.println(dump.get(j));
            opCodes.set(j, opCodes.get(j) + " " +
                dump.get(j).substring(dump.get(j).indexOf(' '), dump.get(j).length())); 
        }
        
        //get number of bytes in the program
        int numBytes = 0;
        for (int i = 0; i < dump.size(); i++)
        {
            String current = dump.get(i);
            numBytes += current.length() - current.replaceAll(" ", "").length();
        }
        
        //set up addresses
        int index = 0;
        int i = 0;
        while (index < dump.size())
        {
            String current = dump.get(index);
            opCodes.set(index, "0x" + i + ":" + opCodes.get(index));
            i+= 1+ current.length() - current.replaceAll(" ", "").length();
            index++;
        }
        
        //now add in register names
        for (int j = 0; j < opCodes.size(); j++)
        {
            String current = opCodes.get(j);
            //System.out.println("Currently registering: " + current);
            opCodes.set(j, getRegName(opCodes.get(j)));
        }
        return opCodes;
    }
    
    public static String getRegName(String ins)
    {
        String data = ins.substring(ins.indexOf(" ") + 1, ins.length());
        String opAndAddr = ins.substring(0, ins.indexOf(" "));
        String opCode = ins.substring(ins.indexOf(":") + 1, ins.indexOf(" "));
        if (!isRegOP(opCode))
            return ins;
        else
        {
            //System.out.println("Current data that it thinks is a register: " + data);
            if (data.indexOf(REG1OP) != -1)
                return opAndAddr + data.replaceAll(REG1OP, REG1NAME);
            else if (data.indexOf(REG2OP) != -1)
                return opAndAddr + data.replaceAll(REG2OP, REG2NAME);
            else
                return "ERROR IN REGNAME";
        }
    }
    
    public static boolean isRegOP(String op)
    {
        if (op.equals(INCDIS))
            return true;
        else
            return false;
    }
    
    public static String getOPCode(String ins)
    {
        //System.out.println(ins);
        String op = ins.substring(0, ins.indexOf(' '));
        if (op.equals(MOVOP))
            return MOVDIS;
        else if (op.equals(JUMPOP))
            return JUMPDIS;
        else if (op.equals(INCOP))
            return INCDIS;
        else
            return "INVALID OPCODE FOUND: " + ins;
    }
}
