
/**
 * This represents the 6 possible abstract behaviors of any assembly language
 * 
 * WRITECONST - Write constVal to constAddr
 * WRITEREL - Write cosntVal to OFFSET
 * GOTOCONST - Go to constAddr
 * GOTOREL - Go to OFFSET
 * SKIPORGOTOCONST - Branch statement that is either a SKIP or is a GOTOCONST
 * SKIPORGOTOREL - Branch statement that is either a SKIP or is a GOTOREL
 * 
 * @author Drew Ivarson
 * @version 2/8/2015
 */
public enum AbstractInstruction
{
    WRITECONST, GOTOCONST, WRITEREL, GOTOREL, SKIP, SKIPORGOTOCONST, SKIPORGOTOREL
}
