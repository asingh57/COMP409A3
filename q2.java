
public class q2{
  
  public static void main(String[] args){
    //args input
    int thread_count=Integer.parseInt(args[0]);
    thread_non_blocking.probability=Float.parseFloat(args[1]);
    thread_non_blocking.delay=Integer.parseInt(args[2]);
    thread_non_blocking.number_operations=Integer.parseInt(args[3]);
    
    
    Thread thread_array[]= new Thread[thread_count];
    for(int t=0;t<thread_count;t++){
      thread_array[t]= new Thread(new thread_non_blocking(t));     //create threads and give them their ids
      
    }
    
    long start_time= System.currentTimeMillis();//record start time
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
    long end_time= System.currentTimeMillis();//record end time
    
    System.out.println((end_time-start_time));//print time taken
    
  }
  
  
  
  
}