/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dbcontext.DBContext;
import entity.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Administrator
 */
public class PostDAO extends DBContext{
    
    public List<Post> getAllPosts() {
        List<Post> posts = new ArrayList<>();
        query = "SELECT * FROM Post ORDER BY createdDate DESC";
        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                Post post = new Post();
                post.setId(rs.getInt("id"));
                post.setAccountId(rs.getInt("accountid"));
                post.setTitle(rs.getString("title"));
                post.setContent(rs.getString("content"));
                post.setCreatedDate(rs.getDate("createdDate"));
                post.setModifiedDate(rs.getDate("modifiedDate"));
                post.setViewCount(rs.getInt("viewCount"));
                post.setStatus(rs.getInt("status"));
                post.setFeatured(rs.getBoolean("featured"));
                posts.add(post);
            }
        } catch (Exception e) {
        }
        return posts;
    }
    public List<Post> getPostsWithPagination(int page, int pageSize) {
        List<Post> posts = new ArrayList<>();
        query = "SELECT * FROM Post ORDER BY createdDate DESC OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, (page - 1) * pageSize);
            ps.setInt(2, pageSize);
            rs = ps.executeQuery();
            while (rs.next()) {
                Post post = new Post();
                post.setId(rs.getInt("id"));
                post.setAccountId(rs.getInt("accountid"));
                post.setTitle(rs.getString("title"));
                post.setContent(rs.getString("content"));
                post.setCreatedDate(rs.getDate("createdDate"));
                post.setModifiedDate(rs.getDate("modifiedDate"));
                post.setViewCount(rs.getInt("viewCount"));
                post.setStatus(rs.getInt("status"));
                post.setFeatured(rs.getBoolean("featured"));
                posts.add(post);
            }
        } catch (Exception e) {
        }
        return posts;
    }

    public int getTotalPosts() {
        query = "SELECT COUNT(*) FROM Post";
        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return 0;
    }
}
