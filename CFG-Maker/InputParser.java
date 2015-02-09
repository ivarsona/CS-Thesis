import java.nio.file.*;
import java.util.*;
/**
 * Given the name a file, parses it into an ArrayList of codebytes
 * 
 * @author Drew Ivarson
 * @version 2/8/2015
 */
public class InputParser
{
    public static Program parseProgram(String name)
    {
        ArrayList<String> fileDump = new ArrayList<String>();
        Path path = FileSystems.getDefault().getPath(name);
        try
        {
               fileDump = (ArrayList<String>)Files.readAllLines(path);
               System.out.println(fileDump);
        }
        catch (Exception E)
        {
               System.out.println("File reader error");
        }
        
        int addrCount = 0;
        String row = "";
        ArrayList<CodeByte> program = new ArrayList<CodeByte>();
        for (int i = 0; i < fileDump.size(); i++)
        {
            row = fileDump.get(i);
            row = row.replaceAll(" ", "");
            //System.out.println(row);
            
            while (!row.equals(""))
            {
                CodeByte current = new CodeByte(addrCount, 
                            Integer.parseInt(row.substring(0, 2), 16));
                program.add(current);
                row = row.substring(2, row.length());
                addrCount++;            
            }
        }
        
        Program p = new Program(program);
        return p;
    }
}
