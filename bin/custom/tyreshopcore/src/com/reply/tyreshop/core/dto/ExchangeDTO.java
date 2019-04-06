package com.reply.tyreshop.core.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class ExchangeDTO {

    @JsonProperty("Cur_ID")
    private Integer curId;

    @JsonProperty("Date")
    private Date date;

    @JsonProperty("Cur_Abbreviation")
    private String curAbbreviation;

    @JsonProperty("Cur_Scale")
    private Integer curScale;

    @JsonProperty("Cur_Name")
    private String curName;

    @JsonProperty("Cur_OfficialRate")
    private Double curOfficialRate;

    public ExchangeDTO() {
    }

    public Integer getCurId() {
        return curId;
    }

    public Date getDate() {
        return date;
    }

    public String getCurAbbreviation() {
        return curAbbreviation;
    }

    public Integer getCurScale() {
        return curScale;
    }

    public String getCurName() {
        return curName;
    }

    public Double getCurOfficialRate() {
        return curOfficialRate;
    }

    public void setCurId(Integer curId) {
        this.curId = curId;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setCurAbbreviation(String curAbbreviation) {
        this.curAbbreviation = curAbbreviation;
    }

    public void setCurScale(Integer curScale) {
        this.curScale = curScale;
    }

    public void setCurName(String curName) {
        this.curName = curName;
    }

    public void setCurOfficialRate(Double curOfficialRate) {
        this.curOfficialRate = curOfficialRate;
    }
}
