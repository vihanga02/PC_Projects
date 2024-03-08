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
            if(challenger.getHomeGround().equals("hillCrest")) {
                if (character.getCharacterType().equals("highLander")) {
                    character.setAttack(character.getAttack() + 1);
                    character.setDefence(character.getDefence() + 1);
                }
            }else if(challenger.getHomeGround().equals("marshLand")) {
                if (character.getCharacterType().equals("marshLander")) {
                    character.setDefence(character.getDefence() + 2);
                }
                if (character.getCharacterType().equals("sunChild")) {
                    character.setAttack(character.getAttack() - 1);

                }
                if (character.getCharacterType().equals("mystics")) {
                    character.setSpeed(character.getSpeed() - 1);

                }
            }else if(challenger.getHomeGround().equals("desert")){
                    if(character.getCharacterType().equals("marshLander")){
                        character.setHealth(character.getHealth() - 1);
                    }if(character.getCharacterType().equals("sunChild")){
                        character.setAttack(character.getAttack() + 1);

                    }

            }else if(challenger.getHomeGround().equals("arcane")){
                if(character.getCharacterType().equals("mystics")){
                    character.setAttack(character.getAttack() + 2);
                }if(character.getCharacterType().equals("highLander")){
                    character.setSpeed(character.getSpeed() - 1);
                    character.setDefence(character.getDefence() - 1);
                }if(character.getCharacterType().equals("marshLander")) {
                    character.setSpeed(character.getSpeed() - 1);
                    character.setDefence(character.getDefence() - 1);
                }

            }

        }
        challengerArmy.sort(Comparator.comparingDouble(character -> character.getSpeed()));
        opponentArmy.sort(Comparator.comparingDouble(character -> character.getSpeed()));

        while (!isWarEnded) {

        }





    }
}
