package TrainPackage;

import java.sql.*;
import java.util.Scanner;

public class Train {

    static Scanner input = new Scanner(System.in);

    private static final String URL = "jdbc:mysql://localhost:3306/trains";
    private static final String USER = "root";
    private static final String PASSWORD = "Mouni@2005";

    // ---------------- ADD TRAIN ----------------

    public static boolean InsertTrain() {

        String name, destination, departureTime, arrivalTime;
        int trainNo, seats;
        float business, economy, second;

        System.out.print("Enter Train Number : ");
        trainNo = input.nextInt();
        input.nextLine();

        System.out.print("Enter Train Name : ");
        name = input.nextLine();

        System.out.print("Enter Total Seats : ");
        seats = input.nextInt();
        input.nextLine();

        System.out.print("Enter Destination : ");
        destination = input.nextLine();

        System.out.print("Enter Departure Time : ");
        departureTime = input.nextLine();

        System.out.print("Enter Arrival Time : ");
        arrivalTime = input.nextLine();

        System.out.print("Business Ticket Price : ");
        business = input.nextFloat();

        System.out.print("Economy Ticket Price : ");
        economy = input.nextFloat();

        System.out.print("Second Class Ticket Price : ");
        second = input.nextFloat();

        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {

            String sql = "INSERT INTO add_train(train_name,train_no,Total_seating_capacity,Destination,Timings,Arrival_time,B_Ticket,E_Ticket,S_Ticket) VALUES(?,?,?,?,?,?,?,?,?)";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, name);
            ps.setInt(2, trainNo);
            ps.setInt(3, seats);
            ps.setString(4, destination);
            ps.setString(5, departureTime);
            ps.setString(6, arrivalTime);
            ps.setFloat(7, business);
            ps.setFloat(8, economy);
            ps.setFloat(9, second);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Train Added Successfully.");
                return true;
            }

        } catch (Exception e) {
            System.out.println("Unable to Add Train.");
            e.printStackTrace();
        }

        return false;
    }

    // ---------------- UPDATE TRAIN ----------------

    public static boolean updateTrain() {

        System.out.print("Enter Train ID : ");
        int id = input.nextInt();
        input.nextLine();

        System.out.println("Columns:");
        System.out.println("train_name");
        System.out.println("train_no");
        System.out.println("Total_seating_capacity");
        System.out.println("Destination");
        System.out.println("Timings");
        System.out.println("Arrival_time");
        System.out.println("B_Ticket");
        System.out.println("E_Ticket");
        System.out.println("S_Ticket");

        System.out.print("Enter Column Name : ");
        String column = input.nextLine();

        if (!(column.equals("train_name")
                || column.equals("train_no")
                || column.equals("Total_seating_capacity")
                || column.equals("Destination")
                || column.equals("Timings")
                || column.equals("Arrival_time")
                || column.equals("B_Ticket")
                || column.equals("E_Ticket")
                || column.equals("S_Ticket"))) {

            System.out.println("Invalid Column Name.");
            return false;
        }

        System.out.print("Enter New Value : ");
        String value = input.nextLine();

        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {

            String sql = "UPDATE add_train SET " + column + "=? WHERE id_train=?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, value);
            ps.setInt(2, id);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Train Updated Successfully.");
                return true;
            }

            System.out.println("Train Not Found.");

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // ---------------- DELETE TRAIN ----------------

    public static boolean deleteTrain() {

        System.out.println("1. Delete by Train ID");
        System.out.println("2. Delete by Train Number");

        int choice = input.nextInt();

        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {

            PreparedStatement ps;

            if (choice == 1) {

                System.out.print("Enter Train ID : ");
                int id = input.nextInt();

                ps = con.prepareStatement("DELETE FROM add_train WHERE id_train=?");
                ps.setInt(1, id);

            } else if (choice == 2) {

                System.out.print("Enter Train Number : ");
                int number = input.nextInt();

                ps = con.prepareStatement("DELETE FROM add_train WHERE train_no=?");
                ps.setInt(1, number);

            } else {

                System.out.println("Invalid Choice.");
                return false;
            }

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Train Deleted Successfully.");
                return true;
            }

            System.out.println("Train Not Found.");

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // ---------------- DISPLAY ALL TRAINS ----------------

    public static boolean trainInfo() {

        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM add_train")) {

            while (rs.next()) {

                System.out.println("---------------------------------------------");
                System.out.println("Train ID : " + rs.getInt("id_train"));
                System.out.println("Train Name : " + rs.getString("train_name"));
                System.out.println("Train Number : " + rs.getInt("train_no"));
                System.out.println("Seats : " + rs.getInt("Total_seating_capacity"));
                System.out.println("Destination : " + rs.getString("Destination"));
                System.out.println("Departure : " + rs.getString("Timings"));
                System.out.println("Arrival : " + rs.getString("Arrival_time"));
                System.out.println("Business : " + rs.getFloat("B_Ticket"));
                System.out.println("Economy : " + rs.getFloat("E_Ticket"));
                System.out.println("Second : " + rs.getFloat("S_Ticket"));
                System.out.println("---------------------------------------------");
            }

            return true;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // ---------------- SEARCH TRAIN ----------------

    public static boolean searchTrain() {

        System.out.print("Enter Train ID : ");
        int id = input.nextInt();

        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {

            PreparedStatement ps = con.prepareStatement(
                    "SELECT * FROM add_train WHERE id_train=?");

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                System.out.println("Train ID : " + rs.getInt("id_train"));
                System.out.println("Train Name : " + rs.getString("train_name"));
                System.out.println("Train Number : " + rs.getInt("train_no"));
                System.out.println("Seats : " + rs.getInt("Total_seating_capacity"));
                System.out.println("Destination : " + rs.getString("Destination"));
                System.out.println("Departure : " + rs.getString("Timings"));
                System.out.println("Arrival : " + rs.getString("Arrival_time"));

                return true;
            }

            System.out.println("Train Not Found.");

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}