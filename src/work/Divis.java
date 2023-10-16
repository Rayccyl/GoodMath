package work;

public class Divis extends Calc{
    public Divis(){
        this(new Param(1,100,1,100,1,10000));
    }
    public Divis(Param p){
        Param pMulti = new Param(p.getMinX(), p.getMaxX(),p.getMinY(),p.getMaxY(),p.getMaxZ(),p.getMaxZ());
        Multiply multi = new Multiply(pMulti);
        x=multi.z;y=multi.y;z=multi.x;
    }
    @Override
    public String toString(){
        return x+"÷"+y+"=";
    }
}
