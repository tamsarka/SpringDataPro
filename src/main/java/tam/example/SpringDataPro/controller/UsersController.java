package tam.example.SpringDataPro.controller;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.mapstruct.factory.Mappers;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import tam.example.SpringDataPro.Entity.Address;
import tam.example.SpringDataPro.Entity.Asset;
import tam.example.SpringDataPro.Entity.AssetFamily;
import tam.example.SpringDataPro.Entity.LogAsset;
import tam.example.SpringDataPro.Entity.Reservation;
import tam.example.SpringDataPro.Entity.TOAsset;
import tam.example.SpringDataPro.Entity.User;
import tam.example.SpringDataPro.Entity.dto.AssetDTO;
import tam.example.SpringDataPro.Entity.dto.LogAssetDTO;
import tam.example.SpringDataPro.mapper.AssetMapper;
import tam.example.SpringDataPro.repository.AddressRepository;
import tam.example.SpringDataPro.repository.AssetFamilyRepo;
import tam.example.SpringDataPro.repository.AssetRepository;
import tam.example.SpringDataPro.repository.LogAssetRepository;
import tam.example.SpringDataPro.repository.ReservationRepository;
import tam.example.SpringDataPro.repository.TOAssetRepository;
import tam.example.SpringDataPro.repository.UserRepository;
import tam.example.SpringDataPro.specification.AssetSpecifications;

@RestController
public class UsersController {
	
	@Inject
	RandomController randomCtrl;

	@Autowired
	UserRepository repo;
	
	@Autowired
	AddressRepository addressRepo;
	
	@Autowired
	ReservationRepository reservationRepo;
	
	@Autowired
	LogAssetRepository logRepo;
	
	@Autowired
	TOAssetRepository toRepo;
	
	
	@Autowired
	AssetRepository assetRepo;
	
	@Autowired
	AssetFamilyRepo assetFamilyRepo;
	
//	@Autowired
	AssetMapper assetMapper = Mappers.getMapper(AssetMapper.class);
	
	
	final static Logger logger = org.slf4j.LoggerFactory.getLogger(UsersController.class);
	
	@GetMapping("/api")
	public String returnHello(){
		String str = randomCtrl.getRandomNumber();
		logger.info("This is logger level info....."+str);
		logger.debug("This is logger level debug....."+str);
		return "Hello"+str;
	}
	
	@GetMapping(value="/addAddress")
	public String saveAddress(){
		Arrays.asList("Malda Sarkar,Kolkata Kundu".split(","))
			  .forEach(n -> addressRepo.save(new Address(n)));
		
		return "Saved address Properly";
	}
	
	@GetMapping(value="/addFamily")
	public String saveFamilies(){
		Arrays.asList("Malda family,Kolkata family".split(","))
		  .forEach(n -> assetFamilyRepo.save(new AssetFamily(n)));
		return "Saved address Properly";
	}
	
	@GetMapping(value="/addUsers")
	public String saveAll(){
		Arrays.asList("Tamal,Sarkar,Ayantika,Kundu".split(","))
			  .forEach(n -> repo.save(new User(n)));
		return "Saved Properly";
	}
	
	@PostMapping(value="/addManager")
	public String saveAll(@RequestParam(value="userName", required=true) String userNAme, @RequestParam(value="managerName", required=true)String managerName){
		User user = repo.findOneByUserName(userNAme);
		User manger = repo.findOneByUserName(managerName);
		user.setManager(manger);
		repo.save(user);
		return "Saved manager Properly";
	}
	
	@PostMapping(value="/tagAddressToUser")
	public String tagAddressToUser(@RequestParam(value="userName", required=true) String userNAme, @RequestParam(value="id", required=true)String addressId){
		User user = repo.findOneByUserName(userNAme);
		Address address = addressRepo.findOne(Long.parseLong(addressId));
		user.setAddress(address);
		repo.save(user);
		return "Saved address Properly";
	}
	
	/*@PostMapping(value="/tagAssetToFamily")
	public String tagAssetToFamily(@RequestParam(value="familyId", required=true) String familyId, @RequestParam(value="assetId", required=true)String assetId){
		AssetFamily family = assetFamilyRepo.findOne(Long.parseLong(familyId));
		Asset asset = assetRepo.findOne(Long.parseLong(assetId));
		Set<Asset> assetListOfAFamily = family.getAssetList();
		System.out.println(assetListOfAFamily.size());
		assetListOfAFamily.add(asset);
		
		assetFamilyRepo.save(family);
		return "tagged family and asset Properly";
	}*/
	
	@GetMapping(value="/families")
	public List<AssetFamily> findAllFamilies(){
		System.out.println("families are :");
		List<AssetFamily> families = assetFamilyRepo.findAll();
		families.forEach(n -> System.out.println(n.getAssetList().size()));
		
		return families;
	}
	
	@GetMapping(value="/users")
//	@Cacheable()
	public List<User> findAll(){
		System.out.println("users are :");
		List<User> users = repo.findAll();
		users.forEach(System.out::println);
		return users;
		
	}
	
	@GetMapping(value="/deleteUsers/{id}")
	public String saveAll(@PathVariable Long id){
		repo.delete(id);
		return "deleted Properly";
	}
	
	@GetMapping(value="/addReservation")
	public String addReservation(){
		Arrays.asList("Reservation,Reservation 1,Reservation 2,Reservation 3".split(","))
			  .forEach(n -> reservationRepo.save(new Reservation(n)));
		return "reservation Saved Properly";
	}
	 /**
	  * 
	  * @return
	  */
	@GetMapping(value="/reservation")
	public List<Reservation> findAllReservation(){
		List<Reservation> reserv = reservationRepo.findAll();
//		reserv.forEach(System.out::println);
		return reserv;
		
	}
	
	
	@PostMapping(value="/addLogAsset")
	public String addLogAsset(@RequestParam(value = "name", required = false) String assetName, @RequestParam(value="userName") String userName,  @RequestParam(value="familyuId") String familyId){
		User user = repo.findOneByUserName(userName);
		Random rand = new Random();
		AssetFamily family = assetFamilyRepo.findOne(Long.parseLong(familyId));
		logRepo.save(new LogAsset(assetName, user, true, false,""+rand.nextInt(10000), "its a logistic one", family));
//		Arrays.asList("Reservation,Reservation 1,Reservation 2,Reservation 3".split(","))
//			  .forEach(n -> reservationRepo.save(new Reservation(n)));
		return "Log Saved Properly";
	}
	
	
	@PostMapping(value="/addTOAsset")
	public String addTOAsset(@RequestParam(value = "name", required = false) String assetName, @RequestParam(value="userName") String userName, 
				@RequestParam(value="resetvationId") String reservationName,   @RequestParam(value="familyuId") String familyId){
		User user = repo.findOneByUserName(userName);
		Random rand = new Random();
		AssetFamily family = assetFamilyRepo.findOne(Long.parseLong(familyId));
		Reservation reservation  = reservationRepo.findOneByReservationName(reservationName);
		toRepo.save(new TOAsset(reservation, assetName, user, ""+rand.nextInt(10000), "its a logistic one",family));

		return "TO Saved Properly";
	}
	
	
	//                          ******************** FOR RETRIVING ****************************
	
	
	@GetMapping(value="/toAsset")
	public List<TOAsset> findAllToAsset(){
		List<TOAsset> asset = toRepo.findAll();
//		reserv.forEach(System.out::println);
		return asset;
	}
	
	@GetMapping(value="/logAsset")
	public List<LogAssetDTO> findAllLogAsset(){
		List<LogAsset> asset = logRepo.findAll();
//		reserv.forEach(System.out::println);
		return assetMapper.logAssetListToAssetDTOList(asset);
	}
	
	@GetMapping(value="/latestAsset/{familyId}")
	public Asset findLatestAsset(@PathVariable Long familyId ){
		AssetFamily family = assetFamilyRepo.findOne(familyId);
		Date start = new Date();
		Asset asset = assetRepo.findByFamilyOrderByAssetSerialNumberDesc(family).get(0);
				
		System.out.println(" Time taken :"+ (new Date().getTime() - start.getTime()));
		start = new Date();
		asset = assetRepo.findTopByFamilyOrderByAssetSerialNumberDesc(family);
				
		System.out.println(" Time taken for first :"+ (new Date().getTime() - start.getTime()));
		start = new Date();
		asset = assetRepo.findFirstByFamilyOrderByAssetSerialNumberDesc(family);
		System.out.println(" Time taken for first :"+ (new Date().getTime() - start.getTime()));
		return asset;
	}
	
	@Transactional
	@PostMapping(value="/assetBySpec")
	public List<AssetDTO> findAllAssets(@RequestParam(value = "assetName", required = false) String assetName, @RequestParam(value = "serialNumber", required = false) String serialNumber,
				@RequestParam(value = "userName", required = false) String userName){
		List<Asset> asset = assetRepo.findAll(AssetSpecifications.searchAsset(assetName, serialNumber, userName));
		/*asset.stream()
		 .filter(o -> o.getUser() != null)
		 .forEach(s -> {
			 User user = s.getUser();
			 System.out.println(user.getUserId());
			 System.out.println(user.getUserName());
		 });*/
		return assetMapper.assetListToAssetDTOList(asset);
//		return assetMapper.assetTypeListToAssetDTOTypeList(asset);
	}

	@Transactional
	@GetMapping(value="/asset/{id}")
	public List<AssetDTO> findAllAssets(@PathVariable Long id){
		List<Asset> asset = assetRepo.findAll();
		/*asset.stream()
			 .filter(o -> o.getUser() != null)
			 .forEach(s -> {
				 User user = s.getUser();
				 System.out.println(user.getUserId());
				 System.out.println(user.getUserName());
			 });*/
//		reserv.forEach(System.out::println);
		/*if(id == 1){
		//TODO but ignired now
		Collections.sort(asset, new AlertEventComparator());
		} else if (id == 2){
			Collections.sort(asset, new AlertComparator());
		}else {
			Collections.sort(asset, new EventComparator());
		}*/
		return assetMapper.assetListToAssetDTOList(asset);
		
	}
	
	
	private class AlertEventComparator implements Comparator<Asset>{
		@Override
		public int compare(Asset o1, Asset o2) {
			
			Reservation toData1 = ((TOAsset)o1).getReservationId();
			Reservation toData2 = ((TOAsset)o2).getReservationId();
 
			return new CompareToBuilder()
	                .append(toData1.getReservationId(), toData2.getReservationId())
//	                .append(o1.getAssetName(), o2.getAssetName())
	                .toComparison();
		}
		
	}
	
	
	private class AlertComparator implements Comparator<Asset>{
		@Override
		public int compare(Asset o1, Asset o2) {
			
			Reservation toData1 = ((TOAsset)o1).getReservationId();
			Reservation toData2 = ((TOAsset)o2).getReservationId();
 
			return new CompareToBuilder()
//	                .append(toData1.getReservationId(), toData2.getReservationId())
	                .append(o1.getAssetName(), o2.getAssetName())
	                .toComparison();
		}
		
	}
	
	private class EventComparator implements Comparator<Asset>{
		@Override
		public int compare(Asset o1, Asset o2) {
			
			Reservation toData1 = ((TOAsset)o1).getReservationId();
			Reservation toData2 = ((TOAsset)o2).getReservationId();
 
			return new CompareToBuilder()
	                .append(toData1.getReservationId(), toData2.getReservationId())
	                .append(o1.getAssetName(), o2.getAssetName())
	                .toComparison();
		}
		
	}
}
