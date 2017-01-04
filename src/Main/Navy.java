package Main;

import java.util.ArrayList;

import javafx.scene.paint.Color;

public class Navy {
	ArrayList<BattleShip> ships = new ArrayList<BattleShip>();

	public Navy(int howMany, int maxPos) {
		int maxCycles = howMany * 10;
		int counting = 0;

		// Generating as many ships as wanted
		for (int i = 0; i < howMany; i++) {

			// building a new ship
			int shipLength = Math.max(5 - i, 1);
			BattleShip ship = new BattleShip(shipLength, maxPos);

			// is it alone or on other ship? source1
			boolean alone = true;
			for (int j = 0; j < ships.size(); j++) {
				alone = alone && !ship.isNearby(ships.get(j));
			}
			if (alone)
				ships.add(ship);
			else {
				i--;
			}
			if (counting > maxCycles)
				break;
			else
				counting++;
		}
	}

	// Attack was made (click made). Gives info to navy. source1
	public void shot(int[] cordinates) {
		for (int i = 0; i < ships.size(); i++) {
			boolean shipGotHit = ships.get(i).shipGotHit(cordinates);
			if (shipGotHit)
				return;
		}
		System.out.println("LASID MERRE!");
	}

	// returns the correct color
	public Color getPosColor(int i, int j) {
		// ask every ship to find out who is in that square. code source1
		for (int k = 0; k < ships.size(); k++) {
			BattleShip ship = ships.get(k);
			int status = ship.getPosStatus(new int[] { i, j });
			if (status == 0)
				return Color.BLACK;
			else if (status == 1) // ship got hit
				return Color.DARKORANGE;
		}
		return Color.DARKBLUE;
	}
	// checks the condidion of all the ships
	public boolean isGameOver() {
		for (BattleShip ship : ships) {
			if (!ship.isPohjas())
				return false;
		}
		return true;
	}
}
