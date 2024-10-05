package com.demo.DaoImpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.demo.Dao.UserDao;
import com.demo.examinationsystem.entities.User;
import com.demo.exception.ResourceNotFoundException;
import com.demo.online_examination_system_demo.util.HibernateUtil;

public class UserDaoImpl implements UserDao {

    @Override
    public User createUser(User user) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
            return user;
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            System.err.println("Error creating user: " + e.getMessage());
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            System.err.println("Unexpected error: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        try (Session session = HibernateUtil.getSession()) {
            Query<User> query = session.createQuery("FROM User", User.class);
            return query.list();
        } catch (HibernateException e) {
            System.err.println("Error retrieving users: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
        }
        return null;
    }

    @Override
    public User getUserById(int userId) {
        try (Session session = HibernateUtil.getSession()) {
            return session.get(User.class, userId);
        } catch (HibernateException e) {
            System.err.println("Error retrieving user: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
        }
        return null;
    }

    @Override
    public User updateUser(int userId, User updatedUser) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();
            User user = session.get(User.class, userId);
            if (user != null) {
                user.setUserName(updatedUser.getUserName());
                user.setEmail(updatedUser.getEmail());
                user.setPassword(updatedUser.getPassword()); // Ensure this is hashed
                user.setRole(updatedUser.getRole());
                session.update(user); // Use update instead of saveOrUpdate
                transaction.commit();
                return user;
            } else {
                throw new ResourceNotFoundException("User not found with ID: " + userId);
            }
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            System.err.println("Error updating user: " + e.getMessage());
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            System.err.println("Unexpected error: " + e.getMessage());
        }
        return null;
    }

    @Override
    public String deleteUser(int userId) {
        Transaction transaction = null;
        String message;
        try (Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();
            User user = session.get(User.class, userId);
            if (user != null) {
                session.delete(user);
                transaction.commit();
                message = "User deleted successfully";
            } else {
                message = "User not found with ID: " + userId;
                transaction.rollback(); // Rollback if the user was not found
            }
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            message = "Error deleting user: " + e.getMessage();
            System.err.println(message);
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            message = "Unexpected error: " + e.getMessage();
            System.err.println(message);
        }
        return message;
    }
}
