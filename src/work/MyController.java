package work;

import java.util.Timer;
import java.util.Random;
import java.util.TimerTask;
import static work.Choose.difficulty;
import static work.Choose.ops;

public class MyController {
    public Calc[] makers;
    public static int numOfQuestions = 0;
    private int currentNum;
    protected String question;
    protected int answer;
    protected int theNum;
    protected Calc theCalc;
    protected int seconds;
    protected Timer timer;
    /*MyController(int numOfQuestions){
        this.numOfQuestions = numOfQuestions;
        makers = new Calc[numOfQuestions];
        Random rand = new Random();
        int which=0;
        try {
            for(int i = 0; i < numOfQuestions;i++){
                which=rand.nextInt(difficulty.length)+1;
                switch (which){
                    case 1:makers[i]=new Add(difficulty[0]); break;
                    case 2:makers[i]=new Minus(difficulty[1]); break;
                    case 3:makers[i]=new Multiply(difficulty[2]); break;
                    case 4:makers[i]=new Divis(difficulty[3]); break;
                }
            }
        }catch (Exception e){
            System.err.println("difficulty数组访问出错");
        }
        question=makers[0].toString();
        currentNum=1;
        timer();
    }*/
    MyController (int numOfQuestions, boolean[] ops){
        this.numOfQuestions=numOfQuestions;
        makers=new Calc[numOfQuestions];
        Random rand = new Random();
        int chose=0;
        if(ops!=null){
            for(boolean is:ops){
                if(is==true)chose++;
            }
        }
        if(chose==0){
            //TODO：Exception:设置错误
            ops=new boolean[4];
            for(int i=0;i<4;i++){
                ops[i]=true;
            }
        }
        int which=0;
        for(int i=0;i<numOfQuestions;i++){
            do{
            which=rand.nextInt(4)+1;
            } while (!ops[which-1]);

            switch (which){
                case 1:makers[i]=new Add(difficulty[0]); break;
                case 2:makers[i]=new Minus(difficulty[1]); break;
                case 3:makers[i]=new Multiply(difficulty[2]); break;
                case 4:makers[i]=new Divis(difficulty[3]); break;
            }
        }
        question=makers[0].toString();
        currentNum=1;
        timer();
    }
    public void timer(){
        timer = new Timer();
        TimerTask timerTask=new TimerTask(){
            @Override
            public void run() {
                seconds++;
            }

        };
        timer.schedule(timerTask,0l,1l);
    }
    public boolean nextQuestion(){
        if(currentNum>=numOfQuestions){
            timer.cancel();
            return false;
        }
        theCalc =makers[currentNum];
        currentNum++;
        question= theCalc.toString();
        return true;
    }
    public void firstQuestion(){
        theCalc =makers[0];
        question= theCalc.toString();

    }
    public String toString(){
        return "已做"+(currentNum-1)+"题，其中对"+ theNum +"题，共"+numOfQuestions+"题";
    }
    public boolean check(MyController myController){
        return myController.theCalc.check();
    }
}
