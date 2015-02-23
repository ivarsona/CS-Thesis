
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
           System.out.println("Welcome to the CFG Maker (someday...)");
           System.out.println("The current instruction set is:\n\n");
           InstructionSet inSet = InstructionSetParser.parseInstructionSet("InstrSet0.txt");
           System.out.println("\n\n");
           System.out.print("Enter file name, new to change instruction sets, or quit to quit: ");
           //System.out.println(inSet);
           name = sc.nextLine();
           System.out.println();
           while (!name.equals("quit"))
           {
              if (name.equals("new"))
              {
                  System.out.print("Enter new instruction set name,\nor nothing if you don't want to change: ");
                  name = sc.nextLine();
                  inSet = InstructionSetParser.parseInstructionSet(name);
                  System.out.println(inSet);
                  System.out.print("Enter file name, new to change instruction sets, or quit to quit: ");
                  name = sc.nextLine();
                  System.out.println();
              }
              else
              {
                  Program p = InputParser.parseProgram(name);
                  System.out.println(p); 
                  
                  System.out.println("---Calculating CodeByte Variants---\n\n");
//                  AMBAlgorithm.calcByteVals(p, inSet);
  //                ArrayList<InstructionByte> instr = AMBAlgorithm.recAlgorithm(p, inSet);
    //              System.out.println("---CodeByte Variants:\n");
      //            System.out.println(p);
                  AMB thing = new AMB(p, inSet);
                  thing.analyze();
                  System.out.println(thing.getProgram());
                  
                  
                  System.out.print("Enter file name, new to change instruction sets, or quit to quit: ");
                  name = sc.nextLine();
                  System.out.println();
              }
           }
    }
}
