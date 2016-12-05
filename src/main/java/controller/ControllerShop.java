package controller;


import java.io.IOException;
import java.util.Iterator;

import fpt.com.SerializableStrategy;
import javafx.event.ActionEvent;
import model.*;
import generator.IDGenerator;
import view.*;
import serialization.*;


public class ControllerShop {

    ModelShop model;
    IDGenerator idGeneartor;
    SerializableStrategy strategie;

    public void link(ModelShop model, ViewShop view) {

        SerializableStrategy bin = new BinaryStrategy();
        SerializableStrategy xml = new XMLStrategy();
        SerializableStrategy xstream = new XStreamStrategy();

        strategie = bin;

        idGeneartor = new IDGenerator();
        this.model = model;

        view.setList(model);

        view.addEventHandler((ActionEvent e) -> {
            if (e.toString().contains("Add")) {
                if (!view.getNameInput().isEmpty() && !view.getPriceInput().isEmpty() && !view.getCountInput().isEmpty()) {

                    Product p = new Product();
                    p.setName(view.getNameInput());
                    p.setPrice(Double.parseDouble(view.getPriceInput()));
                    p.setQuantity(Integer.parseInt(view.getCountInput()));

                    try { p.setId(idGeneartor.generateId()); } catch (Exception e1) { e1.printStackTrace(); }

                    model.add(p);
                }

            }


             // DELETE LOAD SAVE !
            if (e.toString().contains("Delete")) {
                if (view.getSelectedIndex() != -1){ model.remove(view.getSelectedIndex()); }
            }
            if (e.toString().contains("Laden")) { load(); }

            if (e.toString().contains("Speichern")) {
                try { save(); } catch (Exception e1) { e1.printStackTrace(); }
            }

            //CHOICEBOX ÄNDERN !
            if (e.toString().contains("ChoiceBox")) {
                int auswahl = view.getChoice();
                switch (auswahl) {
                    case 0:
                        strategie = bin;
                        break;
                    case 1:
                        strategie = xml;
                        break;
                    case 2:
                        strategie = xstream;
                        break;
                }
            }
        });

    }

    public void load() {

        model.clear();
        idGeneartor.clear();

        fpt.com.Product product;
        try {
            while ((product = strategie.readObject()) != null) {
            	idGeneartor.addId(product.getId());
                model.add((Product) product);
            }
        } catch (IOException e1) { e1.printStackTrace(); }

        try {
            strategie.close();
        } catch (IOException e) { e.printStackTrace(); }

    }

    public void save() throws Exception {

        Iterator<Product> iteratorp = model.iterator();

        if (model.size() < 5)
            throw new Exception("Die Warenliste sollte mindestens 5 Objekte enthalten");
        while (iteratorp.hasNext()) {
            try {
                strategie.writeObject(iteratorp.next());
            } catch (IOException e) { e.printStackTrace(); }
        }
        try {
            strategie.close();
        } catch (IOException e) { e.printStackTrace(); }
    }

}
