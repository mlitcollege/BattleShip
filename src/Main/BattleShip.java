package Main;

public class BattleShip {
	int[][] location; 
	
	// This method returns ship coordinates (location).
	public int[][] getCoordinates() {
		
		return location;
		
	}
	
    // This method checks if ship got hit (code source 1)
   
	public boolean shipGotHit(int[] shot) {
        for (int i = 0; i < location.length; i++) {
            if (location[i][0] == shot[0] && location[i][1] == shot[1]) {
                location[i][2] = 1;       
                System.out.println("Said laevale pihta!");    
                return true;                 
            }
        }
        return false; 
    }

    // This method returns coordinate if the ship has it. (code source 1)
    public int getPosStatus(int[] xy) {
        for (int i = 0; i < location.length; i++) {
            if (location[i][0] == xy[0] && location[i][1] == xy[1])
                return location[i][2];
        }
        return -1; // no coordinate
    }
    
    // This method is from source 1, line 10 to 50: https://github.com/KristerV/javaLaevadePommitamine/blob/master/src/Laev.java
    
    public BattleShip(int kohaline, int maxPos) {
    	location = new int[kohaline][3];
    	location[0][0] = Math.min((int) (Math.random() * maxPos), maxPos-kohaline);
    	location[0][1] = Math.min((int) (Math.random() * maxPos), maxPos-kohaline);
    	int paremale = (int) Math.round(Math.random());
    	int alla = paremale == 1 ? 0 : 1;
    	for (int i = 1; i < location.length; i++) {
    		location[i][0] = location[i-1][0] + paremale;
    		location[i][1] = location[i-1][1] + alla;
    	}
    }
    
    // This method checks if a ship is on another ship (code source 1)
   
    public boolean isNearby(BattleShip teine) {
    	int[][] koord2 = teine.getCoordinates();
    	for (int i = 0; i < koord2.length; i++) {                  
    		for (int j = 0; j < location.length; j++) {           
    			for (int k = -1; k <= 1; k++) {
    				for (int l = -1; l <= 1; l++) {
    					if (koord2[i][0] == location[j][0] + k && koord2[i][1] == location[j][1] + l) {
    						return true; 
    					}
    				}
    			}
    		}
    	}
    	return false; 
    }

    // Check if ship is  Kas laev on põhjas?
   
    public boolean isPohjas() {
    	for (int i = 0; i < location.length; i++) {
    		if (location[i][2] == 0)
    			return false;
    	}
    	return true;
    }
}
