import java.util.concurrent.*; 

public class thread_blocking extends processing_thread{
  static Semaphore[] sem = new Semaphore[priority_count];
  static{
    for(int i=0;i<priority_count;i++){
      sem[i]=new Semaphore(1);
    }
  }
  
  thread_blocking(int thread_id){   
    this.thread_id=thread_id;
  }
  
  @Override
  boolean block_or_wait(int priority){
    try{
      sem[priority].acquire();
    }
    catch(InterruptedException ex){
      return false;
    }
    return true;
  }
  @Override
  void release(int priority){ 
    sem[priority].release();  
  }
  
  
}