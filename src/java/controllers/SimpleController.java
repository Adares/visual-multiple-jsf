/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.util.List;
import java.util.Map;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author andrey
 */
@ManagedBean(eager = false)
@ApplicationScoped
public class SimpleController {

    private Map<Integer, Object> map; // Object меняем на конкретную сущность
    private List<Object> list;

    /**
     * Creates a new instance of Ref_Currency
     */
//    public AccountController() {
//        map = new HashMap<Integer, Account>();
//        list = DataHelper.getInstance().getAllAccount();
//
//        for (Account r : list) {
//            map.put(r.getId(), r);
//        }
//    }
//
//    public List<Account> getAccountList() {
//        return list;
//    }
}
