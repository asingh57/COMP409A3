import java.util.concurrent.atomic.AtomicInteger;

public class thread_non_blocking extends processing_thread{
  
  volatile static AtomicInteger[] priority_holder= new AtomicInteger[priority_count];
  static{
    for(int priority=0; priority<priority_count; priority++){
      priority_holder[priority]=new AtomicInteger(-1);
    }
  }
  
  thread_non_blocking(int thread_id){
    this.thread_id=thread_id;
  }
  
  @Override
  boolean block_or_wait(int priority){
    
    while(true){
      if(priority_holder[priority].compareAndSet(-1,thread_id)){
        break;
      }
      try{
        Thread.sleep(10);
      }
      catch(InterruptedException ex){
        
      }
      
    }
    
    return true;
  }
  @Override
  void release(int priority){  
    priority_holder[priority].set(-1);  
  }
  
}