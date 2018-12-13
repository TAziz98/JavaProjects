package Assignment9;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CareTaker {

	  private List<Memento> mementoList = new ArrayList<Memento>();

	   public void add(Memento state){
	      mementoList.add(state);
	     serialize(state);
	   }
	   
	   public void serialize(Memento state) {
		   try {
		         FileOutputStream fileOut =
		         new FileOutputStream("C:/Users/User/eclipse-workspace/StateFile.txt");
		         ObjectOutputStream out = new ObjectOutputStream(fileOut);
		         out.writeObject(String.valueOf((Date)state.getState()));
		         out.close();
		         fileOut.close();
		      } catch (IOException i) {
		         i.printStackTrace();
		      }
		   }
	
   public Memento get(int index){
	      return mementoList.get(index);
	//	return  (Memento)desearilize().get(index);
	   }
	  
	//Trash
   /*   public ArrayList<Memento> desearilize() {
   ArrayList<Memento> mementos = new ArrayList<Memento>();
   Memento memento = null;
   try {
       ObjectInputStream in = new ObjectInputStream(new FileInputStream("C:/Users/User/eclipse-workspace/State.txt"));
       Object object = in.readObject();
       while (object != null) {
           mementos.add((Memento) object);
           object = in.readObject();
       }
       in.close();
   } catch (IOException i) {
       return null;
   } catch (ClassNotFoundException c) {
       System.out.println("Memento class not found");
       return null;
   }
   return mementos;
}
*/
}
