package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.CreditCardNumber;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

@Embeddable
@Entity
@Access(AccessType.PROPERTY)
public class CreditCard extends DomainEntity{
	
	// Constructors -----------------------------------
	
	public CreditCard(){
		super();
	}
	
	// Attributes -------------------------------------
	
	private String holderName;
	private String brandName;
	private int expirationMonth;
	private int expirationYear;
	private int cVVCode;
	private String cardNumber;
	
	@NotBlank
	public String getHolderName() {
		return holderName;
	}
	public void setHolderName(String holderName) {
		this.holderName = holderName;
	}
	
	@NotBlank
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	
	@Range(min = 1 , max = 12)
	@NotNull
	public int getExpirationMonth() {
		return expirationMonth;
	}
	public void setExpirationMonth(int expirationMonth) {
		this.expirationMonth = expirationMonth;
	}
	
	@Min(2015)
	@NotNull
	public int getExpirationYear() {
		return expirationYear;
	}
	public void setExpirationYear(int expirationYear) {
		this.expirationYear = expirationYear;
	}
	
	@Range(min = 100, max = 999)
	@NotNull
	public int getcVVCode() {
		return cVVCode;
	}
	public void setcVVCode(int cVVCode) {
		this.cVVCode = cVVCode;
	}
	
	@Pattern(regexp="^\\d+$")
	@CreditCardNumber
	@NotBlank
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	
	// Relationships -------------------------------
	
}
