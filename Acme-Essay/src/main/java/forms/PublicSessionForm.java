package forms;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import domain.Essay;

public class PublicSessionForm {
	
	private Integer publicSessionId;
	private Essay essay;
	
	@Min(1)
	@NotNull
	public Integer getPublicSessionId() {
		return publicSessionId;
	}
	public void setPublicSessionId(Integer publicSessionId) {
		this.publicSessionId = publicSessionId;
	}
	
	@Valid
	@NotNull
	public Essay getEssay() {
		return essay;
	}
	public void setEssay(Essay essay) {
		this.essay = essay;
	}

}
