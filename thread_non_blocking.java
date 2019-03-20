import java.util.concurrent.atomic.AtomicInteger;

public class thread_non_blocking extends processing_thread{
  
  volatile static AtomicInteger[] priority_holder= new AtomicInteger[priority_count];//use atomic integer array to protect individual priorities
  static{
    for(int priority=0; priority<priority_count; priority++){
      priority_holder[priority]=new AtomicInteger(-1); //-1 indicates access to priority is available
    }
  }
  
  thread_non_blocking(int thread_id){//init thread
    this.thread_id=thread_id;
  }
  
  @Override
  boolean block_or_wait(int priority){
    
    while(true){
      if(priority_holder[priority].compareAndSet(-1,thread_id)){//use cas in a loop to try acquire
        break;
      }
      try{
        Thread.sleep(1);
      }
      catch(InterruptedException ex){
        return false;
      }
      
    }
    
    return true;
  }
  @Override
  void release(int priority){ //set back to -1 
    priority_holder[priority].set(-1);  
  }
  
}