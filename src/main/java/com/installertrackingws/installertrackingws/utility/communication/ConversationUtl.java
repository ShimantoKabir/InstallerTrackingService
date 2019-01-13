package com.installertrackingws.installertrackingws.utility.communication;

import com.installertrackingws.installertrackingws.bean.communication.ConversationBn;
import com.installertrackingws.installertrackingws.bean.network.Response;
import com.installertrackingws.installertrackingws.model.Communication.Conversation;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

public class ConversationUtl {

    public Response getByConversationId(EntityManagerFactory entityManagerFactory, int conversationId) {

        Response response = new Response();

        SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Query query = session.createNativeQuery("select  * from conversation WHERE conversation_id = :conversationId",Conversation.class);
        query.setParameter("conversationId",conversationId);

        List<Conversation> conversations = query.getResultList();

        if (conversations.size()>0){
            response.setMsg("Found conversation !");
            response.setCode(200);
            response.setConversationId(conversationId);
            response.setList(conversations);
        }else {
            response.setMsg("Didn't found any conversation !");
            response.setCode(400);
        }

        session.getTransaction().commit();
        session.close();

        return response;

    }

    public Response save(HttpServletRequest httpServletRequest, EntityManagerFactory entityManagerFactory, ConversationBn conversationBn) {

        Response response = new Response();

        SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
        Session session = null;
        Transaction tx = null;

        try{

            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            Conversation conversation = new Conversation();
            conversation.setConversationId(conversationBn.getConversationId());
            conversation.setIsSeen(0);
            conversation.setReceiver(conversationBn.getReceiver());
            conversation.setSendDate(new Date());
            conversation.setSender(conversationBn.getSender());
            conversation.setSpeech(conversationBn.getSpeech());
            conversation.setIp(httpServletRequest.getRemoteAddr());
            session.save(conversation);

            response.setCode(200);
            response.setMsg("Conversation save successfully !");

            tx.commit();

        }catch(Exception e){
            if (tx != null) {
                tx.rollback();
                throw e;
            }
            response.setMsg("Exception occurred !");
            response.setCode(400);
        }finally{
            if(session!=null){
                session.close();
            }
        }

        return response;

    }

    public Response seenTheUnseen(EntityManagerFactory entityManagerFactory, ConversationBn conversationBn) {

        Response response = new Response();

        response.setMsg("ok");
        SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
        Session session = null;
        Transaction tx = null;

        try{

            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            Query isSeenQuery = session.createNativeQuery("UPDATE conversation SET is_seen = 1 WHERE conversation_id = :conversationId and is_seen = 0");
            isSeenQuery.setParameter("conversationId",conversationBn.getConversationId());
            isSeenQuery.executeUpdate();

            Query isTypingQuery = session.createNativeQuery("UPDATE user SET is_typing = :isTyping,for_who = :forWho WHERE id = :id ");
            isTypingQuery.setParameter("isTyping",1);
            isTypingQuery.setParameter("forWho",conversationBn.getReceiver());
            isTypingQuery.setParameter("id",conversationBn.getSender());
            isTypingQuery.executeUpdate();

            Query query = session.createNativeQuery("select  * from conversation WHERE conversation_id = :conversationId",Conversation.class);
            query.setParameter("conversationId",conversationBn.getConversationId());

            List<Conversation> conversations = query.getResultList();

            response.setCode(200);
            response.setMsg("Seen the unseen conversation successful !");
            response.setList(conversations);

            tx.commit();

        }catch(Exception e){
            if (tx != null) {
                tx.rollback();
                throw e;
            }
            response.setMsg("Exception occurred !");
            response.setCode(400);

        }finally{
            if(session!=null){
                session.close();
            }
        }

        return response;

    }
}
