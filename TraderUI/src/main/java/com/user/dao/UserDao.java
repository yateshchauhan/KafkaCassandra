package com.user.dao;


import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Session;
import com.user.bean.User;
import com.user.bean.UserEnum;
import com.user.utility.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Repository
public class UserDao {

    private static Logger log = LoggerFactory.getLogger(UserDao.class);
    private static final Cluster cluster;
    private static String keySpace = "tradeDB";

    static{
        cluster = Cluster.builder().addContactPoint("127.0.0.1").withPort(9042).build();
    }

    public boolean createUser(User user){

        Session session = cluster.connect(keySpace);
        String cql = "insert into User (id, name, type, creationDate) values(:id, :name, :type, :creationDate)";
        ResultSet resultSet = null;
        try {
            Map<String, Object> params = new HashMap() {
                {
                    put(UserEnum.ID.col, user.getId());
                    put(UserEnum.Name.col, user.getName());
                    put(UserEnum.Type.col, user.getType());
                    put(UserEnum.CreationDate.col, DateUtils.converLocalDateToDate(user.getCreationDate()));
                }
            };
            resultSet = session.execute(cql, params);
            session.close();
        }catch (Exception e){
            e.printStackTrace();
            if(session != null)
                session.close();
        }

        return resultSet!= null ? resultSet.wasApplied() : false;

    }

    public User getUser(String userId){

        Session session = cluster.connect(keySpace);
        String cql = "select Id, name, type, creationDate from User where id=:id";
        User user = new User();
        try{
            Map<String,Object> params = new HashMap(){
                {
                    put(UserEnum.ID.col, userId);
                }
            };
            ResultSet resultSet = session.execute(cql, params);

            if(resultSet.wasApplied()){
                resultSet.forEach((row)->{

                    user.setId(row.get(UserEnum.ID.col, String.class));
                    user.setName(row.get(UserEnum.Name.col, String.class));
                    user.setType(row.get(UserEnum.Type.col, String.class));
                    user.setCreationDate( DateUtils.convertDateToLocalDate(row.get(UserEnum.CreationDate.col, Date.class)));


                });
            }
            session.close();
        }catch (Exception e){
            if(session != null)
                session.close();
            log.info("Error while getting User details, {}",e);
        }
        return StringUtils.isBlank(user.getId()) ? null : user ;
    }
}
