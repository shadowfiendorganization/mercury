package com.atlandes.auth.shiro.component;

import com.atlandes.auth.po.User;
import com.atlandes.auth.shiro.dao.MercurySessionDao;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;

import java.util.*;

/**
 * Created by XD.Wang on 2017/5/26.
 * Session管理器
 */
public class MercurySessionManager {

    // session状态名
    public static final String SESSION_STATUS = "mercury-online-status";

    private MercurySessionDao customShiroSessionDAO;

    /**
     * 根据ID查询 SimplePrincipalCollection
     *
     * @param userIds 用户ID
     */
    @SuppressWarnings("unchecked")
    public List<SimplePrincipalCollection> getSimplePrincipalCollectionByUserId(Long... userIds) {
        Set<Long> idSet = new HashSet<Long>();
        idSet.addAll(Arrays.asList(userIds));
        //获取所有session
        Collection<Session> sessions = customShiroSessionDAO.getActiveSessions();
        //定义返回
        List<SimplePrincipalCollection> list = new ArrayList<SimplePrincipalCollection>();
        for (Session session : sessions) {
            //获取SimplePrincipalCollection
            Object obj = session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
            if (null != obj && obj instanceof SimplePrincipalCollection) {
                //强转
                SimplePrincipalCollection spc = (SimplePrincipalCollection) obj;
                //判断用户，匹配用户ID。
                obj = spc.getPrimaryPrincipal();
                if (null != obj && obj instanceof User) {
                    User user = (User) obj;
                    //比较用户ID，符合即加入集合
                    if (idSet.contains(user.getId())) {
                        list.add(spc);
                    }
                }
            }
        }
        return list;
    }

}
