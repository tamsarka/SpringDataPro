package tam.example.SpringDataPro.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Reservation{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	Long reservationId;
	
	String reservationName;

	protected Reservation(){
		
	}

	public Reservation(String reservationName) {
		super();
		this.reservationName = reservationName;
	}

	public Long getReservationId() {
		return reservationId;
	}

	public void setReservationId(Long reservationId) {
		this.reservationId = reservationId;
	}

	public String getReservationName() {
		return reservationName;
	}

	public void setReservationName(String reservationName) {
		this.reservationName = reservationName;
	}
	
	
}
