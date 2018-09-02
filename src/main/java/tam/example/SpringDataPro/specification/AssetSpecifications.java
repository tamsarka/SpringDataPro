package tam.example.SpringDataPro.specification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import tam.example.SpringDataPro.Entity.Asset;
import tam.example.SpringDataPro.Entity.User;
import tam.example.SpringDataPro.Entity.User_;
import tam.example.SpringDataPro.Entity.Asset_;

public class AssetSpecifications {

	public static Specification<Asset> searchAsset(final String assetName, final String serialNumber,
			final String userName) {

		return new Specification<Asset>() {
			@Override
			public Predicate toPredicate(Root<Asset> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				
				// join to the Material to have a possibility to check a part Number
				final Join<Asset, User> user = root.join(Asset_.user, JoinType.LEFT);
				
				// Constructing list of parameters
				List<Predicate> predicates = new ArrayList();
				
				if (serialNumber != "" && serialNumber != null) {
					predicates.add(builder.equal(builder.upper(root.<String> get(Asset_.assetSerialNumber)), serialNumber.toUpperCase()));
				}
				if (assetName != "" && assetName != null) {
					predicates.add(builder.equal(builder.upper(root.<String> get(Asset_.assetName)), serialNumber.toUpperCase()));
				}
				if (userName != "" && userName != null) {
					predicates.add(builder.equal(builder.upper(user.<String> get(User_.userName)), userName.toUpperCase()));
				}
				return builder.and(predicates.toArray(new Predicate[predicates.size()]));
			}

		};
	}
}
