package controller;

import model.Game;
import model.Player;
import model.civilization.Civilization;
import model.civilization.Trade;
import model.civilization.TradeList;
import model.resource.ResourceType;

import java.util.Vector;

public class TradeController {
    private Game game;

    public TradeController(Game game) {
        this.game = game;
    }

    private boolean checkForTradeTermination(Trade trade){
        if(trade.getRemainedTurn() == 0) return true;
        for(Object o : trade.getFirstOffer().keySet()){
            if(o instanceof TradeList){
                TradeList t = (TradeList) o;
                if(t.equals(TradeList.FOOD)){
                    if(trade.getFirst().getCurrency().getFood() < trade.getFirstOffer().get(t)) return true;
                }else if(t.equals(TradeList.GOLD)) {
                    if (trade.getFirst().getCurrency().getGold() < trade.getFirstOffer().get(t)) return true;
                }else if(t.equals(TradeList.PRODUCTION)) {
                    if (trade.getFirst().getCurrency().getProduct() < trade.getFirstOffer().get(t)) return true;
                }
            }else if(o instanceof ResourceType){
                ResourceType r = (ResourceType) o;
                if(trade.getFirst().getResourceRepository().get(r) < trade.getFirstOffer().get(o)) return true;
            }else{
                throw new IllegalArgumentException("TradeController: checkForTradeTermination: unknown object type");
            }
        }
        for(Object o : trade.getSecondOffer().keySet()){
            if(o instanceof TradeList){
                TradeList t = (TradeList) o;
                if(t.equals(TradeList.FOOD)){
                    if(trade.getSecond().getCurrency().getFood() < trade.getSecondOffer().get(t)) return true;
                }else if(t.equals(TradeList.GOLD)) {
                    if (trade.getSecond().getCurrency().getGold() < trade.getSecondOffer().get(t)) return true;
                }else if(t.equals(TradeList.PRODUCTION)) {
                    if (trade.getSecond().getCurrency().getProduct() < trade.getSecondOffer().get(t)) return true;
                }
            }else if(o instanceof ResourceType){
                ResourceType r = (ResourceType) o;
                if(trade.getSecond().getResourceRepository().get(r) < trade.getSecondOffer().get(o)) return true;
            }else{
                throw new IllegalArgumentException("TradeController: checkForTradeTermination: unknown object type");
            }
        }
        return false;
    }


    private void payUp(Trade trade){
        for(Object o : trade.getFirstOffer().keySet()){
            if(o instanceof TradeList){
                TradeList t = (TradeList) o;
                if(t.equals(TradeList.FOOD)){
                    trade.getFirst().getCurrency().increase(0,0,trade.getFirstOffer().get(o));
                }
                else if(t.equals(TradeList.PRODUCTION)) {
                    trade.getFirst().getCurrency().increase(0,trade.getFirstOffer().get(t),0);
                }
                else if(t.equals(TradeList.GOLD)) {
                    trade.getFirst().getCurrency().increase(trade.getFirstOffer().get(t),0,0);
                }
            }else if(o instanceof ResourceType){
                ResourceType r = (ResourceType) o;
                trade.getFirst().getResourceRepository().replace(r,trade.getFirst().getResourceRepository().get(r) - trade.getFirstOffer().get(o));
            }else{
                throw new IllegalArgumentException("TradeController: payUp: unknown object type");
            }
        }
        for(Object o : trade.getSecondOffer().keySet()) {
            if (o instanceof TradeList) {
                TradeList t = (TradeList) o;
                if (t.equals(TradeList.FOOD)) {
                    trade.getSecond().getCurrency().increase(0, 0, trade.getSecondOffer().get(t));
                } else if (t.equals(TradeList.PRODUCTION)) {
                    trade.getSecond().getCurrency().increase(0, trade.getSecondOffer().get(t), 0);
                } else if (t.equals(TradeList.GOLD)) {
                    trade.getSecond().getCurrency().increase(trade.getSecondOffer().get(t), 0, 0);
                }
            } else if (o instanceof ResourceType) {
                ResourceType r = (ResourceType) o;
                trade.getSecond().getResourceRepository().replace(r, trade.getSecond().getResourceRepository().get(r) - trade.getSecondOffer().get(o));
            } else {
                throw new IllegalArgumentException("TradeController: payUp: unknown object type");
            }
        }
        trade.nextTurn();
    }


    public void actNextTurn(){
        Vector<Trade> removingTrades = new Vector<>();
        Vector<Trade> resumingTrades = new Vector<>();
        for(Player p : game.getPlayers()){
            for(Trade t : p.getCivilization().getTrades()){
                if(checkForTradeTermination(t)){
                    removingTrades.add(t);
                }else{
                    resumingTrades.add(t);
                }
            }
        }
        for(Trade t : removingTrades){
            t.cancel();
        }
        for(Trade t : resumingTrades){
            payUp(t);
        }
    }


}
