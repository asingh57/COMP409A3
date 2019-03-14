
public class q1{
  
  public static void main(String[] args){
    int thread_count=Integer.parseInt(args[0]);
    processing_thread.probability=Float.parseFloat(args[1]);
    processing_thread.delay=Integer.parseInt(args[2]);
    processing_thread.number_operations=Integer.parseInt(args[3]);
    
    
    Thread thread_array[]= new Thread[thread_count];
    for(int t=0;t<thread_count;t++){
      thread_array[t]= new Thread(new processing_thread());      
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