package com.persistence;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.model.BuyerRequest;
import com.model.TShirt;

import java.util.List;

public class TShirtDatabase {

    public static Session getSession(){
        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");
        SessionFactory factory = cfg.buildSessionFactory();
        Session session = factory.openSession();
        return session;
    }
    
    public static void insertAllDataToDB(List<TShirt> tshirtList) {
    	Session session = getSession();
        Transaction transaction = session.beginTransaction();
        for(TShirt tshirt : tshirtList) {
        	session.save(tshirt);
        }
        transaction.commit();
    }

    public static List<TShirt> getDataFromDB(BuyerRequest buyerRequest){
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        Query query = null;

        String q = "";
        String temp = "";

        if(!(temp = buyerRequest.getColour()).isEmpty()){
            q = q + "colour="+"\'"+temp+"\'";
        }

        if(!(temp = buyerRequest.getGender()).isEmpty()){
            if(!q.isEmpty()){
                q += " and ";
            }
            q += "gender="+"\'"+temp+"\'";
        }

        if(!(temp = buyerRequest.getSize()).isEmpty()){
            if(!q.isEmpty()){
                q += " and ";
            }
            q += "size="+"\'"+temp+"\'";
        }

        if(!q.isEmpty()){
            q = " where "+q;
        }
        if(!(temp = buyerRequest.getSortBy()).isEmpty()){
            q += " order by ";
            if(temp.equals("p")){
                q += "price";
            }else if(temp.equals("r")){
                q += "rating";
            }else if(temp.equals("pr")){
                q += "price , rating";
            }
        }
        if(q.isEmpty()){
            query = session.createQuery("from TShirt",TShirt.class);
        }else{
            query = session.createQuery("from TShirt"+q,TShirt.class);
        }

        List<TShirt> dataFromDB = query.list();
        transaction.commit();

        return dataFromDB;
    }
}
