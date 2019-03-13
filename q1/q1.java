import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.Random;


public class q1{
  
  public static void main(String[] args){
    int thread_count=args[0];
    float probability=args[1];
    int delay_ms=args[2];
    int total_operations_per_thread=args[3];
    
    
    Thread thread_array[]= new Thread[thread_count];
    for(int t=0;t<thread_count;t++){
      Thread th= new Thread(new processing_thread(),t);
      th.start();
    }
    for(int t=0;t<thread_count;t++){      
      thread_array[t].join();
    }
    
  }
  
  
  
  
}