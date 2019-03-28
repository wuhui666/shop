package com.pojo;

import java.util.List;

public class Category {
    private Integer cid;
	private String cname;
    private List<CategorySencond> categorySencondList;

    public List<CategorySencond> getCategorySencondList() {
		return categorySencondList;
	}

	public void setCategorySencondList(List<CategorySencond> categorySencondList) {
		this.categorySencondList = categorySencondList;
	}
	public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname == null ? null : cname.trim();
    }
}