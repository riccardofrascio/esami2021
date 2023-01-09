package a02b.e1;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class UniversityProgramFactoryImpl implements UniversityProgramFactory{

    @Override
    public UniversityProgram flexible() {
        return new UniversityProgram() {

            private List<String> names = new ArrayList<>();
            private List<Sector> sectors = new ArrayList<>();
            private List<Integer> credits = new ArrayList<>();
            private int counter = 0; 

            @Override
            public void addCourse(String name, Sector sector, int credits) {
                this.names.add(counter, name);
                this.sectors.add(counter, sector);
                this.credits.add(counter, credits);
                counter++;
            }

            @Override
            public boolean isValid(Set<String> courseNames) {
                int sumCredits=0;
                for (String string : courseNames) {
                    for (int i = 0; i < names.size(); i++) {
                        if(names.get(i).equals(string)) {
                            sumCredits+=credits.get(i);
                            
                        }
                    }
                }
                System.out.println(sumCredits);
                return sumCredits==60;
            }
            
        };
    }

    @Override
    public UniversityProgram scientific() {
        return new UniversityProgram() {

            private List<String> names = new ArrayList<>();
            private List<Sector> sectors = new ArrayList<>();
            private List<Integer> credits = new ArrayList<>();
            private int counter = 0; 

            @Override
            public void addCourse(String name, Sector sector, int credits) {
                this.names.add(counter, name);
                this.sectors.add(counter, sector);
                this.credits.add(counter, credits);
                counter++;
                
            }

            @Override
            public boolean isValid(Set<String> courseNames) {
                int sumCredits=0;
                int math = 0;
                int compScience = 0;
                int physics = 0;
                for (String string : courseNames) {
                    for (int i = 0; i < names.size(); i++) {
                        if(names.get(i).equals(string)) {
                            sumCredits+=credits.get(i);
                            if(sectors.get(i).equals(Sector.MATHEMATICS)) {
                                math+=credits.get(i);
                            }
                            if(sectors.get(i).equals(Sector.COMPUTER_SCIENCE)) {
                                compScience+=credits.get(i);
                            }
                            if(sectors.get(i).equals(Sector.PHYSICS)) {
                                physics+=credits.get(i);
                            }
                        }
                    }
                }
                //System.out.println(sumCredits);
                return sumCredits==60 && math>=12 && compScience>=12 && physics>=12;
            }
            
        };
    }

    @Override
    public UniversityProgram shortComputerScience() {
        return new UniversityProgram() {

            private List<String> names = new ArrayList<>();
            private List<Sector> sectors = new ArrayList<>();
            private List<Integer> credits = new ArrayList<>();
            private int counter = 0; 

            @Override
            public void addCourse(String name, Sector sector, int credits) {
                this.names.add(counter, name);
                this.sectors.add(counter, sector);
                this.credits.add(counter, credits);
                counter++;
                
            }

            @Override
            public boolean isValid(Set<String> courseNames) {
                int sumCredits=0;
                int computer = 0;
                for (String string : courseNames) {
                    for (int i = 0; i < names.size(); i++) {
                        if(names.get(i).equals(string)) {
                            sumCredits+=credits.get(i);
                            if(sectors.get(i).equals(Sector.COMPUTER_SCIENCE) || sectors.get(i).equals(Sector.COMPUTER_ENGINEERING)) {
                                computer+=credits.get(i);
                            }
                        }
                    }
                }
                //System.out.println(sumCredits);
                return sumCredits>=48 && computer>=30;
            }
            
        };
    }

    @Override
    public UniversityProgram realistic() {
        // TODO Auto-generated method stub
        return null;
    }

}
