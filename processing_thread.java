import java.util.Queue; 
import java.util.LinkedList; 
import java.util.Random;
import java.util.NoSuchElementException;

public class processing_thread implements Runnable{
  Random rand = new Random();
  static int number_operations;
  static int delay;
  static float probability;
  static int priority_count=10;
  static int max_past_reference_count=10;
  volatile static Queue<data_object> []shared_bucked = new Queue[priority_count];
  static {
    for(int i=0;i<priority_count;i++){
      shared_bucked[i]= new LinkedList<data_object>();
    }
  }
  
  //********** pointers for circular array
  int cache_item_count=0;
  int cache_start_position=0;
  int cache_end_position=-1;  
  data_object local_cache[]= new data_object[max_past_reference_count];//circular array
  //**********
  
  int thread_id=-1;
  
  
  //*******
  //to be implemented by blocking/non blocking extensions of this class
  boolean block_or_wait(int priority){    
    return true;
  }
  void release(int priority){    
  }
  //*******
  
  
  
  void add(){//add item to list
    
    int priority= rand.nextInt(priority_count);
    String val = Integer.toString(thread_id)
                  +((char)(rand.nextInt(26)+'A'));    
                  
    data_object obj;
    
    //add item to circular array
    if(cache_item_count>0){//if cached thread's removed cache has something, reassign it
      cache_item_count-=1;
      obj=local_cache[cache_start_position];
      obj.set_object(val);
      cache_start_position=cache_start_position=(cache_start_position+1)%max_past_reference_count;//move start of list
    }
    else{
      obj=new data_object(val);
    }
    
    if(!block_or_wait(priority)){//acquire exclusive access
          return;
    }
    shared_bucked[priority].add(obj);
    ///*
    System.out.println(
      System.currentTimeMillis()
      +" "
      +thread_id
      +" add "
      +val
      +" "
      +priority
    );
    //*/
    release(priority);//release 
    
  }
  
  void deleteMin(){
    data_object ret;
    for(int priority = 0; priority < priority_count; priority++){
      try{
        if(!block_or_wait(priority)){
          break;
        }
        ret=shared_bucked[priority].remove();
        release(priority);
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
          ///*
          System.out.println(
            System.currentTimeMillis()
            +" "
            +thread_id
            +" del "
            +ret.value
            +" "
            +priority
          );
          //*/
          return;
        }
      }
      catch(NoSuchElementException ex){
        release(priority);
      }

      
    }
    ///*
    System.out.println(
      System.currentTimeMillis()
      +" "
      +thread_id
      +" del *"
    );
    //*/
    
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