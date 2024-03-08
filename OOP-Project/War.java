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


3
    public War(User initialChallenger,User initialOpponent){
        this.initialChallenger = initialChallenger;
        this.initialOpponent = initialOpponent;



    }
    void startWar(){

        this.challenger = intialChallenger;
        this.opponent = intialOpponent;
        this.challengerArmy = challenger.getArmy();
        this.defenderArmy= opponent.getArmy();
        for(Character character:challengerArmy){
            if(challenger.homeGround.equals("hIllCrest")) {
                if (character.characterType.equals("highLander")) {
                    character.setAttack(character.getAttack() + 1);
                    character.setDefence(character.getDefence() + 1);
                }
            }else if(challenger.homeGround.equals("marshLand")) {
                if (character.characterType.equals("marshLander")) {
                    character.setDefence(character.getDefence() + 2);
                }
                if (character.characterType.equals("sunChild")) {
                    character.setAttack(character.getAttack() - 1);

                }
                if (character.characterType.equals("mystics")) {
                    character.setSpeed(character.getSpeed() - 1);

                }
            }else if(challenger.homeGround.equals("desert")){
                    if(character.characterType.equals("marshLander")){
                        character.setHealth(character.getHealth() - 1);
                    }if(character.characterType.equals("sunChild")){
                        character.setAttack(character.getAttack() + 1);

                    }

            }else(challenger.homeGround.equals("arcane")){
                if(character.characterType.equals("mystics")){
                    character.setAttack(character.getAttack() + 2);
                }if(character.characterType.equals("highLander")){
                    character.setSpeed(character.getspeed() - 1);
                    character.setDefence(character.getDefence() - 1);
                }if(character.characterType.equals("marshLander")) {
                    character.setSpeed(character.getspeed() - 1);
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
