package org.example;

import java.util.*;

import com.google.gson.Gson;
import org.example.Albergo;

import java.util.ArrayList;


public class AlbergoManager {
    private static AlbergoManager instance;
    private ArrayList<Albergo> AlberghiList = new ArrayList();
    private ArrayList AlberghiListSorted = new ArrayList();

    private AlbergoManager() {
        this.AlberghiList.add(new Albergo("Albergo di lusso con vista mare",1,"Hotel Splendido",200,true));
        this.AlberghiList.add(new Albergo("Albergo economico nel centro citta'",2,"Hotel Economico",50,false));
        this.AlberghiList.add(new Albergo("Resort di lusso con piscina",3,"Resort Paradiso",300,true));
        this.AlberghiList.add(new Albergo("Albergo accogliente nelle colline",4,"Hotel Collinare",150,false));
        this.AlberghiList.add(new Albergo("Albergo di lusso in centro storico",5,"Hotel Storico",250,true));
    }

    public static AlbergoManager getInstance() {
        if (instance == null) {
            instance = new AlbergoManager();
        }
        return instance;
    }

    public String toJson(ArrayList a) {
        Gson gson = new Gson();
        return gson.toJson(a);
    }



    public String findMoreExpensiveSuite() {
        Albergo mostExpensiveSuite = null;
        double maxPrice = AlberghiList.get(0).getPrice();

        for (Albergo alb : AlberghiList) {
            if (alb.getPrice() > maxPrice) {
                maxPrice = alb.getPrice();
                mostExpensiveSuite = alb;
            }
        }
        Gson gson = new Gson();


        return gson.toJson(mostExpensiveSuite);
    }

    public void sortAlbByName() {

        AlberghiListSorted= (ArrayList)AlberghiList.clone();
        Collections.sort(AlberghiListSorted, Comparator.comparing(Albergo::getName));
    }

    public ArrayList<Albergo> getAlberghiList() {
        return AlberghiList;
    }

    public ArrayList getAlberghiListSorted() {
        return AlberghiListSorted;
    }
}
