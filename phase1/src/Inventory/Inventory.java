package Inventory;

import java.io.*;
import java.util.ArrayList;
import Main.GateWay;

/**
 * [Use Case Class]
 * inventory: present existed items. Edit(add, delete, edit) existed items. Get item through item name.
 * Read and write Inventory.txt (temporary, may move to another class).
 */
public class Inventory {
    /**
     * all existed items in user's lending list.
     */
    //ArrayList<Item> lendingList;

    /**
     * [Constructor]
     * get lendingList from file (read file will move to another class).
     */
    //public Inventory() {
    //lendingList = new ArrayList<Item>();
    //}

    /**
     * getter for the lending list
     * @return lendingList
     */
    public ArrayList<Item> getLendingList() {
        return GateWay.inventory;
    }

    /**
     * get a list of items that is not in the trade
     * @return available item list
     */
    ArrayList<Item> getAvailableList() {
        ArrayList<Item> result = new ArrayList<Item>();
        for (Item item : GateWay.inventory) {
            if (!item.getIsInTrade()) {
                result.add(item);
            }
        }
        return result;
    }


    /**
     * add the item into the inventory
     * @param item the item added
     */
    public void addItem(Item item) throws IOException {
        GateWay.inventory.add(item);
    }


    /**
     *
     * @param item the deleted item
     * @throws IOException when the item is not found in the inventory
     */
    public void deleteItem(Item item) throws IOException {
        if (GateWay.inventory.contains(item)) {
            GateWay.inventory.remove(item);
        } else {
            throw new IOException("the item is not in the inventory");
        }
    }

    /**
     *
     * @param item the item you want to edit
     * @param target the type that it wants to edit
     * @param newContent new content
     * @throws IOException when the the new edition can not be updated in the file.
     */
    public void editItem(Item item, String target, String newContent) throws IOException {
        if (target.equals("description")){
            item.setDescription(newContent);
        }else{
            item.setOwner(newContent);
        }
    }


    /**
     * get item through its name
     * @param name the item name you want to get
     * @return item
     */
    public Item getItem(String name){
        for (Item item: GateWay.inventory){
            if (item.getName().equals(name)){
                return item;
            }
        }
        return null;
    }
}