package TrainPackage;

import java.sql.*;
import java.time.LocalDate;
import java.util.Scanner;

public class BookTickets {

    Scanner input = new Scanner(System.in);

    public boolean book() {

        int trainId, ticketCount, seatCount = 0;
        int choice;
        float business = 0, economy = 0, second = 0;
        float amount = 0;
        String destination = "";
        String ticketClass = "";

        System.out.print("Enter Train ID : ");
        trainId = input.nextInt();

        try (Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/trains",
                "root",
                "Mouni@2005")) {

            PreparedStatement ps = con.prepareStatement(
                    "SELECT * FROM add_train WHERE id_train=?");

            ps.setInt(1, trainId);

            ResultSet rs = ps.executeQuery();

            if (!rs.next()) {

                System.out.println("Invalid Train ID.");

                System.out.print("Search available trains (Y/N)? ");

                char ch = input.next().charAt(0);

                if (ch == 'Y' || ch == 'y') {
                    Train.searchTrain();
                }

                return false;
            }

            System.out.println("\n------------ TRAIN DETAILS ------------");

            System.out.println("Train ID : " + rs.getInt("id_train"));
            System.out.println("Train Name : " + rs.getString("train_name"));
            System.out.println("Train Number : " + rs.getInt("train_no"));
            System.out.println("Available Seats : " + rs.getInt("Total_seating_capacity"));
            System.out.println("Destination : " + rs.getString("Destination"));
            System.out.println("Departure : " + rs.getString("Timings"));
            System.out.println("Arrival : " + rs.getString("Arrival_time"));

            business = rs.getFloat("B_Ticket");
            economy = rs.getFloat("E_Ticket");
            second = rs.getFloat("S_Ticket");

            seatCount = rs.getInt("Total_seating_capacity");
            destination = rs.getString("Destination");

            if (seatCount == 0) {

                System.out.println("No Seats Available.");

                return false;
            }

            System.out.println("Departure Date : " + LocalDate.now());

            System.out.print("Enter Number of Tickets : ");
            ticketCount = input.nextInt();

            // ************** FIXED BUG **************

            if (ticketCount > seatCount) {

                System.out.println("Only " + seatCount + " seats available.");

                return false;
            }

            System.out.println("\n1.Business");
            System.out.println("2.Economy");
            System.out.println("3.Second");

            System.out.print("Choose Class : ");

            choice = input.nextInt();

            switch (choice) {

                case 1:
                    amount = business * ticketCount;
                    ticketClass = "Business";
                    break;

                case 2:
                    amount = economy * ticketCount;
                    ticketClass = "Economy";
                    break;

                case 3:
                    amount = second * ticketCount;
                    ticketClass = "Second";
                    break;

                default:

                    System.out.println("Invalid Choice.");

                    return false;
            }

            System.out.println("Total Amount : " + amount);

            System.out.print("Confirm Booking (Y/N) : ");

            char confirm = input.next().charAt(0);

            if (!(confirm == 'Y' || confirm == 'y')) {

                System.out.println("Booking Cancelled.");

                return false;
            }

            // ************** FIXED QUERY **************

            PreparedStatement update = con.prepareStatement(

                    "UPDATE add_train SET Total_seating_capacity=? WHERE id_train=?");

            update.setInt(1, seatCount - ticketCount);
            update.setInt(2, trainId);

            update.executeUpdate();

            new Booking_Report(trainId,
                    destination,
                    ticketClass,
                    ticketCount,
                    amount);

            Booking_Report.report();

            System.out.println("Ticket Booked Successfully.");

            return true;

        } catch (Exception e) {

            e.printStackTrace();
        }

        return false;
    }
}