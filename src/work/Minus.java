package work;

public class Minus extends Calc{
    public Minus(){
        this(new Param(1,1000,1,1000,1,10000));
    }
    public Minus(Param p){
        Param pAdd = new Param(p.getMinX(), p.getMaxX(),p.getMinY(),p.getMaxY(),p.getMinZ(),p.getMaxZ());
        Add add = new Add(pAdd);
        x=add.z;y=add.y;z=add.x;
    }
    @Override
    public String toString(){
        return x+"-"+y+"=";
    }
}
