package springbook.user.service;

import springbook.user.domain.Level;
import springbook.user.domain.User;

public class UserLevelUpgradePolicyImpl implements UserLevelUpgradePolicy {

    public static final int MIN_LOGCOUNT_FOR_SILVER = 50;
    public static final int MIN_RECCOMENT_FOR_GOLD = 30;

    @Override
    public boolean canUpgradeLevel(User user) {
        Level currentLevel = user.getLevel();
        switch (currentLevel) {
            case BASIC:
                return user.getLogin() >= MIN_LOGCOUNT_FOR_SILVER;
            case SILVER:
                return user.getRecommend() >= MIN_RECCOMENT_FOR_GOLD;
            case GOLD:
                return false;
            default:
                throw new IllegalArgumentException("Unknown level: " + currentLevel);
        }
    }

    @Override
    public void upgradeLevel(User user) {
        Level nextLevel = user.getLevel().nextLevel();
        if (nextLevel == null) {
            throw new IllegalStateException(user.getLevel() + "은 업그레이드가 불가능합니다");
        }
        user.upgradeLevel();
    }
}
