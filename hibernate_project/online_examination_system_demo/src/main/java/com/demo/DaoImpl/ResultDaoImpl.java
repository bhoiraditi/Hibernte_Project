package com.demo.DaoImpl;

import java.util.List;
import java.util.Scanner;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import com.demo.Dao.ResultDao;
import com.demo.examinationsystem.entities.Result;
import com.demo.exception.ResourceNotFoundException;
import com.demo.online_examination_system_demo.util.HibernateUtil;

public class ResultDaoImpl implements ResultDao {

    Scanner sc = new Scanner(System.in);

    @Override
    public Result createResult(Result result) {
        try (Session session = HibernateUtil.getSession()) {
            session.beginTransaction();
            session.save(result);
            session.getTransaction().commit();
            return result;
        } catch (HibernateException e) {
            System.out.println(e);
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public List<Result> getAllResults() {
        try (Session session = HibernateUtil.getSession()) {
            Query<Result> query = session.createQuery("FROM Result", Result.class);
            return query.list();
        } catch (HibernateException e) {
            System.out.println(e);
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public Result getResult(int resultId) {
        try (Session session = HibernateUtil.getSession()) {
            return session.get(Result.class, resultId);
        } catch (HibernateException e) {
            System.out.println(e);
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public List<Result> getResultsByUserId(String userId) {
        try (Session session = HibernateUtil.getSession()) {
            Query<Result> query = session.createQuery("FROM Result r WHERE r.user.id = :userId", Result.class);
            query.setParameter("userId", userId);
            return query.list();
        } catch (HibernateException e) {
            System.out.println(e);
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public List<Result> getResultsByExamId(String examId) {
        try (Session session = HibernateUtil.getSession()) {
            Query<Result> query = session.createQuery("FROM Result r WHERE r.exam.id = :examId", Result.class);
            query.setParameter("examId", examId);
            return query.list();
        } catch (HibernateException e) {
            System.out.println(e);
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public Result updateResult(int resultId, Result updatedResult) {
        try (Session session = HibernateUtil.getSession()) {
            Result result = session.get(Result.class, resultId);
            if (result != null) {
                session.beginTransaction();
                result.setScore(updatedResult.getScore());
                result.setStatus(updatedResult.getStatus());
                result.setUserId(updatedResult.getUserId());
                result.setExamId(updatedResult.getExamId());
                result.setAnswer(updatedResult.getAnswer());
                
                session.saveOrUpdate(result);
                session.getTransaction().commit();
                return result;
            } else {
                throw new ResourceNotFoundException("Result with ID " + resultId + " not found.");
            }
        } catch (HibernateException e) {
            System.out.println(e);
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public String deleteResult(int resultId) {
        String message = null;
        try (Session session = HibernateUtil.getSession()) {
            Result result = session.get(Result.class, resultId);
            if (result != null) {
                session.beginTransaction();
                System.out.println("Are you sure you want to delete? (yes/no)");
                String status = sc.next();
                if (status.equalsIgnoreCase("yes")) {
                    session.delete(result);
                    session.getTransaction().commit();
                    message = "Result is deleted";
                } else {
                    message = "User wants to retain this result!";
                }
            } else {
                message = "Result with ID " + resultId + " not found.";
            }
        } catch (HibernateException e) {
            System.out.println(e);
        } catch (Exception e) {
            System.out.println(e);
        }
        return message;
    }
}
