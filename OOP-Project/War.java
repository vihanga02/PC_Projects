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

    Vector<Character> challengerArmyAttackingArray = challengerArmy;
    Vector<Character> opponentArmyAttackingArray = opponentArmy;
    Vector<Character> challengerArmyDefendingArray = challengerArmy;
    Vector<Character> opponentArmyDefendingArray = opponentArmy;
    Vector<Character> opponentArmyHealingArray=opponentArmy;
    Vector<Character> challengerArmyHealingArray=challengerArmy;

    public War(User initialChallenger,User initialOpponent){
        this.initialChallenger = initialChallenger;
        this.initialOpponent = initialOpponent;
        this.startWar();
    }

    private void initialingArrays(Vector<Character> challengerArmy, Vector<Character> opponentArmy){
        challengerArmyAttackingArray = challengerArmy;
        opponentArmyAttackingArray = opponentArmy;
        challengerArmyDefendingArray = challengerArmy;
        opponentArmyDefendingArray = opponentArmy;
        opponentArmyHealingArray=opponentArmy;
        challengerArmyHealingArray=challengerArmy;
    }

    private void setGroundEffects(){

    }


    void startWar(){
        this.challenger = initialChallenger;
        this.opponent = initialOpponent;
        this.challengerArmy = new Vector<>(challenger.getArmy());
        this.opponentArmy = new Vector<>(opponent.getArmy());
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
        initialingArrays(challengerArmy, opponentArmy);

        double healersAttackValue;

        for (Character character: challengerArmyHealingArray){
            if (character instanceof Healer) {
                opponentArmyHealingArray.remove(character);
            }
        }

        for (Character character: challengerArmyHealingArray){
            if (character instanceof Healer){
                challengerArmyHealingArray.remove(character);
            }
        }

        challengerArmyAttackingArray.sort(Comparator.comparingDouble(Character::getSpeed).reversed());//desending order
        opponentArmyAttackingArray.sort(Comparator.comparingDouble(Character::getSpeed).reversed());//desending order
        challengerArmyDefendingArray.sort(Comparator.comparingDouble(Character::getDefence));//ascending array
        opponentArmyDefendingArray.sort(Comparator.comparingDouble(Character::getDefence));//ascending order
        opponentArmyHealingArray.sort(Comparator.comparingDouble(Character::getHealth));//ascending order
        challengerArmyHealingArray.sort(Comparator.comparingDouble(Character::getHealth));//ascending order

        boolean attackingSide = false; //set to 0 when challenger is attacking and set to 1 when opponent is attacking
        int turn = 1;
        double previousHealth = opponentArmyDefendingArray.get(0).getHealth();
        while (!isWarEnded) {
            int i=0;
            System.out.println("Round no = "+turn);
            if(!attackingSide){
                System.out.print("Attacking Player = " + initialChallenger.getUserName() + " " + "Diffending Player = " + initialOpponent.getUserName());
            }
            else{
                System.out.print("Attacking player = " + initialOpponent.getUserName() + " " + "Diffending Player = " + initialChallenger.getUserName());
            }
            //initially first attacking side is challenger
            if (!attackingSide) {

                //check the challenger attacking army character is a healer?
                if(!(challengerArmyAttackingArray.getFirst() instanceof Healer)) {

                    //get the damge value of challenger army attacking array
                    double damage = 0.5 * (challengerArmyAttackingArray.get(0).getAttack()) - 0.1 * (opponentArmyDefendingArray.get(0).getDefence());

                    //reduce the health of defending array army charactrer according to the damage value
                    opponentArmyDefendingArray.get(0).setHealth(opponentArmyDefendingArray.get(0).getHealth() - damage);
                    System.out.println(opponentArmyDefendingArray.get(0).getName()+"'s health reduce by "+damage+" by the attack of "+challengerArmyAttackingArray.get(0).getName());

                    if(initialChallenger.getHomeGround().equals("Hillcrest") && challengerArmyAttackingArray.get(0).getCharacterType().equals("highlander")){
                        //get the damge value of challenger army attacking array
                        double bonusDamage = 0.5 * (challengerArmyAttackingArray.get(0).getAttack()*0.2) - 0.1 * (opponentArmyDefendingArray.get(0).getDefence());
                        //reduce the health of defending array army charactrer according to the damage value
                        opponentArmyDefendingArray.get(0).setHealth(opponentArmyDefendingArray.get(0).getHealth() - bonusDamage);
                    }
                    if(turn != 1){
                        if(initialChallenger.getHomeGround().equals("Arcane") && opponentArmyDefendingArray.get(0).getCharacterType().equals("mystics")){
                            opponentArmyDefendingArray.get(0).setHealth(previousHealth*1.1);
                        }
                    }
                    //check the opponentdifendingaramkycharacter is die or not
                    if(opponentArmyDefendingArray.get(0).getHealth() <= 0){
                        //if died print
                        System.out.println(opponentArmyDefendingArray.get(0).getName()+" Died!");
                        //get the name of died character
                        String diedCharacter = opponentArmyDefendingArray.get(0).getName();
                        //remove the died character from opponentArmyDiffendingArray
                        opponentArmyDefendingArray.remove(0);
                        //search and remove the died charactor from opponentArmyAttacking Array
                        for (Character character: opponentArmyHealingArray){
                            if (character.getName().equals(diedCharacter)){
                                opponentArmyHealingArray.remove(character);
                            }
                        }
                    }
                }
                else{
                    //if challengerArmyAttackingArray current attacking charactor is healer->>>
                    healersAttackValue = challengerArmyAttackingArray.get(0).getAttack();//get the healers attaking value
                    //sort the challengerarmyhealingarray according to current hea,th values
                    challengerArmyHealingArray.sort(Comparator.comparingDouble(Character::getHealth));;
                    //calculate the healvalue of the healer
                    double healvalue = 0.1*(challengerArmyAttackingArray.get(0).getAttack());
                    //get the lowest health character and heal it
                    challengerArmyHealingArray.get(0).setHealth(challengerArmyHealingArray.get(0).getHealth() + healvalue);

                    System.out.println("Healer is healed "+challengerArmyHealingArray.get(0).getHealth()+" by " + healvalue);
                    //save the healed charactor into a varibale
                    Character healedchar = challengerArmyHealingArray.get(0);
                    //also update the health value on opponentArmyDiffenderArray the lowest health charactor health
                    for (Character character : opponentArmyDefendingArray) {
                        if(character.getName().equals(healedchar.getName())){
                            character.setHealth(character.getHealth()+healvalue);
                        }
                    }
                }
                attackingSide = true;
            }
            else{
                //opponent attacking turn
                if(!opponentArmyAttackingArray.get(0).getName().equals("healer")) {
                    //calculate the damage value of opponnet army attacking  value
                    double dmg = 0.5 * (opponentArmyAttackingArray.get(0).getAttack()) - 0.1 * (challengerArmyDefendingArray.get(0).getDefence());
                    //reduce health of challenger army difending  character according to the dmg value
                    challengerArmyDefendingArray.get(0).setHealth(challengerArmyDefendingArray.get(0).getHealth() - dmg);

                    System.out.println(opponentArmyDefendingArray.get(0).getName()+"'s health reduce by"+dmg+"by the attck of"+challengerArmyAttackingArray.get(0).getName());

                    if(initialChallenger.getHomeGround().equals("Hillcrest") && opponentArmyAttackingArray.get(0).getCharacterType().equals("highlander")){
                        //get the damge value of challenger army attacking array
                        double bonusdmg = 0.5 * (opponentArmyAttackingArray.get(0).getAttack()*0.2) - 0.1 * (challengerArmyDefendingArray.get(0).getDefence());
                        //reduce the health of defending array army charactrer according to the damage value
                        opponentArmyDefendingArray.get(0).setHealth(opponentArmyDefendingArray.get(0).getHealth() - bonusdmg);
                    }
                    if(turn!=1){
                        if(initialChallenger.getHomeGround().equals("arcane") && challengerArmyDefendingArray.get(0).getCharacterType().equals("mystics")){
                            challengerArmyDefendingArray.get(0).setHealth(previousHealth*1.1);
                        }
                    }
                    //check whther challener army difending charactor is die or not
                    if(challengerArmyDefendingArray.get(0).getHealth()<=0){
                        System.out.println(challengerArmyDefendingArray.get(0).getName()+"Died!");
                        // get the name of died charactor
                        String diedChar=challengerArmyDefendingArray.get(0).getName();
                        //remove the died charactor from challengerarmy diffending array
                        challengerArmyDefendingArray.remove(0);

                        for (Character character: challengerArmyAttackingArray){
                            if (character.getName().equals(diedChar)) {
                                challengerArmyAttackingArray.remove(character);
                            }
                        }

                        for (Character character: challengerArmyHealingArray){
                            if (character.getName().equals(diedChar)) {
                                challengerArmyHealingArray.remove(character);
                            }
                        }
                    }
                }
                else{
                    healersAttackValue=opponentArmyAttackingArray.get(0).getAttack();
                    challengerArmyHealingArray.sort(Comparator.comparingDouble(Character::getHealth));;
                    double healvalue=0.1*(challengerArmyAttackingArray.get(0).getAttack());
                    //update the heal value on charactor healing value
                    challengerArmyHealingArray.get(0).setHealth(challengerArmyHealingArray.get(0).getHealth()+healvalue);
                    System.out.println("Healer is healed"+challengerArmyHealingArray.get(0).getHealth()+"by"+healvalue);
                    //save the healed charactor into a varibale
                    Character healedchar=challengerArmyHealingArray.get(0);
                    //also update the health value on challengerArmyDiffenderArray the lowest health charactor health
                    for (Character character : challengerArmyDefendingArray) {
                        if(character.getName().equals(healedchar.getName())){
                            character.setHealth(character.getHealth()+healvalue);
                        }
                    }
                }
                previousHealth=opponentArmyDefendingArray.get(0).getHealth();
            }
            if(i==5){
                i = 0;
            }else{
                i++;
            }
            turn=turn+1;
            if(turn==10){
                isWarEnded=true;
                System.out.println("WarEnded");
            }
        }
        System.out.println("------Results----------");
        int finalcountchallengerArmy=challengerArmyAttackingArray.size();
        int finalcountopponentArmy=opponentArmyAttackingArray.size();
        if(finalcountchallengerArmy>finalcountopponentArmy) {
            System.out.println("winner is=" + initialChallenger.getName());
        } else if (finalcountchallengerArmy<finalcountopponentArmy) {
            System.out.println("winner is="+initialOpponent.getName());
        }
        else{
            System.out.println("War is Draw");
        }
    }
}