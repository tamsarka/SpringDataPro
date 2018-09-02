package tam.example.SpringDataPro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tam.example.SpringDataPro.Entity.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long>{

	public Reservation findOneByReservationName(String reservationName);
}
