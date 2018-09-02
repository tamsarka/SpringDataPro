package tam.example.SpringDataPro.Entity.dto;

public class TOAssetDTO extends AssetDTO{
	ReservationDTO reservationId;

	public ReservationDTO getReservationId() {
		return reservationId;
	}

	public void setReservationId(ReservationDTO reservationId) {
		this.reservationId = reservationId;
	}
	
	
}
