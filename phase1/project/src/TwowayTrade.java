import java.util.ArrayList;
public class TwowayTrade extends Trade {
    private final Trader trader1;
    private final Trader trader2;
    private final Item item1to2;
    private final Item item2to1;

    /**
     *
     * @param trader1 Trader who participates in the trade
     * @param trader2 Trader who participates in the trade
     * @param item1 the item that trader1 give to trader2
     * @param item2 the item that trader2 give to trader1
     * @param status whether the trade is unconfirmed, confirmed or completed
     * @param duration whether the trade is temporary or permanent
     */
    public TwowayTrade(Trader trader1, Trader trader2, Item item1, Item item2, String status, String duration){
        super(status, duration);
        this.trader1 = trader1;
        this.trader2 = trader2;
        item1to2 = item1;
        item2to1 = item2;
    }


    /**
     * getter for two traders of the trade
     * @return the two traders
     */
    public ArrayList<Trader> getTraders() {
        ArrayList<Trader> list = new ArrayList<Trader>();
        list.add(trader1);
        list.add(trader2);
        return list;
    }

    /**
     * getter for item that trader1 give to trader2
     * @return item1to2
     */
    public Item getItem1to2() {
        return item1to2;
    }

    /**
     * getter for item that trader2 give to trader1
     * @return item2to1
     */
    public Item getItem2to1(){
        return item2to1;
    }

    /**
     * remove items from wish to trade list
     */
    @Override
    public void makeTrade() {

    }

    /**
     * remove the item from the traded list that reach 1 month(?)
     */
    @Override
    public void returnObject() {

    }
}