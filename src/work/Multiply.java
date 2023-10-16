package work;
import java.util.Random;
public class Multiply extends Calc{
    public Multiply(){
        this(new Param(1,100,1,100,1,10000));
    }
    public Multiply(Param param){
        int xmin = param.getMinX();
        int tmp= param.getMaxZ()/param.getMinY();
        int xmax = param.getMaxX()<tmp?param.getMaxX():tmp;
        x=(new Random().nextInt(xmax-xmin+1))+xmin;

        int ymin = param.getMinY();
        tmp =param.getMaxZ()-x;
        int ymax = param.getMaxY()<tmp?param.getMaxY():tmp;

        y=(new Random().nextInt(ymax-ymin+1))+ymin;

        z=x*y;
    }
    @Override
    public String toString(){
        return x+"×"+y+"=";
    }
}
