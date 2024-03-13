import java.sql.SQLOutput;
import java.util.Iterator;
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
        this.opponentArmy = opponent.getArmy();
        for(Character character:challengerArmy){
            switch (opponent.getHomeGround()) {
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
        Vector<Character> opponentArmyHealingArray=opponentArmy;
        Vector<Character> challengerArmyHealingArray=challengerArmy;
        double healersAttackValue;
        Iterator<Character> iterator = opponentArmyHealingArray.iterator();
        while (iterator.hasNext()) {
            Character character = iterator.next();
            if (character.getName().equals("healer")) {

                iterator.remove();
            }
        }
        Iterator<Character> iterator1 =challengerArmyHealingArray.iterator();
        while (iterator.hasNext()) {
            Character character = iterator1.next();
            if (character.getName().equals("healer")) {
                iterator1.remove();
            }
        }




        challengerArmyAttackingArray.sort(Comparator.comparingDouble(Character::getSpeed).reversed());
        opponentArmyAttackingArray.sort(Comparator.comparingDouble(Character::getSpeed).reversed());
        challengerArmyDefendingArray.sort(Comparator.comparingDouble(Character::getDefence).reversed());
        opponentArmyDefendingArray.sort(Comparator.comparingDouble(Character::getDefence).reversed());
        opponentArmyHealingArray.sort(Comparator.comparingDouble(Character::getHealth));;

        int attackingSide = 0; //set to 0 when challenger is attacking and set to 1 when opponent is attacking
        int turn=0;
        double previousHealth=opponentArmyDefendingArray.get(0);
        while (!isWarEnded) {
            if (attackingSide == 0) {//initially first attacking side is challenger

                if(!challengerArmyAttackingArray.get(0).getName().equals("healer")) {//check the challenger attacking army character is a healer?
                    //if not->>

                    double dmg = 0.5 * (challengerArmyAttackingArray.get(0).getAttack()) - 0.1 * (opponentArmyDefendingArray.get(0).getDefence());//get the damge value of challenger army attacking array
                    opponentArmyDefendingArray.get(0).setHealth(opponentArmyDefendingArray.get(0).getHealth() - dmg);//reduce the health of defending array army charactrer according to the damage value
                    if(initialChallenger.getHomeGround().equals("hillcrest") && challengerArmyAttackingArray.get(0).getCharacterType().equals("highlander")){
                        double bonusdmg = 0.5 * (challengerArmyAttackingArray.get(0).getAttack()*0.2) - 0.1 * (opponentArmyDefendingArray.get(0).getDefence());//get the damge value of challenger army attacking array
                        opponentArmyDefendingArray.get(0).setHealth(opponentArmyDefendingArray.get(0).getHealth() - bonusdmg);//reduce the health of defending array army charactrer according to the damage value

                    }

                    if(turn!=1){
                        if(initialChallenger.getHomeGround().equals("arcane") && opponentArmyDefendingArray.get(0).getCharacterType().equals("mystics")){
                            opponentArmyDefendingArray.get(0).setHealth(previousHealth*1.1);


                        }
                    }
                    if(opponentArmyDefendingArray.get(0).getHealth()<=0){//check the opponentdifendingaramkycharacter is die or not
                        System.out.println(opponentArmyDefendingArray.get(0).getName()+"Died!");//if died print
                        String diedChar=opponentArmyDefendingArray.get(0).getName();//get the name of died character
                        opponentArmyDefendingArray.remove(0);//remove the died character from opponentArmyDiffendingArray
                        Iterator<Character> iterator3 =opponentArmyAttackingArray.iterator();//search and remove the died charactor from opponentArmyAttacking Array
                        while (iterator.hasNext()) {
                            Character character = iterator3.next();
                            if (character.getName().equals(diedChar)) {
                                iterator3.remove();
                            }
                        }
                        Iterator<Character> iterator4 =opponentArmyHealingArray.iterator();//search and remove the died charactor from opponentArmyHealingArray
                        while (iterator.hasNext()) {
                            Character character = iterator4.next();
                            if (character.getName().equals(diedChar)) {
                                iterator4.remove();
                            }
                        }




                    }






                }else{
                    //if challengerArmyAttackingArray current attacking charactor is healer->>>
                    healersAttackValue=challengerArmyAttackingArray.get(0).getAttack();//get the healers attaking value


                    challengerArmyHealingArray.sort(Comparator.comparingDouble(Character::getHealth));;//sort the challengerarmyhealingarray according to current hea,th values
                    double healvalue=0.1*(challengerArmyAttackingArray.get(0).getAttack());//calculate the healvalue of the healer
                    challengerArmyHealingArray.get(0).setHealth(challengerArmyHealingArray.get(0).getHealth()+healvalue);//get the lowest health character and heal it
                    Character healedchar=challengerArmyHealingArray.get(0);//save the healed charactor into a varibale
                    for (Character character : opponentArmyDefendingArray) {//also update the health value on opponentArmyDiffenderArray the lowest health charactor health
                        if(character.getName().equals(healedchar.getName())){
                            character.setHealth(character.getHealth()+healvalue);
                        }
                        // Access each element using the variable 'character'

                    }







                }





                attackingSide = 1;
            }else{

                //opponent attacking turn
                if(!opponentArmyAttackingArray.get(0).getName().equals("healer")) {

                    double dmg = 0.5 * (opponentArmyAttackingArray.get(0).getAttack()) - 0.1 * (challengerArmyDefendingArray.get(0).getDefence());//calculate the damage value of opponnet army attacking  value
                    challengerArmyDefendingArray.get(0).setHealth(challengerArmyDefendingArray.get(0).getHealth() - dmg);//reduce health of challenger army difending  character according to the dmg value
                    if(initialChallenger.getHomeGround().equals("hillcrest") && opponentArmyAttackingArray.get(0).getCharacterType().equals("highlander")){
                        double bonusdmg = 0.5 * (opponentArmyAttackingArray.get(0).getAttack()*0.2) - 0.1 * (challengerArmyDefendingArray.get(0).getDefence());//get the damge value of challenger army attacking array
                        opponentArmyDefendingArray.get(0).setHealth(opponentArmyDefendingArray.get(0).getHealth() - bonusdmg);//reduce the health of defending array army charactrer according to the damage value

                    }

                    if(turn!=1){
                        if(initialChallenger.getHomeGround().equals("arcane") && challengerArmyDefendingArray.get(0).getCharacterType().equals("mystics")){
                            challengerArmyDefendingArray.get(0).setHealth(previousHealth*1.1);


                        }
                    }
                    if(challengerArmyDefendingArray.get(0).getHealth()<=0){//check whther challener army difending charactor is die or not
                        System.out.println(challengerArmyDefendingArray.get(0).getName()+"Died!");
                        String diedChar=challengerArmyDefendingArray.get(0).getName();// get the name of died charactor
                        challengerArmyDefendingArray.remove(0);//remove the died charactor from challengerarmy diffending array
                        Iterator<Character> iterator3 =challengerArmyAttackingArray.iterator();//remove the died charactor from challengerArmy Attacking Array
                        while (iterator.hasNext()) {
                            Character character = iterator3.next();
                            if (character.getName().equals(diedChar)) {
                                iterator3.remove();
                            }
                        }
                        Iterator<Character> iterator4 =challengerArmyHealingArray.iterator();//remove the died character from challanging army healing array
                        while (iterator.hasNext()) {
                            Character character = iterator4.next();
                            if (character.getName().equals(diedChar)) {
                                iterator4.remove();
                            }
                        }




                    }





                }else{
                    healersAttackValue=opponentArmyAttackingArray.get(0).getAttack();


                    challengerArmyHealingArray.sort(Comparator.comparingDouble(Character::getHealth));;
                    double healvalue=0.1*(challengerArmyAttackingArray.get(0).getAttack());
                    challengerArmyHealingArray.get(0).setHealth(challengerArmyHealingArray.get(0).getHealth()+healvalue);//update the heal value on charactor healing value
                    Character healedchar=challengerArmyHealingArray.get(0);//save the healed charactor into a varibale
                    for (Character character : challengerArmyDefendingArray) {//also update the health value on challengerArmyDiffenderArray the lowest health charactor health
                        if(character.getName().equals(healedchar.getName())){
                            character.setHealth(character.getHealth()+healvalue);
                        }
                        // Access each element using the variable 'character'

                    }







                }
                double previousHealth=opponentArmyDefendingArray.get(0).getHealth();







            }
            turn=turn+1;
            if(turn==10){
                isWarEnded=true;


            }




        }
        int finalcountchallengerArmy=challengerArmyAttackingArray.size();
        int finalcountopponentArmy=opponentArmyAttackingArray.size();
        if(finalcountchallengerArmy>finalcountopponentArmy) {
            System.out.println("winner is=" + initialChallenger.getName());
        } else if (finalcountchallengerArmy<finalcountopponentArmy) {
            System.out.println("winner is="+initialOpponent.getName());

        }else{
            System.out.println("War is Draw");
        }


    }
}
