import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;

import com.google.gson.GsonBuilder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import com.google.gson.Gson;

public class Main {
    public static void main(String[] args) {
        Metro metro = new Metro();
        metro.stations = new HashMap<>();
        metro.lines = new ArrayList<>();
        ArrayList<Line> lineArrayList;
        HashMap<Integer, ArrayList<String>> stationsMap;
        Gson gson;
        Document doc = null;
        try {
            doc = Jsoup.connect("https://www.moscowmap.ru/metro.html#lines").maxBodySize(0).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        assert doc != null;
        Elements lines = doc.getElementsByClass("js-metro-line");
        lineArrayList = new ArrayList<>();
        Elements stations = doc.getElementsByClass("js-metro-stations");
        stationsMap = new HashMap<>();
        for (int i = 0; i < lines.size(); i++) {
            Line buff = new Line(i + 1, lines.get(i).text());
            metro.lines.add(buff);
            System.out.println(lines.get(i).text());
            Elements stationsStr = stations.get(i).getElementsByClass("name");
            for (Element e : stationsStr) {
                if(metro.stations.containsKey(i+1)){
                    metro.stations.get(i + 1).add(e.text());
                }
                else{
                    ArrayList<String> buffArray = new ArrayList<>();
                    buffArray.add(e.text());
                    metro.stations.put(i+1, buffArray);
                }
            }
            lineArrayList.add(buff);
        }


        try {
            gson = new GsonBuilder().setPrettyPrinting().create();
            Writer writer = new FileWriter("user.json");

            gson.toJson(metro, writer);


//            for (Line l : lineArrayList) {
//                String s = new Gson().toJson(l);
//                gson.toJson(l, writer);
//            }
//            gson.toJson(metro.lines, writer);
//            new Gson().toJson(lineArrayList, writer);

            writer.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }



    }
}

class Line{
    private Integer number;
    private String name;

    public Line(Integer number, String name) {
        this.number = number;
        this.name = name;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

//class Stations{
//    HashMap<Integer, ArrayList<String>> stations;
//}