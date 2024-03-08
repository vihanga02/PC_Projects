import java.util.Vector;
import java.util.Comparator;

public class War {
    User initialChallenger;
    User initialOpponent;
    User challenger;
    User opponent;
    Vector<Character> challengerArmy;
    Vector<Character> opponentArmy;
    boolean isWarEnded=false;



    public War(User initialChallenger,User initialOpponent){
        this.initialChallenger = initialChallenger;
        this.initialOpponent = initialOpponent;



    }
    void startWar(){

        this.challenger = initialChallenger;
        this.opponent = initialOpponent;
        this.challengerArmy = challenger.getArmy();
        this.opponentArmy= opponent.getArmy();
        for(Character character:challengerArmy){
            switch (challenger.getHomeGround()) {
                case "hillCrest" -> {
                    if (character.getCharacterType().equals("highLander")) {
                        character.setAttack(character.getAttack() + 1);
                        character.setDefence(character.getDefence() + 1);
                    }
                }
                case "marshLand" -> {
                    if (character.getCharacterType().equals("marshLander")) {
                        character.setDefence(character.getDefence() + 2);
                    }
                    if (character.getCharacterType().equals("sunChild")) {
                        character.setAttack(character.getAttack() - 1);

                    }
                    if (character.getCharacterType().equals("mystics")) {
                        character.setSpeed(character.getSpeed() - 1);

                    }
                }
                case "desert" -> {
                    if (character.getCharacterType().equals("marshLander")) {
                        character.setHealth(character.getHealth() - 1);
                    }
                    if (character.getCharacterType().equals("sunChild")) {
                        character.setAttack(character.getAttack() + 1);

                    }
                }
                case "arcane" -> {
                    if (character.getCharacterType().equals("mystics")) {
                        character.setAttack(character.getAttack() + 2);
                    }
                    if (character.getCharacterType().equals("highLander")) {
                        character.setSpeed(character.getSpeed() - 1);
                        character.setDefence(character.getDefence() - 1);
                    }
                    if (character.getCharacterType().equals("marshLander")) {
                        character.setSpeed(character.getSpeed() - 1);
                        character.setDefence(character.getDefence() - 1);
                    }
                }
            }

        }

        Vector<Character> challengerArmyAttackingArray = challengerArmy;
        Vector<Character> opponentArmyAttackingArray = opponentArmy;
        Vector<Character> challengerArmyDefendingArray = challengerArmy;
        Vector<Character> opponentArmyDefendingArray = opponentArmy;

        challengerArmyAttackingArray.sort(Comparator.comparingDouble(Character::getSpeed));
        opponentArmyAttackingArray.sort(Comparator.comparingDouble(Character::getSpeed));
        challengerArmyDefendingArray.sort(Comparator.comparingDouble(Character::getDefence));
        opponentArmyDefendingArray.sort(Comparator.comparingDouble(Character::getDefence));

        int attackingSide = 0; //set to 0 when challenger is attacking and set to 1 when opponent is attacking
        while (!isWarEnded) {
            if (attackingSide == 0) {
                opponentArmyDefendingArray.get(0).setHealth();
            }
        }





    }
}
