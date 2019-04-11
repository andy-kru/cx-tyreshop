package com.reply.tyreshop.jobs.dao;

import com.reply.tyreshop.addons.callback.model.CallbackModel;

import java.util.List;

public interface TyreshopCallbackJobDao {
    List<CallbackModel> getOldCallbackModels();
}
