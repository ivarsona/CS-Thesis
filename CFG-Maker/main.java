
/**
 * Write a description of class main here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.ArrayList;
import java.util.Scanner;
public class main
{
    public static void main(String[] args)
    {
           Scanner sc = new Scanner(System.in);
           String name = "blahhhh";
           System.out.print("Enter file name, or quit to quit: ");
           
           name = sc.nextLine();
           System.out.println();
           while (!name.equals("quit"))
           {
               FileIO stuff = new FileIO(name);
               System.out.println(Disassembler.DisAsmFromStrList(stuff.getFileDump()));
               ArrayList<CodeByte> bytes = new ArrayList<CodeByte>();
               ArrayList<String> fullDisAsm = new ArrayList<String>();
               fullDisAsm = (ArrayList) Disassembler.DisAsmFromStrList(stuff.getFileDump());
               for (int i = 0; i < fullDisAsm.size(); i++)
               {
                   String currentLine = fullDisAsm.get(i);
               
                   String instruction = stuff.getFileDump().get(i); 
                   String address = currentLine.substring(0, currentLine.indexOf(":"));
                   String disassembly = currentLine;
                   String opcode = instruction.substring(0, instruction.indexOf(" "));
                   bytes.add(new CodeByte(address, instruction, disassembly, opcode));
                }
                for (int i = 0; i < bytes.size(); i++)
                {
                    //System.out.println(bytes.get(i) + "\n");
                }
           
                ByteMap map = new ByteMap(stuff.getFileDump(), bytes);
           
                Analyzer.visitAllCodeBytes(map);
           

               System.out.print("Enter file name, or quit to quit: ");
               name = sc.nextLine();
           }
    }
}
