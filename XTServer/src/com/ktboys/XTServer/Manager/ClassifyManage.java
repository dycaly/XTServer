package com.ktboys.XTServer.Manager;

import java.util.Iterator;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;

import com.google.gson.Gson;
import com.ktboys.XTServer.HibernateSessionFactory;
import com.ktboys.XTServer.Entity.Classify;
import com.ktboys.XTServer.Entity.Product;
import com.ktboys.XTServer.Result.ProductInfo;
import com.ktboys.XTServer.Result.ProductResult;

public class ClassifyManage {

	private Classify mClassify;
	private Session session;
	
	public ClassifyManage(String classname){
		session = HibernateSessionFactory.getSession();
		String hql="from Classify c where c.name='"+classname+"'";
		Query query = session.createQuery(hql);
		mClassify = (Classify) query.uniqueResult();
	}
	
	public void close(){
		session.close();
	}
	
	public Set<Product> getProducts(){
		return mClassify.getProducts();
	}
	public Classify getClassify(){
		return mClassify;
	}
	public String getProductsJson() {
		String result = null;
		ProductResult pr = new ProductResult(0);
		Set<Product> set = mClassify.getProducts();
		Iterator<Product> iterator = set.iterator();
		while (iterator.hasNext()) {
			Product product = (Product) iterator.next();
			UserDitalsManage udm = new UserDitalsManage(product.getUserBySellerId().getUserId());
			pr.addProductInfo(new ProductInfo(product.getProductUrl(), product
					.getProductName(),udm.getUserditals().getNickname(), product.getProductIntro(), product
					.getHightestPrice(), product.getLowestPrice(), product
					.getCutTime(), product.getStatus(), product
					.getUserBySellerId().getUsername(), product.getSellDate()
					.toString(), product.getUserBySellerId().getUsername(),
					product.getLastPrice(), product.getClassify().getName()));
		}
		Gson gson = new Gson();
		
		result = gson.toJson(pr); 
		return result;
	}
}
