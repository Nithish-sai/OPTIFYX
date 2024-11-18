import java.util.ArrayList;
import java.util.Scanner;

 class Reservation
{
 private String name;
 private String contact;
 private String date;
 private int numberOfSeats;

  public Reservation(String name, String contact, String date, int numberOfSeats)
  {
    this.name=name;
    this.contact=contact;
    this.date=date;
    this.numberOfSeats=numberOfSeats;
  }

@Override
  public String toString() 
  {
    return "Name: " + name + ", Contact: " + contact + ", Date: " + date + ", Seats: " + numberOfSeats;
  }
}

class BusReservationSystem
{
    private ArrayList<Reservation> reservations;

    public BusReservationSystem() 
    {
        reservations = new ArrayList<>();
    }

    public void addReservation(String name, String contact, String date, int numberOfSeats)
    {
        reservations.add(new Reservation(name,contact,date,numberOfSeats));
        System.out.println("Reservation added successfully");
    }

    public void viewReservation()
    {
        if(reservations.isEmpty())
        {
            System.out.println("No reservation found");
        }
        else
        {
            reservations.forEach(System.out::println);
        }
    }

    public void cancelReservation(String name)
    {
        boolean found = false;
        for(int i=0;i<reservations.size();i++)
        {
            if(reservations.get(i).toString().contains(name))
            {
                reservations.remove(i);
                System.out.println("Reservation cancelled successfully");
                found = true;
                break;
            }
        }
        if(!found)
        {
            System.out.println("No reservation founf for : " + name);
        }
    }

}

public class OnlineReservationSystem
{
    public static void main(String[] args)
    {
        BusReservationSystem busReservationSystem = new BusReservationSystem();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do
        {
            System.out.println("\n Bus Reservation System");
            System.out.println("1.Add Reservation ");
            System.out.println("2. view reservation ");
            System.out.println("3. cancel reservation ");
            System.out.println("4.Exit");
            System.out.println("Enter your choice : ");
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                      System.out.print("Enter name : ");
                      String name = scanner.nextLine();
                      System.out.print("Enter contact : ");
                      String contact = scanner.nextLine();
                      System.out.print("Enter date (YYYY-MM-DD): ");
                      String date = scanner.nextLine();
                      System.out.print("Enter no of seats : ");
                      int numberOfSeats = scanner.nextInt();
                      busReservationSystem.addReservation(name, contact, date, numberOfSeats);
                      break;    
                case 2:
                      busReservationSystem.viewReservation();
                      break;
                case 3:
                      System.out.print("Enter to cancel Reservation : ");
                      String cancelName = scanner.nextLine();
                      busReservationSystem.cancelReservation(cancelName); 
                      break;
                case 4:
                      System.out.println("Exiting the System");
                      break;

                default:
                    System.out.println("Invalid choice. Please try again");
            }
        }while(choice != 4);

        scanner.close();
        
    }
}
