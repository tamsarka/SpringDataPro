package tam.example.SpringDataPro.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import tam.example.SpringDataPro.Entity.Asset;
import tam.example.SpringDataPro.Entity.LogAsset;
import tam.example.SpringDataPro.Entity.Reservation;
import tam.example.SpringDataPro.Entity.TOAsset;
import tam.example.SpringDataPro.Entity.User;
import tam.example.SpringDataPro.Entity.dto.AssetDTO;
import tam.example.SpringDataPro.Entity.dto.LogAssetDTO;
import tam.example.SpringDataPro.Entity.dto.ReservationDTO;
import tam.example.SpringDataPro.Entity.dto.TOAssetDTO;
import tam.example.SpringDataPro.Entity.dto.UserDTO;

@Mapper
public abstract class AssetMapper {
	public abstract UserDTO userToUserDTO(User user);
	public abstract ReservationDTO reservationToReservationDTO(Reservation reservation);
	public abstract AssetDTO assetTOAssetDTO(Asset asset);
	public abstract LogAssetDTO logAssetToLogAssetDTO(LogAsset asset);
	public abstract TOAssetDTO toAssetTOAssetDTO(TOAsset asset);
	public abstract List<AssetDTO> assetListToAssetDTOList(List<Asset> assetList);
//	public abstract List<? extends AssetDTO> assetTypeListToAssetDTOTypeList(List<? extends Asset> assetList);
	public abstract List<LogAssetDTO> logAssetListToAssetDTOList(List<LogAsset> assetList);
}
