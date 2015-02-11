import java.util.ArrayList;
/**
 * Contains static methods for most parts of the AMB algorithm
 * 
 * @author Drew Ivarson
 * @version 2/8/2015
 */
public class AMBAlgorithm
{
    /**
     * Takes an arraylist of Codebytes that represents a set of data bits for an instruction.
     * 
     * Using the size of the list of codebytes, it returns a list of a list of integers.
     * The outermost list represents all possible combinations of the codebyte values.
     * The innter lists are the specific combinations
     * 
     */     
    private static ArrayList<ArrayList<Integer>> getDataBits(ArrayList<CodeByte> bytes)
    {
        ArrayList<ArrayList<Integer>> toRet = new ArrayList<ArrayList<Integer>>();
        switch (bytes.size())
        {
            case 1:
            {
              for (int i = 0; i < bytes.get(0).getValues().size(); i++)
                    toRet.add(new ArrayList<Integer>(bytes.get(0).getValues().get(i)));  
            };
            case 2:
            {
                ArrayList<Integer> current = new ArrayList<Integer>();
                current.add(0);
                current.add(0);
                for (int i = 0; i < bytes.get(0).getValues().size(); i++)
                {
                    for (int j = 0; j < bytes.get(1).getValues().size(); j++)
                    {
                        current.set(0, bytes.get(0).getValues().get(i));
                        current.set(1, bytes.get(1).getValues().get(j));
                        toRet.add(new ArrayList<Integer>(current));
                    }
                }
            };
            case 3:
            {
                ArrayList<Integer> current = new ArrayList<Integer>();
                current.add(0);
                current.add(0);
                current.add(0);
                for (int i = 0; i < bytes.get(0).getValues().size(); i++)
                {
                    for (int j = 0; j < bytes.get(1).getValues().size(); j++)
                    {
                        for (int k = 0; k < bytes.get(2).getValues().size(); k++)
                        {
                            current.set(0, bytes.get(0).getValues().get(i));
                            current.set(1, bytes.get(1).getValues().get(j));
                            current.set(2, bytes.get(2).getValues().get(k));
                            toRet.add(new ArrayList<Integer>(current));
                        }
                    }
                }
            };
            default:
            {}
        }   
        return toRet;
    }
    
    /**
     * Given:
     * A base address, an opcode, a list of CodeBytes, and an instruction set, this function:
     * 
     * Produces an InstructionByte which encapsulates all possible disassemblies of the given opcode
     * with the data codebytes.
     */
      
    private static InstructionByte parseDataBits(int baseAddr, int opcode, ArrayList<CodeByte> bytes)
    {
        InstructionByte ins = new InstructionByte(baseAddr);
        ArrayList<ArrayList<Integer>> allData = getDataBits(bytes);
        for (int i = 0; i < allData.size(); i++)
        {
            ins.addInstruction(new ConcIns(opcode, allData.get(i)));
        }
        return ins;
    }
    
    
    
    /**
     * Recursively goes through the algorithm
     * 
     * @param the program
     * @param the instruction set
     * @return a list of instruction bytes
     */
    public static ArrayList<InstructionByte> recAlgorithm(Program p, InstructionSet inSet)
    {
        ArrayList<InstructionByte> ins = new ArrayList<InstructionByte>();
        ArrayList<CodeByte> bytes = p.getBytes();
        for (int i = 0; i < bytes.size(); i++)
        {
            bytes.get(i).markUnvisited();
        }
        int next = nextInstruction(bytes, 0, inSet);
        return ins;
    }
    
    private static int nextInstruction(ArrayList<CodeByte> b, int start, InstructionSet inSet)
    {
        b.get(start).markVisited();
        ArrayList<ConcIns> instructions = new ArrayList<ConcIns>();
        int next = start;
        for (int i = 0; i < b.get(start).getValues().size(); i++)
        {
            int numBytes = inSet.getNumBytesFromOpcode(b.get(start).getValues().get(i));
            InstructionByte ins = disassembleInstruction(next, i, b, inSet, numBytes);
            System.out.println(ins);
        }
        return next;
    }
    
    private static InstructionByte disassembleInstruction(int start, int startVal, ArrayList<CodeByte> b, InstructionSet inSet, int numBytes)
    {
        InstructionByte ins = new InstructionByte(start);
        int opcode = b.get(start).getValues().get(startVal);
        ArrayList<Integer> data = new ArrayList<Integer>();
        data.add(0);
        data.add(0);
        if (numBytes == 3)
        {
            for (int i = 0; i < b.get(start+1).getValues().size(); i++)
            {
                data.set(0, b.get(start+1).getValues().get(i));
                for (int j = 0; j <  b.get(start+2).getValues().size(); j++)
                {
                    System.out.println("Second data bit: " + b.get(start+2).getValues().get(j));
                    data.set(1, b.get(start+2).getValues().get(j));
                    ins.addInstruction(new ConcIns(opcode, data));
                    
                }
            }
        }
        else if(numBytes == 2)
        {
            for (int i = 0; i < b.get(start+1).getValues().size(); i++)
            {
                data.add(b.get(start+1).getValues().get(i));
                ins.addInstruction(new ConcIns(opcode, data));
                data = new ArrayList<Integer>();
            }
        }
        
        return ins;
    }
    
    /**
     * Fills in CodeBytes of program p by searching for rewrites
     * 
     * @param  p the Program
     * @param inSet the instruction set
     */
    public static void calcByteVals(Program p, InstructionSet inSet)
    {
        int stepper = 1;
        for (int i = 0; i < p.getBytes().size(); i+= stepper)
        {
            for (int j = 0; j < p.getBytes().get(i).getValues().size(); j++)
            {
                int opcode = p.getBytes().get(i).getValues().get(j);
                stepper = inSet.getNumBytesFromOpcode(opcode);
                String current = opcode + " ";
                current += p.getBytes().get(i+1).getValues().get(0);
                
                if (stepper == 3)
                {
                    current += " " + p.getBytes().get(i+2).getValues().get(0);
                }
                //System.out.println(current);
                AbstractInstruction abs = inSet.getAbstractSyntaxFromOP(opcode);
                if (abs == AbstractInstruction.WRITECONST)
                {
                    for (int k = 0; k < p.getBytes().get(i+1).getValues().size(); k++)
                    {
                        int value = p.getBytes().get(i+1).getValues().get(k);
                        for (int l = 0; l < p.getBytes().get(i+2).getValues().size(); l++)
                        {
                            int addr = p.getBytes().get(i+2).getValues().get(l);
                            p.getBytes().get(addr).addValue(value);
                        }
                
                    }
                }
                else if (abs == AbstractInstruction.WRITEREL)
                {
                    for (int k = 0; k < p.getBytes().get(i+1).getValues().size(); k++)
                    {
                        int value = p.getBytes().get(i+1).getValues().get(k);
                        for (int l = 0; l < p.getBytes().get(i+2).getValues().size(); l++)
                        {
                            int offset = p.getBytes().get(i+2).getValues().get(l);
                            p.getBytes().get(i+offset).addValue(value);
                        }
                
                    }
                }
                else if (abs == AbstractInstruction.GOTOCONST)
                {
                }
                else if (abs == AbstractInstruction.GOTOREL)
                {
                }
                else
                {}
            }
        }
    }
    
    
    public static ArrayList<ProgramRunThrough> generateAllRuns(Program p, InstructionSet inSet)
    {
        int opcode;
        ArrayList<Integer> data = new ArrayList<Integer>();
        
        ArrayList<ProgramRunThrough> allRuns = new ArrayList<ProgramRunThrough>();
        
        ArrayList<CodeByte> bytes = p.getBytes();
        
        ProgramRunThrough current = new ProgramRunThrough();
        
        
        
        
        
        
        
        
        return allRuns;        
    }
    
    
    
}
