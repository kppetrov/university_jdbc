package ua.com.foxminded.university.service.impl;

import ua.com.foxminded.university.model.Period;
import ua.com.foxminded.university.service.PeriodService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.com.foxminded.university.dao.PeriodDao;
import ua.com.foxminded.university.exception.DaoException;
import ua.com.foxminded.university.exception.ServiceException;

@Service
public class PeriodServiceImpl implements PeriodService {
    private PeriodDao periodDao;

    @Autowired
    public void setPeriodDao(PeriodDao periodDao) {
        try {
            this.periodDao = periodDao;
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public List<Period> getAll() {
        try {
            return periodDao.getAll();
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public Period getById(int id) {
        try {
            return periodDao.getById(id);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public Period insert(Period item) {
        try {
            return periodDao.insert(item);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public int update(Period item) {
        try {
            return periodDao.update(item);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public int delete(int id) {
        try {
            return periodDao.delete(id);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
