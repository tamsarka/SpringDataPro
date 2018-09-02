package tam.example.SpringDataPro.Entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class TOAsset extends Asset{
	

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "reservationId")
	Reservation reservationId;
	
	public TOAsset() {
		// TODO Auto-generated constructor stub
	}
	
	public TOAsset(Reservation reservation, String toName, User user, String assetSerialNumber, String description, AssetFamily family) {
		super(toName, assetSerialNumber, description, user, family);
		this.reservationId = reservation;
	}

	public Reservation getReservationId() {
		return reservationId;
	}

	public void setReservationId(Reservation reservationId) {
		this.reservationId = reservationId;
	}

	
	
}
