package TrainPackage;

import java.util.Scanner;

public class TrainTicketReservationSystem {

    static Scanner Input = new Scanner(System.in);

    public static void main(String args[]) {

        int a, b;
        String s;

        System.out.println("\t--------------------------------------------");
        System.out.println("\tWELCOME TO TRAIN TICKET RESERVATION SYSTEM");
        System.out.println("\t--------------------------------------------");

        System.out.println("\t1.ADMIN");
        System.out.println("\t2.USER");
        System.out.print("\tENTER YOUR CHOICE : ");

        a = Input.nextInt();

        switch (a) {

            case 1:

                System.out.print("Enter Password: ");
                s = Input.next();

                if (s.equals("Admin&123")) {

                    System.out.println("\nACCESS GRANTED");

                    do {

                        System.out.println("\n1.TRAIN SECTION");
                        System.out.println("2.BOOKING SECTION");
                        System.out.println("3.LOG OUT");

                        b = Input.nextInt();

                        switch (b) {

                            case 1:
                                TrainSection t = new TrainSection();
                                t.Train_Menu();
                                break;

                            case 2:
                                ViewBookings.searchBookings();
                                break;

                            case 3:
                                System.out.println("Logged Out Successfully.");
                                break;

                            default:
                                System.out.println("Enter 1, 2 OR 3.");
                        }

                    } while (b != 3);

                } else {

                    System.out.println("Incorrect Admin Password.");
                }

                break;

            case 2:

                UserMenu U = new UserMenu();
                U.Menu();

                break;

            default:

                System.out.println("Enter 1 OR 2.");
        }
    }
}