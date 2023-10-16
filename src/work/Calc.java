package work;
public abstract class Calc {
    protected int x;
    protected int y;
    protected int z;
    protected int ans;
    public boolean check(){
        return ans==z;
    };
    public abstract String toString();
    public void copy(Calc other){
        other.x = x;
        other.y = y;
        other.z = z;
        other.ans = ans;
    }
}
