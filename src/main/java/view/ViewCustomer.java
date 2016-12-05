package view;

import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.*;
import javafx.util.converter.IntegerStringConverter;
import model.Product;
import model.Order;


/**
 * Created by Surya on 18.11.2016.
 */


public class ViewCustomer extends BorderPane {

    private TableView<Product> table = new TableView<Product>();
    TableColumn<Product, Integer> buyColum;
    TableColumn<Product, String> nameColum;
    private Button button = new Button("Buy");
    private ListView<Order> liste = new ListView<Order>();

    public ViewCustomer() {
        nameColum = new TableColumn<Product, String>("Name");
        nameColum.setMinWidth(100);
        
        table.setEditable(true);

        TableColumn<Product, Number> priceColum = new TableColumn<Product, Number>("Price"); 
        priceColum.setMinWidth(80);


        TableColumn<Product, Number> quantityColum = new TableColumn<Product, Number>("Quantity");
        quantityColum.setMinWidth(80);
       
        //hier wird, die Liste in der Customer übersicht aktualsiert

        buyColum = new TableColumn<Product, Integer>("Buy Count");
        buyColum.setMinWidth(80);


        //hier wird, die Liste in der Customer übersicht aktualsiert




        table.getColumns().addAll(nameColum, priceColum, quantityColum, buyColum);

        VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 10, 10, 10));
        vbox.getChildren().addAll(table, button);
        VBox.setVgrow(table, Priority.ALWAYS);

        setRight(vbox);

        liste.setPrefWidth(400);
        setCenter(liste);

    }

    public void setList(ObservableList<Product> liste) {
        table.setItems(liste);
    }

    public void addEventHandler(
            EventHandler<CellEditEvent<Product, String>> eventHandler) {

        //nameColum.setOnEditCommit(eventHandler);

    }


}
