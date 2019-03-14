
public class q2{
  
  public static void main(String[] args){
    int thread_count=Integer.parseInt(args[0]);
    thread_non_blocking.probability=Float.parseFloat(args[1]);
    thread_non_blocking.delay=Integer.parseInt(args[2]);
    thread_non_blocking.number_operations=Integer.parseInt(args[3]);
    
    
    Thread thread_array[]= new Thread[thread_count];
    for(int t=0;t<thread_count;t++){
      thread_array[t]= new Thread(new thread_non_blocking(t));      
    }
    
    long start_time= System.currentTimeMillis();
    for(int t=0;t<thread_count;t++){
      thread_array[t].start();
    }
    
    for(int t=0;t<thread_count;t++){
      try{
        thread_array[t].join();
      }
      catch(InterruptedException ex){
        
      }
    }
    long end_time= System.currentTimeMillis();
    
    System.out.println("time taken by application: "+(end_time-start_time));
    
  }
  
  
  
  
}