package TrainPackage;

import java.util.Scanner;

public class UserMenu {

    Scanner input = new Scanner(System.in);

    public void Menu() {

        int choice;

        while (true) {

            System.out.println("\n====================================");
            System.out.println("            USER MENU");
            System.out.println("====================================");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Enter Choice : ");

            choice = input.nextInt();

            switch (choice) {

                case 1:

                    Reg_User.reg();
                    break;

                case 2:

                    if (Reg_User.Login()) {

                        userOperations();
                    } else {

                        System.out.println("Invalid Username or Password.");
                    }

                    break;

                case 3:

                    return;

                default:

                    System.out.println("Invalid Choice.");
            }
        }
    }

    private void userOperations() {

        int choice;

        do {

            System.out.println("\n============== USER OPERATIONS ==============");
            System.out.println("1. Book Ticket");
            System.out.println("2. Search Train");
            System.out.println("3. View All Trains");
            System.out.println("4. Cancel Ticket");
            System.out.println("5. View Bookings");
            System.out.println("6. Change Password");
            System.out.println("7. Logout");
            System.out.print("Enter Choice : ");

            choice = input.nextInt();

            switch (choice) {

                case 1:

                    BookTickets book = new BookTickets();
                    book.book();
                    break;

                case 2:

                    Train.searchTrain();
                    break;

                case 3:

                    Train.trainInfo();
                    break;

                case 4:

                    Bookings.CancelTickets();
                    break;

                case 5:

                    Bookings.viewbookings();
                    break;

                case 6:

                    Reg_User.UpdatePassword();
                    break;

                case 7:

                    System.out.println("Logged Out Successfully.");
                    break;

                default:

                    System.out.println("Invalid Choice.");
            }

        } while (choice != 7);
    }
}