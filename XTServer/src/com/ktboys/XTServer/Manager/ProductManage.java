package com.ktboys.XTServer.Manager;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.util.Timer;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.omg.CORBA.PUBLIC_MEMBER;

import com.google.gson.Gson;
import com.ktboys.XTServer.HibernateSessionFactory;
import com.ktboys.XTServer.Entity.Classify;
import com.ktboys.XTServer.Entity.Comment;
import com.ktboys.XTServer.Entity.Product;
import com.ktboys.XTServer.Entity.User;
import com.ktboys.XTServer.Entity.Userditals;
import com.ktboys.XTServer.Result.CommentInfo;
import com.ktboys.XTServer.Result.CommentResult;
import com.ktboys.XTServer.Result.ProductInfo;
import com.ktboys.XTServer.Result.ProductResult;
import com.ktboys.XTServer.Time.MyTask;

public class ProductManage {

	private Product mProduct;
	private Session session;
	private Transaction transaction;

	public ProductManage() {
		session = HibernateSessionFactory.getSession();

	}

	public ProductManage(int id) {
		session = HibernateSessionFactory.getSession();
		String hql = "from Product p where p.productId=" + id;
		Query query = session.createQuery(hql);
		mProduct = (Product) query.uniqueResult();
	}

	public void close() {
		session.close();
	}

	public ProductManage(Classify classify,
			User userBySellerId, String productUrl, String productName,
			String productIntro, Integer hightestPrice, Integer lowestPrice,
			Integer cutprice, Integer cutTime, Integer status,
			Timestamp sellDate) {
		session = HibernateSessionFactory.getSession();
		transaction = session.beginTransaction();
		mProduct = new Product( userBySellerId,classify,userBySellerId, 
				productUrl, productName, productIntro, hightestPrice,
				lowestPrice, cutprice, cutTime, status, sellDate,hightestPrice);
		session.save(mProduct);
		transaction.commit();
		
		Timer timer = new Timer();
		timer.schedule(new MyTask(mProduct), cutTime *1000, 1000 * cutTime);
	}

	public int buyProduct(String token) {

		UserManage um = new UserManage(token);
		if (!um.isExist()) {
			return 1;
		}
		if (mProduct.getStatus() == 0) {
			transaction = session.beginTransaction();
			mProduct.setUserByBuyerId(um.getUser());
			mProduct.setStatus(2);
			mProduct.setLastPrice(mProduct.getLastPrice());
			session.update(mProduct);
			transaction.commit();
			um.close();
		} else {
			return 2;
		}

		return 0;
	}

	public String getCommentsJson() {
		String result = null;

		Set<Comment> comments = mProduct.getComments();

		Iterator<Comment> iterator = comments.iterator();
		CommentResult cr = new CommentResult(0);
		while (iterator.hasNext()) {
			Comment comment = (Comment) iterator.next();
			User u = comment.getUser();
			UserDitalsManage ud = new UserDitalsManage(u.getUserId());
			cr.addCommentInfo(new CommentInfo(ud.getUserditals().getNickname(),
					u.getUsername(), comment.getContent(), comment
							.getCommentDate().toString(), comment.getFloor()));
		}

		Gson gson = new Gson();
		result = gson.toJson(cr);
		return result;
	}

	public void addComment(String token, String content) {
		UserManage um = new UserManage(token);
		String hql1 = "select c.commentId from Comment c where c.commentId >=all(select cm.commentId from Comment cm)";
		String hql2 = "select c.floor from Comment c where c.floor >=all(select cm.floor from Comment cm)";
		Query query1 = session.createQuery(hql1);
		Query query2 = session.createQuery(hql2);
		int commentId = (int) query1.uniqueResult();
		int floor = (int) query2.uniqueResult();
		commentId++;
		floor++;
		Comment comment = new Comment(commentId, mProduct, um.getUser(),
				content, floor, new Timestamp(System.currentTimeMillis()));
		transaction = session.beginTransaction();
		session.save(comment);
		transaction.commit();
	}
	
	public Product getProduct(){
		return mProduct;
	}
	public String getProductInfoJson(){
		String result=null;
		Product product = mProduct;
		UserDitalsManage udm = new UserDitalsManage(product.getUserBySellerId().getUserId());
		ProductInfo productInfo = new ProductInfo(product.getProductId(),product.getProductUrl(), product
				.getProductName(),udm.getUserditals().getNickname(), product.getProductIntro(), product
				.getHightestPrice(), product.getLowestPrice(), product
				.getCutTime(),product.getCutPrice(), product.getStatus(), product
				.getUserBySellerId().getUsername(), product.getSellDate()
				.toString(), product.getUserByBuyerId().getUsername(),
				product.getLastPrice(), product.getClassify().getName());
		Gson gson = new Gson();
		result = gson.toJson(productInfo);
		return result;
	}
}
