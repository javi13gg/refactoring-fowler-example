package ubu.gii.dass.refactoring;

/**
* Tema  Refactorizaciones 
*
* Ejemplo de aplicacion de refactorizaciones. Actualizado para colecciones genï¿½ricas de java 1.5
*
* @author M. Fowler y Diego Martín
* @version 1.1
* @see java.io.File
*
*/
import java.util.*;

public class Customer {
	private String _name;
	private List<Rental> _rentals;

	public Customer(String name) {
		_name = name;
		_rentals = new ArrayList<Rental>();

	};

	public void addRental(Rental arg) {
		_rentals.add(arg);
	}

	public String getName() {
		return _name;
	};

	public String statement() {
		double totalAmount = 0;
		int frequentRenterPoints = 0;
		Iterator<Rental> rentals = _rentals.iterator();
		String result = "Rental Record for " + getName() + "\n";
		while (rentals.hasNext()) {
			double thisAmount = 0;
			Rental each = rentals.next();
			// determine amounts for each line
			thisAmount = each.amountFor();
			
			frequentRenterPoints = getFrecuentRenderPoints(frequentRenterPoints, each);
			
			// show figures for this rental
			result += "\t" + each.getMovie().getTitle() + "\t"
					+ String.valueOf(thisAmount) + "\n";
			totalAmount += thisAmount;
		}
		// add footer lines
		result += "Amount owed is " + String.valueOf(totalAmount) + "\n";
		result += "You earned " + String.valueOf(frequentRenterPoints)
				+ " frequent renter points";
		return result;
	}

	private int getFrecuentRenderPoints(int frequentRenterPoints, Rental renta) {
		// add frequent renter points
		frequentRenterPoints++;
		
		// add bonus for a two day new release rental
		if ((renta.getMovie().getPriceCode() == Movie.NEW_RELEASE)
				&& renta.getDaysRented() > 1)
			frequentRenterPoints++;
		return frequentRenterPoints;
	}
}
