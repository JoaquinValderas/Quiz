package quiz2;

public class Ranking implements Comparable<Ranking>{
    public String name;
    public double score;
    
    public Ranking (String name, double score){
        this.name = name;
        this.score = score;
    
    }

    @Override
    public int compareTo(Ranking o) {
        if (this.score != o.getScore()) {
            return (int) (this.score - o.getScore())*-1;
        }
        return this.name.compareTo(o.getName());
    }
    @Override
    public String toString(){
        int i = 1;
        return "\n" +i+")"+" name: " + name + " ===> " + "score = " + score + "\n";
    }
    
     public double getScore() {
        return score;
    }
     
    public String getName() {
        return name;
    }
}
