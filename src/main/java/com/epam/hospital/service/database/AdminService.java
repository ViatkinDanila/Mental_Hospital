package com.epam.hospital.service.database;

public interface AdminService {
    boolean isUserBanned (int userId) throws SecurityException;
    void setUserBanStatusById(int userId, boolean banStatus) throws SecurityException;
}
