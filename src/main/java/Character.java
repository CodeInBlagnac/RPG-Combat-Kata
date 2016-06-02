
public class Character {
	public static final int MAX_HP = 1000;
	private int health = MAX_HP;
	private int level = 1;

	public int getHealth() {
		return health;
	}

	public int getLevel() {
		return level;
	}

	public boolean isAlive() {
		return health > 0;
	}

	public void hit(Character opponent, int damage) {
		if (opponent == this) {
			return;
		}

		if (level - opponent.getLevel() >= 5) {
			damage = (int) (damage * 1.5);
		}
	
		if (opponent.getLevel() - level >= 5) {
			damage = damage / 2;
		}
		opponent.takeHit(damage);
	}

	private void takeHit(int damage) {
		health -= damage;
		health = health < 0 ? 0 : health;
	}

	public void heal(int healAmount) {
		if (isAlive()) {
			health += healAmount;
			health = health > MAX_HP ? MAX_HP : health;
		}
	}

	public void setLevel(int level) {
		this.level = level;
	}

}
