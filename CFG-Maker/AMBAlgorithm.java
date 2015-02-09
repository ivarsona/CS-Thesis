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
                else if (abs == AbstratInstruction.GOTOREL)
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
