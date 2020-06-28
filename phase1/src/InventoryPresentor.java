import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InventoryPresentor {
    Inventory inventory;
    public InventoryPresentor(Inventory inventory){
        this.inventory = inventory;
    }

    public void run(){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            String line = br.readLine();
            if (line.equals("see inventory")){
                System.out.println(inventory.getUnfrozenList());
            }

        } catch (IOException e) {
            System.out.println("your input is not correct, please try again");
        }
    }


}
