package TrainPackage;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Booking_Report {

    static int trainId;
    static int ticketCount;
    static String destination;
    static String ticketClass;
    static float amount;

    private static final String URL = "jdbc:mysql://localhost:3306/trains";
    private static final String USER = "root";
    private static final String PASSWORD = "Mouni@2005";

    public Booking_Report(int trainId,
                          String destination,
                          String ticketClass,
                          int ticketCount,
                          float amount) {

        Booking_Report.trainId = trainId;
        Booking_Report.destination = destination;
        Booking_Report.ticketClass = ticketClass;
        Booking_Report.ticketCount = ticketCount;
        Booking_Report.amount = amount;
    }

    public static void report() {

        LocalDateTime now = LocalDateTime.now();

        System.out.println("\n==============================================================");
        System.out.println("                    BOOKING RECEIPT");
        System.out.println("==============================================================");
        System.out.println("User Name    : " + Reg_User.getN());
        System.out.println("Train ID     : " + trainId);
        System.out.println("Destination  : " + destination);
        System.out.println("Class        : " + ticketClass);
        System.out.println("Tickets      : " + ticketCount);
        System.out.println("Amount       : " + amount);
        System.out.println("Date & Time  : " + now);
        System.out.println("==============================================================");

        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {

            String sql =
                    "INSERT INTO bookings(UserName,Train_Id,Destination,classBooked,NoOfTickets,Total_Amount,DateTime) VALUES(?,?,?,?,?,?,?)";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, Reg_User.getN());
            ps.setInt(2, trainId);
            ps.setString(3, destination);
            ps.setString(4, ticketClass);
            ps.setInt(5, ticketCount);
            ps.setFloat(6, amount);
            ps.setString(7, now.toString());

            ps.executeUpdate();

            System.out.println("Booking Saved Successfully.");

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}

class Bookings {

    static Scanner input = new Scanner(System.in);

    private static final String URL = "jdbc:mysql://localhost:3306/trains";
    private static final String USER = "root";
    private static final String PASSWORD = "Mouni@2005";
    // ---------------- VIEW BOOKINGS ----------------

    public static void viewbookings() {

        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {

            String sql = "SELECT * FROM bookings WHERE UserName=?";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, Reg_User.getN());

            ResultSet rs = ps.executeQuery();

            boolean found = false;

            while (rs.next()) {

                found = true;

                System.out.println("------------------------------------------------------------");
                System.out.println("User Name      : " + rs.getString("UserName"));
                System.out.println("Train ID       : " + rs.getInt("Train_Id"));
                System.out.println("Destination    : " + rs.getString("Destination"));
                System.out.println("Class          : " + rs.getString("classBooked"));
                System.out.println("Tickets        : " + rs.getInt("NoOfTickets"));
                System.out.println("Amount         : " + rs.getFloat("Total_Amount"));
                System.out.println("Booking Time   : " + rs.getTimestamp("DateTime"));
                System.out.println("------------------------------------------------------------");
            }

            if (!found) {
                System.out.println("No Bookings Found.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ---------------- CANCEL TICKET ----------------

    public static void CancelTickets() {

        System.out.print("Enter Train ID to Cancel : ");
        int trainId = input.nextInt();

        System.out.print("Confirm Cancellation (Y/N) : ");
        char ch = input.next().charAt(0);

        if (!(ch == 'Y' || ch == 'y')) {
            System.out.println("Cancellation Cancelled.");
            return;
        }

        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {

            String bookingSql =
                    "SELECT NoOfTickets FROM bookings WHERE UserName=? AND Train_Id=?";

            PreparedStatement bookingPs =
                    con.prepareStatement(bookingSql);

            bookingPs.setString(1, Reg_User.getN());
            bookingPs.setInt(2, trainId);

            ResultSet bookingRs = bookingPs.executeQuery();

            if (!bookingRs.next()) {
                System.out.println("Booking Not Found.");
                return;
            }

            int bookedSeats = bookingRs.getInt("NoOfTickets");

            String seatSql =
                    "SELECT Total_seating_capacity FROM add_train WHERE id_train=?";

            PreparedStatement seatPs =
                    con.prepareStatement(seatSql);

            seatPs.setInt(1, trainId);

            ResultSet seatRs = seatPs.executeQuery();

            if (!seatRs.next()) {
                System.out.println("Train Not Found.");
                return;
            }

            int availableSeats =
                    seatRs.getInt("Total_seating_capacity");
            String updateSql =
                    "UPDATE add_train SET Total_seating_capacity=? WHERE id_train=?";

            PreparedStatement updatePs =
                    con.prepareStatement(updateSql);

            updatePs.setInt(1, availableSeats + bookedSeats);
            updatePs.setInt(2, trainId);

            updatePs.executeUpdate();

            String deleteSql =
                    "DELETE FROM bookings WHERE UserName=? AND Train_Id=?";

            PreparedStatement deletePs =
                    con.prepareStatement(deleteSql);

            deletePs.setString(1, Reg_User.getN());
            deletePs.setInt(2, trainId);

            int rows = deletePs.executeUpdate();

            if (rows > 0) {
                System.out.println("Ticket Cancelled Successfully.");
            } else {
                System.out.println("Unable to Cancel Ticket.");
            }

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}

class ViewBookings {

    static Scanner input = new Scanner(System.in);

    private static final String URL = "jdbc:mysql://localhost:3306/trains";
    private static final String USER = "root";
    private static final String PASSWORD = "Mouni@2005";

    public static void searchBookings() {

        System.out.println("\nSearch Bookings");
        System.out.println("1. Search by Train ID");
        System.out.println("2. Search by User Name");
        System.out.print("Enter Choice : ");

        int choice = input.nextInt();

        switch (choice) {

            case 1:
                searchByTrainId();
                break;

            case 2:
                searchByUserName();
                break;

            default:
                System.out.println("Invalid Choice.");
        }
    }

    private static void searchByTrainId() {

        System.out.print("Enter Train ID : ");

        int trainId = input.nextInt();

        String sql = "SELECT * FROM bookings WHERE Train_Id=?";

        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, trainId);

            ResultSet rs = ps.executeQuery();

            boolean found = false;

            while (rs.next()) {

                found = true;
                displayBooking(rs);
            }

            if (!found) {

                System.out.println("No Bookings Found.");
            }

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
    private static void searchByUserName() {

        System.out.print("Enter User Name : ");

        String username = input.next();

        String sql = "SELECT * FROM bookings WHERE UserName=?";

        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, username);

            ResultSet rs = ps.executeQuery();

            boolean found = false;

            while (rs.next()) {

                found = true;
                displayBooking(rs);
            }

            if (!found) {

                System.out.println("No Bookings Found.");
            }

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    private static void displayBooking(ResultSet rs) throws SQLException {

        System.out.println("------------------------------------------------------------");
        System.out.println("User Name      : " + rs.getString("UserName"));
        System.out.println("Train ID       : " + rs.getInt("Train_Id"));
        System.out.println("Destination    : " + rs.getString("Destination"));
        System.out.println("Class          : " + rs.getString("classBooked"));
        System.out.println("Tickets        : " + rs.getInt("NoOfTickets"));
        System.out.println("Total Amount   : " + rs.getFloat("Total_Amount"));
        System.out.println("Booking Time   : " + rs.getTimestamp("DateTime"));
        System.out.println("------------------------------------------------------------");
    }
}

