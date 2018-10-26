import java.util.Random;
import java.util.concurrent.Callable;


public class Task implements Callable<Integer>{
    private String name;
    private Status status;
    private static int num = 0;
    private Random random = new Random();
    private Integer result = 0;

    public Task() {
        name = "Task " + ++num;
        this.status = Status.PENDING;
    }
    
    @Override
    public Integer call() throws Exception {
        if(random.nextBoolean()){
            result = random.nextInt();
            status = Status.ACCOMPLISHED;
            return result;
        }
        status = Status.FAILED;
        return result;
    }

    public String getName() {
        return name;
    }

    public Status getStatus() {
        return status;
    }

    public Integer getResult() {
        return result;
    }

    public static int getNum() {
        return num;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public void setStatus(Status status) {
        this.status = status;
    } 
}
