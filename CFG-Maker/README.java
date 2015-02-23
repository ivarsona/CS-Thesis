
/**
 * 
 *     combine(input, new int[input.size()], 0);
}

private static void combine(ArrayList<int[]> input, int[] current, int k) {

    if(k == input.size()) {
        for(int i = 0; i < k; i++) {
            System.out.print(current[i] + " ");
        }
        System.out.println();
    } else {            
        for(int j = 0; j < input.get(k).length; j++) {
            current[k] = input.get(k)[j];
            combine(input, current, k + 1);
        }       
    }
}
 * THIS IS HORRIBLY OUT OF DATE I'LL UPDATE IT LATER
 * 
 * 
 * TO USE:
 * 
 * Write binary file with spaces between each byte, and a newLine between 
 * each instruction. It can also parse a straight hex dump, but that is not as easy to write.
 * 
 * Maybe add assembly instruction support?
 * 
 * Current instructions:
 * 
 * MOVB VALUE LOCATION  = c6 xx xx (must create a legal instruction! can only over-write code!)
 * JMP LOCATION         = 0c xx (must be a reachable point of the programs memory!)
 * INC REG              = 40 xx (from current register list!)
 * 
 * Current Registers:
 * 01 = %eax
 * 02 = %ebx
 * 
 * 
 * Currently there are two testing files:
 * goodMod.txt which re-writes code producing legal new code
 * badMod.txt which re-writes code producing illegal new code
 * 
 * @author Drew Ivarson
 * @version Feb 2, 2015
 */
