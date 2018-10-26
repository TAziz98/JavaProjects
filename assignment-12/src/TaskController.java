
public class TaskController {
  
    public Task createTask(){
        Task t = new Task();
        return t;
    }
    
    public void processTask(Task t){
        t.setStatus(Status.RUNNING);
    }
    
    public synchronized int callTask(Task t){        
        int r = 0;
        try {
            Thread.sleep(1000 + (int)(Math.random() * ((5000 - 1000) + 1)));
            r = t.call();   
        }catch (Exception ex) {
            ex.printStackTrace();
        }
        return r;
    }
}
