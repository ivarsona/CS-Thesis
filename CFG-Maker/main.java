
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
              Program p = InputParser.parseProgram(name);
              System.out.println(p); 
               
               
               
              System.out.print("Enter file name, or quit to quit: ");
              name = sc.nextLine();
           }
    }
}
