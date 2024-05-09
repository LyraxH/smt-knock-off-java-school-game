
/**
 * Write a description of class testing here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.util.*;
import javax.swing.*;
import java.awt.*;
public class testing
{
    
    Random rng  = new Random();
    /*
    private JFrame frame = new JFrame();
    private JLayeredPane lpane = new JLayeredPane();
    private JPanel panelBlue = new JPanel();
    private JPanel panelGreen = new JPanel();
    
    public int weak;
    public int resist;
    public int nullify;
    public boolean can = false;
    */
    
    int asd[] = new int[]{0,1,2,3,4,5,6};
    int dsa[] = new int[6];
    
    public testing()
    {
        System.out.println(asd[5]);
        for (int i = 0; i<7; i++){
            dsa[i] = i;
            System.out.println(dsa[i]);
        }
        /*
        frame.setPreferredSize(new Dimension(600, 400));
        frame.setLayout(new BorderLayout());
        frame.add(lpane, BorderLayout.CENTER);
        lpane.setBounds(0, 0, 600, 400);
        panelBlue.setBackground(Color.BLUE);
        panelBlue.setBounds(0, 0, 600, 400);
        //panelBlue.setOpaque(true);
        panelGreen.setBackground(Color.GREEN);
        panelGreen.setBounds(200, 100, 100, 100);
        //panelGreen.setOpaque(true);
        lpane.add(panelBlue, new Integer(0), 0);
        lpane.add(panelGreen, new Integer(1), 0);
        frame.pack();
        frame.setVisible(true);
        
        double a = 100 * 1.6 * 0.6 * 1;
        int b = (int)Math.round(a);
        System.out.println(b);
        
        System.out.println(a);
        while (true){
            int b = rng.nextInt(a);
            System.out.println(b);
        }
        
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
        */
    }
}
