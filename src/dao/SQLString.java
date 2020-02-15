package dao;

/**
 * @author ：Tong
 * @date ：Created in 2019/12/26 9:53
 * @description： 干员查询语句
 * @version: $
 */
public class SQLString {
    static final String queryStaffMain = "select sta_id,sta_name,sta_sex,sta_health,sta_attack_Power,sta_cost,sta_defence," +
            "sta_avoid_Num,sta_spell_Resistance,sta_rarity,sta_Redeploy_Speed,sta_attack_Speed,sta_career,sta_faction " +
            "from staff where 1 = 1 ";

    static final String orderById = " order by sta_id ";

    static final String pageGroup = " limit ?,? ";

    static final String queryUserMain = "select pui_user_id, pui_user_mobile, pui_user_name, pui_user_email, pui_user_pwd, pui_user_stt from pub_user_info" +
            " where 1 = 1 ";
}
