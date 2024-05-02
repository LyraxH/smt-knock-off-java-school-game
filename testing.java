
/**
 * Write a description of class testing here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.util.*;
public class testing
{
    Random rng  = new Random();
    public int weak;
    public int resist;
    public int nullify;
    public boolean can = false;
    public testing()
    {
        while (!can){
            if (weak != resist && weak != nullify && resist != nullify){
                can = true;
                System.out.println(weak);
                System.out.println(resist);
                System.out.println(nullify);
                System.out.println(can);
            } else {
                can = false;
                System.out.println("couldnt");
                weak = rng.nextInt(7);
                resist = rng.nextInt(7);
                nullify = rng.nextInt(7);
            }
        }
    }
}
