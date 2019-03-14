
public class q1{
  
  public static void main(String[] args){
    int thread_count=Integer.parseInt(args[0]);
    thread_blocking.probability=Float.parseFloat(args[1]);
    thread_blocking.delay=Integer.parseInt(args[2]);
    thread_blocking.number_operations=Integer.parseInt(args[3]);
    
    
    Thread thread_array[]= new Thread[thread_count];
    for(int t=0;t<thread_count;t++){
      thread_array[t]= new Thread(new thread_blocking(t));      
    }
    
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
    
  }
  
  
  
  
}