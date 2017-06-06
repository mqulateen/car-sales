package uk.co.tribal;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

//Car sale exercise
//Mohamed Qulateen

public class App extends ArrayList<Sale>
{
    /*Returns the most popular car model, that is, the model (or models) with 
    the highest number of overall sales.*/
    public List<String> mostPopularModel(){
        //map that holds each unique model with its respective total count
        Map<String,Long> modelCount = this.stream().collect(Collectors.groupingBy(s -> s.getModel(), Collectors.counting()));
        return getPopularModel(modelCount);
    }
    
    /*Returns the most popular car model (or models) by a given city.*/
    public List<String> mostPopularModelOf(String city){
        //map that holds each unique model with its respective total count within a specified city
        Map<String, Long> modelCount = this.stream().filter(s -> s.getCity().equals(city))
                                           .collect(Collectors.groupingBy(c -> c.getModel(), Collectors.counting()));
        return getPopularModel(modelCount);
    }
    
    /* mostPopularModel and mostPopularModelOf are almost exactly the same,
       only difference is that an extra check is performed in the latter.
       I decided to create this method which is used by both; it gets the max count
       and returns a list of models with that count */
    private List<String> getPopularModel(Map<String, Long> modelCount){
        long maxValue = modelCount.entrySet().stream().max(Map.Entry.comparingByValue(Long::compareTo)).get().getValue();
        return modelCount.keySet().stream().filter(k -> modelCount.get(k).equals(maxValue)).collect(Collectors.toList());
    }
    
    /*Returns the overall number of times a given car model has been sold nationwide.*/
    public long sales(String model){
        return this.stream().filter(s -> s.getModel().equals(model)).count();
    }
    
    /*Adds a car model, within the associated city, to the overall overall collection of cars sold*/
    public App addSale(String model, String city){
        this.add(new Sale(model, city));
        return this;
    }
    
    public static void main( String[] args )
    {
        App app = new App();
        app.addSale("Passat", "London")
           .addSale("Golf", "London")
           .addSale("Passat", "Bristol")
           .addSale("Sharan", "Leeds");
        
        app.forEach(s -> System.out.println("Model: " + s.getModel() + " | City: " + s.getCity()));
        System.out.println("- - - - - - - - - -");
        System.out.println("Most Popular model: " + app.mostPopularModel());
        System.out.println("Most Popular model in London: " + app.mostPopularModelOf("London"));
        System.out.println("Passat total sale count: " + app.sales("Passat"));
    }
}

//class to create each individual sale - containing a car's model and city w/getters
class Sale{
    private String model, city;

    public Sale(String model, String city){
        this.model = model;
        this.city = city;
    }
    
    public String getModel(){return model;}
    public String getCity(){return city;}
}

/**
 * Design choices 
   -----------------
    We can have more than one results when searching for a popular model.
    Therefore, an ArrayList is used to return the model(s).

    Java 8 streams API with lambda expressions was the simplest, most readable 
    solution to tackle this problem

    The App class consists of a list of Sale objects to ensure ease of access and
    manipulation of the list
*/