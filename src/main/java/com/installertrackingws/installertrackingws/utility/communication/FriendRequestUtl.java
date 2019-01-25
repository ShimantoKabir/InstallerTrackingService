package com.installertrackingws.installertrackingws.utility.communication;

import com.installertrackingws.installertrackingws.bean.communication.FriendRequestBn;
import com.installertrackingws.installertrackingws.bean.network.Request;
import com.installertrackingws.installertrackingws.bean.network.Response;
import com.installertrackingws.installertrackingws.model.communication.FriendRequest;
import com.installertrackingws.installertrackingws.model.communication.Notification;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

public class FriendRequestUtl {

    public Response checkFriend(EntityManagerFactory entityManagerFactory, Request request) {

        Response response = new Response();
        response.setObject(new FriendRequestBn());
        response.setCode(400);
        response.setMsg("This user is not your friend");

        SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Query query = session.createNativeQuery("select  * from friend_request WHERE sender = :sender OR receiver = :sender",FriendRequest.class);
        query.setParameter("sender",request.getFriendRequestBn().getSender());
        query.setParameter("sender",request.getFriendRequestBn().getSender());

        List<FriendRequest> friendRequests = query.getResultList();

        for (int i = 0; i < friendRequests.size(); i++) {

            if (friendRequests.get(i).getReceiver() == request.getFriendRequestBn().getReceiver() ||
                    friendRequests.get(i).getSender() == request.getFriendRequestBn().getReceiver()){

                response.setCode(200);
                response.setMsg("This user is your friend ");
                response.setObject(friendRequests.get(i));
                break;

            }

        }

        session.getTransaction().commit();
        session.close();

        return response;

    }

    public Response manageFriendRequest(HttpServletRequest httpServletRequest, EntityManagerFactory entityManagerFactory, Request request) {

        Response response = new Response();

        SessionFactory sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
        Session session = null;
        Transaction tx = null;

        try{

            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            if (request.getFriendRequestBn().getAreFriend()==0){

                Query query = session.createNativeQuery("SELECT * FROM friend_request where id=:id ",FriendRequest.class);
                query.setParameter("id",request.getFriendRequestBn().getId());

                if (query.getResultList().size()>0){

                    Query areFriendQuery = session.createNativeQuery("UPDATE friend_request SET  are_friend= :areFriend,sender=:sender,receiver=:receiver WHERE id = :id");
                    areFriendQuery.setParameter("id",request.getFriendRequestBn().getId());
                    areFriendQuery.setParameter("areFriend",1);
                    areFriendQuery.setParameter("sender",request.getFriendRequestBn().getSender());
                    areFriendQuery.setParameter("receiver",request.getFriendRequestBn().getReceiver());
                    areFriendQuery.executeUpdate();

                }else {

                    BigInteger conversationId = (BigInteger) session.createNativeQuery("SELECT IFNULL(MAX(conversation_id),100)+1 AS conversation_id FROM friend_request").getResultList().get(0);

                    FriendRequest friendRequest = new FriendRequest();
                    friendRequest.setAreFriend(1);
                    friendRequest.setSender(request.getFriendRequestBn().getSender());
                    friendRequest.setReceiver(request.getFriendRequestBn().getReceiver());
                    friendRequest.setConversationId(conversationId.intValue());
                    friendRequest.setIp(httpServletRequest.getRemoteAddr());
                    session.save(friendRequest);

                }

                Notification notification = new Notification();
                notification.setIsSeen(0);
                // notification.setLink("http://"+httpServletRequest.getRemoteAddr()+":3308/#/messenger/"+friendRequestBn.getSender());
                notification.setLink("/messenger/");
                notification.setIp(httpServletRequest.getRemoteAddr());
                notification.setReceiver(request.getFriendRequestBn().getReceiver());
                notification.setMessage("Your have got a friend request form "+request.getFriendRequestBn().getSenderEmail());
                session.save(notification);

                response.setCode(200);
                response.setMsg("You have successfully send a friend to "+request.getFriendRequestBn().getReceiverEmail()+" !");

            }else if (request.getFriendRequestBn().getAreFriend()==1){

                Query query = session.createNativeQuery("UPDATE friend_request SET  are_friend= :areFriend,accept_date=:acceptDate WHERE id = :id");
                query.setParameter("id",request.getFriendRequestBn().getId());
                query.setParameter("areFriend",2);
                query.setParameter("acceptDate",new Date());
                query.executeUpdate();

                Notification notification = new Notification();
                notification.setIsSeen(0);
                // notification.setLink("http://"+httpServletRequest.getRemoteAddr()+":3308/#/messenger/"+friendRequestBn.getSender());
                notification.setLink("/messenger");
                notification.setIp(httpServletRequest.getRemoteAddr());
                notification.setReceiver(request.getFriendRequestBn().getReceiver());
                notification.setMessage(request.getFriendRequestBn().getSenderEmail()+" accept your friend friend request !");
                session.save(notification);

                response.setCode(200);
                response.setMsg("Your have successfully accept "+request.getFriendRequestBn().getReceiverEmail()+" friend request !");

            }else if (request.getFriendRequestBn().getAreFriend()==3){

                Query query = session.createNativeQuery("UPDATE friend_request SET  are_friend= :areFriend WHERE id = :id");
                query.setParameter("id",request.getFriendRequestBn().getId());
                query.setParameter("areFriend",0);
                query.executeUpdate();

                Notification notification = new Notification();
                notification.setIsSeen(0);
                notification.setLink("/messenger");
                // notification.setLink("http://"+httpServletRequest.getRemoteAddr()+":3308/#/messenger/"+friendRequestBn.getSender());
                notification.setIp(httpServletRequest.getRemoteAddr());
                notification.setReceiver(request.getFriendRequestBn().getReceiver());
                notification.setMessage(request.getFriendRequestBn().getSenderEmail()+" cancel the friend request that he/she sent to you ");
                session.save(notification);

                response.setCode(200);
                response.setMsg("You have successfully cancel the friend request that you send to "+request.getFriendRequestBn().getReceiverEmail());

            }else {

                Query query = session.createNativeQuery("UPDATE friend_request SET  are_friend= :areFriend WHERE id = :id");
                query.setParameter("id",request.getFriendRequestBn().getId());
                query.setParameter("areFriend",0);
                query.executeUpdate();

                Notification notification = new Notification();
                notification.setIsSeen(0);
                // notification.setLink("http://"+httpServletRequest.getRemoteAddr()+":3308/#/messenger/"+friendRequestBn.getSender());
                notification.setLink("/messenger");
                notification.setIp(httpServletRequest.getRemoteAddr());
                notification.setReceiver(request.getFriendRequestBn().getReceiver());
                notification.setMessage(request.getFriendRequestBn().getSenderEmail()+" and you no longer be a friend, because he/she un friend you");
                session.save(notification);

                response.setCode(200);
                response.setMsg("You have successfully un friend "+request.getFriendRequestBn().getReceiverEmail()+" !");

            }

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
