import java.nio.file.*;
import java.util.*;
/**
 * Write a description of class FileIO here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class FileIO
{
    private List<String> fileDump;
    
    /**
     * Constructor for objects of class FileIO
    **/
    public FileIO(String name)
    {
                Path path = FileSystems.getDefault().getPath(name);
                try
                {
                    fileDump = Files.readAllLines(path);
                    System.out.println(fileDump);
                }
                catch (Exception E)
                {
                    System.out.println("File reader error");
                }
    }
    
    
    public List<String> getFileDump()
    {
            return fileDump;
    }
    
    
    
    
    

}
