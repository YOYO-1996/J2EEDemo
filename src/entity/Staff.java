package entity;

/**
 * @description:
 * @author: Tong
 * @date: 2019-12-16 20:00
 */
public class Staff {
    /**
     * ID staff.sta_id
     */
    private Long staId;

    /**
     * 姓名 staff.sta_name
     */
    private String staName;
    /**
     * 性别 staff.sta_sex
     */
    private String staSex;
    /**
     * 血量 staff.sta_health
     */
    private String staHealth;

    /**
     * 攻击 staff.sta_attack_Power
     */
    private String staAttackPower;

    /**
     * 部署费用 staff.sta_cost
     */
    private String staCost;

    /**
     * 防御 staff.sta_defence
     */
    private String staDefence;

    /**
     * 阻挡数 staff.sta_avoid_Num
     */
    private String staAvoidNum;

    /**
     * 法术防御 staff.sta_spell_Resistance
     */
    private String staSpellResistance;

    /**
     * 稀有度 staff.sta_rarity
     */
    private String staRarity;

    /**
     * 再部署速度（慢、中、快） staff.sta_Redeploy_Speed
     */
    private String staRedeploySpeed;

    /**
     * 攻击速度（慢、中、快） staff.sta_attack_Speed
     */
    private String staAttackSpeed;

    /**
     * 职业(狙击、重装等) staff.sta_career
     */
    private String staCareer;

    /**
     * 派别 staff.sta_faction
     */
    private String staFaction;

    public Long getStaId() {
        return staId;
    }

    public void setStaId(Long staId) {
        this.staId = staId;
    }

    public String getStaName() {
        return staName;
    }

    public void setStaName(String staName) {
        this.staName = staName;
    }

    public String getStaSex() {
        return staSex;
    }

    public void setStaSex(String staSex) {
        this.staSex = staSex;
    }

    public String getStaHealth() {
        return staHealth;
    }

    public void setStaHealth(String staHealth) {
        this.staHealth = staHealth;
    }

    public String getStaAttackPower() {
        return staAttackPower;
    }

    public void setStaAttackPower(String staAttackPower) {
        this.staAttackPower = staAttackPower;
    }

    public String getStaCost() {
        return staCost;
    }

    public void setStaCost(String staCost) {
        this.staCost = staCost;
    }

    public String getStaDefence() {
        return staDefence;
    }

    public void setStaDefence(String staDefence) {
        this.staDefence = staDefence;
    }

    public String getStaAvoidNum() {
        return staAvoidNum;
    }

    public void setStaAvoidNum(String staAvoidNum) {
        this.staAvoidNum = staAvoidNum;
    }

    public String getStaSpellResistance() {
        return staSpellResistance;
    }

    public void setStaSpellResistance(String staSpellResistance) {
        this.staSpellResistance = staSpellResistance;
    }

    public String getStaRarity() {
        return staRarity;
    }

    public void setStaRarity(String staRarity) {
        this.staRarity = staRarity;
    }

    public String getStaRedeploySpeed() {
        return staRedeploySpeed;
    }

    public void setStaRedeploySpeed(String staRedeploySpeed) {
        this.staRedeploySpeed = staRedeploySpeed;
    }

    public String getStaAttackSpeed() {
        return staAttackSpeed;
    }

    public void setStaAttackSpeed(String staAttackSpeed) {
        this.staAttackSpeed = staAttackSpeed;
    }

    public String getStaCareer() {
        return staCareer;
    }

    public void setStaCareer(String staCareer) {
        this.staCareer = staCareer;
    }

    public String getStaFaction() {
        return staFaction;
    }

    public void setStaFaction(String staFaction) {
        this.staFaction = staFaction;
    }

    @Override
    public String toString() {
        return "Staff{" +
                "staId=" + staId +
                ", staName='" + staName + '\'' +
                ", staSex='" + staSex + '\'' +
                ", staHealth='" + staHealth + '\'' +
                ", staAttackPower='" + staAttackPower + '\'' +
                ", staCost='" + staCost + '\'' +
                ", staDefence='" + staDefence + '\'' +
                ", staAvoidNum='" + staAvoidNum + '\'' +
                ", staSpellResistance='" + staSpellResistance + '\'' +
                ", staRarity='" + staRarity + '\'' +
                ", staRedeploySpeed='" + staRedeploySpeed + '\'' +
                ", staAttackSpeed='" + staAttackSpeed + '\'' +
                ", staCareer='" + staCareer + '\'' +
                ", staFaction='" + staFaction + '\'' +
                '}';
    }
}
