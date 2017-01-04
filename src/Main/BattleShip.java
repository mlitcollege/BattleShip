package Main;

public class BattleShip {
	int[][] location;

	// This method returns ship coordinates (location).
	public int[][] getCoordinates() {

		return location;

	}

	// This method checks if ship got hit (code source1)

	public boolean shipGotHit(int[] shot) {
		for (int i = 0; i < location.length; i++) {
			if (location[i][0] == shot[0] && location[i][1] == shot[1]) {
				location[i][2] = 1;
				System.out.println("SAID LAEVALE PIHTA!");
				return true;
			}
		}
		return false;
	}

	// This method returns coordinate if the ship has it. (code source1)
	public int getPosStatus(int[] xy) {
		for (int i = 0; i < location.length; i++) {
			if (location[i][0] == xy[0] && location[i][1] == xy[1])
				return location[i][2];
		}
		return -1; // no coordinate
	}

	// This method generates new Battleship. (code source1)

	public BattleShip(int kohaline, int maxPos) {
		location = new int[kohaline][3];
		location[0][0] = Math.min((int) (Math.random() * maxPos), maxPos - kohaline);
		location[0][1] = Math.min((int) (Math.random() * maxPos), maxPos - kohaline);
		int paremale = (int) Math.round(Math.random());
		int alla = paremale == 1 ? 0 : 1;
		for (int i = 1; i < location.length; i++) {
			location[i][0] = location[i - 1][0] + paremale;
			location[i][1] = location[i - 1][1] + alla;
		}
	}

	// This method checks if a ship is on another ship (code source1)

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

	// Check if ship is dead, checks coordinates

	public boolean isPohjas() {
		for (int i = 0; i < location.length; i++) {
			if (location[i][2] == 0)
				return false; // still got squares left
		}
		return true;
	}
}
