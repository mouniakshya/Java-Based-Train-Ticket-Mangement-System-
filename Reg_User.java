package TrainPackage;

import java.sql.*;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Reg_User {

    static String n;
    static Scanner input = new Scanner(System.in);

    private static final String URL = "jdbc:mysql://localhost:3306/trains";
    private static final String USER = "root";
    private static final String PASSWORD = "Mouni@2005";

    public static void reg() {

        String name, pass, Email;
        long mobile, CNIC;

        System.out.print("Enter Name: ");
        name = input.next();

        System.out.print("Mobile No: ");
        mobile = input.nextLong();

        System.out.print("Enter CNIC: ");
        CNIC = input.nextLong();

        System.out.print("Enter Email Address: ");
        Email = input.next();

        while (!validation.emailVerification(Email)) {
            System.out.print("Enter Email Address: ");
            Email = input.next();
        }

        System.out.print("Enter a strong password: ");
        pass = input.next();

        while (!validation.PassVerification(pass)) {
            System.out.print("Enter a strong password: ");
            pass = input.next();
        }

        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {

            String q = "INSERT INTO user_info(User_Name,Mobile_NO,password,email,CNIC) VALUES(?,?,?,?,?)";

            PreparedStatement st = con.prepareStatement(q);

            st.setString(1, name);
            st.setLong(2, mobile);
            st.setString(3, pass);
            st.setString(4, Email);
            st.setLong(5, CNIC);

            st.executeUpdate();

            System.out.println("Your Account has been created.");

        } catch (Exception e) {

            System.out.println("Some error occurred while registration.");

            e.printStackTrace();
        }
    }

    public static boolean Login() {

        String p;

        System.out.println("------ LOGIN ------");

        System.out.print("User Name : ");
        n = input.next();

        System.out.print("Password  : ");
        p = input.next();

        setN(n);

        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {

            String q =
                    "SELECT User_Name,password FROM user_info WHERE User_Name=? AND password=?";

            PreparedStatement st = con.prepareStatement(q);

            st.setString(1, n);
            st.setString(2, p);

            ResultSet res = st.executeQuery();

            if (res.next()) {

                System.out.println("***** WELCOME USER *****");

                return true;
            }

            System.out.println("Incorrect Username or Password.");

            return false;

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
    }

    public static void setN(String n) {

        Reg_User.n = n;
    }

    public static String getN() {

        return n;
    }

    public static void UpdatePassword() {

        String pass;

        System.out.print("Enter New Password: ");

        pass = input.next();

        while (!validation.PassVerification(pass)) {

            System.out.print("Enter Strong Password: ");

            pass = input.next();
        }

        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {

            String q =
                    "UPDATE user_info SET password=? WHERE User_Name=?";

            PreparedStatement s = con.prepareStatement(q);

            s.setString(1, pass);
            s.setString(2, Reg_User.getN());

            int rows = s.executeUpdate();

            if (rows > 0) {

                System.out.println("PASSWORD UPDATED.");

            } else {

                System.out.println("User not found.");
            }

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}

class validation {

    public static boolean emailVerification(String email) {

        Pattern pattern = Pattern.compile(
                "[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}");

        Matcher matcher = pattern.matcher(email);

        if (matcher.matches()) {
            return true;
        }

        System.out.println("Not a valid Email Address.");
        return false;
    }

    public static boolean PassVerification(String password) {

        String PASSWORD_PATTERN =
                "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()\\-\\[{}\\]:;',?/*~$^+=<>]).{8,20}$";

        Pattern pattern = Pattern.compile(PASSWORD_PATTERN);

        Matcher matcher = pattern.matcher(password);

        if (matcher.matches()) {
            return true;
        }

        System.out.println("Not a valid Password.");
        return false;
    }
}