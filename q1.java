
public class q1{
  
  public static void main(String[] args){
    //accept args
    int thread_count=Integer.parseInt(args[0]);
    thread_blocking.probability=Float.parseFloat(args[1]);
    thread_blocking.delay=Integer.parseInt(args[2]);
    thread_blocking.number_operations=Integer.parseInt(args[3]);
    
    
    Thread thread_array[]= new Thread[thread_count];
    for(int t=0;t<thread_count;t++){
      thread_array[t]= new Thread(new thread_blocking(t));//create threads of blocking type 
    }
    
    long start_time= System.currentTimeMillis();//record start time
    
    for(int t=0;t<thread_count;t++){//start threads
      thread_array[t].start();
    }
    
    for(int t=0;t<thread_count;t++){//join threads
      try{
        thread_array[t].join();
      }
      catch(InterruptedException ex){
        
      }
    }
    
    long end_time= System.currentTimeMillis();
    
    System.out.println((end_time-start_time));//print total time
    
    
  }
  
  
  
  
}