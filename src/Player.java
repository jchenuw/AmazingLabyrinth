public abstract class Player {

  private Card[] hand;
  private Card current;

  public abstract void moveTo(Integer posX, Integer posY);
  public abstract boolean checkTreasure();
  public abstract void nextCard();
}
