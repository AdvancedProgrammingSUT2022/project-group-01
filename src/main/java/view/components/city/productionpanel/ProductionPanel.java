package view.components.city.productionpanel;

import com.jfoenix.controls.JFXTabPane;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import model.building.BuildingType;
import model.civilization.city.City;
import model.civilization.production.Producible;
import model.unit.UnitType;

import java.util.Vector;

public class ProductionPanel extends JFXTabPane {
    private final Tab productionTab = new Tab();
    private final Tab purchaseTab = new Tab();
    private final ScrollPane productionPane = new ScrollPane();
    private final ScrollPane purchasePane = new ScrollPane();
    private final City city;
    private final int width = 300;

    public ProductionPanel(City city) {
        this.city = city;
        init();
    }

    public void init() {
        this.setMinWidth(width);
        this.setMaxWidth(width);
        this.setMinHeight(300);
        this.setMaxHeight(300);
        this.setStyle("-fx-background-color: blue");
        productionPane.setMinHeight(300);
        purchasePane.setMinHeight(300);
        initItems();
    }

    public void initItems() {
        Vector<Producible> allItems = city.getProductionInventory().getAllProductions();
        Vector<Producible> items = city.getProductionInventory().getAvailableProductions();
        Vector<UnitType> allUnits = new Vector<>();
        Vector<UnitType> units = new Vector<>();
        Vector<BuildingType> allBuildings = new Vector<>();
        Vector<BuildingType> buildings = new Vector<>();
        for (Producible producible : allItems) {
            if (producible instanceof UnitType)
                allUnits.add((UnitType) producible);
            else if (!city.getBuildingInventory().hasBuilding((BuildingType) producible))
                allBuildings.add((BuildingType) producible);
        }
        for (Producible producible : items) {
            if (producible instanceof UnitType)
                units.add((UnitType) producible);
            else if(!city.getBuildingInventory().hasBuilding((BuildingType)producible))
                buildings.add((BuildingType) producible);
        }
        initProductionTab(units, buildings);
        initPurchaseTab(allUnits, allBuildings);
    }

    private void initProductionTab(Vector<UnitType> units, Vector<BuildingType> buildings) {
        productionTab.setContent(productionPane);
        productionTab.setText("Production");
        productionPane.setContent(newPaneContent(units, buildings, true));
        productionPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        productionPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        productionPane.fitToHeightProperty();
        this.getTabs().add(productionTab);
    }

    private void initPurchaseTab(Vector<UnitType> units, Vector<BuildingType> buildings) {
        purchaseTab.setContent(purchasePane);
        purchaseTab.setText("Purchase");
        purchasePane.setContent(newPaneContent(units, buildings, false));
        purchasePane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        purchasePane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        productionPane.fitToHeightProperty();
        this.getTabs().add(purchaseTab);
    }

    private HBox newTitleBox(String text) {
        HBox out = new HBox();
        out.setMinWidth(width);
        out.setMaxWidth(width);
        out.setMinHeight(15);
        out.setMaxHeight(15);
        out.setStyle("-fx-background-color: #000c58");
        Label label = new Label(text);
        label.setStyle("-fx-text-fill: #ffeea0");
        Font font = new Font("Times New Roman", 15);
        label.setFont(font);
        out.getChildren().add(label);
        return out;
    }

    private VBox newPaneContent(Vector<UnitType> units, Vector<BuildingType> buildings, boolean isProducing) {
        VBox vBox = new VBox();
        vBox.setMaxWidth(width);
        vBox.setMinWidth(width);
        vBox.setSpacing(2);
        vBox.getChildren().add(newTitleBox("Units"));
        for (UnitType unitType : units) {
            String cost = (isProducing ? (city.getCurrency().getProduct() > 0 ? String.valueOf(Math.ceil(unitType.getCost(city) / city.getCurrency().getProduct())) : ("Infinity")) : String.valueOf(unitType.getCost(city)));
            vBox.getChildren().add(new ProductionRow(unitType, isProducing, cost, city));
        }
        vBox.getChildren().add(newTitleBox("Buildings"));
        for (BuildingType building : buildings) {
            String cost = (isProducing ? (city.getCurrency().getProduct() > 0 ? String.valueOf(Math.ceil(building.getCost(city) / city.getCurrency().getProduct())) : ("Infinity")) : String.valueOf(building.getCost(city)));
            vBox.getChildren().add(new ProductionRow(building, isProducing, cost, city));
        }
        return vBox;
    }
}
