/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dbcontext.DBContext;
import entity.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
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
    
    public int createPost(Post post) {
        query = "INSERT INTO Post (accountid, title, content, createdDate, status, featured) VALUES (?,?,?,GETDATE(),?,?)";
        try {
            ps = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setInt(1, post.getAccountId());
            ps.setString(2, post.getTitle());
            ps.setString(3, post.getContent());
            ps.setInt(4, post.getStatus());
            ps.setBoolean(5, post.isFeatured());
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return -1;
    }
    
    public void addPostImage(PostImage image) {
        query = "INSERT INTO PostImage (postId, imageUrl, isThumbnail) VALUES (?,?,?)";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, image.getPostId());
            ps.setString(2, image.getImageUrl());
            ps.setBoolean(3, image.isThumbnail());
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
    
    public List<PostImage> getPostImages(int postid) {
        List<PostImage> images = new ArrayList<>();
        query = "SELECT * FROM PostImage WHERE postId=?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, postid);
            rs = ps.executeQuery();
            while (rs.next()) {
                PostImage image = new PostImage();
                image.setId(rs.getInt("id"));
                image.setPostId(rs.getInt("postId"));
                image.setImageUrl(rs.getString("imageUrl"));
                image.setThumbnail(rs.getBoolean("isThumbnail"));
                images.add(image);
            }
        } catch (Exception e) {
        }
        return images;
    }
    
    public void updatePost(Post post) {
        query = "UPDATE Post SET title=?, content=?, modifiedDate=GETDATE(), status=?, featured=? WHERE id=?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, post.getTitle());
            ps.setString(2, post.getContent());
            ps.setInt(3, post.getStatus());
            ps.setBoolean(4, post.isFeatured());
            ps.setInt(5, post.getId());
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
    
    public void deletePostImage(int imageid) {
        query = "DELETE FROM PostImage WHERE id=?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, imageid);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
    
    public void deleteComment(int commentId) {
        query = "DELETE FROM Comment WHERE id=?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, commentId);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
    
    public void deletePostAndRelated(int postId) {
        // Xóa ảnh liên quan
        String query1 = "DELETE FROM PostImage WHERE postId=?";
        try {
            ps = connection.prepareStatement(query1);
            ps.setInt(1, postId);
            ps.executeUpdate();
        } catch (Exception e) {}

        // Xóa comment liên quan
        String query2 = "DELETE FROM Comment WHERE postId=?";
        try {
            ps = connection.prepareStatement(query2);
            ps.setInt(1, postId);
            ps.executeUpdate();
        } catch (Exception e) {}

        // Xóa like liên quan
        String query3 = "DELETE FROM [Like] WHERE postId=?";
        try {
            ps = connection.prepareStatement(query3);
            ps.setInt(1, postId);
            ps.executeUpdate();
        } catch (Exception e) {}

        // Cuối cùng xóa post
        String query4 = "DELETE FROM Post WHERE id=?";
        try {
            ps = connection.prepareStatement(query4);
            ps.setInt(1, postId);
            ps.executeUpdate();
        } catch (Exception e) {}
    }
    public List<Post> getPostsByAccountId(int accountId, int page, int pageSize) {
        List<Post> posts = new ArrayList<>();
        query = "SELECT * FROM Post WHERE accountid=? ORDER BY createdDate DESC OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, accountId);
            ps.setInt(2, (page - 1) * pageSize);
            ps.setInt(3, pageSize);
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

    public int getTotalPostsByAccountId(int accountId) {
        query = "SELECT COUNT(*) FROM Post WHERE accountid=?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, accountId);
            rs = ps.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                return count;
            }
        } catch (Exception e) {
        }
        return 0;
    }

    // Saved Post (Bookmark) methods
    public boolean hasSaved(int accountId, int postId) {
        String query = "SELECT COUNT(*) FROM SavedPost WHERE accountId=? AND postId=?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, accountId);
            ps.setInt(2, postId);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (Exception e) {}
        return false;
    }

    public void savePost(int accountId, int postId) {
        String query = "INSERT INTO SavedPost (accountId, postId, savedAt) VALUES (?, ?, GETDATE())";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, accountId);
            ps.setInt(2, postId);
            ps.executeUpdate();
        } catch (Exception e) {}
    }

    public void unsavePost(int accountId, int postId) {
        String query = "DELETE FROM SavedPost WHERE accountId=? AND postId=?";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, accountId);
            ps.setInt(2, postId);
            ps.executeUpdate();
        } catch (Exception e) {}
    }

    public List<Post> getSavedPostsByAccountId(int accountId) {
        List<Post> posts = new ArrayList<>();
        String query = "SELECT p.* FROM SavedPost s JOIN Post p ON s.postId = p.id WHERE s.accountId=? ORDER BY s.savedAt DESC";
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, accountId);
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
        } catch (Exception e) {}
        return posts;
    }
}
