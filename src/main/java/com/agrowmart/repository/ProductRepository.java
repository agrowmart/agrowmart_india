package com.agrowmart.repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.util.Streamable;

import com.agrowmart.entity.ApprovalStatus;
import com.agrowmart.entity.Category;
import com.agrowmart.entity.Product;
import com.agrowmart.entity.Product.ProductStatus;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByMerchantId(Long merchantId);
    Page<Product> findAll(Specification<Product> spec, Pageable pageable);
    List<Product> findByStatus(Product.ProductStatus status);
  //Deepti Kadam
    List<Product> findAllByOrderByCreatedAtDesc();
    
    //Added by Ankita
    List<Product> findByApprovalStatusOrderByCreatedAtDesc(ApprovalStatus status);
    
    
    // ================= ADD FOR SERIAL NO (POINT 6) =================

    // 🔹 Find max serial number for a merchant
    @Query("select max(p.serialNo) from Product p where p.merchantId = :merchantId")
    Long findMaxSerialNoByMerchantId(Long merchantId);

    // 🔹 Used after delete to re-order serial numbers
    List<Product> findByMerchantIdAndStatusOrderBySerialNoAsc(
            Long merchantId,
            Product.ProductStatus status
    );
    
    // ✅ REQUIRED FOR PAGINATION (IMPORTANT)
    Page<Product> findByMerchantIdAndStatus(
            Long merchantId,
            Product.ProductStatus status,
            Pageable pageable
    );
	List<Product> findAll(Specification<Product> spec);
	
	
	List<Product> findByMerchantIdAndStatus(
            Long merchantId,
            Product.ProductStatus status
    );
	
	List<Product> findByStatusOrderByCreatedAtDesc(Product.ProductStatus status);
	
	
	
	
	@Query("""
		    SELECT p FROM Product p
		    JOIN User u ON p.merchantId = u.id
		    WHERE p.status = 'ACTIVE'
		      AND u.onlineStatus = 'ONLINE'
		      AND u.profileCompleted = 'YES'
		""")
		List<Product> findAllActiveFromOnlineVendors();

		@Query("""
		    SELECT p FROM Product p
		    JOIN User u ON p.merchantId = u.id
		    WHERE p.merchantId = :shopUserId
		      AND p.status = 'ACTIVE'
		      AND u.onlineStatus = 'ONLINE'
		      AND u.profileCompleted = 'YES'
		""")
		List<Product> findActiveByShopUserIdAndOnline(@Param("shopUserId") Long shopUserId);
	
	
	
}