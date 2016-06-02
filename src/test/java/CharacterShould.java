import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CharacterShould {
	private Character character;
	private Character opponent;

	@Before
	public void setUp() {
		character = new Character();
		opponent = new Character();
	}

	@Test
	public void have_health_starting_at_1000() {
		assertThat(character.getHealth()).isEqualTo(Character.MAX_HP);
	}

	@Test
	public void have_level_starting_at_1() {
		assertThat(character.getLevel()).isEqualTo(1);
	}

	@Test
	public void be_alive_by_default() {
		assertThat(character.isAlive()).isTrue();
	}

	@Test
	public void be_able_to_deal_damages() {
		character.hit(opponent, 42);

		assertThat(opponent.getHealth()).isEqualTo(958);
	}

	@Test
	public void be_able_to_heal() {
		character.hit(opponent, 142);
		opponent.heal(42);

		assertThat(opponent.getHealth()).isEqualTo(900);
	}

	@Test
	public void not_be_able_to_have_more_than_1000_hp() {
		character.heal(425346);
		assertThat(character.getHealth()).isEqualTo(Character.MAX_HP);
	}

	@Test
	public void die_when_his_hp_equals_0() {
		character.hit(opponent, Character.MAX_HP);
		assertThat(opponent.isAlive()).isFalse();
	}
	
	@Test
	public void not_be_able_to_have_less_than_0_hp() {
		character.hit(opponent, Character.MAX_HP * 2);
		assertThat(opponent.getHealth()).isEqualTo(0);
	}

	@Test
	public void not_be_able_to_be_healed_when_already_dead() {
		character.hit(opponent, Character.MAX_HP);
		opponent.heal(42);
		assertThat(opponent.getHealth()).isEqualTo(0);
	}

	@Test
	public void not_be_able_to_hit_himself() {
		Character himself = character;
		character.hit(himself, Character.MAX_HP);
		assertThat(character.getHealth()).isEqualTo(Character.MAX_HP);
	}
	
	@Test
	public void hit_harder_when_target_is_more_than_5_levels_below_him() {
		character.setLevel(10);
		character.hit(opponent, 50);
		assertThat(opponent.getHealth()).isEqualTo(Character.MAX_HP - 75);
	}
	
	@Test
	public void hit_harder_when_target_is_exactly_5_levels_below_him() {
		character.setLevel(6);
		character.hit(opponent, 50);
		assertThat(opponent.getHealth()).isEqualTo(Character.MAX_HP - 75);
	}
	
	@Test
	public void hit_softer_when_target_is_more_than_5_levels_above_him() {
		opponent.setLevel(10);
		character.hit(opponent, 50);
		assertThat(opponent.getHealth()).isEqualTo(Character.MAX_HP - 25);
	}

	@Test
	public void not_hit_when_is_melee_and_in_more_than_2_meters() {
		character.hit(opponent, 50);
	}
	
}
