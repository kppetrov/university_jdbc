package ua.com.foxminded.university.service;

import ua.com.foxminded.university.model.Period;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.com.foxminded.university.dao.PeriodDao;

@Service
public class PeriodServiceImpl implements PeriodService {
    private PeriodDao periodDao;

    @Autowired
    public void setPeriodDao(PeriodDao periodDao) {
        this.periodDao = periodDao;
    }

    @Override
    public List<Period> getAll() {
        return periodDao.getAll();
    }

    @Override
    public Period getById(int id) {
        return periodDao.getById(id);
    }

    @Override
    public Period insert(Period item) {
        return periodDao.insert(item);
    }

    @Override
    public int update(Period item) {
        return periodDao.update(item);
    }

    @Override
    public int delete(int id) {
        return periodDao.delete(id);
    }
}
