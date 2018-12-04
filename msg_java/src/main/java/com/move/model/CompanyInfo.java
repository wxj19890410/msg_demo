package com.move.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name="company_info")
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler"})
public class CompanyInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    private  Integer id;

    private  String name;
    
    @Column(name = "code_uuid")
    private  String codeUuid;
    
    private  String autograph;
    
    private  BigDecimal amount;
    
    @Column(name = "msg_flag")
    private  String msgFlag;
    
    @Column(name = "del_flag")
    private  String delFlag;
    
    @Column(name = "edit_date")
    private  Date editDate;
    
    @Column(name = "create_date")
    private  Date createDate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCodeUuid() {
		return codeUuid;
	}

	public void setCodeUuid(String codeUuid) {
		this.codeUuid = codeUuid;
	}

	public String getAutograph() {
		return autograph;
	}

	public void setAutograph(String autograph) {
		this.autograph = autograph;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getMsgFlag() {
		return msgFlag;
	}

	public void setMsgFlag(String msgFlag) {
		this.msgFlag = msgFlag;
	}

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

	public Date getEditDate() {
		return editDate;
	}

	public void setEditDate(Date editDate) {
		this.editDate = editDate;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
    

}
