package TrainPackage;

import java.util.Scanner;

public class TrainSection {

    Scanner input = new Scanner(System.in);

    public void Train_Menu() {

        int choice;

        do {

            System.out.println("\n==================================================");
            System.out.println("            TRAIN MANAGEMENT SECTION");
            System.out.println("==================================================");
            System.out.println("1. Add Train");
            System.out.println("2. Delete Train");
            System.out.println("3. Update Train");
            System.out.println("4. Search Train");
            System.out.println("5. View All Trains");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            choice = input.nextInt();

            switch (choice) {

                case 1:
                    Train.InsertTrain();
                    break;

                case 2:
                    Train.deleteTrain();
                    break;

                case 3:
                    Train.updateTrain();
                    break;

                case 4:
                    Train.searchTrain();
                    break;

                case 5:
                    Train.trainInfo();
                    break;

                case 6:
                    System.out.println("Returning to Admin Menu...");
                    break;

                default:
                    System.out.println("Invalid Choice.");
            }

        } while (choice != 6);
    }
}