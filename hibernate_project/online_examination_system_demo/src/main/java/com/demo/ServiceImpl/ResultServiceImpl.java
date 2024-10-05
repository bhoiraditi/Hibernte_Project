package com.demo.ServiceImpl;

import java.util.List;

import com.demo.Dao.ResultDao;
import com.demo.DaoImpl.ResultDaoImpl;
import com.demo.examinationsystem.entities.Result;
import com.demo.Service.ResultService;

public class ResultServiceImpl implements ResultService {

    ResultDao resultDao = new ResultDaoImpl();

    @Override
    public Result createResult(Result result) {
        // Invoke dao implementation method to save result object
        return resultDao.createResult(result);
    }

    @Override
    public List<Result> getAllResults() {
        // Retrieve all results
        return resultDao.getAllResults();
    }

    @Override
    public Result getResult(int resultId) {
        // Get a specific result by ID
        return resultDao.getResult(resultId);
    }

    @Override
    public List<Result> getResultsByUserId(String userId) {
        // Get results associated with a specific user
        return resultDao.getResultsByUserId(userId);
    }

    @Override
    public List<Result> getResultsByExamId(String examId) {
        // Get results associated with a specific exam
        return resultDao.getResultsByExamId(examId);
    }

    @Override
    public Result updateResult(int resultId, Result updatedResult) {
        // Update an existing result
        return resultDao.updateResult(resultId, updatedResult);
    }

    @Override
    public String deleteResult(int resultId) {
        // Delete a result by ID
        return resultDao.deleteResult(resultId);
    }
}
