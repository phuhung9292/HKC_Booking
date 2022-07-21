/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositories;

import entities.Room;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import variables.RoomStatus;

public class RoomRepository {

    private Connection repo;
    private PreparedStatement preStm;
    private ResultSet rs;

    public void closeRepo() throws Exception {
        if (rs != null) {
            rs.close();
        }
        if (preStm != null) {
            preStm.close();
        }
        if (repo != null) {
            repo.close();
        }
    }

    public ArrayList<Room> getAllRoom() throws Exception {
        try {
            repo = RepoConnector.connectDatabase();
            String sql = "SELECT * FROM hkcbooking_room";
            preStm = repo.prepareStatement(sql);
            rs = preStm.executeQuery();

            ArrayList<Room> list = new ArrayList<Room>();
            while (rs.next()) {
                Integer roomId = rs.getInt("roomId");
                Integer roomTypeId = rs.getInt("roomTypeId");
                String description = rs.getString("description");
                Float price = rs.getFloat("price");
                String urlImage = rs.getString("urlImage");
                String roomStatus = rs.getString("roomStatus");
                list.add(new Room(roomId, roomTypeId, description, price, urlImage, roomStatus));
            }
            return list;
        } finally {
            this.closeRepo();
        }
    }

    public Room getRoomById(Integer id) throws Exception {
        try {
            String query = "SELECT * FROM hkcbooking_room where roomId=?";
            repo = RepoConnector.connectDatabase();
            preStm = repo.prepareStatement(query);
            preStm.setInt(1, id);
            rs = preStm.executeQuery();
            while (rs.next()) {
                Integer roomId = rs.getInt("roomId");
                Integer roomTypeId = rs.getInt("roomTypeId");
                String description = rs.getString("description");
                Float price = rs.getFloat("price");
                String urlImage = rs.getString("urlImage");
                String roomStatus = rs.getString("roomStatus");
                Room room = new Room(roomId, roomTypeId, description, price, urlImage, roomStatus);
                return room;
            }

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            this.closeRepo();
        }
        return null;
    }

    public boolean addRoom(Room room) {
        String INSERT_USER_SQL = "INSERT INTO hkcbooking_room"
                + "(roomTypeId,price,urlImage,description,roomStatus) VALUES"
                + "(?,?,?,?,?)";
        try {
            repo = RepoConnector.connectDatabase();
            preStm = repo.prepareStatement(INSERT_USER_SQL);
            preStm.setInt(1, room.getRoomTypeId());
            preStm.setFloat(2, room.getPrice());
            preStm.setString(3, room.getUrlImage());
            preStm.setString(4, room.getDescription());
            preStm.setString(5, room.getRoomStatus());
            preStm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;

    }

    public boolean updateRoom(Room room) {
        String sql = "UPDATE hkcbooking_room \n"
                + "SET\n"
                + "    roomTypeId = ?,\n"
                + "    price = ?,\n"
                + "    [description] = ?,\n"
                + "    roomStatus = ?\n";

        if (room.getUrlImage() != null) {
            sql += "    ,urlImage = ?\n";
        }

        sql += " WHERE roomId = ?";

        try {
            repo = RepoConnector.connectDatabase();
            preStm = repo.prepareStatement(sql);
            preStm.setInt(1, room.getRoomTypeId());
            preStm.setFloat(2, room.getPrice());
            preStm.setString(3, room.getDescription());
            preStm.setString(4, room.getRoomStatus());
            if (room.getUrlImage() != null) {
                preStm.setString(5, room.getUrlImage());
                preStm.setInt(6, room.getRoomId());
            } else {
                preStm.setInt(5, room.getRoomId());
            }

            preStm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;

    }

    public boolean deleteRoom(Integer roomId) {

        String sql = "UPDATE hkcbooking_room SET roomStatus=? WHERE roomId=?";

        try {
            repo = RepoConnector.connectDatabase();
            preStm = repo.prepareStatement(sql);
            preStm.setString(1, RoomStatus.status.DELETED.toString());
            preStm.setInt(2, roomId);
            preStm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;

    }

}
