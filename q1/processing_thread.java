public class processing_thread implements Runnable{
  static volatile Bin<T>[] pqueue= new Bin[10];
  Random rand = new Random();
  static int number_operations;
  static int delay;
  static int probability;
  
  processing_thread(){
    
  }
  
  void add(){
    
  }
  
  void deleteMin(){
    
  }
  
  public void run(){
    Float move_prob;
    for(int i=0;i<number_operations;i++){
      
      move_prob = rand.nextFloat();
      (move_prob<probability) ? add() : deleteMin();
      
      
      Thread.sleep(delay);      
    }
    
    
  }
}