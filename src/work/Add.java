package work;
import java.util.Random;

public class Add extends Calc{
    public Add(){
        this(new Param(1,10000,1,10000,1,10000));
    }
    public Add(Param p){
        int xmin = p.getMinX();
        int tmp= p.getMaxZ()- p.getMinY();
        int xmax = p.getMaxX()<tmp?p.getMaxX():tmp;
        x=(new Random().nextInt(xmax-xmin+1))+xmin;

        int ymin = p.getMinY();
        tmp =p.getMaxZ()-x;
        int ymax = p.getMaxY()<tmp?p.getMaxY():tmp;

        y=(new Random().nextInt(ymax-ymin+1))+ymin;

        z=x+y;

    }
    @Override
    public String toString(){
        return x+"+"+y+"=";
    }


}
