package com.reply.tyreshop.core.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class ExchangeDto {

    @JsonProperty("Cur_ID")
    private Integer cur_Id;

    @JsonProperty("Date")
    private Date date;

    @JsonProperty("Cur_Abbreviation")
    private String cur_Abbreviation;

    @JsonProperty("Cur_Scale")
    private Integer cur_Scale;

    @JsonProperty("Cur_Name")
    private String cur_Name;

    @JsonProperty("Cur_OfficialRate")
    private Double cur_OfficialRate;

    public ExchangeDto() {
    }

    public ExchangeDto(Integer cur_Id, Date date, String cur_Abbreviation, Integer cur_Scale, String cur_Name,
                       Double cur_OfficialRate) {
        this.cur_Id = cur_Id;
        this.date = date;
        this.cur_Abbreviation = cur_Abbreviation;
        this.cur_Scale = cur_Scale;
        this.cur_Name = cur_Name;
        this.cur_OfficialRate = cur_OfficialRate;
    }

    public Integer getCur_Id() {
        return cur_Id;
    }

    public Date getDate() {
        return date;
    }

    public String getCur_Abbreviation() {
        return cur_Abbreviation;
    }

    public Integer getCur_Scale() {
        return cur_Scale;
    }

    public String getCur_Name() {
        return cur_Name;
    }

    public Double getCur_OfficialRate() {
        return cur_OfficialRate;
    }

    public void setCur_Id(Integer cur_Id) {
        this.cur_Id = cur_Id;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setCur_Abbreviation(String cur_Abbreviation) {
        this.cur_Abbreviation = cur_Abbreviation;
    }

    public void setCur_Scale(Integer cur_Scale) {
        this.cur_Scale = cur_Scale;
    }

    public void setCur_Name(String cur_Name) {
        this.cur_Name = cur_Name;
    }

    public void setCur_OfficialRate(Double cur_OfficialRate) {
        this.cur_OfficialRate = cur_OfficialRate;
    }
}
