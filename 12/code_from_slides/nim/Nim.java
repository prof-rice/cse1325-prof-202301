public class Nim {
    public Nim(int sticks, int firstPlayer) {
        this.sticks = sticks;
        cPlayer = firstPlayer;
    }
    public Nim() {  // chain (delegate) to first constructor
        this((int) (7 + Math.random()*9),   // between 7 and 15, inclusive
             (int) (1 + Math.random()*2));  // between 1 and  2, inclusive
    }
    public int currentPlayer() {return cPlayer;}
    public int sticksLeft() {return sticks;}
    public void takeSticks(int sticks) {
       assert(1 <= sticks && sticks <= 3);
       if(this.sticks >= sticks) this.sticks -= sticks;
       else throw new IllegalArgumentException("Out of sticks!");
       cPlayer = 3 - cPlayer;
    }
    public int optimalMove() {
        if(sticks <= 1) return 1;
        switch(sticks%4) {
            case 1: return (int) (1 + Math.random()*3);
            case 2: return 1;
            case 3: return 2;
            case 0: return 3;
            default: throw new RuntimeException("Invalid optimal move calculation: " + sticks%4);
        }
    }
    
    private int sticks;
    private int cPlayer;
}
    
