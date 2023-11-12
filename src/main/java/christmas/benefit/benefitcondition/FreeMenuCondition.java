package christmas.benefit.benefitcondition;

public class FreeMenuCondition implements BenefitCondition {
    private boolean isSatisfied = false;
    @Override
    public void checkDiscountCondition(int date, int totalPrice) {

        if (totalPrice >= 120000) setSatisfied(true);
    }

    @Override
    public int applyDiscount(int price) {
        if(isSatisfied) return -25000;
        return 0;
    }

    @Override
    public boolean isSatisfied() {
        return isSatisfied;
    }
    private void setSatisfied(boolean satisfied) {
        isSatisfied = satisfied;
    }

}
