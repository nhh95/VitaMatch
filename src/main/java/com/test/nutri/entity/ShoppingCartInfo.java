package com.test.nutri.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "shoppingCart")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ShoppingCartInfo {
	
	@Id
    @Column(name = "id")
    private Long id;
	
	@Column(name = "member_seq")
	private Long member_seq;
    
	@Column(name = "productInfo_seq")
	private Long productInfo_seq;
    
    @Column(name = "amount")
    private Long amount;
    
    @Column(name = "status")
    private Long status;
    
    @Column(name = "productImage")
    private String productImage;
    
    @Column(name = "CompanyName")
    private String companyName;
    
    @Column(name = "productName")
    private String productName;
    
    @Column(name = "price")
    private Long price;
}
