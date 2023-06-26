package org.example;

import java.util.*;

import com.google.gson.Gson;

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

    public String toHtmlAll(ArrayList<Albergo> e) {
        StringBuilder htmlBuilder = new StringBuilder();
        htmlBuilder.append("<html><head><title>Car List</title></head><body>");
        htmlBuilder.append("<table style='border:1px solid black' border-spacing: 40px><tr><th>ID</th><th>Nome</th><th>Price</th><th>Descrizione</th><th>Suite</th></tr>");

        for (Albergo car : e) {
            htmlBuilder.append("<tr>");
            htmlBuilder.append("<td>").append(car.getId()).append("</td>");
            htmlBuilder.append("<td>").append(car.getName()).append("</td>");
            htmlBuilder.append("<td>").append(car.getPrice()).append("</td>");
            htmlBuilder.append("<td>").append(car.getDescrizione()).append("</td>");
            htmlBuilder.append("<td>").append(car.isSuite()).append("</td>");
            htmlBuilder.append("</tr>");
        }

        htmlBuilder.append("</table>");
        htmlBuilder.append("</body></html>");

        return htmlBuilder.toString();
    }
}
