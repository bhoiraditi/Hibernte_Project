package com.demo.Service;

import java.util.List;

import com.demo.examinationsystem.entities.Result;

public interface ResultService {
    Result createResult(Result result);	
    List<Result> getAllResults();
    Result getResult(int resultId);
    List<Result> getResultsByUserId(String userId);
    List<Result> getResultsByExamId(String examId);
    Result updateResult(int resultId, Result updatedResult);
    String deleteResult(int resultId);
}
