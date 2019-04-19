package com.reply.tyreshop.addons.callback.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class CallbackForm {
    @NotNull
    @Size(min = 2, max = 30)
    private String name;

    @NotNull
    @Pattern(regexp = "^(\\+375|80|375)(29|25|44|33)(\\d{3})(\\d{2})(\\d{2})$")
    private String phone;

    private String comment;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
