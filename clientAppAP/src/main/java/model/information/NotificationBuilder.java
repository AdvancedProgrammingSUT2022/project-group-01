package model.information;

import model.Game;
import model.Notification;
import model.civilization.Civilization;
import model.tile.Tile;
import model.unit.Unit;
import org.mockito.internal.matchers.Not;

public class NotificationBuilder {
    static Game game;
    public static void setGame(Game game){
        NotificationBuilder.game = game;
    }
// TODO call aval har turn
    public static void buildNotifications(Civilization civilization){
        setTurnsForNotifications(civilization);
        zeroGold(civilization);
        zeroFood(civilization);
        zeroProduction(civilization);
        nearbyEnemy(civilization);
        dumbDumb(civilization);
        setTurnsForNotifications(civilization);
    }

    private static void zeroGold(Civilization civilization){
        if(civilization.getCurrency().getGold() == 0) {
            Notification notification = new Notification(Notification.NotificationTexts.ZERO_GOLD.getText(),game.getTurn());
            civilization.getNotificationInbox().addNotification(notification);
        }
    }
    private static void nearbyEnemy(Civilization civilization){
        for(Tile tile : civilization.visibleTiles()){
            if((tile.getArmedUnit() != null) && (!tile.getArmedUnit().getOwnerCivilization().equals(civilization))){
                Notification notification = new Notification(Notification.NotificationTexts.NEARBY_ENEMY.getText(),game.getTurn());
                civilization.getNotificationInbox().addNotification(notification);
            }
        }
    }

    private static void zeroFood(Civilization civilization){
        if(civilization.getCurrency().getFood() == 0) {
            Notification notification = new Notification(Notification.NotificationTexts.ZERO_FOOD.getText(),game.getTurn());
            civilization.getNotificationInbox().addNotification(notification);
        }
    }

    private static void zeroProduction(Civilization civilization){
        if(civilization.getCurrency().getProduct() == 0) {
            Notification notification = new Notification(Notification.NotificationTexts.ZERO_PRODUCTION.getText(),game.getTurn());
            civilization.getNotificationInbox().addNotification(notification);
        }
    }

    private static void dumbDumb(Civilization civilization){
        if(game.getTurn() == 666){
            Notification notification = new Notification(Notification.NotificationTexts.DUMB.getText(), game.getTurn());
            civilization.getNotificationInbox().addNotification(notification);
        }
    }

    private static void setTurnsForNotifications(Civilization civilization){
        civilization.getNotificationInbox().setUnsetTurns(game.getTurn());
    }
}
