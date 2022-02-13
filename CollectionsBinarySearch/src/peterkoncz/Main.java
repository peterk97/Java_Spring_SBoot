package peterkoncz;


/**
 * Simple seat booking system in theatre to see how these classes that we've used earlier fit into the
 * Java Collection Frameworks.
 * (seats will be numbered with a row letter and then a seat number within each row.)
 *
 * This project  is an example of a theatre and how you can create seats and also store theme in the arraylist
 * and some other function and an inner class for seats
 *
 * we are going to change the seats data structure for a few different format,
 * a list then also looking at turning it into a collection or making it more generic and see how that all works
 */
public class Main {

    public static void main(String[] args) {
	// Create an Instance of a class
        Theatre theatre = new Theatre("Sydney", 8,12);
//        theatre.getSeats();

    // make soma reservation
        if (theatre.reservedSeat("H02")){
            System.out.println("Please pay");
        }else{
            System.out.println("Sorry seat is taken");
        }
//        if (theatre.reservedSeat("H04")){
//            System.out.println("Please pay");
//        }else{
//            System.out.println("Sorry seat is taken");
//        }

    }
}
