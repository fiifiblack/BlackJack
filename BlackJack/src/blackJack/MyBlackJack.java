package blackJack;



import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Evans Appiah, ITTP @author GCE Black Jack Project 02/05/2018 Contact Evans (
 * evans.appiah@me.com) if you want a copy of this property All rights reserved.
 */
public class MyBlackJack {
	public static int Bet;
	static int[] BetExcrow = new int[3];
	static String Hello;
	static int PlayerPoints[] = new int[23];
	static int HousePoints[] = new int[23];
	static int UserSum;
	static int choiceToContinue;
	private static int HouseSum;
	static int wins;
	static int game = 100;
	static int ratio;
	static boolean won = false;
	static int PlayAgain;
	static int Bank = 1000;
	static boolean positiveWin = false;
	static ArrayList<Integer> userWin = new ArrayList<Integer>();
	static ArrayList<Integer> betsWon = new ArrayList<Integer>();
	static ArrayList<Integer> bankAmount = new ArrayList<Integer>();
	static int runOnce = 0;
	static int winResults = 0;
	static int bankSum = 0;
	static double gameCount = 0;
	static double gamePercentage = 0;
	static double gameWin = 0;
	static boolean firstTurn = true;
	static int bankFunds = 1000;

	public static void main(String[] args) throws java.io.IOException {
		// Welcome message: User inputs BET and NAME
		caution();// Two caution methods to meet overloading requirement

		@SuppressWarnings("resource")
		Scanner name = new Scanner(System.in);
		Hello = name.nextLine();
		System.out.println('\n' + "Hello " + Hello + ",");
		System.out.println("Only FOUR games are allowed per session");

		beginBet(); // 1. This method ask player to bet

		requestCard(); // 2. User enters 0 to start game
		// requestCard(); calls Pool(); Twice:

		// Pool(); Generates 2 cards and saves into array & PRINTS out ARRAY
		do {
			PlayHitStand();// 3.

			sumUserPoint();// 4. Used arrays

		} while ((UserSum <= 21) & (choiceToContinue == 1) & (gameCount != 4));// changed && to & +gameCt03/30/18
		// PlayStop(); // 5.

		// The "do while-loop" hands over to the PC conditionally to compare
		// results&winner
	}

	// Methods//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

	private static void recallMain() {
		caution();// Two caution methods to meet overloading requirement

		@SuppressWarnings("resource")
		Scanner name = new Scanner(System.in);
		Hello = name.nextLine();
		System.out.println('\n' + "Hello " + Hello + ",");
		System.out.println("Only FOUR games are allowed per session");

		beginBet(); // 1. This method ask player to bet

		requestCard(); // 2. User enters 0 to start game
		// requestCard(); calls Pool(); Twice:

		// Pool(); Generates 2 cards and saves into array & PRINTS out ARRAY
		do {
			PlayHitStand();// 3.

			sumUserPoint();// 4. Used arrays

		} while ((UserSum <= 21) & (choiceToContinue == 1) & (gameCount != 4));// changed && to & +gameCt03/30/18
		// PlayStop(); // 5.

		// The "do while-loop" hands over to the PC conditionally to compare
		// results&winner
	}

	public static void requestCard() {// Saves points into array and prints out array
		System.out.println("Please Enter 0 to begin game ");
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		int c = sc.nextInt();
		if (c == 0)// if statement to generate 1st card
		{
			gameCount++;
			Pool();
			Pool();

			try {
				sumUserPoint();
			} catch (Exception e) {
				e.printStackTrace();
			}

			if (UserSum == 22) {
				UserSum -= 10;
			}

		} else if (c != 0) {
			System.out.println("Run program  again and follow instructions ");
			System.exit(0);
		}
	}

	public static void beginBet() {
		System.out.println("___________________________________________________");
		System.out.println("Please select betting amount: ");

		System.out.print("$10" + '\n' + "$20" + '\n' + "$50" + '\n' + "$100" + '\n' + "Good Luck!:");
		@SuppressWarnings("resource")
		Scanner inputBet = new Scanner(System.in);

		try {
			Bet = inputBet.nextInt();
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		switch (Bet) {
		case 10:
			Bet = 10;
			break;
		case 20:
			Bet = 20;
			break;
		case 50:
			Bet = 50;
			break;
		case 100:
			Bet = 100;
			break;
		default:
			System.out.println("Invalid Entry! " + '\n' + " To play again, run program againand follow instructions.");
			System.exit(0);
			break;
		}
		int BetSum = 0;
		try {
			BetSum = 0;
			Arrays.fill(BetExcrow, 0, 1, Bet);// Saving bet in array

			for (int i = 0; i < BetExcrow.length; i++) {

				BetSum += BetExcrow[i];
			}

			for (int i = 0; i < BetExcrow.length; i++) {

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("An amout of: " + "$" + BetSum + "  is secured in vault for the eventual WINNER");
		System.out.println(Arrays.toString(BetExcrow));
	}

	public static void PlayHitStand() {// User options to play method. CheckS for USER BUST and over all points if
										// 2input
		if ((UserSum > 21) && (choiceToContinue == 1)) {
			System.out.println("___________________________________________________#!");
			System.out.println(" BUST! Player's Points are over 21");
			System.out.println("House WINS" + "\n");
			System.out.println("House " + " WINS " + "a " + "Bet " + "amount of " + Bet + "\n");
			// GameRatio();
			System.out.println("Game Over!  " + '\n' + " To play again, run program  again and follow instructions");
			// Bank -= Bet;
			// failBalance();
			// failRatio();
			Replay();
			System.exit(0);

		}

		System.out.println("**************************************************!");
		System.out.println(
				"\n " + "Do you want to " + "\n " + "1.Hit (1)" + " \n" + "2.Stand (2) " + "\n" + "Choose One (1/2): ");
		Scanner nextCard = new Scanner(System.in);
		choiceToContinue = nextCard.nextInt();
		if (choiceToContinue == 1) {
			Pool();
			if (UserSum > 21) {// Checking for Player BUST
				System.out.println("___________________________________________________#0");
				System.out.println(" BUST! " + Hello + "'s" + " Points are over 21");
				System.out.println("House " + " WINS" + "\n");
				System.out.println("House " + " WINS " + "a " + "Bet " + "amount of " + Bet + "\n");
				Bank -= Bet;
				// failBalance();
				// failRatio();
				positiveWin = false;
				percentOfWin();
				System.out
						.println("Game Over!  " + '\n' + " To play again, run program  again and follow instructions");
				System.exit(0);
				Replay();
			}
		} else if (choiceToContinue == 2) {
			// System.exit(0);
			while (HouseSum < 17 && HouseSum < 21) {// House loop for cards
				HousePool();
				sumHousePoint();
			}

		} else if ((choiceToContinue != 1) || (choiceToContinue != 2)) {// Error checking
			System.out.println("**************************************************@");
			System.out.println("Please run the Program again and follow instructions");
			System.exit(0);
		}

		if ((choiceToContinue == 2) && (HouseSum <= 17)) {// Making decisions on house and user results

			// if (HouseSum > 21) {// Deleted else
			// System.out.println("___________________________________________________#0");
			// System.out.println(" BUST! House Points are over 21");
			// System.out.println(Hello + " WINS" + "\n");
			// System.out.println(Hello + " WINS " + "a " + "Bet " + "amount of " + Bet +
			// "\n");
			// GameRatio();
			// Replay();
			// }
			while (HouseSum < 17) {// House loop for cards
				HousePool();
				sumHousePoint();
			}

			if (HouseSum > UserSum && HouseSum < 21) {
				System.out.println("___________________________________________________#2");
				System.out.println("House WINS" + "\n");
				System.out.println("House " + " WINS " + "a " + "Bet " + "amount of " + Bet + "\n");
				// failBalance();
				// failRatio();
				positiveWin = false;
				percentOfWin();
				Replay();
			} else if (HouseSum < UserSum) {
				System.out.println("___________________________________________________#3");
				System.out.println(Hello + " WINS" + "\n");
				System.out.println(Hello + " WINS " + "a " + "Bet " + "amount of " + Bet + "\n");
				Bank += Bet;
				won = true;
				positiveWin = true;
				percentOfWin();
				// BalanceCal();
				Replay();
			} else if (HouseSum == 17 && HouseSum > UserSum) {
				System.out.println("___________________________________________________#4");
				System.out.println("House WINS" + "\n");
				System.out.println("House " + " WINS " + "a " + "Bet " + "amount of " + Bet + "\n");
				Bank -= Bet;
				won = false;
				// failBalance();
				// failRatio();
				positiveWin = false;
				percentOfWin();
				Replay();

			} else if (HouseSum == 17 && UserSum > HouseSum) {
				System.out.println("___________________________________________________#5");
				System.out.println(Hello + " WINS" + "\n");
				System.out.println(Hello + " WINS " + "a " + "Bet " + "amount of " + Bet + "\n");
				// Bank += Bet;
				// won = true;
				// GameRatio();
				positiveWin = true;
				percentOfWin();
				// BalanceCal();
				Replay();

			}

		}
		if (choiceToContinue == 2 && HouseSum >= 17) {

			if ((UserSum == 17) && (HouseSum == 17)) {
				System.out.println("___________________________________________________#5a");

				System.out.println(" TIE " + "\n");
				System.out.println("Additional Note : A TIE game is not counted");
				System.out.println(" You may replay at anytime " + "\n");
				// GameRatio();
				gameCount--;
				Replay();

			} else if (HouseSum == UserSum) {
				System.out.println("___________________________________________________#5b");
				System.out.println(" TIE " + "\n");
				System.out.println("Additional Note : A TIE game is not counted");
				System.out.println(" GameRatio :TIE. You may replay at anytime " + "\n");
				// GameRatio();
				gameCount--;
				Replay();
			}

			else if (HouseSum > UserSum && HouseSum <= 21) {

				if (HouseSum > UserSum) {// Double error checking
					System.out.println("___________________________________________________#6");
					System.out.println("House WINS" + "\n");
					System.out.println("House " + " WINS " + "a " + "Bet " + "amount of " + Bet + "\n");
					positiveWin = false;
					percentOfWin();
					Replay();
				}
			} else if (UserSum == 21 && HouseSum == 21) {
				System.out.println("___________________________________________________#7");

				System.out.println(" TIE " + "\n");
				System.out.println("Additional Note : A TIE game is not counted");
				System.out.println(" GameRatio :TIE. You may replay at anytime " + "\n");
				// GameRatio();
				gameCount--;
				Replay();

			} else if (UserSum > HouseSum) {
				System.out.println("___________________________________________________#8");
				System.out.println(Hello + " WINS" + "\n");
				System.out.println(Hello + " WINS " + "a " + "Bet " + "amount of " + Bet + "\n");
				// Bank += Bet;
				// won = true;
				positiveWin = true;
				percentOfWin();
				// BalanceCal();
				Replay();
			} else if (HouseSum > UserSum && HouseSum == 20) {
				System.out.println("___________________________________________________#8a");
				System.out.println("House WINS" + "\n");
				System.out.println("House " + " WINS " + "a " + "Bet " + "amount of " + Bet + "\n");
				// Bank -= Bet;
				// won = false;
				// failBalance();
				// failRatio();
				positiveWin = false;
				percentOfWin();
				Replay();
			} else if (UserSum > 21 && HouseSum == 20) {
				System.out.println("___________________________________________________#9");
				System.out.println(" BUST! House WINS" + "\n");
				System.out.println("House " + " WINS " + "a " + "Bet " + "amount of " + Bet + "\n");
				// Bank -= Bet;
				// won = false;
				// failBalance();
				// failRatio();
				positiveWin = false;
				percentOfWin();
				Replay();

			} else if (HouseSum > 21 && choiceToContinue == 2) {
				System.out.println("___________________________________________________#10");
				System.out.println(" BUST! House Points are over 21");
				sumHousePoint();
				System.out.println(Hello + " WINS" + "\n");
				System.out.println(Hello + " WINS " + "a " + "Bet " + "amount of " + Bet + "\n");
				// Bank += Bet;
				// won = true;
				try {
					// GameRatio();
					positiveWin = true;
					percentOfWin();
					// BalanceCal();
					Replay();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

	public static void Pool() {// Selecting card suite/type

		// for (int i = 0; i < PlayerPoints.length; i++) {
		//
		// }

		int suite = (int) (Math.random() * 21 + 2);
		switch (suite) {
		case 5:
			int Value5 = 5;
			System.out.println("**************************************************11");
			System.out.print(" 5 of Spades " + Value5 + " : Points" + "\n");
			Arrays.fill(PlayerPoints, 4, 5, Value5);

			System.out.print(Arrays.toString(PlayerPoints) + '\n');

			break;
		case 2:
			int Value2 = 2;
			System.out.println("**************************************************22");
			System.out.println(" 2 of Spades " + Value2 + " : Points" + "\n");
			Arrays.fill(PlayerPoints, 1, 2, Value2);

			System.out.print(Arrays.toString(PlayerPoints) + '\n');

			break;
		case 3:
			int Value3 = 3;
			System.out.println("**************************************************33");
			System.out.print("3 of Spades " + Value3 + " : Points" + "\n");
			Arrays.fill(PlayerPoints, 2, 3, Value3);

			System.out.print(Arrays.toString(PlayerPoints) + '\n');
			break;
		case 4:
			int Value4 = 4;
			System.out.println("**************************************************1");
			System.out.print(" 4 of Spades " + Value4 + " : Points" + "\n");
			Arrays.fill(PlayerPoints, 3, 4, Value4);

			System.out.print(Arrays.toString(PlayerPoints) + '\n');
			break;
		case 6:
			int Value6 = 6;
			System.out.println("**************************************************2");
			System.out.print(" 6 of Spades " + Value6 + " : Points" + "\n");
			Arrays.fill(PlayerPoints, 5, 6, Value6);

			System.out.print(Arrays.toString(PlayerPoints) + '\n');
			break;
		case 7:
			int Value7 = 7;
			System.out.println("**************************************************3");
			System.out.print(" 7 of Spades. " + Value7 + " : Points" + "\n");
			Arrays.fill(PlayerPoints, 6, 7, Value7);
			System.out.print(Arrays.toString(PlayerPoints) + '\n');
			break;
		case 8:
			int Value8 = 8;
			System.out.println("**************************************************4");
			System.out.print("Spades " + Value8 + " : Points" + "\n");
			Arrays.fill(PlayerPoints, 7, 8, Value8);
			System.out.print(Arrays.toString(PlayerPoints) + '\n');
			break;

		case 9:
			int Value9 = 9;
			System.out.println("**************************************************5");
			System.out.print("Spades " + Value9 + " : Points" + "\n");
			Arrays.fill(PlayerPoints, 8, 9, Value9);
			System.out.print(Arrays.toString(PlayerPoints) + '\n');
			break;
		case 10:
			int Value10 = 10;
			System.out.println("**************************************************6");
			System.out.print("King orf Hearts " + Value10 + " : Points" + "\n");
			Arrays.fill(PlayerPoints, 9, 10, Value10);
			System.out.print(Arrays.toString(PlayerPoints) + '\n');
			break;
		case 25:
			int Value25 = 10;
			System.out.println("**************************************************7");
			System.out.print("Qeen of Hearts." + Value25 + " : Points" + "\n");
			Arrays.fill(PlayerPoints, 24, 25, Value25);
			System.out.print(Arrays.toString(PlayerPoints) + '\n');
			break;
		case 16:
			int Value16 = 10;
			System.out.println("**************************************************8");
			System.out.print("Jack Of  Hearts. " + Value16 + " : Points" + "\n");
			Arrays.fill(PlayerPoints, 15, 16, Value16);
			System.out.print(Arrays.toString(PlayerPoints) + '\n');
			break;

		case 24:
			int Value24 = 10;
			System.out.println("**************************************************9");
			System.out.print("King Clubs. " + Value24 + " : Points" + "\n");
			Arrays.fill(PlayerPoints, 23, 24, Value24);
			System.out.print(Arrays.toString(PlayerPoints) + '\n');
			break;
		case 18:
			int Value18 = 10;
			System.out.println("**************************************************a");
			System.out.print("Qeen of Clubs. " + Value18 + " : Points" + "\n");
			System.out.print("10 Points" + "\n");
			Arrays.fill(PlayerPoints, 17, 18, Value18);
			System.out.print(Arrays.toString(PlayerPoints) + '\n');
			break;
		case 19:
			int Value19 = 10;
			System.out.println("**************************************************b");
			System.out.print("Jack Of  Clubs. " + Value19 + ":Points" + "\n");
			Arrays.fill(PlayerPoints, 18, 19, Value19);
			System.out.print(Arrays.toString(PlayerPoints) + '\n');
			break;
		case 20:
			int Value20 = 10;
			System.out.println("**************************************************//");
			System.out.println("King Diamonds. " + Value20 + ":Points" + "\n");
			Arrays.fill(PlayerPoints, 19, 20, Value20);
			System.out.print(Arrays.toString(PlayerPoints) + '\n');
			break;
		case 21:
			int Value21 = 10;
			System.out.println("**************************************************??");
			System.out.println("Qeen of Diamonds. ");
			System.out.println("10 Points" + "\n");
			Arrays.fill(PlayerPoints, 20, 21, Value21);
			System.out.print(Arrays.toString(PlayerPoints) + '\n');
			break;
		case 22:
			int Value22 = 10;
			System.out.println("**************************************************>>");
			System.out.println("Jack Of  Diamonds : ");
			System.out.print("10 Points" + "\n");
			Arrays.fill(PlayerPoints, 21, 22, Value22);
			System.out.print(Arrays.toString(PlayerPoints) + '\n');
			break;
		case 23:
			int Value23 = 10;
			System.out.println("**************************************************^^");
			System.out.print("King Spades: " + "\n" + Value23 + " :Points" + "\n");
			System.out.print(Arrays.toString(PlayerPoints) + '\n');

			Arrays.fill(PlayerPoints, 22, 23, Value23);
			System.out.print(Arrays.toString(PlayerPoints) + '\n');
			break;
		case 17:
			int Value17 = 10;
			System.out.println("***************************************************!+");
			System.out.println("Jack Of  Spades : " + "\n" + Value17 + " :Points" + "\n");
			Arrays.fill(PlayerPoints, 16, 17, Value17);
			System.out.print(Arrays.toString(PlayerPoints) + '\n');
			System.out.println();
			break;
		case 15:
			int Value15 = 10;
			System.out.println("**************************************************((");
			System.out.println("Jack Of  Clubs " + "\n" + Value15 + " :Points" + "\n");
			Arrays.fill(PlayerPoints, 14, 15, Value15);
			System.out.print(Arrays.toString(PlayerPoints) + '\n');
			break;
		case 14:
			int Value14 = 11;
			System.out.println("**************************************************))");
			System.out.println("Ace  of Spades" + "\n" + Value14 + " :Points" + "\n");
			Arrays.fill(PlayerPoints, 13, 14, Value14);
			System.out.print(Arrays.toString(PlayerPoints) + '\n');
			break;
		case 11:
			int Value11 = 11;
			System.out.println("**************************************************==");
			System.out.println("Ace  of Spades" + "\n" + Value11 + " :Points" + "\n");
			System.out.print("11 Points" + "\n");
			Arrays.fill(PlayerPoints, 10, 11, Value11);
			System.out.print(Arrays.toString(PlayerPoints) + '\n');
			break;
		case 12:
			int Value12 = 11;
			System.out.println("**************************************************%%");
			System.out.println("Ace Of  Clubs ");
			System.out.print("11 Points" + "\n");
			Arrays.fill(PlayerPoints, 11, 12, Value12);
			System.out.print(Arrays.toString(PlayerPoints) + '\n');
			break;
		case 13:
			int Value13 = 11;
			System.out.println("**************************************************++");
			System.out.println("Ace Of  Diamonds ");
			System.out.println();
			System.out.print("11 Points" + "\n");
			Arrays.fill(PlayerPoints, 12, 13, Value13);
			System.out.println();
			System.out.print(Arrays.toString(PlayerPoints));
			break;
		}

	}

	public static void sumUserPoint() {
		UserSum = 0;
		for (int i = 0; i < PlayerPoints.length; i++) {
			UserSum += PlayerPoints[i];
		}

		System.out.println("**************************************************1a");
		System.out.println(Hello + "," + " you have a total points of :" + UserSum);
		if (UserSum > 21) {
			System.out.println(" BUST! " + Hello + " Points are over 21");
			System.out.println("House WINS" + "\n");
			System.out.println("House " + " WINS " + "a " + "Bet " + "amount of " + Bet + "\n");
			// System.out.println("Game Over! " + '\n' + " To play again, run program again
			// and follow instructions");
			// failBalance();
			// failRatio();
			positiveWin = false;
			percentOfWin();
			// System.exit(0);
			Replay();
		}

	}

	public static void PlayStop() {// User options to play Method
		do {
			PlayHitStand();
			sumUserPoint();
			// PlayHitStand();
		} while (choiceToContinue == 1 && UserSum <= 21);
		if (UserSum > 21) {// Filter for error checking
			System.out.println("___________________________________________________#!");
			System.out.println(" BUST! " + Hello + " Points are over 21");
			System.out.println("House WINS" + "\n");
			System.out.println("House " + " WINS " + "a " + "Bet " + "amount of " + Bet + "\n");
			won = false;
			try {
				// failRatio();
				// failBalance();
				Replay();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void HousePool() {// Selecting card suite/type

		int suite = (int) (Math.random() * 21 + 2);
		switch (suite) {
		case 5:
			int Value5 = 5;
			System.out.println("**************************************************@");
			System.out.print(" 5 of Spades " + Value5 + " : Points" + "\n");
			Arrays.fill(HousePoints, 4, 5, Value5);

			System.out.print(Arrays.toString(HousePoints) + '\n');

			break;
		case 2:
			int Value2 = 2;
			System.out.println("**************************************************$");
			System.out.println(" 2 of Spades. " + Value2 + " : Points" + "\n");
			Arrays.fill(HousePoints, 1, 2, Value2);

			System.out.print(Arrays.toString(HousePoints) + '\n');

			break;
		case 3:
			int Value3 = 3;
			System.out.println("**************************************************%");
			System.out.print("3 of Spades. " + Value3 + " : Points" + "\n");
			Arrays.fill(HousePoints, 2, 3, Value3);

			System.out.print(Arrays.toString(HousePoints) + '\n');
			break;
		case 4:
			int Value4 = 4;
			System.out.println("**************************************************^");
			System.out.print(" 4 of Spades. " + Value4 + " : Points" + "\n");
			Arrays.fill(HousePoints, 3, 4, Value4);
			System.out.print(Arrays.toString(HousePoints) + '\n');
			break;
		case 6:
			int Value6 = 6;
			System.out.println("**************************************************(");
			System.out.print(" 6 of Spades. " + Value6 + " : Points" + "\n");
			Arrays.fill(HousePoints, 5, 6, Value6);

			System.out.print(Arrays.toString(HousePoints) + '\n');
			break;
		case 7:
			int Value7 = 7;
			System.out.println("**************************************************)");
			System.out.print(" 7 of Spades. " + Value7 + " : Points" + "\n");
			Arrays.fill(HousePoints, 6, 7, Value7);
			System.out.print(Arrays.toString(HousePoints) + '\n');
			break;
		case 8:
			int Value8 = 8;
			System.out.println("**************************************************_");
			System.out.print("Spades. " + Value8 + " : Points" + "\n");
			Arrays.fill(HousePoints, 7, 8, Value8);
			System.out.print(Arrays.toString(HousePoints) + '\n');
			break;

		case 9:
			int Value9 = 9;
			System.out.println("**************************************************+");
			System.out.print("Spades. " + Value9 + " : Points" + "\n");
			Arrays.fill(HousePoints, 8, 9, Value9);
			System.out.print(Arrays.toString(HousePoints) + '\n');
			break;
		case 10:
			int Value10 = 10;
			System.out.println("**************************************************+");
			System.out.print("King orf Hearts. " + Value10 + " : Points" + "\n");
			Arrays.fill(HousePoints, 9, 10, Value10);
			System.out.print(Arrays.toString(HousePoints) + '\n');
			break;
		case 25:
			int Value25 = 10;
			System.out.println("**************************************************<");
			System.out.print("Qeen of Hearts." + Value25 + " : Points" + "\n");
			Arrays.fill(HousePoints, 24, 25, Value25);
			System.out.print(Arrays.toString(HousePoints) + '\n');
			break;
		case 16:
			int Value16 = 10;
			System.out.println("**************************************************>");
			System.out.print("Jack Of  Hearts. " + Value16 + " : Points" + "\n");
			Arrays.fill(HousePoints, 15, 16, Value16);
			System.out.print(Arrays.toString(HousePoints) + '\n');
			break;

		case 24:
			int Value24 = 10;
			System.out.println("**************************************************?");
			System.out.print("King Clubs. " + Value24 + " : Points" + "\n");
			Arrays.fill(HousePoints, 23, 24, Value24);
			System.out.print(Arrays.toString(HousePoints) + '\n');
			break;
		case 18:
			int Value18 = 10;
			System.out.println("**************************************************/");
			System.out.print("Qeen of Clubs. " + Value18 + " : Points" + "\n");
			System.out.print("10 Points" + "\n");
			Arrays.fill(HousePoints, 17, 18, Value18);
			System.out.print(Arrays.toString(HousePoints) + '\n');
			break;
		case 19:
			int Value19 = 10;
			System.out.println("**************************************************;");
			System.out.print("Jack Of  Clubs. " + Value19 + ":Points" + "\n");
			Arrays.fill(HousePoints, 18, 19, Value19);
			System.out.print(Arrays.toString(HousePoints) + '\n');
			break;
		case 20:
			int Value20 = 10;
			System.out.println("**************************************************:");
			System.out.println("King Diamonds. " + Value20 + ":Points" + "\n");
			Arrays.fill(HousePoints, 19, 20, Value20);
			System.out.print(Arrays.toString(HousePoints) + '\n');
			break;
		case 21:
			int Value21 = 10;
			System.out.println("**************************************************'");
			System.out.println("Qeen of Diamonds. ");
			System.out.println("10 Points" + "\n");
			Arrays.fill(HousePoints, 20, 21, Value21);
			System.out.print(Arrays.toString(HousePoints) + '\n');
			break;
		case 22:
			int Value22 = 10;
			System.out.println("**************************************************-");
			System.out.println("Jack Of  Diamonds : ");
			System.out.print("10 Points" + "\n");
			Arrays.fill(HousePoints, 21, 22, Value22);
			System.out.print(Arrays.toString(HousePoints) + '\n');
			break;
		case 23:
			int Value23 = 10;
			System.out.println("**************************************************``");
			System.out.print("King  of Spades. " + "\n" + Value23 + " :Points" + "\n");
			System.out.print(Arrays.toString(HousePoints) + '\n');

			Arrays.fill(HousePoints, 22, 23, Value23);
			System.out.print(Arrays.toString(HousePoints) + '\n');
			break;
		case 17:
			int Value17 = 10;
			System.out.println("**************************************************~~");
			System.out.println("Jack Of  Spades. " + "\n" + Value17 + " :Points" + "\n");
			Arrays.fill(PlayerPoints, 16, 17, Value17);
			System.out.print(Arrays.toString(HousePoints) + '\n');
			break;
		case 15:
			int Value15 = 10;
			System.out.println("**************************************************");
			System.out.println("Jack Of  Clubs. " + "\n" + Value15 + " :Points" + "\n");
			Arrays.fill(HousePoints, 14, 15, Value15);
			System.out.print(Arrays.toString(HousePoints) + '\n');
			break;
		case 14:
			int Value14 = 11;
			System.out.println("**************************************************!!");
			System.out.println("Ace  of Spades. " + "\n" + Value14 + " :Points" + "\n");
			Arrays.fill(HousePoints, 13, 14, Value14);
			System.out.print(Arrays.toString(HousePoints) + '\n');
			break;
		case 11:
			int Value11 = 11;
			System.out.println("**************************************************@@");
			System.out.println("Ace  of Heartss. " + "\n" + Value11 + " :Points" + "\n");
			System.out.print("11 Points" + "\n");
			Arrays.fill(HousePoints, 10, 11, Value11);
			System.out.print(Arrays.toString(HousePoints) + '\n');
			break;
		case 12:
			int Value12 = 11;
			System.out.println("**************************************************##");
			System.out.println("Ace Of  Clubs. ");
			System.out.print("11 Points" + "\n");
			Arrays.fill(HousePoints, 11, 12, Value12);
			System.out.print(Arrays.toString(HousePoints) + '\n');
			break;
		case 13:
			int Value13 = 11;
			System.out.println("**************************************************$$");
			System.out.println("Ace Of  Diamonds. ");
			System.out.print("11 Points" + "\n");
			Arrays.fill(HousePoints, 12, 13, Value13);
			System.out.print('\n' + Arrays.toString(HousePoints) + '\n');
			System.out.println();
			break;
		}

	}

	public static void sumHousePoint() {
		HouseSum = 0;
		for (int i = 0; i < HousePoints.length; i++) {
			HouseSum += HousePoints[i];
		}
		System.out.println("Dealer's Total Points: " + HouseSum);
	}

	public static void GameRatio() {
		try {

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (won) {
			wins++;

			try {
				ratio = (wins / game) * 100;
				System.out.println("Game Ratio : " + ratio + "%");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {
			wins--;
			try {
				ratio = (wins / game) * 100;
				System.out.println("Game Ratio : " + ratio + "%");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void Replay() {// Giving User options to replay Method
		if (UserSum > 21) {// BUST Error checking
			System.out.println("___________________________________________________#!");
			System.out.println(" BUST! " + Hello + " Points are over 21");
			System.out.println("House WINS" + "\n");
			System.out.println("House " + " WINS " + "a " + "Bet " + "amount of " + Bet + "\n");
			// won = false;
			// GameRatio();
			// failBalance();
			// Replay(); 03/27/18 I THINK IT IS REDUNDANT. CALLING SAME METHOD IN THAT
			// METHOD
		}
		// runOnce++;
		System.out.println("**************************************************!@");
		System.out.println();
		caution(Hello);
		System.out.println("\n " + "Do you want to play again? " + "\n " + "1.Yes (1)" + " \n"
				+ "2.Enter any Key to exit game (2) ");
		@SuppressWarnings("resource")
		Scanner again = new Scanner(System.in);
		try {
			PlayAgain = again.nextInt();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (PlayAgain == 1) {
			for (int i : HousePoints) {
				HousePoints[i] = 0;

			}
			game += 100;// change made03/21/18
			for (int i : PlayerPoints)
				PlayerPoints[i] = 0;
			game += 100;
			Arrays.fill(HousePoints, 0);// Reseting points
			Arrays.fill(PlayerPoints, 0);

			// main(new String[0]);
			recallMain();// just a call to main as //above

		} else if (PlayAgain != 1) {

			System.out.println("Game Over!  " + '\n' + " To play again, run program  again and follow instructions");
			System.exit(0);
		}
	}

	public static void BalanceCal() {// Calculating Player's cash & Reseting game if Bank==0
		if (won) {
			try {

				Bank += Bet;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.print("Bank Balnce is : " + Bank + '\n');
		} else {
			try {

				Bank -= Bet;
				System.out.print("Bank Balnce is : " + Bank + '\n');
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (Bank == 0) {
			for (int i : HousePoints)// Remove int(s)
				HousePoints[i] = 0;
			for (int i : PlayerPoints)// Clear array
				PlayerPoints[i] = 0;

			// main(null);
			beginBet(); // 1. This method ask player to bet
			Arrays.fill(HousePoints, 0);
			Arrays.fill(PlayerPoints, 0);
			requestCard(); // 2. User enters 0 to start game
			// requestCard(); calls Pool(); Twice:
			// Pool(); Generates 2 CARDS SAVES POINTSs into array and PRINTS out ARRAY
			do {
				PlayHitStand();// 3.

				sumUserPoint();// 4.
			} while ((UserSum <= 21) && (choiceToContinue == 1));
			// PlayStop(); // 5.

		}

	}

	private static void failBalance() {// Redundant method
		// TODO Auto-generated method stub

		if (won == false) {
			try {
				Bank = 1000;
				Bank -= Bet;
				System.out.print("Bank Balnce is : " + Bank + '\n');
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (won == true) {
			try {
				Bank = 1000;
				Bank += Bet;
				System.out.print("Bank Balnce is : " + Bank + '\n');
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private static void failRatio() {
		// TODO Auto-generated method stub
		try {
			ratio = (wins / game) * 100;
			System.out.println("Game Ratio : " + ratio + "%");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void caution(String message) {// overloading methods

		System.out.println(message + ", always play resposibly");

		// TODO Auto-generated method stub

	}

	private static void caution() {// overloading methods
		String message = "Welcome to Black Jack." + '\n' + "Please enter your name";

		System.out.println(message);
		// TODO Auto-generated method stub

	}

	private static void percentOfWin() {/*
										 * Substitute this for the ratios and balances make only boolean a field and
										 * insert T/F and the method near the win or loose decisions
										 */

		int sumUserWin = 0;
		int gamesPlayed = 0;
		int winPercentage = (sumUserWin / game);
		int percentage = 0;
		int winWin = 100;

		if (firstTurn) {
			bankAmount.add(1000);
		}
		firstTurn = false;

		if (positiveWin) {
			positiveWin = true;
			winResults += winWin;
			userWin.add(25);// 1/4 points for win
			betsWon.add(Bet);
			gameWin++;
			bankFunds += Bet;
			// bankAmount.add(Bet);
			int betSum = 0;
			for (int bet : betsWon) {// sum of bets
				betSum += bet;
			}
			bankAmount.add(betSum);
			proportion();
			for (int userSum : userWin) {// sum of wins
				sumUserWin += userSum;
			}
			System.out.println("**************************************************:");
			System.out.printf("%n Total Bets WON: %d ", betSum);
			// System.out.printf("%n WIN percentage : %d ", sumUserWin);TURNED OFF UNTIL
			// GAME IS OVER
			// TURNED OFF DATE 03/29/2018
			for (int bank : bankAmount) {// sum of bank
				bankSum += bank;
			}
			if (bankSum >= 2000) {// 3/8/18 ERROR CHECKING
				bankSum -= 1000;
			}
			System.out.printf("%n Bank Balance .: %d ", bankFunds);// (bankSum)
			System.out.println();
			// System.out.println(" Game percentage : " + gamePercentage);

			if (gameCount == 4) {

				// System.out.printf("%n Bank Balance .: %d ", bankFunds);
				System.out.println(" Game percentage : " + sumUserWin);
				System.out.println("Game Ended. Only four games allowed per cycle");
				System.exit(0);
			}
		} else {
			positiveWin = false;
			winResults -= winWin;
			bankFunds -= Bet;
			int bankSum = 0;
			for (int bet : bankAmount) {// deduct Bet lost
				bankSum += bet;
			}
			int LossbankSum = (bankSum - Bet);// Money left in Bank

			int betSum = 0;
			for (int bet : betsWon) {// sum of bets
				betSum += bet;
			}
			for (int userSum : userWin) {// sum of percent
				sumUserWin += userSum;
			}
			// System.out.printf("%n WIN Ratio : %d ", sumUserWin);TURNED OFF UNTIL GAME IS
			// OVER
			System.out.println("**************************************************:");
			System.out.println("HOUSE POINTS: " + HouseSum);
			System.out.printf("%n Total Bests Won: %d ", betSum);
			System.out.println();
			System.out.printf("%n Bank Balance : %d ", bankFunds);// LossbankSum

			// System.out.println("gamePercentage " + gamePercentage);

			if (gameCount == 4) {

				// System.out.printf("%n Bank Balance .: %d ", bankFunds);// (bankSum )
				System.out.println(" Game percentage : " + sumUserWin);
				System.out.println("Game Ended. Only four games allowed per cycle");
				System.exit(0);
			}

		}

	}

	private static void proportion() {
		// TODO Auto-generated method stub
		if (gameWin == 1) {
			gamePercentage = 25;

		} else if (gameWin == 2) {
			gamePercentage = 50;
		} else if (gameWin == 3) {
			gamePercentage = 75;
		} else if (gameWin == 4) {
			gamePercentage = 100;
		}
	}/*
		 * work on the percentage only the final percentage is right 1/1 is 100% not 25%
		 * Call to the main skips dealer's points 03/28/2018
		 */
}
