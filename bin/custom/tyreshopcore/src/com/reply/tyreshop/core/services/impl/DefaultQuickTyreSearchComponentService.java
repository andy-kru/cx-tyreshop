package com.reply.tyreshop.core.services.impl;

import com.reply.tyreshop.core.daos.QuickTyreSearchComponentDao;
import com.reply.tyreshop.core.model.QuickTyreSearchComponentModel;
import com.reply.tyreshop.core.services.QuickTyreSearchComponentService;
import org.springframework.beans.factory.annotation.Autowired;


public class DefaultQuickTyreSearchComponentService implements QuickTyreSearchComponentService {

    @Autowired
    private QuickTyreSearchComponentDao quickTyreSearchComponentDao;

    @Override
    public QuickTyreSearchComponentModel getQuickTyreSearchComponentModel() {
        return quickTyreSearchComponentDao.getQuickTyreSearchComponentModel();
    }

    public QuickTyreSearchComponentDao getQuickTyreSearchComponentDao() {
        return quickTyreSearchComponentDao;
    }

    public void setQuickTyreSearchComponentDao(QuickTyreSearchComponentDao quickTyreSearchComponentDao) {
        this.quickTyreSearchComponentDao = quickTyreSearchComponentDao;
    }
}
