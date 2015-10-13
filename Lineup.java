import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jason on 10/13/15.
 */
public class Lineup {

    Player QB;
    Player WR1;
    Player WR2;
    Player WR3;
    Player RB1;
    Player RB2;
    Player Flex;
    List<Player> quarterBacks = new ArrayList<Player>();
    List<Player> runningBacks= new ArrayList<Player>();
    List<Player> wideReceivers= new ArrayList<Player>();
    List<Player> tightEnds= new ArrayList<Player>();
    List<Defense> defenses= new ArrayList<Defense>();


    double totalPPG;

    public void findBestQuarterBack() {
        Player best = runningBacks.get(0);
        System.out.println("Analyzing " + runningBacks.size() + " Running Backs...");
        for(Player qb : runningBacks) {
            System.out.println("Checking RB: " + qb.name ); // "("+ qb.avgPPG +")");
            if(best.avgPPG < qb.avgPPG) {
                best = qb;
            }
        }

        System.out.println("Best RB Player: " + best.name);
        System.out.println("\tPosition: " + best.position + "  Salary: " + best.salary + "  avgPPG: " + best.avgPPG);
    }

    public void findBestQBWithSalaryBelow(int salaryCap) {
        Player best = null;
        System.out.println("Analyzing " + quarterBacks.size() + " Quarter Backs...");
        for(Player qb : quarterBacks) {
            //System.out.println("Checking QB: " + qb.name ); // "("+ qb.avgPPG +")");
            if(best == null && qb.salary < salaryCap) {
                best = qb;
            }else if(qb.salary < salaryCap && best.avgPPG < qb.avgPPG ) {
                best = qb;
            }
        }

        System.out.println("Best QB Player: " + best.name);
        System.out.println("\tSalary: " + best.salary + "  avgPPG: " + best.avgPPG);
    }

    public void findBestRBWithSalaryBelow(int salaryCap) {
        Player best = null;
        System.out.println("Analyzing " + runningBacks.size() + " Running Backs...");
        for(Player rb : runningBacks) {
            //System.out.println("Checking RB: " + rb.name ); // "("+ qb.avgPPG +")");
            if(best == null && rb.salary < salaryCap) {
                best = rb;
            }else if(rb.salary < salaryCap && best.avgPPG < rb.avgPPG ) {
                best = rb;
            }
        }

        System.out.println("Best RB Player: " + best.name);
        System.out.println("\tSalary: " + best.salary + "  avgPPG: " + best.avgPPG);
    }

    public void findBestWRWithSalaryBelow(int salaryCap) {
        Player best = null;
        System.out.println("Analyzing " + wideReceivers.size() + " Wide Receivers...");
        for(Player wr : wideReceivers) {
            //System.out.println("Checking WR: " + wr.name ); // "("+ qb.avgPPG +")");
            if(best == null && wr.salary < salaryCap) {
                best = wr;
            }else if(wr.salary < salaryCap && best.avgPPG < wr.avgPPG ) {
                best = wr;
            }
        }

        System.out.println("Best WR Player: " + best.name);
        System.out.println("\tSalary: " + best.salary + "  avgPPG: " + best.avgPPG);
    }

    public void findBestTEWithSalaryBelow(int salaryCap) {
        Player best = null;
        System.out.println("Analyzing " + tightEnds.size() + " Tight Ends...");
        for(Player te : tightEnds) {
            //System.out.println("Checking TE: " + te.name ); // "("+ qb.avgPPG +")");
            if(best == null && te.salary < salaryCap) {
                best = te;
            }else if(te.salary < salaryCap && best.avgPPG < te.avgPPG ) {
                best = te;
            }
        }

        System.out.println("Best TE Player: " + best.name);
        System.out.println("\tSalary: " + best.salary + "  avgPPG: " + best.avgPPG);
    }

    public void findBestDSTWithSalaryBelow(int salaryCap) {
        Defense best = null;
        System.out.println("Analyzing " + defenses.size() + " Defenses...");
        for(Defense dst : defenses) {
            //System.out.println("Checking Defense: " + dst.name ); // "("+ qb.avgPPG +")");
            if(best == null && dst.salary < salaryCap) {
                best = dst;
            }else if(dst.salary < salaryCap && best.avgPPG < dst.avgPPG ) {
                best = dst;
            }
        }

        System.out.println("Best Defense: " + best.name);
        System.out.println("\tSalary: " + best.salary + "  avgPPG: " + best.avgPPG);
    }


    private class Player {

        String name;
        String team;
        double avgPPG;
        String position;
        int salary;

        public Player(String name, String position, int salary, double avgPPG, String team){
            this.name = name;
            this.position = position;
            this.salary = salary;
            this.avgPPG = avgPPG;
            this.team = team;
        }
    }


    private class Defense {
        double avgPPG;
        String name;
        int salary;

        public Defense(String name, int salary, double avgPPG) {
            this.name = name;
            this.salary = salary;
            this.avgPPG = avgPPG;
        }
    }

    public void run() {

        String csvFile = "/home/jason/DKSalaries.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        boolean skipLine = true; //Used to skip first line
        try {
            br = new BufferedReader(new FileReader(csvFile));

            while ((line = br.readLine()) != null) {
                if(!skipLine) {

                    // use comma as separator
                    String[] lineup = line.split(cvsSplitBy);
                    String position = lineup[0].replaceAll("\\\"", "");
                    String name = lineup[1];
                    int salary = Integer.parseInt(lineup[2]);
                    double avgPPG = Double.parseDouble(lineup[4]);
                    String team = lineup[5];
                    if(position.equals("DST")) {
                     Defense defense = new Defense(name,salary, avgPPG);
                        defenses.add(defense);
                    }
                    else {
                        Player player = new Player(name, position, salary, avgPPG, team);
                      //  System.out.println("Player: " + player.name);
                      //  System.out.println("\tPosition: " + player.position + "  Salary: " + player.salary + "  avgPPG: " + player.avgPPG);
                        if(position.equals("QB")) {
                            quarterBacks.add(player);
                        }
                        else if(position.equals("RB")) {
                            runningBacks.add(player);
                        }
                        else if(position.equals("WR")) {
                            wideReceivers.add(player);
                        }
                        else if(position.equals("TE")) {
                            tightEnds.add(player);
                        }
                    }
                }
                else {
                    skipLine = false;
                }

                }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        System.out.println("Done");
    }



    public static void main(String[] args) {
        Lineup lineup = new Lineup();
        lineup.run();
        lineup.findBestQBWithSalaryBelow(10000);
        System.out.println();
        lineup.findBestRBWithSalaryBelow(10000);
        System.out.println();
        lineup.findBestTEWithSalaryBelow(10000);
        System.out.println();
        lineup.findBestDSTWithSalaryBelow(10000);
    }
}
