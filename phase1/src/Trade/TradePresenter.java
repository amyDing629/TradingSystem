package Trade;

import User.User;

public class TradePresenter {
    User currUser;
    Trade trade;


    public TradePresenter(User currUser, Trade trade){
        this.currUser = currUser;
        this.trade = trade;
    }

    public void presentTradeUIInfo(){
        System.out.println("===========================");
        System.out.println(currUser.getUsername());
        System.out.println("tradeId: " + trade.getId());
        System.out.println("tradeUsers: " + trade.getUsers());
        System.out.println("tradeStatus: " + trade.getStatus());
        System.out.println("tradeItem: " + trade.getItemList());
        System.out.println("first meeting: " + trade.getMeeting());
        if (trade.getMeeting()!= null){
            System.out.println("first meeting status: " + trade.getMeeting().getStatus());
            System.out.println("id to confirm: " + trade.getMeeting().getConfirmedStatusFull());
            System.out.println("id to edition: " + trade.getMeeting().getIdToEditor());
        }
        System.out.println("second meeting: " + trade.getSecondMeeting());
        if (trade.getSecondMeeting()!=null){
            System.out.println("second meeting status: "+trade.getSecondMeeting().getStatus());
        }
        System.out.println("============================");
    }
}
