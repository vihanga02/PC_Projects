import java.util.*;

public class War {
    User initialChallenger;
    User initialOpponent;
    User challenger;
    User opponent;
    Vector<Character> challengerArmy;
    Vector<Character> opponentArmy;

    private Vector<Character> challengerArmyAttackingArray;
    private Vector<Character> opponentArmyAttackingArray;
    private Vector<Character> challengerArmyDefendingArray;
    private Vector<Character> opponentArmyDefendingArray;
    private Vector<Character> opponentArmyHealingArray;
    private Vector<Character> challengerArmyHealingArray;

    public War(User initialChallenger,User initialOpponent){
        this.initialChallenger = initialChallenger;
        this.initialOpponent = initialOpponent;
        this.startWar();
    }

    private void initialingArrays(Vector<Character> challengerArmy, Vector<Character> opponentArmy){
        this.challengerArmyAttackingArray = new Vector<>(attackPriorityCheck(challengerArmy));
        this.opponentArmyAttackingArray = new Vector<>(attackPriorityCheck(opponentArmy));
        this.opponentArmyDefendingArray = new Vector<>(defencePriorityCheck(opponentArmy));
        this.challengerArmyDefendingArray = new Vector<>(defencePriorityCheck(challengerArmy));
        this.opponentArmyHealingArray = new Vector<>(opponentArmy); // Create new Vector objects
        this.challengerArmyHealingArray = new Vector<>(challengerArmy); // Create new Vector objects
        this.opponentArmyHealingArray.sort(Comparator.comparingDouble(Character::getHealth));//ascending order
        this.challengerArmyHealingArray.sort(Comparator.comparingDouble(Character::getHealth));//ascending order

    }

    public Vector<Character> attackPriorityCheck(Vector<Character> list){
        list.sort(Comparator.comparingDouble(Character::getSpeed)
                .thenComparing(character -> {
                    if (character instanceof Healer) return 1;
                    else if (character instanceof Mage) return 2;
                    else if (character instanceof MythicalCreature) return 3;
                    else if (character instanceof Knight) return 4;
                    else return 5; // Archer
                }).reversed());
        return list;
    }

    public Vector<Character> defencePriorityCheck(Vector<Character> list){
        list.sort(Comparator.comparingDouble(Character::getDefence)
                .thenComparing(character -> {
                    if (character instanceof Mage) return 1;
                    else if (character instanceof Knight) return 2;
                    else if (character instanceof Archer) return 3;
                    else if (character instanceof MythicalCreature) return 4;
                    else return 5; // Healer
                }));
        return list;
    }

    void startWar(){
        this.challenger = initialChallenger;
        this.opponent = initialOpponent;
        this.challengerArmy = new Vector<>(challenger.getArmy());
        this.opponentArmy = new Vector<>(opponent.getArmy());
        for(Character character:challengerArmy){
            switch (opponent.getHomeGround()) {
                case "HillCrest" -> {
                    if (character.getCharacterType().equals("HighLander")) {
                        character.setAttack(character.getAttack() + 1);
                        character.setDefence(character.getDefence() + 1);
                    }
                }
                case "MarshLand" -> {
                    if (character.getCharacterType().equals("MarshLander")) {
                        character.setDefence(character.getDefence() + 2);
                    }
                    if (character.getCharacterType().equals("SunChild")) {
                        character.setAttack(character.getAttack() - 1);
                    }
                    if (character.getCharacterType().equals("Mystics")) {
                        character.setSpeed(character.getSpeed() - 1);
                    }
                }
                case "Desert" -> {
                    if (character.getCharacterType().equals("MarshLander")) {
                        character.setHealth(character.getHealth() - 1);
                    }
                    if (character.getCharacterType().equals("SunChild")) {
                        character.setAttack(character.getAttack() + 1);
                    }
                }
                case "Arcane" -> {
                    if (character.getCharacterType().equals("Mystics")) {
                        character.setAttack(character.getAttack() + 2);
                    }
                    if (character.getCharacterType().equals("HighLander")) {
                        character.setSpeed(character.getSpeed() - 1);
                        character.setDefence(character.getDefence() - 1);
                    }
                    if (character.getCharacterType().equals("MarshLander")) {
                        character.setSpeed(character.getSpeed() - 1);
                        character.setDefence(character.getDefence() - 1);
                    }
                }
            }
        }
        initialingArrays(challengerArmy, opponentArmy);

        Iterator<Character> challengerHealingIterator = challengerArmyHealingArray.iterator();
        while (challengerHealingIterator.hasNext()) {
            Character character = challengerHealingIterator.next();
            if (character instanceof Healer) {
                challengerHealingIterator.remove(); // Remove the healer from challengerArmyHealingArray
            }
        }

        Iterator<Character> opponentHealingIterator = opponentArmyHealingArray.iterator();
        while (opponentHealingIterator.hasNext()) {
            Character character = opponentHealingIterator.next();
            if (character instanceof Healer) {
                opponentHealingIterator.remove(); // Remove the healer from opponentArmyHealingArray
            }
        }
        boolean attackingSide = false; //set to 0 when challenger is attacking and set to 1 when opponent is attacking
        int turn = 1;
        double previousHealth = opponentArmyDefendingArray.get(0).getHealth();
        int i=0;

        while (true) {
            System.out.println("Round no = " + turn);

            if(!attackingSide){
                System.out.print("Attacking Player = " + initialChallenger.getUserName() + " " + challengerArmyAttackingArray.get(i).getName() + "     " + "Diffending Player = " + initialOpponent.getUserName() + " " + opponentArmyDefendingArray.get(0).getName() + "\n");
            }
            else{
                System.out.print("Attacking player = " + initialOpponent.getUserName() + " " + opponentArmyAttackingArray.get(i).getName() +  "     " + "Diffending Player = " + initialChallenger.getUserName() +" " + challengerArmyDefendingArray.get(0).getName() + "\n");
            }
            //initially first attacking side is challenger
            if (!attackingSide) {

                //check the challenger attacking army character is a healer?
                if(!(challengerArmyAttackingArray.getFirst() instanceof Healer)) {

                    //get the damge value of challenger army attacking array
                    double damage = 0.5 * (challengerArmyAttackingArray.get(i).getAttack()) - 0.1 * (opponentArmyDefendingArray.get(0).getDefence());

                    //reduce the health of defending array army charactrer according to the damage value
                    opponentArmyDefendingArray.get(0).setHealth(opponentArmyDefendingArray.get(0).getHealth() - damage);
                    System.out.println(opponentArmyDefendingArray.get(0).getName()+"'s health reduce by " + damage + " by the attack of "+challengerArmyAttackingArray.get(i).getName());

                    if(initialChallenger.getHomeGround().equals("Hillcrest") && challengerArmyAttackingArray.get(i).getCharacterType().equals("Highlander")){
                        //get the damge value of challenger army attacking array
                        double bonusDamage = 0.5 * (challengerArmyAttackingArray.get(i).getAttack()*0.2) - 0.1 * (opponentArmyDefendingArray.get(0).getDefence());
                        //reduce the health of defending array army charactrer according to the damage value
                        opponentArmyDefendingArray.get(0).setHealth(opponentArmyDefendingArray.get(0).getHealth() - bonusDamage);
                    }
                    if(turn != 1){
                        if(initialChallenger.getHomeGround().equals("Arcane") && opponentArmyDefendingArray.get(0).getCharacterType().equals("Mystics")){
                            opponentArmyDefendingArray.get(0).setHealth(previousHealth*1.1);
                        }
                    }
                    //check the opponentdifendingaramkycharacter is die or not
                    if(opponentArmyDefendingArray.get(0).getHealth() <= 0){
                        //if died print
                        System.out.println(opponentArmyDefendingArray.get(0).getName()+" Died!");

                        System.out.println(opponentArmyDefendingArray.size());

                        if (opponentArmyDefendingArray.size() == 1){
                            opponentArmyDefendingArray.clear();
                            opponentArmyHealingArray.clear();
                            break;
                        }
                        else {
                            //get the name of died character
                            String diedCharacter = opponentArmyDefendingArray.get(0).getName();
                            //remove the died character from opponentArmyDiffendingArray
                            int index= 0;
                            List<Integer> opponentDefendingIndicesToRemove = new ArrayList<>();
                            for (int j = 0; j < opponentArmyDefendingArray.size(); j++) {
                                if (opponentArmyDefendingArray.get(j).getHealth() <= 0) {
                                    opponentDefendingIndicesToRemove.add(j);
                                    index = j;
                                }
                            }

                            // Remove the characters from challengerArmyDefendingArray using the collected indices
//                            for (int index : opponentDefendingIndicesToRemove) {
//                                challengerArmyDefendingArray.remove(index);
//                                challengerArmyHealingArray.remove(index);
//                            }
                            opponentArmyDefendingArray.remove(index);
                            opponentArmyHealingArray.remove(index);
                        }
                    }
                }
                else{
                    //sort the challengerarmyhealingarray according to current hea,th values
                    challengerArmyHealingArray.sort(Comparator.comparingDouble(Character::getHealth));;
                    //calculate the healvalue of the healer
                    //double healvalue = 0.1*(challengerArmyAttackingArray.get(i).getAttack());
                    double healvalue = 0.1*(challengerArmyHealingArray.get(0).getAttack());
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
                if(!(opponentArmyAttackingArray.getFirst() instanceof Healer)) {
                    //calculate the damage value of opponnet army attacking  value
                    double dmg = 0.5 * (opponentArmyAttackingArray.get(i).getAttack()) - 0.1 * (challengerArmyDefendingArray.get(0).getDefence());
                    //reduce health of challenger army difending  character according to the dmg value
                    challengerArmyDefendingArray.get(0).setHealth(challengerArmyDefendingArray.get(0).getHealth() - dmg);

                    System.out.println(opponentArmyDefendingArray.get(0).getName()+"'s health reduce by"+dmg+"by the attck of"+challengerArmyAttackingArray.get(0).getName());

                    if(initialChallenger.getHomeGround().equals("Hillcrest") && opponentArmyAttackingArray.get(0).getCharacterType().equals("Highlander")){
                        //get the damge value of challenger army attacking array
                        double bonusdmg = 0.5 * (opponentArmyAttackingArray.get(i).getAttack()*0.2) - 0.1 * (challengerArmyDefendingArray.get(0).getDefence());
                        //reduce the health of defending array army charactrer according to the damage value
                        opponentArmyDefendingArray.get(0).setHealth(opponentArmyDefendingArray.get(0).getHealth() - bonusdmg);
                    }
                    if(turn != 1){
                        if(initialChallenger.getHomeGround().equals("Arcane") && challengerArmyDefendingArray.get(0).getCharacterType().equals("Mystics")){
                            challengerArmyDefendingArray.get(0).setHealth(previousHealth*1.1);
                        }
                    }
                    //check whther challener army difending charactor is die or not
                    if(challengerArmyDefendingArray.get(0).getHealth() <= 0){
                        System.out.println(challengerArmyDefendingArray.get(0).getName()+" Died!");

                        // get the name of died charactor
                        if (challengerArmyDefendingArray.size() == 1){
                            challengerArmyDefendingArray.clear();
                            challengerArmyHealingArray.clear();
                            break;
                        }
                        else {
                            String diedCharacter = challengerArmyDefendingArray.get(0).getName();
                            //remove the died charactor from challengerarmy diffending array
                            List<Integer> challengerDefendingIndicesToRemove = new ArrayList<>();
                            int index = 0;
                            for (int j = 0; j < opponentArmyDefendingArray.size(); j++) {
                                if (opponentArmyDefendingArray.get(j).getHealth() <= 0) {
                                    challengerDefendingIndicesToRemove.add(j);
                                    index = j;
                                }
                            }
                            System.out.println("removing index= " + index);
                            // Remove the characters from opponentArmyDefendingArray using the collected indices
//                            for (int index : opponentDefendingIndicesToRemove) {
//                                opponentArmyDefendingArray.remove(index);
//                                opponentArmyHealingArray.remove(index);
//                            }
                            challengerArmyHealingArray.remove(index);
                            challengerArmyDefendingArray.remove(index);
                        }
                    }
                }
                else{

                    //double healvalue = 0.1*(challengerArmyAttackingArray.get(0).getAttack());
                    double healvalue = 0.1*(challengerArmyHealingArray.get(0).getAttack());
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
                    ;
                }
                if (!opponentArmyDefendingArray.isEmpty()) {
                    previousHealth = opponentArmyDefendingArray.get(0).getHealth();
                }
                challengerArmyHealingArray.sort(Comparator.comparingDouble(Character::getHealth));
                opponentArmyHealingArray.sort(Comparator.comparingDouble(Character::getHealth));
                attackingSide = false;
                turn++;
                i++;
            }

            if (i > 4){
                i = 0;
            }

            if(turn > 10){
                System.out.println("WarEnded");
                break;
            }
            System.out.println("challenger left= " + challengerArmyDefendingArray.size());
            System.out.println("opponent left= " + opponentArmyDefendingArray.size());
            System.out.println();
        }

        System.out.println("------Result----------");
        if(opponentArmyAttackingArray.isEmpty()) {
            System.out.println("winner is = " + initialChallenger.getName());
        } else if (challengerArmyAttackingArray.isEmpty()) {
            System.out.println("winner is = "+initialOpponent.getName());
        }
        else{
            System.out.println("War is Draw");
        }
    }
}