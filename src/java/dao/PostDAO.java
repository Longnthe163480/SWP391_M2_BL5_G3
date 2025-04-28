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
    
    public Post getPostById(int postid) {
        query = "SELECT * FROM Post WHERE id=?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, postid);
            rs = ps.executeQuery();
            if (rs.next()) {
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
                return post;
            }
        } catch (Exception e) {
        }
        return null;
    }
    
    public List<Comment> getCommentsByPostId(int postid) {
        List<Comment> comments = new ArrayList<>();
        query = "SELECT * FROM Comment WHERE postId=? AND status=1 ORDER BY createdDate DESC";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, postid);
            rs = ps.executeQuery();
            while (rs.next()) {
                Comment comment = new Comment();
                comment.setId(rs.getInt("id"));
                comment.setPostId(rs.getInt("postId"));
                comment.setAccountId(rs.getInt("accountId"));
                comment.setContent(rs.getString("content"));
                comment.setCreatedDate(rs.getDate("createdDate"));
                comment.setParentId(rs.getInt("parentId"));
                comment.setStatus(rs.getInt("status"));
                comments.add(comment);
            }
        } catch (Exception e) {
        }
        return comments;
    }

    public void addComment(Comment comment) {
        query = "INSERT INTO Comment (postId, accountId, content, createdDate, parentId, status) VALUES (?,?,?,GETDATE(),?,1)";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, comment.getPostId());
            ps.setInt(2, comment.getAccountId());
            ps.setString(3, comment.getContent());
            if (comment.getParentId() != null) {
                ps.setInt(4, comment.getParentId());
            } else {
                ps.setNull(4, java.sql.Types.INTEGER);
            }
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }


    // LikeDAO methods
    public void addLike(PostLike like) {
        query = "INSERT INTO [Like] (postId, accountId, createdDate) VALUES (?,?,GETDATE())";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, like.getPostId());
            ps.setInt(2, like.getAccountId());
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void removeLike(int postid, int accid) {
        query = "DELETE FROM [Like] WHERE postId=? AND accountId=?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, postid);
            ps.setInt(2, accid);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public int getLikeCount(int postid) {
        query = "SELECT COUNT(*) FROM [Like] WHERE postId=?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, postid);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return 0;
    }

    public boolean hasLiked(int postid, int accid) {
        query = "SELECT COUNT(*) FROM [Like] WHERE postId=? AND accountId=?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, postid);
            ps.setInt(2, accid);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (Exception e) {
        }
        return false;
    }
}
