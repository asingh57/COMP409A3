public class thread_non_blocking extends processing_thread{
  
  
  thread_non_blocking(int thread_id){   
    this.thread_id=thread_id;
  }
  
  @Override
  boolean block_or_wait(int priority){    
    return true;
  }
  @Override
  void release(int priority){    
  }
  
}