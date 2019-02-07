package isa_api.dto;

public class BasicAvioCompanyDTO {

	private Long id;
	private String name;
	private String promotiveDescription;

	public BasicAvioCompanyDTO() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPromotiveDescription() {
		return promotiveDescription;
	}

	public void setPromotiveDescription(String promotiveDescription) {
		this.promotiveDescription = promotiveDescription;
	}

}
