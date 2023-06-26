package org.example;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Albergo {
    private String descrizione;
    private int id;
    private String name;
    private double price;
    private boolean suite;


    public Albergo(String descrizione, int id, String name, double price, boolean suite) {
        this.descrizione = descrizione;
        this.id = id;
        this.name = name;
        this.price = price;
        this.suite = suite;
    }


    public String getDescrizione() {
        return descrizione;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public boolean isSuite() {
        return suite;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setSuite(boolean suite) {
        this.suite = suite;
    }

    @Override
    public String toString() {
        return "Albergo{" +
                "descrizione='" + descrizione + '\'' +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", suite=" + suite +
                '}';
    }


}
