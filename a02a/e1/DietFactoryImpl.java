package a02a.e1;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DietFactoryImpl implements DietFactory{

    @Override
    public Diet standard() {
        return new Diet() {

            Map<String, Integer> food = new HashMap<>();
            private int calories=0;
            private double quantity=0;
            

            @Override
            public void addFood(String name, Map<Nutrient, Integer> nutritionMap) {
                int calorie=0;
                var iterator = nutritionMap.values().iterator(); 
                while(iterator.hasNext()) {
                    calorie+= iterator.next();
                }
                food.put(name, calorie);
                
            }

            @Override
            public boolean isValid(Map<String, Double> dietMap) {
                dietMap.forEach((key, value) -> {
                    if(food.containsKey(key)) {
                        quantity = dietMap.get(key);
                        calories+= food.get(key)/100*quantity;
                    }
                });
                return calories>=1500 && calories<=2000;
            }
            
        };
    }

    @Override
    public Diet lowCarb() {
        return new Diet() {

            Map<String, Pair<Integer, Integer>> food = new HashMap<>();
            private int carbs = 0;
            private int calories = 0;
            private double quantity = 0;

            @Override
            public void addFood(String name, Map<Nutrient, Integer> nutritionMap) {
                int calorie=0;
                int carb;
                var iterator = nutritionMap.values().iterator(); 
                while(iterator.hasNext()) {
                    calorie+= iterator.next();
                }
                carb = nutritionMap.get(Nutrient.CARBS);
                food.put(name,new Pair<Integer,Integer>(calorie,carb));
                
            }

            @Override
            public boolean isValid(Map<String, Double> dietMap) {
                carbs=0;
                calories=0;
                dietMap.forEach((key, value) -> {
                    if(food.containsKey(key)) {
                        quantity = dietMap.get(key);
                        calories+= food.get(key).get1()/100*quantity;
                        carbs+= food.get(key).get2()*(int)quantity/100;
                        
                    }
                });
                return calories>=1000 && calories<=1500 && carbs<=300;
            }
            
        };
    }

    @Override
    public Diet highProtein() {
        return new Diet() {

            Map<String, List<Integer>> food = new HashMap<>();
            private int carbs = 0;
            private int proteins;
            private int calories = 0;
            private double quantity = 0;

            @Override
            public void addFood(String name, Map<Nutrient, Integer> nutritionMap) {
                int calorie=0;
                int carb;
                int protein;
                var iterator = nutritionMap.values().iterator(); 
                while(iterator.hasNext()) {
                    calorie+= iterator.next();
                }
                carb = nutritionMap.get(Nutrient.CARBS);
                protein = nutritionMap.get(Nutrient.PROTEINS);
                food.put(name,List.of(calorie, carb, protein));
            }

            @Override
            public boolean isValid(Map<String, Double> dietMap) {
                dietMap.forEach((key, value) -> {
                    if(food.containsKey(key)) {
                        this.quantity = dietMap.get(key);
                        this.calories+= food.get(key).get(0)/100*quantity;
                        this.carbs+= food.get(key).get(1)*(int)quantity/100;
                        this.proteins = food.get(key).get(2)*(int)quantity/100;
                        
                    }
                });
                
                return calories>=2000 && calories<=2500 && carbs<=300 && proteins>=1300;
            }
            
        };
    }

    @Override
    public Diet balanced() {
        // TODO Auto-generated method stub
        return null;
    }

}
