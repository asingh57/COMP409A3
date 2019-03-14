import java.util.Queue; 
import java.util.LinkedList; 
import java.util.Random;
import java.util.NoSuchElementException;

public class processing_thread implements Runnable{
  Random rand = new Random();
  static int number_operations;
  static int delay;
  static float probability;
  static int queue_length=10;
  static int max_past_reference_count=10;
  static Queue<data_object> []q = new Queue[queue_length];
  static {
    for(int i=0;i<queue_length;i++){
      q[i]= new LinkedList<data_object>();
    }
  }
  
  //********** pointers for circular array
  int cache_item_count=0;
  int cache_start_position=0;
  int cache_end_position=-1;  
  data_object local_cache[]= new data_object[max_past_reference_count];//circular array
  //**********
  
  processing_thread(){
    
  }
  
  void add(){
    int priority= rand.nextInt(queue_length);
    String val = Thread.currentThread().getName()
                  +((char)(rand.nextInt(26)+'a'));    
                  
    data_object obj;
    
    if(cache_item_count>0){
      cache_item_count-=1;
      obj=local_cache[cache_start_position];
      obj.set_object(val);
      cache_start_position=cache_start_position=(cache_start_position+1)%max_past_reference_count;//move start of list
    }
    else{
      obj=new data_object(val);
    }
    
    q[priority].add(obj);
    
    System.out.println(
      System.currentTimeMillis()
      +" "
      +Thread.currentThread().getName()
      +" add"
      +val
      +" "
      +priority
    );
    
  }
  
  void deleteMin(){
    data_object ret;
    for(int priority = 0; priority < queue_length; priority++){
      try{
        ret=q[priority].remove();
        if(ret!=null){
          if(cache_item_count==max_past_reference_count){//overwrite an old value
          local_cache[cache_start_position]=ret;//replace the earliest value
          cache_end_position=cache_start_position;//move end of list
          cache_start_position=(cache_start_position+1)%max_past_reference_count;//move start of list
          }
          else{
            cache_end_position=(cache_end_position+1)%max_past_reference_count;//position where item is placed
            local_cache[cache_end_position]=ret;//place item
            cache_item_count+=1;//increase count
          }
          System.out.println(
            System.currentTimeMillis()
            +" "
            +Thread.currentThread().getName()
            +" del"
            +ret.value
            +" "
            +priority
          );
          return;
        }
      }
      catch(NoSuchElementException err){
        
      }     
    }
    System.out.println(
      System.currentTimeMillis()
      +" "
      +Thread.currentThread().getName()
      +" del *"
    );
    
  }
  
  public void run(){
    Float move_prob;
    for(int i=0;i<number_operations;i++){      
      move_prob = rand.nextFloat();
      if(move_prob<probability){
        add();
      }
      else{
        deleteMin();
      }
      
      try{
        Thread.sleep(delay);      
      }
      catch(InterruptedException err){
        
      }
      
    }
    
  }
}